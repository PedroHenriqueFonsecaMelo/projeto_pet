package servicos;

import entidades.Produtos;
import java.util.ArrayList;

/**
 *
 * @author Pedro Henrique
 */
public class initCli {
    private static int id=0;
    private static ArrayList<Produtos> s = new ArrayList<>();
    
    public static void setCliId(Integer idusuario) {
        id = idusuario;
    }
    public static int getCliId() {
        return id;
    }

    public static ArrayList<Produtos> getS() {
        return s;
    }

    public static void setS(ArrayList<Produtos> s) {
        initCli.s = s;
    }
    
    public static void addS(Produtos e) {
        initCli.s.add(e);
    }
    public static void clear() {
        initCli.s.clear();
    }
    public static String toTable(){
        StringBuilder str = new StringBuilder();
     
        for(Produtos p : s){
            str.append("<tr>\n" +
"                            <td>\n" +
"                                <div class=\"product\">\n" +
"                                    <img src=\""+p.getImg()+"\" alt=\"\"width=\"200\" height=\"200\">\n" +
"                                    <div class=\"info\">\n" +
"                                        <div class=\"nome\">"+p.getDesc()+"</div>\n" +
"                                        <div class=\"categoria\">categoria</div>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                            </td>\n" +
"                            <td>R$:"+p.getPreco()+"</td>\n" +
"                            <td>\n" +
"                                <div class=\"qtde\">\n" +
"                                    <button type=\"button\" i class=\"bx bx-minus\"></button>\n" +
"                                    <span class=\"num\">1</span>\n" +
"                                    <button type=\"button\" i class=\"bx bx-plus\"></button>\n" +
"                                </div>\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <a href=\"./addproduto\" class=\"remove\"><i class='bx bx-x'></i></a>\n" +
"                            </td>\n" +
"                        </tr>");
        }
        return str.toString();
    }
    public static String Vtable(){
        StringBuilder str = new StringBuilder();
        int i = 1;
        for(Produtos p : s){
            str.append("<input type=\"hidden\" id=\"desc" + i + "\" name=\"desc" + i + "\" value=\"\">"+
                    "<input type=\"hidden\" id=\"QUANTIDADE" + i + "\" name=\"QUANTIDADE" + i + "\" value=\"\">"+
                    "<input type=\"hidden\" id=\"OBS" + i + "\" name=\"OBS" + i + "\" value=\"\">"+
                    "<input type=\"hidden\" id=\"PRECO"+ i + "\" name=\"PRECO"+ i + "\" value=\"\">");
        }
        return str.toString();
    }
}