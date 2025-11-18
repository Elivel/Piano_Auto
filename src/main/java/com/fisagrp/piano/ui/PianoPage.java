package com.fisagrp.piano.ui;

import com.fisagrp.piano.model.Nota;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.ElementClickInterceptedException;

public class PianoPage {
    private static final String URL = "https://www.musicca.com/es/piano";
    private WebDriver driver;

    // Selectores CSS para cada nota
    private static final By TECLA_DO  = By.cssSelector("[data-note='1c']");
    private static final By TECLA_RE  = By.cssSelector("[data-note='1d']");
    private static final By TECLA_MI  = By.cssSelector("[data-note='1e']");
    private static final By TECLA_FA  = By.cssSelector("[data-note='1f']");
    private static final By TECLA_SOL = By.cssSelector("[data-note='1g']");
    private static final By TECLA_LA  = By.cssSelector("[data-note='3a']");
    private static final By TECLA_SI  = By.cssSelector("[data-note='3b']");

    public PianoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrirPagina() {
        driver.get(URL);
    }

    public void tocarNota(Nota nota) throws InterruptedException {
        By selector = obtenerSelectorPara(nota);
        WebElement tecla = driver.findElement(selector);
        // Esperar a que la tecla sea clickable
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(selector));

            Actions actions = new Actions(driver);
            actions.moveToElement(tecla).click().perform();
        } catch (ElementClickInterceptedException e) {
          
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tecla);
        }
        Thread.sleep(50); // Pausa mínima para que se escuche la nota sin alargar el test
    }

    private By obtenerSelectorPara(Nota nota) {
        switch (nota) {
            case DO:  return TECLA_DO;
            case RE:  return TECLA_RE;
            case MI:  return TECLA_MI;
            case FA:  return TECLA_FA;
            case SOL: return TECLA_SOL;
            case LA:  return TECLA_LA;
            case SI:  return TECLA_SI;
            default:  return TECLA_DO;
        }
    }
}
