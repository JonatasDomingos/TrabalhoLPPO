/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jonatas
 */
public class Conexao {
    
   public static EntityManagerFactory emf;

    public static Conexao conexao;

    public Conexao() {
        emf = Persistence.createEntityManagerFactory("TrabalhoLPPOPU");
    }

    public synchronized static EntityManager getEntityManager() {
        if (conexao == null) {
            conexao = new Conexao();
        }
        return emf.createEntityManager();
    }
}