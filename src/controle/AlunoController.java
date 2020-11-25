/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AlunoDao;
import dao.TurmaDao;
import dominio.Aluno;
import dominio.Turma;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import util.ValidacaoException;

/**
 *
 * @author Jonatas
 */
public class AlunoController {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private Aluno alunoDigitado;
    private Aluno alunoSelecionado;
    private List<Aluno> alunoTabela;
    private AlunoDao alunoDao;

    public List<Aluno> getAlunoTabela() {
        return alunoTabela;
    }

    public void setAlunoTabela(List<Aluno> alunoTabela) {
        this.alunoTabela = alunoTabela;
    }

    public Aluno getAlunoDigitado() {
        return alunoDigitado;
    }

    public void setAlunoDigitado(Aluno alunoDigitado) {
        Aluno oldAlunoDigitado = this.alunoDigitado;
        this.alunoDigitado = alunoDigitado;
        propertyChangeSupport.firePropertyChange("alunoDigitado", oldAlunoDigitado, alunoDigitado);
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        if (this.alunoSelecionado != null) {
            setAlunoDigitado(alunoSelecionado);
        }
        this.alunoSelecionado = alunoSelecionado;
    }

    public AlunoController() {
        alunoDao = new AlunoDao();
        alunoTabela = ObservableCollections.observableList(new ArrayList<Aluno>());
        novo();
        pesquisar(null);

    }

    public void pesquisar(String idFiltro) {
        alunoTabela.clear();
        alunoTabela.addAll(alunoDao.pesquisar(idFiltro));

    }

    public void salvar() throws ValidacaoException, RemoteException {
        alunoDigitado.validar();
        Turma turmaCorrigida = new Turma();
        String idTurma = this.alunoDigitado.getTurma().toString().split("-")[0].trim();
        turmaCorrigida.setId(Long.parseLong(idTurma));
        alunoDigitado.setTurma(turmaCorrigida);
        alunoDao.salvarAtualizar(alunoDigitado);
        novo();
        pesquisar(null);
    }

    public final void novo() {
        TurmaDao turmaDao = new TurmaDao();
        Aluno aluno = new Aluno();
        aluno.setTurmas(turmaDao.pesquisar(new Turma()));
        setAlunoDigitado(aluno);
    }

    public void excluir() {
        alunoDao.RemoverAtualizar(alunoSelecionado);
        novo();
        pesquisar(null);
    }

    public void addPropertyChangeListener(PropertyChangeSupport e) {
        propertyChangeSupport.addPropertyChangeListener((PropertyChangeListener) e);
    }

    public void removePropertyChangeListener(PropertyChangeSupport e) {
        propertyChangeSupport.removePropertyChangeListener((PropertyChangeListener) e);
    }

}
