package com.fisagrp.piano.ui;

import com.fisagrp.piano.model.Nota;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@DefaultUrl("https://www.musicca.com/es/piano")
public class PianoPage extends PageObject {
    public static final Target TECLA_DO  = Target.the("Tecla DO")
        .located(By.cssSelector("[data-note='1c']"));

    public static final Target TECLA_RE  = Target.the("Tecla RE")
        .located(By.cssSelector("[data-note='3d']"));

    public static final Target TECLA_MI  = Target.the("Tecla MI")
        .located(By.cssSelector("[data-note='2e']"));

    public static final Target TECLA_FA  = Target.the("Tecla FA")
        .located(By.cssSelector("[data-note='2f']"));

    public static final Target TECLA_SOL = Target.the("Tecla SOL")
        .located(By.cssSelector("[data-note='2g']"));

    public static final Target TECLA_LA  = Target.the("Tecla LA")
        .located(By.cssSelector("[data-note='2a']"));

    public static final Target TECLA_SI  = Target.the("Tecla SI")
        .located(By.cssSelector("[data-note='2b']"));

    public static Target teclaPara(Nota nota) {
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
