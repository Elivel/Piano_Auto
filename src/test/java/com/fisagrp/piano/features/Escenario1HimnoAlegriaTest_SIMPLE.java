package com.fisagrp.piano.features;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;

@RunWith(SerenityRunner.class)
public class Escenario1HimnoAlegriaTest_SIMPLE {

    @Managed(uniqueSession = true)
    public WebDriver driver;

    @Test
    public void debeTocarEscenario1() throws InterruptedException {
        driver.get("https://www.musicca.com/es/piano");
        Thread.sleep(2000);
        
        // Tocar secuencia: SI SI DO RE RE DO SI LA SOL SOL LA SI SI LA LA
        String[] notas = {"[data-note='2b']", "[data-note='2b']", "[data-note='1c']", 
                          "[data-note='3d']", "[data-note='3d']", "[data-note='1c']",
                          "[data-note='2b']", "[data-note='2a']", "[data-note='2g']",
                          "[data-note='2g']", "[data-note='2a']", "[data-note='2b']",
                          "[data-note='2b']", "[data-note='2a']", "[data-note='2a']"};
        
        for (String selector : notas) {
            driver.findElement(By.cssSelector(selector)).click();
            Thread.sleep(300);
        }
    }
}
