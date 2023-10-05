/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.export.Prepare;

/**
 *
 * @author juan_
 */
public class TP13 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            String URL="jdbc:mariadb://localhost:3306/tp13";
            String USUARIO="root";
            String PASSWORD="";
            Connection con=DriverManager.getConnection(URL,USUARIO,PASSWORD);
           
            String sqlLine4 = "INSERT INTO materia(nombre, año, estado) VALUES ('matematicas',6,1)";
            String sqlLine5 = "INSERT INTO materia(nombre, año, estado) VALUES ('Sociología',6,1)";
            String sqlLine6 = "INSERT INTO materia(nombre, año, estado) VALUES ('Calculo',6,1)";
            
            PreparedStatement carga4 = con.prepareStatement(sqlLine4);
            PreparedStatement carga5 = con.prepareStatement(sqlLine5);
            PreparedStatement carga6 = con.prepareStatement(sqlLine6);
            
            carga4.executeUpdate();
            carga5.executeUpdate();
            carga6.executeUpdate();
            Alumno bruno = new Alumno(456455, "Palmucci", "Bruno", LocalDate.of(2000, Month.MAY, 1), true);
            String sqlLine3 = "INSERT INTO materia(nombre, año, estado) VALUES ('Lengua',6,1)";
            PreparedStatement carga3 = con.prepareStatement(sqlLine3);
            carga3.executeUpdate();
            String sqlLine= "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) VALUES (?, ?, ?, ?, ?)";
            String sqlLine2= "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) VALUES (7854, 'meesi', 'Lionel', '1986-06-24', 1)";
            PreparedStatement carga = con.prepareStatement(sqlLine);
            PreparedStatement carga2 = con.prepareStatement(sqlLine2);
            carga.setInt(1, bruno.getDni());
            carga.setString(2, bruno.getNombre());
            carga.setString(3, bruno.getApellido());
            carga.setDate(4, java.sql.Date.valueOf(bruno.getFechaNacimiento()));
            carga.setBoolean(5, true);
            int filas = carga.executeUpdate();
            carga2.executeUpdate();
            if(filas>0){
                JOptionPane.showMessageDialog(null,"Alumno agregado.");        
            }

                String sqlLine7 = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (9,1,1)";
                String sqlLine8 = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (7,1,2)";
                String sqlLine9 = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (9,6,1)";
                String sqlLine10 = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (8,6,2)";
                String sqlLine11 = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (7,8,1)";
                String sqlLine12 = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES (9,8,2)";
                
                PreparedStatement carga7 = con.prepareStatement(sqlLine7);
                PreparedStatement carga8 = con.prepareStatement(sqlLine8);
                PreparedStatement carga9 = con.prepareStatement(sqlLine9);
                PreparedStatement carga10 = con.prepareStatement(sqlLine10);
                PreparedStatement carga11 = con.prepareStatement(sqlLine11);
                PreparedStatement carga12 = con.prepareStatement(sqlLine12);
                
                carga7.executeUpdate();
                carga8.executeUpdate();
                carga9.executeUpdate();
                carga10.executeUpdate();
                carga11.executeUpdate();
                carga12.executeUpdate();
                    
                  String sqlLine13 = "SELECT * FROM inscripcion "
                  + "INNER JOIN alumno ON inscripcion.idAlumno = alumno.idAlumno and nota >= 8;";
                  PreparedStatement carga13 = con.prepareStatement(sqlLine13);
                  ResultSet query = carga13.executeQuery();
                  while (query.next()) {
                      System.out.println("DNI: "+ query.getInt("dni"));
                      System.out.println("Apellido: "+ query.getString("apellido"));
                      System.out.println("Nombre: "+ query.getString("nombre"));
                      System.out.println("Fecha de nacimiento: "+ query.getDate("fechaNacimiento").toString());
                      System.out.println("Estado: "+ query.getBoolean("estado"));
                      System.out.println("Nota: "+query.getInt("nota"));
                      System.out.println("--------------------------------------------");
                      
                
            }
           
           String sqlLine14 = "DELETE FROM inscripcion WHERE inscripcion.idInscripto = 8;";
            PreparedStatement carga14 = con.prepareStatement(sqlLine14);
            carga14.executeUpdate();
                    
        }catch(ClassNotFoundException cnf){
            JOptionPane.showMessageDialog(null,"Error al cargar driver");
            
        }catch(SQLException sql){
            System.out.println(sql.getErrorCode());
            if (sql.getErrorCode()== 1062) {
                JOptionPane.showMessageDialog(null,"Ya existe un alumno con ese DNI.");
            }else if(sql.getErrorCode()== 1049){
                JOptionPane.showMessageDialog(null,"Base de datos inexistente.");
            }else{
                JOptionPane.showMessageDialog(null,"Error al conectarse a bd");
                System.out.println(sql.getMessage());
            }        
            
                   
        }
    }
        
    }
    

