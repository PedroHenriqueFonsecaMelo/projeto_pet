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
import java.util.Map.Entry;
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
@Table(name = "PEDIDOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p"),
    @NamedQuery(name = "Pedidos.findByIdpedidos", query = "SELECT p FROM Pedidos p WHERE p.idpedidos = :idpedidos"),
    @NamedQuery(name = "Pedidos.findByDesc", query = "SELECT p FROM Pedidos p WHERE p.desc = :desc"),
    @NamedQuery(name = "Pedidos.findByPreco", query = "SELECT p FROM Pedidos p WHERE p.preco = :preco"),
    @NamedQuery(name = "Pedidos.findByQuantidade", query = "SELECT p FROM Pedidos p WHERE p.quantidade = :quantidade"),
    @NamedQuery(name = "Pedidos.findByObs", query = "SELECT p FROM Pedidos p WHERE p.obs = :obs")})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPEDIDOS")
    private Integer idpedidos;
    @Column(name = "DESC")
    private String desc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECO")
    private BigDecimal preco;
    @Column(name = "QUANTIDADE")
    private Integer quantidade;
    @Column(name = "OBS")
    private String obs;

    public Pedidos() {
    }
    public Pedidos(String desc, BigDecimal preco, Integer quantidade, String obs) {
        this.desc = desc;
        this.preco = preco;
        this.quantidade = quantidade;
        this.obs = obs;
    }
    public Pedidos( Map<String, String> aux) {
       
        
        for (Entry<String, String> entry : aux.entrySet()) {
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
    public Pedidos(Integer idpedidos) {
        this.idpedidos = idpedidos;
    }

    public Integer getIdpedidos() {
        return idpedidos;
    }

    public void setIdpedidos(Integer idpedidos) {
        this.idpedidos = idpedidos;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedidos != null ? idpedidos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.idpedidos == null && other.idpedidos != null) || (this.idpedidos != null && !this.idpedidos.equals(other.idpedidos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pedidos{" + "idpedidos=" + idpedidos + ", desc=" + desc + ", preco=" + preco + ", quantidade=" + quantidade + ", obs=" + obs + '}';
    }

   
    
}
