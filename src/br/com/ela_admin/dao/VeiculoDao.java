/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ela_admin.dao;


import java.util.List;

import br.com.ela_admin.pojo.Veiculo;


public interface VeiculoDao extends BaseDao {

    List<Veiculo> pesquisarPorModelo(String modelo) throws Exception;
}
