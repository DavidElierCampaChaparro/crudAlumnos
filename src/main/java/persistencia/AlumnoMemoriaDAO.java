/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidad.AlumnoEntidad;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author crazy
 */
public class AlumnoMemoriaDAO implements IAlumnoDAO{
    private List<AlumnoEntidad> alumnosLista;
    
    public AlumnoMemoriaDAO (){
        this.alumnosLista = new LinkedList();
        this.alumnosLista.add(new AlumnoEntidad(1, "Davidd1", "Campa", "Chaparro", false, true));
        this.alumnosLista.add(new AlumnoEntidad(2, "Davidd2", "Campa", "Chaparro", false, true));
        this.alumnosLista.add(new AlumnoEntidad(3, "Davidd3", "Campa", "Chaparro", false, true));
    }

    @Override
    public List<AlumnoEntidad> buscarAlumnosTabla(int limit, int offset) throws PersistenciaException {
        return this.alumnosLista;
    }

    @Override
    public void insertarAlumno(AlumnoEntidad alumno) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editarAlumno(AlumnoEntidad alumno) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AlumnoEntidad getAlumnoByID(int ID) throws PersistenciaException {
        return this.alumnosLista.get( ID - 1);
    }

    @Override
    public void eliminarAlumno(int ID) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
