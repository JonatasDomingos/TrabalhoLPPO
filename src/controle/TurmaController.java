/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.TurmaDao;
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
public class TurmaController {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private Turma turmaDigitado;
    private Turma turmaSelecionado;
    private List<Turma> turmaTabela;
    private TurmaDao turmaDao;

    public List<Turma> getTurmaTabela() {
        return turmaTabela;
    }

    public void setTurmaTabela(List<Turma> turmaTabela) {
        this.turmaTabela = turmaTabela;
    }

    public Turma getTurmaDigitado() {
        return turmaDigitado;
    }

    public void setTurmaDigitado(Turma turmaDigitado) {
        Turma oldTurmaDigitado = this.turmaDigitado;
        this.turmaDigitado = turmaDigitado;
        propertyChangeSupport.firePropertyChange("turmaDigitado", oldTurmaDigitado, turmaDigitado);
    }

    public Turma getTurmaSelecionado() {
        return turmaSelecionado;
    }

    public void setTurmaSelecionado(Turma turmaSelecionado) {
        if (this.turmaSelecionado != null) {
            setTurmaDigitado(turmaSelecionado);
        }
        this.turmaSelecionado = turmaSelecionado;
    }

    public TurmaController() {
        turmaDao = new TurmaDao();
        turmaTabela = ObservableCollections.observableList(new ArrayList<Turma>());
        novo();
        pesquisar();

    }

    public void pesquisar() {
        turmaTabela.clear();
        turmaTabela.addAll(turmaDao.pesquisar(turmaDigitado));

    }

    public void salvar()  throws ValidacaoException, RemoteException {
        turmaDigitado.validar();
        turmaDao.salvarTurma(turmaDigitado);
        novo();
        pesquisar();
    }

    public final void novo() {
//         Turma turma = new  Turma();
//         turma.setTotalAlunos("1");
//         turma.setEnsino("Fundamental");
        setTurmaDigitado(new Turma());
    }

    public void excluir() {
        turmaDao.removerTurma(turmaDigitado);
        novo();
        pesquisar();
    }

    public void addPropertyChangeListener(PropertyChangeSupport e) {
        propertyChangeSupport.addPropertyChangeListener((PropertyChangeListener) e);
    }

    public void removePropertyChangeListener(PropertyChangeSupport e) {
        propertyChangeSupport.removePropertyChangeListener((PropertyChangeListener) e);
    }

}
