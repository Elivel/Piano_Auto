import java.com.fisagrp.piano.ui.PianoPage;

public class AbrirPiano implements Task {
    public static AbrirPiano enLaPagina() {
        return Tasks.instrumented(AbrirPiano.class);
    }

    @Step("{0} abre el piano virtual")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn().the(PianoPage.class));
    }
    
}
