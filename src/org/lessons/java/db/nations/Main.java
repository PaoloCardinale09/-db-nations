package org.lessons.java.db.nations;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // parametri di connessione
        String url = "jdbc:mysql://localhost:8889/db-nations";
        String user = "root";
        String password = "root";

        // provo ad aprire una connessione dati
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // stampo il catalogo del DB
            System.out.println(connection.getCatalog());
            // preparo lo statement SQL
            String sql = " SELECT c.country_id, c.name as country_name, r.name as region_name, con.name as continent_name FROM countries c JOIN regions r ON c.region_id = r.region_id LEFT JOIN continents con ON r.continent_id = con.continent_id ORDER BY c.name;";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String country_id = rs.getString("country_id");
                        String country_name = rs.getString("country_name");
                        String region_name = rs.getString("region_name");
                        String continent_name = rs.getString("continent_name");
                        System.out.println(region_name + " - " + country_id + " - " + region_name + " - " + continent_name);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Unable to connect to DB");
            e.printStackTrace();
        }

    }
}
