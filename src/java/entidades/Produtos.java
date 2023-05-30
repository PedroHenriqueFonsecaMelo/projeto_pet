/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;
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
@Table(name = "PRODUTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p"),
    @NamedQuery(name = "Produtos.findByIdprodutos", query = "SELECT p FROM Produtos p WHERE p.idprodutos = :idprodutos"),
    @NamedQuery(name = "Produtos.findByDesc", query = "SELECT p FROM Produtos p WHERE p.desc = :desc"),
    @NamedQuery(name = "Produtos.findByPreco", query = "SELECT p FROM Produtos p WHERE p.preco = :preco")})
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPRODUTOS")
    private Integer idprodutos;
    @Column(name = "DESC")
    private String desc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECO")
    private BigDecimal preco;

    public Produtos() {
    }

    public Produtos(Map<String, String> aux) {
                for (Map.Entry<String, String> entry : aux.entrySet()) {
            String value = entry.getValue().substring(1,entry.getValue().length()-1);

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

    public Produtos(Integer idprodutos) {
        this.idprodutos = idprodutos;
    }

    public Integer getIdprodutos() {
        return idprodutos;
    }

    public void setIdprodutos(Integer idprodutos) {
        this.idprodutos = idprodutos;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprodutos != null ? idprodutos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.idprodutos == null && other.idprodutos != null) || (this.idprodutos != null && !this.idprodutos.equals(other.idprodutos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Produtos[ idprodutos=" + idprodutos + " ]";
    }
    
}
