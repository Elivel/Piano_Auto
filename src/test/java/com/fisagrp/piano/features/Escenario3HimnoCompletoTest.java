package com.fisagrp.piano.features;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.fisagrp.piano.task.AbrirPiano;
import com.fisagrp.piano.task.TocarSecuencia;
import com.fisagrp.piano.model.SecuenciaHimno;

public class Escenario3HimnoCompletoTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void debeTocarEscenario3() throws InterruptedException {
        // Abrir la página del piano
        AbrirPiano abrirPiano = new AbrirPiano(driver);
        abrirPiano.ejecutar();
        
        Thread.sleep(2000); // Esperar a que cargue

        // Tocar la secuencia completa (Himno x2 + Himno)
        TocarSecuencia tocarSecuencia = new TocarSecuencia(driver, SecuenciaHimno.escenario3());
        tocarSecuencia.ejecutar();

        TocarSecuencia tocarSecuencia1 = new TocarSecuencia(driver, SecuenciaHimno.escenario1());
        tocarSecuencia1.ejecutar();

        System.out.println("✓ Escenario 3 completado: Himno Completo");
    }
}
