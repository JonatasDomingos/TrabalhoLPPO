/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import util.ValidacaoException;

/**
 *
 * @author Jonatas
 */
@Entity
@Table(name = "TB_Turma")
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nome_turma", length = 255, nullable = false)
    private String nome;
    @Column(name = "Grau_escolaridade", length = 255, nullable = false)
    private String ensino;
    private String ano;
    @Column(name = "Maximo_alunos", length = 255, nullable = false)
    private String totalAlunos;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "turma")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Aluno> alunos;

    public Turma() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnsino() {
        return ensino;
    }

    public void setEnsino(String ensino) {
        this.ensino = ensino;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTotalAlunos() {
        return totalAlunos;
    }

    public void setTotalAlunos(String totalAlunos) {
        this.totalAlunos = totalAlunos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    
   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return id + " - " + nome;

    }
    
    public void validar() throws ValidacaoException{
        if(this.nome == null || this.nome.equals(""))
            throw new ValidacaoException("Nome precisa ser Informado");
        if(this.ensino == null || this.ensino.equals(""))
            throw new ValidacaoException("Grau de escolaridade precisa ser Informado");
        if(this.ano == null || this.ano.equals(""))
            throw new ValidacaoException("Idade da turma precisa ser Informada");
        
        
        } 
    
    
    
}
