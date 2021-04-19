
package br.com.ela_admin.pojo;

import java.math.BigDecimal;
import java.util.Objects;

public class Veiculo {
    private Integer id;
    private String tipo;
    private String modelo;
    private String fabricante;
    private BigDecimal valor;

    public Veiculo() {
    }

    public Veiculo(Integer id, String tipo, String modelo, String fabricante, BigDecimal valor) {
        this.id = id;
        this.tipo = tipo;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Veiculo other = (Veiculo) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
