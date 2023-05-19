package controle;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fatec
 */
public class servicos {
    
   
     String className = "com.mysql.jdbc.Driver";
     String url = "jdbc:mysql://localhost:3306/escola"; //Nome da base de dados
     String user = "root"; //nome do usuário do MySQL
     String password = ""; //senha do MySQL
     Connection con;
     Statement stmt;


    public servicos() {
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, password);
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    public void exe(String query){
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            stmt.execute(query);
            con.close();
            System.out.println("sucesso EXEquery" + "\n <---------> \n");
        } catch (SQLException e) {
            System.out.println("erro EXEquery" + "\n <---------> \n");
        }
    
    }
        
        
}
