/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
// no dtos

import entidad.AlumnoEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author crazy
 */
public class AlumnoDAO implements IAlumnoDAO {
    private IConexionBD conexionBD;
    
    public AlumnoDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    } 

    @Override
    public List<AlumnoEntidad> buscarAlumnosTabla(int limit, int offset) throws PersistenciaException {
        try{
            List<AlumnoEntidad> alumnosLista = null;
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idAlumno, nombres, apellidoPaterno, apellidoMaterno, eliminado, activo FROM alumnos WHERE eliminado = b'0' LIMIT " + String.valueOf(limit) + " OFFSET " + String.valueOf(offset);
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while(resultado.next()){
                if(alumnosLista == null){
                    alumnosLista = new ArrayList<>();
                }
                AlumnoEntidad alumno = this.convertirAEntidad(resultado);
                alumnosLista.add(alumno);
            }
            conexion.close();
            return alumnosLista;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new PersistenciaException("Ocurrio un error");
        }   
    }// fin metodo

    private AlumnoEntidad convertirAEntidad(ResultSet resultado) throws SQLException{
        int id = resultado.getInt("idAlumno");
        String nombres = resultado.getString("nombres");
        String apellidoPaterno = resultado.getString("apellidoPaterno");
        String apellidoMaterno = resultado.getString("apellidoMaterno");
        boolean eliminado = resultado.getBoolean("eliminado");
        boolean activo = resultado.getBoolean("activo");
        return new AlumnoEntidad(id, nombres, apellidoPaterno, apellidoMaterno, eliminado, activo);
    } // fin metodo

    @Override
    public void insertarAlumno(AlumnoEntidad alumno) throws PersistenciaException {
        try {
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "INSERT INTO alumnos(nombres, apellidoPaterno, apellidoMaterno) VALUES (?,?,?)";
            
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, alumno.getNombres());
            preparedStatement.setString(2, alumno.getApellidoPaterno());
            preparedStatement.setString(3, alumno.getApellidoMaterno());
            preparedStatement.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error");
        }
    } // fin metodo

    @Override
    public void editarAlumno(AlumnoEntidad alumno) throws PersistenciaException {
        int id = alumno.getIdAlumno();
        String nombres = alumno.getNombres();
        String apellidoP = alumno.getApellidoPaterno();
        String apellidoM = alumno.getApellidoMaterno();
        String estatus;
        if(alumno.isActivo()){
            estatus = "1";
        } else {
            estatus = "0";
        }
        
        try {
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "UPDATE alumnos SET nombres = ?,  apellidoPaterno = ?, apellidoMaterno = ?, activo = b? WHERE idAlumno = ?; ";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, nombres);
            preparedStatement.setString(2, apellidoP);
            preparedStatement.setString(3, apellidoM);
            preparedStatement.setString(4, estatus);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error");
        }
    }
    
    @Override
    public AlumnoEntidad getAlumnoByID(int ID) throws PersistenciaException {
        AlumnoEntidad alumnoConsultado = null;
        try {
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idAlumno, nombres, apellidoPaterno, apellidoMaterno, activo FROM alumnos WHERE idAlumno = " + String.valueOf(ID);
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while(resultado.next()){
                int id = resultado.getInt("idAlumno");
                String nombres = resultado.getString("nombres");
                String apellidoP = resultado.getString("apellidoPaterno");
                String apellidoM = resultado.getString("apellidoMaterno");
                boolean activo = resultado.getBoolean("activo");
                alumnoConsultado = new AlumnoEntidad(id, nombres, apellidoP, apellidoM, activo);
            }
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error");
        }
        return alumnoConsultado;
    }
    
    
    @Override
    public void eliminarAlumno(int ID) throws PersistenciaException {
         try {
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "UPDATE alumnos SET eliminado = b? WHERE idAlumno = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL);
            preparedStatement.setString(1, "1");
            preparedStatement.setInt(2, ID);
            preparedStatement.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error");
        }
    }
    
    
    
}
