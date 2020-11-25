/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dominio.Turma;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jonatas
 */
public class TurmaDao {
    
    
    public void salvarTurma(Turma turma){
        EntityManager em = Conexao.getEntityManager();  
        em.getTransaction().begin();
           if(turma.getId()!=null){
               turma = em.merge(turma);
           }
               em.persist(turma);
               em.getTransaction().commit();
               em.close();
    }
        
    public void removerTurma(Turma turma){
            EntityManager em = Conexao.getEntityManager();    
            em.getTransaction().begin();
            em.remove(turma);
            em.getTransaction().commit();
            em.close();
    }
    
    public List<Turma> pesquisar(Turma turma){
        EntityManager em = Conexao.getEntityManager();
            
        StringBuilder sql = new StringBuilder("from Turma t" + " where 1=1");
        
            if(turma.getId()!= null){
                sql.append("and t.id = :id");
            }
            if(turma.getNome() !=null && !turma.getNome().equals("")){
                sql.append("and t.nome like :Nome_turma");
            }
            if(turma.getEnsino()!= null && !turma.getEnsino().equals("")){
                sql.append("and t.ensino like :Grau_escolaridade");
            }
            if(turma.getAno()!= null && !turma.getAno().equals("")){
                sql.append("and t.ano like :ano");
            }
            if(turma.getTotalAlunos()!= null && !turma.getTotalAlunos().equals("")){
                sql.append("and t.totalAlunos like :Maximo_alunos");
            }
            
            
            Query query = em.createQuery(sql.toString());
            
                if(turma.getId()!=null){
                    query.setParameter("id", turma.getId());
                }
                if(turma.getNome()!= null && !turma.getNome().equals("")){
                    query.setParameter("nome", "%" + turma.getNome());
                }
                if(turma.getEnsino()!= null && !turma.getEnsino().equals("")){
                    query.setParameter("ensino", "%" + turma.getEnsino());
                }
                if(turma.getAno()!= null && !turma.getAno().equals("")){
                    query.setParameter("ano", "%" + turma.getAno());
                }
                if(turma.getTotalAlunos()!=null && !turma.getTotalAlunos().equals("")){
                    query.setParameter("totalAlunos", "%" + turma.getTotalAlunos());
                }
           
        return query.getResultList();
        
    }
}
