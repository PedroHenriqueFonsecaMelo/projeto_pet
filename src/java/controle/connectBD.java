package controle;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.persistence.sessions.server.Server;



public class connectBD {

    private static final String url = "jdbc:h2:tcp://localhost/~/test";
    private static final String className = "org.h2.Driver";
    private static final String user = "sa";
    private static final String password = "password";
    static Server server;
    static Connection con;
    static Statement stmt;

    public connectBD() {
        try {
           
            Class.forName(className);
            con = DriverManager.getConnection(url, user, password);
           
            con.close();
            System.out.println("sucesso");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public static void CreateTableX(Class<?> x) {
        Field[] Fields = x.getDeclaredFields();
        StringBuilder table = new StringBuilder();
        String name, tipo;
        int i = 0;
        table.append("create table ").append(x.getSimpleName()).append(" ("); // + "(" + "id" + x.getSimpleName() + " INT PRIMARY
                                                                  // KEY
        // AUTO_INCREMENT ,");

        for (Field f : Fields) {
            // f.setAccessible(true);
            i++;
            name = f.getName();
            tipo = f.getType().getSimpleName();

            if (name.contains("data")) {
                tipo = "Date";
            } else if (name.contains("id" + x.getSimpleName())
                    | name.contains("id" + x.getSimpleName().toLowerCase())) {
                if(!name.equalsIgnoreCase("serialVersionUID")){
                    tipo = "ID";
                }
               
            }
            if(!name.equalsIgnoreCase("serialVersionUID")){
                if (i <= Fields.length - 1) {
                    table.append(extracted(name, tipo)).append(" , ");
                } else {
                    table.append(extracted(name, tipo)).append(");");
                }
            }
     
        }

        System.out.println(table.toString());
        EXEquery(table.toString());

    }

    public static String InserirTableX(Object obj) {
        StringBuilder Inserir = new StringBuilder();
        String name;
        int i = 0;
        Field[] Fields = obj.getClass().getDeclaredFields();

        Inserir.append("insert into ").append(obj.getClass().getSimpleName()).append(" (");

        for (Field f : Fields) {
            // f.setAccessible(true);
            i++;
            if (!f.getName().contains("id" + obj.getClass().getSimpleName())) {
                name = f.getName();
                if (i <= Fields.length - 1) {
                    Inserir.append(name + " , ");
                } else {
                    Inserir.append(name + ") VALUES  (");
                }
            }

        }
        i = 0;
        for (Field f : Fields) {
            i++;
            if (!f.getName().contains("id" + obj.getClass().getSimpleName())) {
                switch (f.getType().getSimpleName()) {
                    case "String":
                    case "Date":
                        Inserir.append("'" + runGetter(f, obj) + "'");
                        break;
                    default:
                        Inserir.append(" " + runGetter(f, obj) + " ");
                        break;
                }
                if (i <= Fields.length - 1) {
                    Inserir.append(" , ");
                } else {
                    Inserir.append(" ); ");
                }
            }

        }

        return Inserir.toString();
    }

    public static String DeletarTableX(int uid, Class<?> obj) {
        StringBuilder queryDel = new StringBuilder();
        queryDel.append("delete from ").append(obj.getClass().getSimpleName()).append(" where id").append(obj.getClass().getSimpleName()).append(" = ").append(uid).append(";");
        return queryDel.toString();
    }

    private static String extracted(String name, String tipo) {
        switch (tipo) {
            case "String":
                return " " + name + " VARCHAR(100) ";
            case "int":
            case "long":
                return " " + name + " INT ";
            case "Date":
                return " " + name + " DATE ";
            case "float":
            case "BigDecimal":
                return " " + name + " NUMERIC(20, 2) ";
            case "ID":
                return (" " + name + " INT PRIMARY KEY AUTO_INCREMENT ");
            default:
                return "";
        }
    }

    public static void EXEquery(String BDquery) {

      
            
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
           
            stmt.execute(BDquery);
           
            con.close();
            System.out.println("sucesso EXEquery" + "\n <---------> \n");
        } catch (SQLException ex) {
            Logger.getLogger(connectBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(connectBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    public static String EXE_Select_UID(String BDquery) {
        ResultSet rs;
        String resultQuery = "";

        try {
            con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            rs = stmt.executeQuery(BDquery);
            while (rs.next()) { // Position the cursor 3
                resultQuery = rs.getString(1); // Retrieve only the first column value
                // Print the column value
            }
            con.close();
            rs.close();
            System.out.println("sucesso");
            return resultQuery;

        } catch (SQLException e) {
            System.out.println("erro");
            return "";
        }
    }

    public static String runGetter(Field field, Object o) {
        // MZ: Find the correct method
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    // MZ: Method found, run it
                    try {
                        return method.invoke(o).toString();
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println("Could not determine method: " + method.getName());
                    }
                }
            }
        }

        return null;
    }

    public static List<Map<String, Object>> EXE_Select(String query) {
        ResultSet rs;
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> row = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();

            while (rs.next()) {
                row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));

                }
                resultList.add(row);
            }
            con.close();
            rs.close();
            return resultList;
        } catch (SQLException e) {
            System.out.println("Erro public static ResultSet EXE_Select(String query)");
        }

        return null;

    }

    public static void executeQuery(String BDquery) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.executeQuery(BDquery);
            con.close();
            System.out.println("sucesso public static void executeQuery" + "\n <---------> \n");
        } catch (SQLException e) {
            System.out.println("erro public static void executeQuery");
        }
    }

    public static ArrayList<ArrayList<String>> mrows(String query) {

        ResultSet rs;
        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();

            while (rs.next()) {
                ArrayList<String> aList = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    aList.add(rs.getObject(i).toString());
                }
                rows.add(aList);
            }

            con.close();
            rs.close();
            return rows;

        } catch (SQLException e) {
            System.out.println("Erro public ArrayList<ArrayList<String>> mrows(String query)");
        }

        return null;

    }

    public static ArrayList<ArrayList<String>> mcolum(String query) {

        ResultSet rs;
        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        try {
            
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();
            ArrayList<String> aList = new ArrayList<>();

            for (int i = 1; i <= columnCount; i++) {

                aList.add(metaData.getColumnName(i));
            }

            rows.add(aList);
          
            
            con.close();
            rs.close();
            return rows;

        } catch (SQLException e) {
            System.out.println("Erro ArrayList<ArrayList<String>> mcolum(String query)");
        }

        return null;

    }

    public static Map<String, ArrayList<String>> EXE_Map(String query) {
        ResultSet rs;
        Map<String, ArrayList<String>> row = new HashMap<>();

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    row.computeIfAbsent(metaData.getColumnName(i), k -> new ArrayList<>())
                            .add(rs.getObject(i).toString());
                }
            }

            con.close();
            rs.close();
            return row;

        } catch (SQLException e) {
            System.out.println("Erro Map<String, ArrayList<Object>> EXE_Map(String query)");
        }

        return null;
    }
    
}
