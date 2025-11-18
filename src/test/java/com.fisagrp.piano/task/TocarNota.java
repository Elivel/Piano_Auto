import java.com.fisagrp.piano.model.Nota;
import java.com.fisagrp.piano.ui.PianoPage;
import java.lang.annotation.Target;

public class TocarNota implements Task {
     private final Nota nota;

    public TocarNota(Nota nota) {
        this.nota = nota;
    }

    public static TocarNota de(Nota nota) {
        return Tasks.instrumented(TocarNota.class, nota);
    }

    @Step("{0} toca la nota #nota")
    public <T extends Actor> void performAs(T actor) {
        Target tecla = PianoPage.teclaPara(nota);
        actor.attemptsTo(Click.on(tecla));
        // Podrías agregar una pequeña espera si es necesario
    }
    
}
