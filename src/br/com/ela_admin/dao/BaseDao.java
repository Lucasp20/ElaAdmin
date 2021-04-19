/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ela_admin.dao;


/**
 *
 * @author Aluno
 */
public interface BaseDao {

    void salvar(Object object) throws Exception;

    void alterar(Object object) throws Exception;

    void excluir(Integer id) throws Exception;

    Object pesquisarPorId(Integer id) throws Exception;
    
}
