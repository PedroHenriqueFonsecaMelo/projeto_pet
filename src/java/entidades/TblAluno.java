/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fatec
 */
@Entity
@Table(name = "tbl_aluno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAluno.findAll", query = "SELECT t FROM TblAluno t")
    , @NamedQuery(name = "TblAluno.findByMatricula", query = "SELECT t FROM TblAluno t WHERE t.matricula = :matricula")
    , @NamedQuery(name = "TblAluno.findByNome", query = "SELECT t FROM TblAluno t WHERE t.nome = :nome")
    , @NamedQuery(name = "TblAluno.findByDisciplina", query = "SELECT t FROM TblAluno t WHERE t.disciplina = :disciplina")
    , @NamedQuery(name = "TblAluno.findBySituacao", query = "SELECT t FROM TblAluno t WHERE t.situacao = :situacao")})
public class TblAluno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private Integer matricula;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "disciplina")
    private String disciplina;
    @Basic(optional = false)
    @Column(name = "situacao")
    private String situacao;

    public TblAluno() {
    }

    public TblAluno(Integer matricula) {
        this.matricula = matricula;
    }

    public TblAluno(Integer matricula, String nome, String disciplina, String situacao) {
        this.matricula = matricula;
        this.nome = nome;
        this.disciplina = disciplina;
        this.situacao = situacao;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAluno)) {
            return false;
        }
        TblAluno other = (TblAluno) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controle.TblAluno[ matricula=" + matricula + " ]";
    }
    
}
