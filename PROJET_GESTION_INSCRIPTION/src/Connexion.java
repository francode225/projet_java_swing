import java.sql.*;
import static java.lang.Class.forName;

public class Connexion{
    Connection connect;
    public  Connexion(){
        String url = "jdbc:mysql://localhost/gestioninscription";
        String login = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.err.println("Connextion etablie");
        }catch (ClassNotFoundException e){
            System.err.println("Erreur de connextion: "+ e.getMessage());
            e.printStackTrace();
        }

        try {
            connect =  DriverManager.getConnection(url,login,password);
            System.out.println("Connexion à la base de donnée avec succés");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection maConnexion(){
        return  connect;
    }

}
