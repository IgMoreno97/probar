import java.sql.*;
public class conexionBBDD {
    //Atributos de la clase
    private String bd = "jardineria";
    private String login = "root";
    private String pwd = "";
    private String url = "jdbc:mysql://localhost/" + bd;
    private Connection conexion;

    //Constructor que crea la conexión
    public conexionBBDD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, login, pwd);
            System.out.println(" - Conexión con MySQL establecida -");
//			conexion.close();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Driver JDBC no encontrado");
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            System.out.println("Error al conectarse a la BD");
            sqle.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error general");
            e.printStackTrace();
        }
    }

    public void Consulta(String query, int columna) {
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rset = stmt.executeQuery(query);
            while (rset.next())
                System.out.println(rset.getString(columna));
            rset.close();
            stmt.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void Consulta2(String query, int cod, int columna) {
        try {
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setInt(1, cod);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next())
                System.out.println(rset.getString(columna));
            rset.close();
            pstmt.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new conexionBBDD();
        conexionBBDD prueba = new conexionBBDD();
        prueba.Consulta("SELECT * FROM jardineria.clientes", 2);
        // Para ver esto entrar en localhost dentro del buscador y phpMyAdmin
        prueba.Consulta2("SELECT * FROM clientes WHERE CodigoCliente=?", 1, 3);

    }
}
