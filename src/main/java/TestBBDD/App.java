package TestBBDD;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public App() {
    }
    public Connection getConnection() {
        Connection connection = null;
        try {
            /**/
            connection=DriverManager.getConnection("jdbc:mysql://192.168.22.220:3306/NBA BBDD", "root", "#Password0");
            System.out.println("BBDD Connected");

            System.out.println("EXERCICIO 1");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM jugadores WHERE Nombre_equipo=?");
            preparedStatement.setString(1,"Cavaliers");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) System.out.println("Nombre: "+rs.getString("Nombre")+"\nProcedencia: "+rs.getString("Procedencia")+"\nPosicion: "+rs.getString("Posicion")+"\n-----------");

            System.out.println("EXERCICIO 2");
            preparedStatement = connection.prepareStatement("SELECT count(*) as contador FROM jugadores WHERE Procedencia=?");
            preparedStatement.setString(1,"Spain");
            rs = preparedStatement.executeQuery();
            rs.next();
            System.out.println("Españoles: "+rs.getString("contador"));

            System.out.println("EXERCICIO 3");
            preparedStatement=connection.prepareStatement("INSERT INTO jugadores"+"(codigo,Nombre,Procedencia,Altura,Peso,Posicion,Nombre_equipo) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, "614");
            preparedStatement.setString(2, "Luka Doncic");
            preparedStatement.setString(3, "Slovenia");
            preparedStatement.setString(4, "6-7");
            preparedStatement.setString(5, "230");
            preparedStatement.setString(6, "G-F");
            preparedStatement.setString(7, "Mavericks");
            preparedStatement.executeUpdate();

            System.out.println("EJERCICIO 4");
            preparedStatement = connection.prepareStatement("SELECT * FROM jugadores LEFT JOIN estadisticas ON jugadores.codigo = estadisticas.jugador WHERE temporada = ? AND puntos_por_partido >?");
            preparedStatement.setString(1, "04/05");
            preparedStatement.setString(2, "10");
            rs = preparedStatement.executeQuery();
            while (rs.next()) System.out.println("Nombre: "+rs.getString("nombre")+"\nPuntos por partida: "+rs.getString("Puntos_por_partido")+"\n----------");

            System.out.println("EJERCICIO 5");
            preparedStatement = connection.prepareStatement("SELECT * FROM partidos WHERE temporada=?");
            preparedStatement.setString(1, "05/06");
            rs = preparedStatement.executeQuery();
            int i=0;
            while (rs.next()) {
                int pl = rs.getInt("puntos_local");
                int pv = rs.getInt("puntos_visitante");
                String el = rs.getString("equipo_local");
                String ev = rs.getString("equipo_visitante");
                if ((pl-pv)>15 & el.equals("Warriors")) {
                    i++;
                }
                if ((pv-pl)>15 & ev.equals("Warriors")) {
                    i++;
                }
            }
            System.out.println(i);


//            System.out.println();
//            System.out.println("EXERCICIO 2");
//            ResultSet rs2 = st.executeQuery("SELECT count(*) FROM jugadores WHERE Procedencia='Spain'");
//            System.out.println(rs2.getString("count(*)"));

            rs.close();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    public static void main(String[] args) {
        App app = new App();
        Connection c = app.getConnection();
    }
}
