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

public class Escenario2RepetirSecuenciaTest {
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
    public void debeTocarEscenario2() throws InterruptedException {
        // Abrir la página del piano
        AbrirPiano abrirPiano = new AbrirPiano(driver);
        abrirPiano.ejecutar();
        
        Thread.sleep(2000); 

       
        TocarSecuencia tocarSecuencia = new TocarSecuencia(driver, SecuenciaHimno.escenario2());
        tocarSecuencia.ejecutar();

        System.out.println("✓ Escenario 2 completado: Secuencia Repetida");
    }
}
