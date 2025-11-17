"""Execution helpers for scenario steps."""
from __future__ import annotations

from typing import Callable

from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webdriver import WebDriver


ACTION_MAP = {
    "get": "_visit",
    "click": "_click",
    "type": "_type",
    "assert_title_contains": "_assert_title_contains",
    "assert_url_contains": "_assert_url_contains",
    "assert_text_present": "_assert_text_present",
}


class StepExecutor:
    """Executes individual scenario steps with a Selenium driver."""

    def __init__(self, driver: WebDriver):
        self.driver = driver

    def run(self, action: str, target: str | None = None, by: str | None = None, value=None):
        method_name = ACTION_MAP.get(action)
        if not method_name:
            raise ValueError(f"Unsupported action: {action}")

        method: Callable = getattr(self, method_name)
        return method(target=target, by=by, value=value)

    def _find(self, target: str | None, by: str | None):
        if target is None:
            raise ValueError("A target locator is required for this action.")
        locator = self._resolve_by(by)
        return self.driver.find_element(locator, target)

    @staticmethod
    def _resolve_by(by: str | None) -> str:
        if by is None or by == "css":
            return By.CSS_SELECTOR
        by_map = {
            "xpath": By.XPATH,
            "id": By.ID,
            "name": By.NAME,
            "class": By.CLASS_NAME,
            "link_text": By.LINK_TEXT,
        }
        if by not in by_map:
            raise ValueError(f"Unsupported locator strategy: {by}")
        return by_map[by]

    def _visit(self, target: str | None, **_kwargs):
        if not target:
            raise ValueError("The 'get' action requires a URL target.")
        self.driver.get(target)

    def _click(self, target: str | None, by: str | None, **_kwargs):
        element = self._find(target, by)
        element.click()

    def _type(self, target: str | None, by: str | None, value=None):
        element = self._find(target, by)
        element.clear()
        if value is not None:
            element.send_keys(str(value))

    def _assert_title_contains(self, value=None, **_kwargs):
        if value is None:
            raise ValueError("The 'assert_title_contains' action requires a value to compare.")
        assert str(value) in self.driver.title, f"Expected '{value}' in page title '{self.driver.title}'"

    def _assert_url_contains(self, value=None, **_kwargs):
        if value is None:
            raise ValueError("The 'assert_url_contains' action requires a value to compare.")
        current_url = self.driver.current_url
        assert str(value) in current_url, f"Expected '{value}' in URL '{current_url}'"

    def _assert_text_present(self, value=None, target: str | None = None, by: str | None = None):
        if value is None:
            raise ValueError("The 'assert_text_present' action requires a value to match.")
        if target:
            element = self._find(target, by)
            text = element.text
        else:
            text = self.driver.page_source
        assert str(value) in text, f"Expected '{value}' to be present in the page content."
