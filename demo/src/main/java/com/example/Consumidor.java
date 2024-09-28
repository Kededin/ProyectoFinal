package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Consumidor implements Runnable {
    private final BufferCompartido<CDR> buffer;

    public Consumidor(BufferCompartido<CDR> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        // Conectar a MySQL (XAMPP)
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdr_db", "root", "")) {  // Conexión con MySQL
            String sql = "INSERT INTO registros_cdr (numero_cuenta, duracion, tarifa_total) VALUES (?, ?, ?)";//creamo una consulta que ará las inseriones a la bsa de datos 
            PreparedStatement statement = conexion.prepareStatement(sql); //podremos generar la consultas en su devido tiempo

            while (true) {
                // Tomar un CDR del buffer que el productor colocó
                CDR cdr = buffer.tomar();

                // Calcular la tarifa total
                double tarifaTotal = cdr.getDuracionMinutos() * cdr.getTarifaPorMinuto();

                // Guardar en la base de datos
                statement.setString(1, cdr.getNumeroCuenta());
                statement.setInt(2, cdr.getDuracionMinutos());
                statement.setDouble(3, tarifaTotal);
                statement.executeUpdate();

                System.out.println("Consumidor procesó: " + cdr.toString());
                Thread.sleep(1500);  // Simular tiempo de procesamiento
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
