/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import util.ValidacaoException;

/**
 *
 * @author Jonatas
 */
@Entity
@Table(name = "TB_Aluno")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Aluno_Nome", length = 255, nullable = false)
    private String nome;
    @Column(name = "Data_Nascimento")
    private String nascimento;
    @Column(name = "Deficiencia")
    private String pcd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Aluno_Turma")
    private Turma turma;

    @Transient
    private List<Turma> turmas = new ArrayList<>();

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public Aluno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPcd() {
        return pcd;
    }

    public void setPcd(String pcd) {
        this.pcd = pcd;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", nome=" + nome + ", nascimento=" + nascimento + ", pcd=" + pcd + '}';
    }

    public void validar() throws ValidacaoException {
        if (this.nome == null || this.nome.equals("")) {
            throw new ValidacaoException("Nome precisa ser Preenchido");
        }
        if (this.nascimento == null || this.nascimento.equals("")) {
            throw new ValidacaoException("Data de nascimento precisa ser Preenchida");
        }
    }

}
