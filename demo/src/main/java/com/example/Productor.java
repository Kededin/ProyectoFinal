package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Productor implements Runnable { //Runnable nos permitirá usar la clase Productor en un hilo
    private final BufferCompartido<CDR> buffer;
    private final String archivoCSV;

    // Constructor  para obtener la ruta del archivo CSV
    public Productor(BufferCompartido<CDR> buffer) {
        this.buffer = buffer;
        // Obtener la ruta del archivo CSV usando el ClassLoader
        this.archivoCSV = getClass().getClassLoader().getResource("archivo.csv").getPath();
    }

    @Override
    public void run() {
        try {
            // Decodificar la ruta del archivo para manejar espacios o caracteres especiales
            String rutaDecodificada = URLDecoder.decode(archivoCSV, StandardCharsets.UTF_8.name());
            
            // Abrir el archivo CSV
            BufferedReader br = new BufferedReader(new FileReader(rutaDecodificada));
            String linea;

            // Leer línea por línea del archivo CSV
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("NumeroCuenta")) continue;  // Saltar la línea de encabezado
                String[] datos = linea.split(",");  // Dividir los datos por comas
                CDR cdr = new CDR(datos[0], datos[1], datos[2], datos[3], Integer.parseInt(datos[4]), Double.parseDouble(datos[5]), datos[6]);

                // Poner el CDR en el buffer
                buffer.poner(cdr);//este metodo esperara hasta que haya un espacio en el buffer compartido
                System.out.println("Productor añadió: " + cdr.toString());
                Thread.sleep(1000);  // Simula tiempo de producción
            }

            br.close();  // Cerrar el lector al finalizar
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
