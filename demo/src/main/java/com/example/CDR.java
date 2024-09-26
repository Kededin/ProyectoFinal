package com.example;

public class CDR {

    //cración de variables en donde se agurdara la información de las llamadas
    private String numeroCuenta; //numero de inentificación de la llamada
    private String numeroLlamante;
    private String numeroReceptor;
    private String timestamp; //fecha de cuando se hizo la llamada
    private int duracionMinutos;
    private double tarifaPorMinuto;
    private String categoria; //guardaremos la información de la llamada si es nacional o internacional

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

    //funciones para poder pedri información de lo que te tenemos en nuestras variables
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public double getTarifaPorMinuto() {
        return tarifaPorMinuto;
    }

    // en este metodo convertimos nuestra información de las varibles en una cadena de texto y las mostramos en consola
    @Override
    public String toString() {
        return numeroCuenta + "," + numeroLlamante + "," + numeroReceptor + "," + timestamp + "," + duracionMinutos + "," + tarifaPorMinuto + "," + categoria;
    }
}
