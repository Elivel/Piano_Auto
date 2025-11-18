package com.fisagrp.piano.model;

import java.util.ArrayList;
import java.util.List;

public class SecuenciaHimno {
    
    public static List<Nota> escenario1() {
        List<Nota> notas = new ArrayList<>();
        // SI SI DO RE RE DO SI LA SOL SOL LA SI SI LA LA
        notas.add(Nota.SI);
        notas.add(Nota.SI);
        notas.add(Nota.DO);
        notas.add(Nota.RE);
        notas.add(Nota.RE);
        notas.add(Nota.DO);
        notas.add(Nota.SI);
        notas.add(Nota.LA);
        notas.add(Nota.SOL);
        notas.add(Nota.SOL);
        notas.add(Nota.LA);
        notas.add(Nota.SI);
        notas.add(Nota.SI);
        notas.add(Nota.LA);
        notas.add(Nota.LA);
        return notas;
    }
    
    public static List<Nota> escenario2() {
        List<Nota> notas = new ArrayList<>();
        // Repetir escenario1 dos veces
        notas.addAll(escenario1());
        notas.addAll(escenario1());
        return notas;
    }
    
    public static List<Nota> escenario3() {
        List<Nota> notas = new ArrayList<>();
        // Escenario2 (x2) + Escenario1
        notas.addAll(escenario2());
        notas.addAll(escenario1());
        return notas;
    }
}
