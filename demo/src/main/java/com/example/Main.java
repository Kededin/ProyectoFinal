package com.example;


public class Main {

    public static void main(String[] args) {
        // Crear un buffer compartido con una capacidad de 10 elementos
        BufferCompartido<CDR> buffer = new BufferCompartido<>(10);
        
        // Crear instancias de los hilos productores y consumidores
        Thread productor1 = new Thread(new Productor(buffer));
        Thread productor2 = new Thread(new Productor(buffer));
        Thread productor3 = new Thread(new Productor(buffer));
        
        Thread consumidor1 = new Thread(new Consumidor(buffer));
        Thread consumidor2 = new Thread(new Consumidor(buffer));

        // Iniciar los hilos de productores y consumidores
        productor1.start();
        productor2.start();
        productor3.start();
        consumidor1.start();
        consumidor2.start();
    }
}



