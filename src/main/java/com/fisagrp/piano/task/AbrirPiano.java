package com.fisagrp.piano.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.tasks.Open;
import net.serenitybdd.core.annotations.Step;
import com.fisagrp.piano.ui.PianoPage;

public class AbrirPiano implements Task {
    @Step("{0} abre el piano virtual")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn().the(PianoPage.class));
    }
}
