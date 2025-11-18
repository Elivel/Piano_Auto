package com.fisagrp.piano.task;

import net.thucydides.core.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import com.fisagrp.piano.model.Nota;
import com.fisagrp.piano.ui.PianoPage;

public class TocarNota implements Task {
    private final Nota nota;

    public TocarNota(Nota nota) {
        this.nota = nota;
    }

    public static TocarNota de(Nota nota) {
        return Tasks.instrumented(TocarNota.class, nota);
    }

    @Step("{0} toca la nota {0}")
    public <T extends Actor> void performAs(T actor) {
        Target tecla = PianoPage.teclaPara(nota);
        actor.attemptsTo(Click.on(tecla));
    }
}
