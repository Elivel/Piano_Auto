"""Utilities for loading scenario definitions from YAML files."""
from __future__ import annotations

from dataclasses import dataclass
from pathlib import Path
from typing import Any, Dict, List

import yaml


@dataclass
class ScenarioStep:
    action: str
    target: str | None = None
    by: str | None = None
    value: Any | None = None


@dataclass
class Scenario:
    name: str
    steps: List[ScenarioStep]


class ScenarioLoadError(ValueError):
    """Raised when a scenario file cannot be parsed correctly."""


SUPPORTED_ACTIONS = {
    "get",
    "click",
    "type",
    "assert_title_contains",
    "assert_url_contains",
    "assert_text_present",
}


def _ensure_list(value: Any, path: Path) -> List[Any]:
    if not isinstance(value, list):
        raise ScenarioLoadError(
            f"Expected a list in {path}, but found {type(value).__name__}."
        )
    return value


def load_scenarios(file_path: str | Path) -> List[Scenario]:
    """Load scenario definitions from a YAML file.

    Args:
        file_path: YAML file containing a top-level ``scenarios`` list.

    Returns:
        List of Scenario objects.

    Raises:
        ScenarioLoadError: if the file does not have the expected structure.
    """

    path = Path(file_path)
    if not path.exists():
        raise ScenarioLoadError(f"Scenario file not found: {file_path}")

    with path.open("r", encoding="utf-8") as fh:
        raw = yaml.safe_load(fh) or {}

    scenarios_raw = raw.get("scenarios")
    if scenarios_raw is None:
        raise ScenarioLoadError("The YAML file must define a 'scenarios' section.")

    scenarios_list = _ensure_list(scenarios_raw, path)

    scenarios: List[Scenario] = []
    for scenario_data in scenarios_list:
        if not isinstance(scenario_data, dict):
            raise ScenarioLoadError(
                f"Each scenario entry must be a mapping, got {type(scenario_data).__name__}."
            )

        name = scenario_data.get("name")
        steps_raw = scenario_data.get("steps")
        if not name or steps_raw is None:
            raise ScenarioLoadError("Each scenario requires both 'name' and 'steps'.")

        steps_list = _ensure_list(steps_raw, path)
        steps: List[ScenarioStep] = []
        for step in steps_list:
            if not isinstance(step, dict):
                raise ScenarioLoadError(
                    f"Steps must be mappings, got {type(step).__name__} in scenario '{name}'."
                )

            action = step.get("action")
            if not action or action not in SUPPORTED_ACTIONS:
                raise ScenarioLoadError(
                    f"Unsupported or missing action '{action}' in scenario '{name}'."
                )

            steps.append(
                ScenarioStep(
                    action=action,
                    target=step.get("target"),
                    by=step.get("by"),
                    value=step.get("value"),
                )
            )

        scenarios.append(Scenario(name=name, steps=steps))

    return scenarios
