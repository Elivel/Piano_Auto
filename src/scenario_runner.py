"""Command-line interface for executing YAML-based Selenium scenarios."""
from __future__ import annotations

import argparse
import sys
from contextlib import contextmanager
from typing import Iterable

from selenium.common.exceptions import WebDriverException

from .driver_factory import DriverFactory
from .scenario_loader import Scenario, ScenarioLoadError, load_scenarios
from .steps import StepExecutor


@contextmanager
def managed_driver(headless: bool):
    driver = None
    try:
        driver = DriverFactory(headless=headless).create()
        yield driver
    finally:
        if driver:
            driver.quit()


def run_scenario(driver, scenario: Scenario) -> None:
    executor = StepExecutor(driver)
    for index, step in enumerate(scenario.steps, start=1):
        executor.run(
            action=step.action,
            target=step.target,
            by=step.by,
            value=step.value,
        )
        print(f"  ✓ Paso {index}: {step.action}")


def run(file_path: str, headless: bool = True) -> int:
    try:
        scenarios = load_scenarios(file_path)
    except ScenarioLoadError as exc:
        print(f"Error al cargar escenarios: {exc}")
        return 1

    print(f"Se encontraron {len(scenarios)} escenarios en {file_path}\n")

    try:
        with managed_driver(headless=headless) as driver:
            for scenario in scenarios:
                print(f"Ejecutando: {scenario.name}")
                run_scenario(driver, scenario)
                print("")
    except WebDriverException as exc:
        print("No fue posible iniciar el navegador. Detalles:\n", exc)
        return 2

    return 0


def build_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--scenarios",
        dest="scenarios",
        default="config/scenarios.yaml",
        help="Ruta del archivo YAML con la definición de escenarios.",
    )
    parser.add_argument(
        "--no-headless",
        dest="headless",
        action="store_false",
        help="Desactiva el modo headless para ver el navegador.",
    )
    parser.set_defaults(headless=True)
    return parser


def main(argv: Iterable[str] | None = None) -> int:
    args = build_parser().parse_args(argv)
    return run(args.scenarios, headless=args.headless)


if __name__ == "__main__":
    sys.exit(main())
