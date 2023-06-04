/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import controle.Conexao;
import entidades.Usuario;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Pedro Henrique
 */
public class usuarioDAO {
    EntityManager em;
    
    public usuarioDAO (){
        initConnection();
    }

    private void initConnection() {
        EntityManagerFactory emf;
        emf = Conexao.getConexao();
        em = emf.createEntityManager();
    }
    
    public void incluir(Usuario obj) { 
        initConnection();
        
        EntityTransaction tx = em.getTransaction();
        try {
           
            tx.begin();
            em.persist(obj);
            tx.commit();
            fechaEmf();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            if(em.isOpen()){
                em.close();
            }
            
        }
        
    }

    public List<Usuario> listar() {
        initConnection();
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }
    
    public List<Usuario> listar(Map<String, String[]> aux) {
        
        initConnection();
        String senha = Arrays.toString(aux.get("senha"));
        String nome =  Arrays.toString(aux.get("nome"));
       if(senha.contains("[") && senha.contains("]")){
           senha = senha.substring(1, senha.length()-1);
       }
       if(nome.contains("[") && nome.contains("]")){
           nome = nome.substring(1, nome.length()-1);
       }
       
       System.out.println("nome"+"senha"+nome+senha);
        List ListOfInterventions = em.createNamedQuery("Usuario.findBySenhaNome")
            .setParameter("senha",senha)
            .setParameter("nome",nome).getResultList();
        
        return ListOfInterventions;
    }
    
    public void alterar(Usuario obj) throws Exception {
        
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    public void excluir(Usuario obj) throws Exception {
        
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void fechaEmf() {
        Conexao.closeConexao();
    }
}
