package com.example;
import java.util.LinkedList;

public class BufferCompartido<T> {
    private final LinkedList<T> buffer;
    private final int capacidad;

    public BufferCompartido(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new LinkedList<>();
    }

    // Método para agregar un elemento al buffer
    public synchronized void poner(T item) throws InterruptedException {
        while (buffer.size() == capacidad) {
            wait();  // Espera si el buffer está lleno
        }
        buffer.add(item);
        notifyAll();  // Notifica a los consumidores que hay un nuevo elemento
    }

    // Método para tomar un elemento del buffer
    public synchronized T tomar() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();  // Espera si el buffer está vacío
        }
        T item = buffer.removeFirst();
        notifyAll();  // Notifica a los productores que hay espacio
        return item;
    }
}
