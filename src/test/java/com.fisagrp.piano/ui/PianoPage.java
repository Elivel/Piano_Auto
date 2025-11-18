import java.com.fisagrp.piano.model.Nota;
import java.lang.annotation.Target;

@DefaultUrl("https://www.musicca.com/es/piano")
public class PianoPage extends PageObject {
    public static Target TECLA_DO  = Target.the("Tecla DO")
        .located(By.cssSelector("[data-note='1c']]"));

    public static Target TECLA_RE  = Target.the("Tecla RE")
        .located(By.cssSelector("[data-note='3d']"));

    public static Target TECLA_MI  = Target.the("Tecla MI")
        .located(By.cssSelector("[data-note='2e']"));

    public static Target TECLA_FA  = Target.the("Tecla FA")
        .located(By.cssSelector("[data-note='2f']"));

    public static Target TECLA_SOL = Target.the("Tecla SOL")
        .located(By.cssSelector("[data-note='2g']"));

    public static Target TECLA_LA  = Target.the("Tecla LA")
        .located(By.cssSelector("[data-note='2a']"));

    public static Target TECLA_SI  = Target.the("Tecla SI")
        .located(By.cssSelector("[data-note='2b']"));

    public static Target teclaPara(Nota nota) {
        return switch (nota) {
            case DO  -> TECLA_DO;
            case RE  -> TECLA_RE;
            case MI  -> TECLA_MI;
            case FA  -> TECLA_FA;
            case SOL -> TECLA_SOL;
            case LA  -> TECLA_LA;
            case SI  -> TECLA_SI;
        };
    }
    
}
