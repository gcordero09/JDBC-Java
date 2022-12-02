package test;

import datos.*;
import domain.*;
import java.sql.*;
import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            
            PersonaDAO personaDao = new PersonaDAOJDBC(conexion);
            
            PersonaDTO personaDto = new PersonaDTO(3, "Jerez", "", "", "");
            System.out.println(personaDao.actualizar(personaDto) + " registros modificados");

            List<PersonaDTO> personas = personaDao.seleccionar();
            personas.forEach(persona -> {
                System.out.println("persona = " + persona);
            });

            conexion.commit();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }

    }
}
