/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONEXION;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;          //Aqui obtenemos el metodo conectar
import java.sql.DriverManager;       //Aqui obtenemos el manejo del driver de java a mysql
import java.sql.PreparedStatement;   //Aqui obtenemos una sintaxis facil de crear sentencias sql
import java.sql.SQLException;        //Aqui obtenemos los metodo para manejo de excepciones
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jimmy Vasquez
 */
//Dentro del constructor vamos a crear la conexion usando un try-catch
public class Conexion {

    private Connection conexion = null;
    private String servidor = "";
    private String database = "";
    private String usuario = "";
    private String password = "";
    private String url = "";

    public Conexion(String servidor, String database, String usuario, String password) {
        this.servidor = servidor;
        this.database = database;
        this.usuario = usuario;
        this.password = password;
        CrearConexion();
    }

    public Conexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void CrearConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            url = "jdbc:mysql://" + servidor + "/" + database + "?useSSL=false";//Grego linea para advertencia "Establishing SSL connection without server's identity verification is not recommended. "
            try {
                conexion = DriverManager.getConnection(url, usuario, password);
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Conexion a Base de Datos " + url + " . . . . .Ok");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        if (conexion == null) {
            CrearConexion();
        }
        return conexion;
    }

    public Connection cerrarConexion() {
        try {
            conexion.close();
            System.out.println("Cerrando conexion a " + url + " . . . . . Ok");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        conexion = null;
        return conexion;
    }
}
