/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.AlumnoTablaDTO;
import dtos.EditarAlumnoDTO;
import dtos.EliminarAlumnoDTO;
import dtos.InsertarAlumnoDTO;
import entidad.AlumnoEntidad;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import persistencia.IAlumnoDAO;
import persistencia.PersistenciaException;
import utilerias.Utilidades;

/**
 *
 * @author crazy
 */
public class AlumnoNegocio implements IAlumnoNegocio{
private IAlumnoDAO alumnoDAO;

    public AlumnoNegocio(IAlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }
    
    private void esNumeroNegativo(int numero) throws NegocioException{
        if (numero < 0){
            throw new NegocioException("El numero ingresado es negativo");
        }
    }
    
    private int obtenerOFFSETMySQL(int limit, int pagina) throws NegocioException{
        int offset = new Utilidades().RegresarOFFSETMySQL(limit, pagina);
        this.esNumeroNegativo(offset);
        return offset;
    }
    
    @Override
    public List<AlumnoTablaDTO> buscarAlumnosTabla(int limit, int offset) throws NegocioException {
        try {
            this.esNumeroNegativo(limit);
            this.esNumeroNegativo(offset);
            int Offset = this.obtenerOFFSETMySQL(limit, offset);
            
            List<AlumnoEntidad> alumnos = this.alumnoDAO.buscarAlumnosTabla(limit, offset);         
            return this.convertirAlumnoTablaDTO(alumnos);
        } catch (PersistenciaException ex) {
            // hacer uso de Logger
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }// fin metodo

    private List<AlumnoTablaDTO> convertirAlumnoTablaDTO(List<AlumnoEntidad> alumnos) throws NegocioException {
        if (alumnos == null) {
            throw new NegocioException("No se pudieron obtener los alumnos");
        }

        List<AlumnoTablaDTO> alumnosDTO = new ArrayList<>();
        for (AlumnoEntidad alumno : alumnos) {
            AlumnoTablaDTO dto = new AlumnoTablaDTO();
            dto.setIdAlumno(alumno.getIdAlumno());
            dto.setNombres(alumno.getNombres());
            dto.setApellidoPaterno(alumno.getApellidoPaterno());
            dto.setApellidoMaterno(alumno.getApellidoMaterno());
            dto.setEstatus(alumno.isActivo() == true ? "Activo" : "Inactivo");
            alumnosDTO.add(dto);
        }
        return alumnosDTO;
    } //fin metodo 
    
    public void insertarAlumno(InsertarAlumnoDTO alumno) throws NegocioException{
        if(alumno.getNombres().length() >= 30 || alumno.getApellidoPaterno().length() >= 20 || alumno.getApellidoMaterno().length() >= 20){
           throw new NegocioException("Requisitos: Nombre menos de 30 caracteres, apellidos menos de 20");
        } else {
            String nombres = alumno.getNombres(); 
            String apellidoP = alumno.getApellidoPaterno();
            String apellidoM = alumno.getApellidoMaterno();            
            AlumnoEntidad alumnoEntidad = new AlumnoEntidad(nombres, apellidoP, apellidoM);
            try {
                this.alumnoDAO.insertarAlumno(alumnoEntidad);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
                throw new NegocioException("Ocurrio un error aasasdadas");
            }
        }
    } // fin metodo 

    @Override
    public void editarAlumno(EditarAlumnoDTO alumno) throws NegocioException {
        if(alumno.getNombres().length() >= 30 || alumno.getApellidoPaterno().length() >= 20 || alumno.getApellidoMaterno().length() >= 20){
           throw new NegocioException("Requisitos: Nombre menos de 30 caracteres, apellidos menos de 20");
        } else {
            int id = alumno.getIdAlumno();
            String nombres = alumno.getNombres(); 
            String apellidoP = alumno.getApellidoPaterno();
            String apellidoM = alumno.getApellidoMaterno();  
            boolean activo;
            activo = ("Activo".equals(alumno.getEstatus())) ? true : false;
            
            AlumnoEntidad alumnoEntidad = new AlumnoEntidad(id, nombres, apellidoP, apellidoM, activo);
            try {
                this.alumnoDAO.editarAlumno(alumnoEntidad);
            } catch (Exception ex){
                System.out.println(ex.getMessage());
                throw new NegocioException("Ocurrio un error aasasdadas");
            }
        }
    }

    @Override
    public void eliminarAlumno(int ID) throws NegocioException {
        try {
            this.alumnoDAO.eliminarAlumno(ID);
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @Override
    public EditarAlumnoDTO getAlumnoByID(int ID) throws NegocioException {
        AlumnoEntidad ae;
        EditarAlumnoDTO eadto = new EditarAlumnoDTO();
        try {
            ae = this.alumnoDAO.getAlumnoByID(ID);
            eadto.setIdAlumno(ae.getIdAlumno());
            eadto.setNombres(ae.getNombres());
            eadto.setApellidoPaterno(ae.getApellidoPaterno());
            eadto.setApellidoMaterno(ae.getApellidoMaterno());
            if(ae.isActivo()){
                eadto.setEstatus("Activo");
            } else {
                eadto.setEstatus("Inactivo");
            }
            
            
        } catch (PersistenciaException ex) {
            System.out.println("Error");
        }
        
        return eadto;
    }
    
}
