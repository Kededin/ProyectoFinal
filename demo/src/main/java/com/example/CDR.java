package com.example;

public class CDR {
    private String numeroCuenta;
    private String numeroLlamante;
    private String numeroReceptor;
    private String timestamp;
    private int duracionMinutos;
    private double tarifaPorMinuto;
    private String categoria;

    // Constructor
    public CDR(String numeroCuenta, String numeroLlamante, String numeroReceptor, String timestamp, int duracionMinutos, double tarifaPorMinuto, String categoria) {
        this.numeroCuenta = numeroCuenta;
        this.numeroLlamante = numeroLlamante;
        this.numeroReceptor = numeroReceptor;
        this.timestamp = timestamp;
        this.duracionMinutos = duracionMinutos;
        this.tarifaPorMinuto = tarifaPorMinuto;
        this.categoria = categoria;
    }

    // Getters para acceder a los datos
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public double getTarifaPorMinuto() {
        return tarifaPorMinuto;
    }

    public String toString() {
        return numeroCuenta + "," + numeroLlamante + "," + numeroReceptor + "," + timestamp + "," + duracionMinutos + "," + tarifaPorMinuto + "," + categoria;
    }
}
