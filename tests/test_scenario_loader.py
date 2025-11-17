import pytest

from src.scenario_loader import ScenarioLoadError, load_scenarios, ScenarioStep


def test_loads_scenarios(tmp_path):
    yaml_content = """
scenarios:
  - name: Demo
    steps:
      - action: get
        target: https://example.com
      - action: assert_title_contains
        value: Example
"""
    scenario_file = tmp_path / "scenarios.yaml"
    scenario_file.write_text(yaml_content)

    scenarios = load_scenarios(scenario_file)

    assert len(scenarios) == 1
    assert scenarios[0].name == "Demo"
    assert isinstance(scenarios[0].steps[0], ScenarioStep)


def test_raises_when_missing_section(tmp_path):
    scenario_file = tmp_path / "invalid.yaml"
    scenario_file.write_text("{}")

    with pytest.raises(ScenarioLoadError):
        load_scenarios(scenario_file)


def test_rejects_unsupported_action(tmp_path):
    yaml_content = """
scenarios:
  - name: Bad
    steps:
      - action: not-real
"""
    scenario_file = tmp_path / "invalid.yaml"
    scenario_file.write_text(yaml_content)

    with pytest.raises(ScenarioLoadError):
        load_scenarios(scenario_file)
