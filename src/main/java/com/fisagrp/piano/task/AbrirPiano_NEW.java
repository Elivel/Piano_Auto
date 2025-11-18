package com.fisagrp.piano.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.tasks.Open;
import com.fisagrp.piano.ui.PianoPage;

public class AbrirPiano_NEW implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn().the(PianoPage.class));
    }
    
    public static AbrirPiano_NEW enLaPagina() {
        return new AbrirPiano_NEW();
    }
}
