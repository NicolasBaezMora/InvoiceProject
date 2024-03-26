package com.proyectoTC.Taller_17_TC.utils;

import java.util.HashMap;
import java.util.Map;

public class PaymentType {

    private static final Map<Integer, String> types = new HashMap<>();

    static {
        types.put(1, "EFECTIVO");
        types.put(2, "TARJETA DE CREDITO");
        types.put(3, "TARJETA DEBITO");
    }

    public static String getTypePay(int id) {
        if (types.get(id) == null) return "Otro";
        return types.get(id);
    }

}
