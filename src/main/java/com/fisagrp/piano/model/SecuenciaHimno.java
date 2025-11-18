package com.fisagrp.piano.model;

import java.util.ArrayList;
import java.util.List;

public class SecuenciaHimno {
    public static List<Nota> escenario1() {
        return List.of(
            Nota.SI, Nota.SI, Nota.DO, Nota.RE, Nota.RE, Nota.DO, Nota.SI, Nota.LA,
            Nota.SOL, Nota.SOL, Nota.LA, Nota.SI, Nota.SI, Nota.LA, Nota.LA
        );
    }

    public static List<Nota> escenario2() {
        List<Nota> base = new ArrayList<>(escenario1());
        List<Nota> doble = new ArrayList<>(base);
        doble.addAll(base);
        return doble;
    }

    public static List<Nota> escenario3() {
        List<Nota> secuencia = new ArrayList<>();
        secuencia.addAll(escenario2());
        secuencia.addAll(escenario1());
        return secuencia;
    }
}
