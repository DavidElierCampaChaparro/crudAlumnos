/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import entidad.AlumnoEntidad;
import java.util.List;

/**
 *
 * @author David Elier Campa Chaparro 245178
 */
public interface IAlumnoDAO {
    
    public List<AlumnoEntidad> buscarAlumnosTabla(int limit, int offset) throws PersistenciaException;
    public void insertarAlumno(AlumnoEntidad alumno) throws PersistenciaException;
    public void editarAlumno(AlumnoEntidad alumno) throws PersistenciaException;
    public AlumnoEntidad getAlumnoByID(int ID) throws PersistenciaException;
    public void eliminarAlumno(int ID) throws PersistenciaException;
    
}
