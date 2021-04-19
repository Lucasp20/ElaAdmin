package br.com.ela_admin.dao;

import java.util.List;

import br.com.ela_admin.pojo.Usuario;

public interface UsuarioDao extends BaseDao {

	List<Usuario> pesquisarPorNome(String nome) throws Exception; 
}
