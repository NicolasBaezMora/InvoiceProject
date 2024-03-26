package com.proyectoTC.Taller_17_TC.utils;

import java.util.Map;

public class PaymentType {

    private static final Map<Integer, String> types = Map.of(
            1, "EFECTIVO",
            2, "TARJETA DE CREDITO",
            3, "TARJETA DEBITO"

    );
    public static String getTypePay(int id) {
        if (types.get(id) == null) return "Otro";
        return types.get(id);
    }

}
