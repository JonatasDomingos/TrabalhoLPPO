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

    public List<Aluno> pesquisar(Aluno aluno) {
        EntityManager em = Conexao.getEntityManager();

        StringBuilder sql = new StringBuilder("from Aluno a" + " where 1=1");

        if (aluno != null) {
            if (aluno.getId() != null) {
                sql.append("and a.id = :id");
            }
            if (aluno.getNome() != null && !aluno.getNome().equals("")) {
                sql.append("and a.nome like :Aluno_Nome");
            }
            if (aluno.getNascimento() != null && !aluno.getNascimento().equals("")) {
                sql.append("and a.nascimento like :Aluno_Nascimento");
            }
            if (aluno.getPcd() != null && !aluno.getPcd().equals("")) {
                sql.append("and a.pcd like :Deficiencia");
            }
            if (aluno.getTurma() != null && !aluno.getTurma().equals("")) {
                sql.append("and a.turma like :Aluno_Turma");
            }
        }

        Query query = em.createQuery(sql.toString());
        if (aluno != null) {
            if (aluno.getId() != null) {
                query.setParameter("id", aluno.getId());
            }
            if (aluno.getNome() != null && !aluno.getNome().equals("")) {
                query.setParameter("nome", "%" + aluno.getNome());
            }
            if (aluno.getNascimento() != null && !aluno.getNascimento().equals("")) {
                query.setParameter("nascimento", "%" + aluno.getNascimento());
            }
            if (aluno.getPcd() != null && !aluno.getPcd().equals("")) {
                query.setParameter("pcd", "%" + aluno.getPcd());
            }
            if (aluno.getTurma() != null && !aluno.getTurma().equals("")) {
                query.setParameter("turma", "%" + aluno.getTurma());
            }
        }
        return query.getResultList();

    }
}
