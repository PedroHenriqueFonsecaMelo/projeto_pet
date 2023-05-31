/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pedro Henrique
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
    @NamedQuery(name = "Usuario.findBySexo", query = "SELECT u FROM Usuario u WHERE u.sexo = :sexo"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuario.findBySenhaNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome AND u.senha = :senha")
})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSUARIO")
    private Integer idusuario;
    @Column(name = "SENHA")
    private String senha;
    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "TELEFONE")
    private String telefone;
 
    @Column(name = "NOME")
    private String nome;
   //@Column(name = "TELEFONE")
    //private String telefone;

    /*>>>>>>> 5d549e221bc0b10b579cb417f4b3726ab4bb333e*/

    public Usuario() {
    }

   
    
    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    
    public Usuario(Map<String, String[]> entrada) {
        for (Map.Entry<String, String[]> entry : entrada.entrySet()) {
            String charentrada = Arrays.toString(entry.getValue());
            
            String value = charentrada.substring(1,charentrada.length()-1);

            Field field;
            try {
               
                field = this.getClass().getDeclaredField(entry.getKey().toLowerCase());
                
                if (field != null) {
                    switch (field.getType().getSimpleName()) {
                        case "String":
                            field.set(this, value);
                            break;
                        case "int":
                        case "Integer":
                            field.set(this, Integer.parseInt(value));
                            break;
                        case "float":
                            field.set(this, Float.parseFloat(value));
                        case "BigDecimal":
                            field.set(this,BigDecimal.valueOf(Double.valueOf(value)));
                        default:
                            break;
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.idusuario);
        hash = 47 * hash + Objects.hashCode(this.senha);
        hash = 47 * hash + Objects.hashCode(this.sexo);
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.telefone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.idusuario, other.idusuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{idusuario=").append(idusuario);

        sb.append(", senha=").append(senha);
        sb.append(", sexo=").append(sexo);
        sb.append(", nome=").append(nome);
        sb.append(", telefone=").append(telefone);
        sb.append('}');
        return sb.toString();
    }

    
}
