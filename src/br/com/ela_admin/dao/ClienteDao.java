/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ela_admin.dao;

import br.com.ela_admin.pojo.Cliente;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface ClienteDao extends BaseDao {

    List<Cliente> pesquisarPorNome(String nome) throws Exception;
}
