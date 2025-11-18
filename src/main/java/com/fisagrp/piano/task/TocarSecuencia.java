package com.fisagrp.piano.task;

import net.thucydides.core.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import com.fisagrp.piano.model.Nota;
import java.util.List;

public class TocarSecuencia implements Task {
    private final List<Nota> notas;

    public TocarSecuencia(List<Nota> notas) {
        this.notas = notas;
    }

    public static TocarSecuencia de(List<Nota> notas) {
        return Tasks.instrumented(TocarSecuencia.class, notas);
    }

    @Step("{0} toca la secuencia del himno")
    public <T extends Actor> void performAs(T actor) {
        for (Nota nota : notas) {
            actor.attemptsTo(TocarNota.de(nota));
        }
    }
}
