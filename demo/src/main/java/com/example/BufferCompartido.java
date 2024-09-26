package com.example;
import java.util.LinkedList;

public class BufferCompartido<T> {     // en esta clase definimos que puede contener cuaquier tipo de datos <T>
    private final LinkedList<T> buffer; // usaremos una variable de tipo LinKedList como contenedor donde se guardará los datos
    private final int capacidad; //la variable capacidad indicara cuando elementos puede tener el buffer a la vez

    //constructor para crear el buffer conmpartido
    public BufferCompartido(int capacidad) {
        this.capacidad = capacidad; 
        this.buffer = new LinkedList<>(); // se creará una nueva lista para almacenar los datos del buffer
    }

    // Método para agregar un elemento al buffer compartido
    public synchronized void poner(T item) throws InterruptedException { //usaremos synchronized que hará que solo un hilo a la vez pueda haceder al método
        while (buffer.size() == capacidad) { 
            wait();  // Espera si el buffer está lleno
        }
        buffer.add(item); //si hay espacio se agrera a una lista nueva
        notifyAll();  // Notifica a los consumidores que hay un nuevo elemento
    }

    // Método para tomar un elemento del buffer compartido
    public synchronized T tomar() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();  // Espera si el buffer está vacío
        }
        T item = buffer.removeFirst();
        notifyAll();  // Notifica a los productores que hay espacio
        return item;
    }
}
