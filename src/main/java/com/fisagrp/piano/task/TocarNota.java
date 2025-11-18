package com.fisagrp.piano.task;

import com.fisagrp.piano.model.Nota;
import com.fisagrp.piano.ui.PianoPage;
import org.openqa.selenium.WebDriver;

public class TocarNota {
    private Nota nota;
    private PianoPage pianoPage;

    public TocarNota(WebDriver driver, Nota nota) {
        this.nota = nota;
        this.pianoPage = new PianoPage(driver);
    }

    public void ejecutar() throws InterruptedException {
        pianoPage.tocarNota(nota);
    }
}
