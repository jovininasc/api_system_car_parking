package com.systemcars.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://mysql-1af29760-systemcars01.h.aivencloud.com:15588/defaultdb";
        String user = "avnadmin";
        String password = "AVNS_GtFIlu60vF5hMPo72vF";

        System.out.println("🔄 Testando conexão com o banco Aiven...");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("✅ Conexão bem-sucedida com o banco de dados!");
            } else {
                System.out.println("❌ Falha ao conectar ao banco!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro de conexão:");
            e.printStackTrace();
        }
    }
}
