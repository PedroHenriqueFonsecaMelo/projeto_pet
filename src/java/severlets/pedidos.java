/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package severlets;

import DAOS.pedidosDAO;
import controle.connectBD;
import entidades.Pedidos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pedro Henrique
 */
public class pedidos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //connectBD.CreateTableX(Pedidos.class);
        Map<String, String[]> map = request.getParameterMap();
         System.out.println(" map.keySet() " + map.keySet());
        Map<String, String> mapaux = new HashMap<>();
        ArrayList<Map<String, String>> mapList = new ArrayList<>();
        pedidosDAO pedao = new pedidosDAO();
        
        int i = 1;
        Pedidos ped = null;
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        for(Entry e : map.entrySet()){
            String key = e.getKey().toString();
            
            String value = Arrays.toString(map.get(key));
            
            if(key.contains(String.valueOf(i))){
                System.out.println("key.substring(0,key.length()-1) " + key.substring(0,key.length()-1));
                mapaux.put(key.substring(0,key.length()-1), value);
                
                System.out.println("mapaux.entrySet() " + mapaux.entrySet());
            }
            
            if(!ifNumber(key)){
                 mapaux.put(key, value);
            }
            if(mapaux.size()>=4){
                mapList.add(mapaux);
                 System.out.println("mapList " + mapList.get(0).entrySet());
                 mapaux = new HashMap<>();
                 System.out.println("mapList " + mapList.get(0).entrySet());
                i++;
            }
            
        } 
        for(Map<String, String> aux : mapList){
                
            ped = new Pedidos(aux);
            System.out.println(ped.toString());
            pedao.incluir(ped);
            mapaux.clear();
            i++;
        }  
      
        System.out.println(pedao.listar());
    }
    
    private boolean ifNumber(String s){
      char[] chars = s.toCharArray();
     
      for(char c : chars){
         if(Character.isDigit(c)){
           return true;
         }
      }
      return false;
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
