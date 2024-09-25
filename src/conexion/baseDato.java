package conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

public class baseDato {

    public static DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        
        // Configuración del DataSource con los datos de la base de datos
        dataSource.setServerNames(new String[] { "localhost" }); // Cambia si está en otro servidor
        dataSource.setPortNumbers(new int[] { 5432 }); // El puerto por defecto de PostgreSQL
        dataSource.setDatabaseName("postgres"); // Nombre de la base de datos
        dataSource.setUser("postgres"); // Usuario de PostgreSQL
        dataSource.setPassword("superusuario"); // Contraseña de PostgreSQL

        return dataSource;
    }

    public static void main(String[] args) {
        DataSource dataSource = getDataSource();
        try (Connection conn = dataSource.getConnection()) {
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos PostgreSQL usando DataSource");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
        try (Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM alumno")) {

               while (rs.next()) {
                   // Suponiendo que tu tabla tiene columnas 'id' y 'nombre'
                   int id = rs.getInt("id");
                   String nombre = rs.getString("nombre");
                   System.out.println("ID: " + id + ", Nombre: " + nombre);
               }
           } catch (SQLException e) {
               System.out.println("Error al conectar a la base de datos: " + e.getMessage());
               e.printStackTrace();
           }
    }
}
