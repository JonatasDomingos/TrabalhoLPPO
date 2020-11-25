/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dominio.Aluno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jonatas
 */
public class AlunoDao {

    public void salvarAtualizar(Aluno aluno) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        if (aluno.getId() != null) {
            aluno = em.merge(aluno);
        }
        em.persist(aluno);
        em.getTransaction().commit();
        em.close();
    }

    public void RemoverAtualizar(Aluno aluno) {
        EntityManager em = Conexao.getEntityManager();
        em.getTransaction().begin();
        aluno = em.merge(aluno);
        em.remove(aluno);
        em.getTransaction().commit();
        em.close();
    }

    public List<Aluno> pesquisar(String idFiltro) {
        EntityManager em = Conexao.getEntityManager();

        StringBuilder sql = new StringBuilder("from Aluno a where 1=1");

        if (idFiltro != null && !idFiltro.equals("")) {
            sql.append("and a.id = :id");
        }

        Query query = em.createQuery(sql.toString());

        if (idFiltro != null && !idFiltro.equals("")) {
            query.setParameter("id", Long.parseLong(idFiltro));
        }

        return query.getResultList();

    }
}
