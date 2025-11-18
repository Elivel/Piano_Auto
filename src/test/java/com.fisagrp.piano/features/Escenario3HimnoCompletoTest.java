package com.fisagrp.piano.features;

import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;
import org.junit.Test;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import com.fisagrp.piano.task.AbrirPiano;
import com.fisagrp.piano.task.TocarSecuencia;
import com.fisagrp.piano.model.SecuenciaHimno;

@RunWith(SerenityRunner.class)
public class Escenario3HimnoCompletoTest {

    private final Actor usuario = Actor.named("Eli");

    @Managed(uniqueSession = true)
    WebDriver herBrowser;

    @Test
    public void debeTocarEscenario3() {
        usuario.can(BrowseTheWeb.with(herBrowser));

        usuario.attemptsTo(
            AbrirPiano.enLaPagina(),
            TocarSecuencia.de(SecuenciaHimno.escenario3())
        );
    }
}
