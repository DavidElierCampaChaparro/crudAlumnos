package com.mycompany.mavenproject2;

import dtos.AlumnoTablaDTO;
import entidad.AlumnoEntidad;
import negocio.AlumnoNegocio;
import negocio.IAlumnoNegocio;
import negocio.NegocioException;
import persistencia.AlumnoDAO;
import persistencia.AlumnoMemoriaDAO;
import persistencia.ConexionBD;
import persistencia.IAlumnoDAO;
import persistencia.IConexionBD;
import persistencia.PersistenciaException;
import presentacion.frmCRUD;

/**
 *
 * @author David Elier Campa Chaparro - 00000245178
 */
public class Mavenproject2 {

    public static void main(String[] args) throws PersistenciaException, NegocioException {
        System.out.println("Hello World!");
        
        IConexionBD conexionBD = new ConexionBD();
        IAlumnoDAO alumnodao = new AlumnoDAO(conexionBD);
        IAlumnoNegocio alumnoNegocio = new AlumnoNegocio(alumnodao);
        frmCRUD fc = new frmCRUD(alumnoNegocio);
        fc.setVisible(true);
        
        //IAlumnoDAO alumnoMemoriaDAO = new AlumnoMemoriaDAO();
        //IAlumnoNegocio alumnoNegocioMemoria = new AlumnoNegocio(alumnoMemoriaDAO);
        //frmCRUD fcd = new frmCRUD(alumnoNegocioMemoria);
        //fcd.setVisible(true);
        
        
        
    }
}
