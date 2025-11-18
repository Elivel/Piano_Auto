package com.fisagrp.piano.task;

import com.fisagrp.piano.model.Nota;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class TocarSecuencia {
    private List<Nota> notas;
    private WebDriver driver;

    public TocarSecuencia(WebDriver driver, List<Nota> notas) {
        this.driver = driver;
        this.notas = notas;
    }

    public void ejecutar() throws InterruptedException {
        for (Nota nota : notas) {
            TocarNota tocarNota = new TocarNota(driver, nota);
            tocarNota.ejecutar();
        }
    }
}
