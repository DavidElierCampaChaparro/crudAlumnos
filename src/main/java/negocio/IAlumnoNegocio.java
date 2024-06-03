/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.AlumnoTablaDTO;
import dtos.EditarAlumnoDTO;
import dtos.EliminarAlumnoDTO;
import dtos.InsertarAlumnoDTO;
import java.util.List;

/**
 *
 * @author crazy
 */
public interface IAlumnoNegocio {
    public List<AlumnoTablaDTO> buscarAlumnosTabla(int limit, int offset) throws NegocioException;
    public void insertarAlumno(InsertarAlumnoDTO alumno) throws NegocioException;
    public void editarAlumno(EditarAlumnoDTO alumno) throws NegocioException;
    public EditarAlumnoDTO getAlumnoByID(int ID) throws NegocioException;
    public void eliminarAlumno(int ID) throws NegocioException;

}
