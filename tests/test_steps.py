from unittest.mock import MagicMock

import pytest

from src.steps import StepExecutor


def build_driver():
    driver = MagicMock()
    element = MagicMock()
    driver.find_element.return_value = element
    driver.title = "Example Domain"
    driver.current_url = "https://example.com"
    driver.page_source = "<html><body>Example Domain</body></html>"
    return driver, element


def test_visit_and_assertions():
    driver, _ = build_driver()
    executor = StepExecutor(driver)

    executor.run("get", target="https://example.com")
    driver.get.assert_called_once_with("https://example.com")

    executor.run("assert_title_contains", value="Example")
    executor.run("assert_url_contains", value="example.com")


def test_typing_and_clicking():
    driver, element = build_driver()
    executor = StepExecutor(driver)

    executor.run("type", target="input", by="css", value="hello")
    element.clear.assert_called_once()
    element.send_keys.assert_called_once_with("hello")

    executor.run("click", target="button", by="css")
    element.click.assert_called_once()


def test_assert_text_present_uses_page_source_when_no_target():
    driver, _ = build_driver()
    executor = StepExecutor(driver)

    executor.run("assert_text_present", value="Example Domain")


def test_unsupported_action_raises():
    driver, _ = build_driver()
    executor = StepExecutor(driver)

    with pytest.raises(ValueError):
        executor.run("nope")
