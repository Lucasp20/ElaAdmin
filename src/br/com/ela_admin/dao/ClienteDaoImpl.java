
package br.com.ela_admin.dao;




import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.com.ela_admin.pojo.Cliente;


public class ClienteDaoImpl implements ClienteDao{
    
    private Cliente cliente;
    private Connection conn;
    private PreparedStatement psmt;
    private ResultSet rs;

    public void salvar(Object object) throws Exception {
        cliente = (Cliente) object;
        String sql = "INSERT INTO cliente(nome, cpf, rg, telefone, email)"
        			+ "values(?, ?, ?, ?, ?)";
        try {
        	conn = FabricaConexao.abrirConexao();
        	psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        	psmt.setString(1,  cliente.getNome());
        	psmt.setString(2,  cliente.getCpf());
        	psmt.setString(3,  cliente.getRg());
        	psmt.setString(4,  cliente.getTelefone());
        	psmt.setString(5,  cliente.getEmail());
        	psmt.executeUpdate();
        	rs = psmt.getGeneratedKeys();
        	rs.next();
        	cliente.setId(rs.getInt(1));
            
        } catch (Exception e) {
        	System.out.println("Erro ao salvar cliente" + e.getMessage());
        }finally {
        	FabricaConexao.fecharConexao(conn, psmt, rs);
        }
    }

    public void alterar(Object object) throws Exception {
    	  Cliente cliente = (Cliente) object;
          String sql = "update cliente set nome = ?, cpf = ?, rg = ?, telefone = ?, email = ? where id = ?";
          try {
              conn = FabricaConexao.abrirConexao();
              psmt = conn.prepareStatement(sql);
              psmt.setString(1, cliente.getNome());
              psmt.setString(2, cliente.getCpf());
              psmt.setString(3, cliente.getRg());
              psmt.setString(4, cliente.getTelefone());
              psmt.setString(5, cliente.getEmail());
              psmt.setInt(6, cliente.getId());
              psmt.executeUpdate();
          } catch (Exception e) {
              System.out.println("Erro ao alterar cliente " + e.getMessage());
          } finally {
              FabricaConexao.fecharConexao(conn, psmt);
          }
      }

    
    public void excluir(Integer id) throws Exception {
    	try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement("delete from cliente where id = ?");
            psmt.setInt(1, id);
            psmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt);
        }
    }

    
    public Object pesquisarPorId(Integer id) throws Exception {
    	String consulta = "Select * from cliente where id = ?";
        Cliente cliente = null;
        try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement(consulta);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("rg"),
                        rs.getString("telefone"),
                        rs.getString("email")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar cliente por id " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt, rs);
        }
        return cliente;
    }

   
    public List<Cliente> pesquisarPorNome(String nome) throws Exception {
        List<Cliente> clientes = new LinkedList<>();
        String consulta = "Select * from cliente where nome like ?";
        Cliente cliente;
        try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement(consulta);
            psmt.setString(1, '%' + nome + '%');            
            rs = psmt.executeQuery();
            while (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("rg"),
                        rs.getString("telefone"),
                        rs.getString("email")
                       
                );
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar cliente por nome " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt, rs);
        }
        return clientes;
    }
    
}
