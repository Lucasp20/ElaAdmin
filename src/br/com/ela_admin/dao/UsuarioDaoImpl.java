package br.com.ela_admin.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.com.ela_admin.pojo.Usuario;


public class UsuarioDaoImpl implements UsuarioDao{
    
    private Usuario usuario;
    private Connection conn;
    private PreparedStatement psmt;
    private ResultSet rs;

    public void salvar(Object object) throws Exception {
        usuario = (Usuario) object;
        String sql = "INSERT INTO usuario(login, senha, nome, ultimologin)"
        			+ "values(?, ?, ?, ?)";
        try {
        	conn = FabricaConexao.abrirConexao();
        	psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        	psmt.setString(1, usuario.getLogin());
        	psmt.setString(2, usuario.getSenha());
        	psmt.setString(3, usuario.getNome());
        	psmt.setString(4, usuario.getUltimoLogin()); 
        	psmt.executeUpdate();
        	rs = psmt.getGeneratedKeys();
        	rs.next();
        	usuario.setId(rs.getInt(1));
            
        } catch (Exception e) {
        	System.out.println("Erro ao salvar usuário" + e.getMessage());
        }finally {
        	FabricaConexao.fecharConexao(conn, psmt, rs);
        }
    }

    public void alterar(Object object) throws Exception {
    	  Usuario usuario = (Usuario) object;
          String sql = "update usuario set login = ?, senha = ?, nome = ?, ultimologin = ? where id = ?";
          try {
              conn = FabricaConexao.abrirConexao();
              psmt = conn.prepareStatement(sql);
              psmt.setString(1, usuario.getLogin());
              psmt.setString(2, usuario.getSenha());
              psmt.setString(3, usuario.getNome());
              psmt.setString(4, usuario.getUltimoLogin());
              psmt.setInt(5, usuario.getId());
              psmt.executeUpdate();
          } catch (Exception e) {
              System.out.println("Erro ao alterar usuario " + e.getMessage());
          } finally {
              FabricaConexao.fecharConexao(conn, psmt);
          }
      }

    public void excluir(Integer id) throws Exception {
    	
    	try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement("delete from usuario where id = ?");
            psmt.setInt(1, id);
            psmt.executeLargeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao excluir usuario " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt);
        }
    }
    
    public Object pesquisarPorId(Integer id) throws Exception {
    	String consulta = "Select * from usuario where id = ?";
        Usuario usuario = null;
        try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement(consulta);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("nome"),
                        rs.getString("ultimologin")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar usuario por id " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt, rs);
        }
        return usuario;
    }

   
    public List<Usuario> pesquisarPorNome(String nome) throws Exception {
        List<Usuario> usuarios = new LinkedList<>() ;
        String consulta = "Select * from usuario where nome like ?";
        Usuario usuario;
        try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement(consulta);
            psmt.setString(1, "%" + nome + "%");            
            rs = psmt.executeQuery();
            while (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("nome"),
                        rs.getString("ultimologin")
                       
                );
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar usuario por nome " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conn, psmt, rs);
        }
        return usuarios;
    }
    
}
