package com.fisagrp.piano.task;

import com.fisagrp.piano.ui.PianoPage;
import org.openqa.selenium.WebDriver;

public class AbrirPiano {
    private PianoPage pianoPage;

    public AbrirPiano(WebDriver driver) {
        this.pianoPage = new PianoPage(driver);
    }

    public void ejecutar() {
        pianoPage.abrirPagina();
    }
}
