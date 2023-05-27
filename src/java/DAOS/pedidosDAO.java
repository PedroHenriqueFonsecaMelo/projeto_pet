/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import controle.Conexao;
import entidades.Pedidos;
import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Pedro Henrique
 */
public class pedidosDAO {
    EntityManager em;
    
    public pedidosDAO (){
        initConnection();
    }

    private void initConnection() {
        EntityManagerFactory emf;
        emf = Conexao.getConexao();
        em = emf.createEntityManager();
    }
    
    public void incluir(Pedidos obj) {
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

    public List<Pedidos> listar() {
        initConnection();
        return em.createNamedQuery("Pedidos.findAll").getResultList();
    }
    
    public void alterar(Pedidos obj) throws Exception {
        
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
    
    public void excluir(Pedidos obj) throws Exception {
        
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
