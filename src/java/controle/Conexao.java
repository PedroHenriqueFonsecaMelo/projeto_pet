/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Fatec
 */
public class Conexao {

    private static EntityManagerFactory emf;
    
    public Conexao() {
    }
    public static EntityManagerFactory getConexao (){
        if((emf == null) || (!emf.isOpen())){
            emf = Persistence.createEntityManagerFactory("WebApplication1PU");
        }
        return emf;
    }
    public static void closeConexao(){
        if(emf.isOpen()){
            emf.close();
        }
    }
}
