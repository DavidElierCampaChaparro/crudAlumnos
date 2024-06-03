package com.mycompany.mavenproject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author David Elier Campa Chaparro - 00000245178
 */
public class AlumnosTemporal {
    
    final String SERVER = "localhost";
    final String BASE_DATOS = "clase";
    final String CADENA_CONEXION = "jdbc:mysql://" + SERVER + "/" + BASE_DATOS;
    final String USUARIO = "root";
    final String CONTRASEÑA = "xr471112";
    
    public void insertar(){
        
        try {
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
            
            String sentenciaSQL = "INSERT INTO alumnos (nombres, apellidoPaterno, apellidoMaterno) VALUES (?, ?, ?);";
            
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, "Edgar Alonso");
            preparedStatement.setString(2, "Panduro");
            preparedStatement.setString(3, "Jocobi");
            
            preparedStatement.executeUpdate();
            
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            while(resultado.next()){
                System.out.println(resultado.getInt(1));
            }
            
            
        } catch(SQLException e){
            System.out.println("Ocurrio un error" + e.getMessage());
        }
    }
        
    public void buscar() {
        try {

            // Establecer la conexión a la base de datos
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);

            // Sentencia SQL para seleccionar un alumno por su id
            String sentenciaSql = "SELECT * FROM alumnos WHERE idAlumno = ?;";
            // Preparar la sentencia SQL
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql);
            // Establecer los valores para los parámetros de la sentencia SQL
            preparedStatement.setInt(1, 1);

            // Ejecutar la sentencia SQL y obtener el resultado
            ResultSet resultado = preparedStatement.executeQuery();

            // Procesar el resultado
            while (resultado.next()) {
                int idAlumno = resultado.getInt("idAlumno");
                String nombres = resultado.getString("nombres");
                String apellidoPaterno = resultado.getString("apellidoPaterno");
                String apellidoMaterno = resultado.getString("apellidoMaterno");
                boolean eliminado = resultado.getBoolean("eliminado");
                boolean activo = resultado.getBoolean("activo");

                System.out.println("ID del Alumno: " + idAlumno);
                System.out.println("Nombres: " + nombres);
                System.out.println("Apellido Paterno: " + apellidoPaterno);
                System.out.println("Apellido Materno: " + apellidoMaterno);
                System.out.println("Eliminado: " + eliminado);
                System.out.println("Activo: " + activo);
            }
        } catch (SQLException ex) {
            // Capturar y manejar cualquier excepción SQL que ocurra
            System.out.println("Ocurrio un error  " + ex.getMessage());
        }
    }
    
}
