package com.proyectoTC.Taller_17_TC.utils;

public class PaymentType {

    public static String getTypePay(int id) {
        switch (id) {
            case 1:
                return "EFECTIVO";
            case 2:
                return "TARJETA DE CREDITO";
            case 3:
                return "TARJETA DEBITO";
            default:
                return "OTRO";
        }
    }

}
