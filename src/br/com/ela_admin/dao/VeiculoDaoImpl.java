/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ela_admin.dao;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.com.ela_admin.pojo.Veiculo;

public class VeiculoDaoImpl implements VeiculoDao {

    private Connection conn;
    private PreparedStatement psmt;
    private ResultSet rs;

    public void salvar(Object object) throws Exception {
        Veiculo veiculo = (Veiculo) object;
        String sql = "INSERT INTO veiculo(tipo, modelo, fabricante, valor)"
                + " values(?, ?, ?, ?)";
        try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, veiculo.getTipo());
            psmt.setString(2, veiculo.getModelo());
            psmt.setString(3, veiculo.getFabricante());
            psmt.setBigDecimal(4, veiculo.getValor());
            psmt.executeUpdate();
            rs = psmt.getGeneratedKeys();
            rs.next();
            veiculo.setId(rs.getInt(1));
        } catch (Exception e) {
            System.out.println("Erro ao salvar veiculo " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt);
        }
    }

  
    public void alterar(Object object) throws Exception {
        Veiculo veiculo = (Veiculo) object;
        String sql = "update veiculo set tipo = ?, modelo = ?, fabricante = ?, valor = ? where id = ?";
        try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, veiculo.getTipo());
            psmt.setString(2, veiculo.getModelo());
            psmt.setString(3, veiculo.getFabricante());
            psmt.setBigDecimal(4, veiculo.getValor());
            psmt.setInt(5, veiculo.getId());
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao alterar veiculo " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt);
        }
    }

  
    public void excluir(Integer id) throws Exception {
        try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement("delete from veiculo where id = ?");
            psmt.setInt(1, id);
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao excluir veículo " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt);
        }
    }


    public Object pesquisarPorId(Integer id) throws Exception {
        String consulta = "Select * from veiculo where id = ?";
        Veiculo veiculo = null;
        try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement(consulta);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            if (rs.next()) {
                veiculo = new Veiculo(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("modelo"),
                        rs.getString("fabricante"),
                        rs.getBigDecimal("valor")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar veÃ­culo por id " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt, rs);
        }
        return veiculo;
    }


    public List<Veiculo> pesquisarPorModelo(String modelo) throws Exception {
        List<Veiculo> veiculos = new LinkedList<>();
        String consulta = "Select * from veiculo where modelo = ?";
        Veiculo veiculo;
        try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement(consulta);
            psmt.setString(1, modelo);            
            rs = psmt.executeQuery();
            while (rs.next()) {
                veiculo = new Veiculo(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("modelo"),
                        rs.getString("fabricante"),
                        new BigDecimal(rs.getString("valor")).setScale(2)//cuidado nessa conversao
                );
                veiculos.add(veiculo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar veículo por modelo " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt, rs);
        }
        return veiculos;
    }

}
