package br.com.ela_admin.controle;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ela_admin.dao.VeiculoDao;
import br.com.ela_admin.dao.VeiculoDaoImpl;
import br.com.ela_admin.pojo.Veiculo;


@WebServlet(name= "VeiculoControle", urlPatterns = {"/do_veiculo"})
public class VeiculoControle extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private VeiculoDao veiculoDao;
	private RequestDispatcher rd;
    
	protected void processarRequisicao() throws ServletException, IOException {
		String comando = request.getParameter("cmd");
		switch(comando) {
			case "pesquisarPorModelo":
				pesquisarModelo();
				break;
				
			case "excluir":
				remover();
				break;
				
			case "salvar":
				salvar();
				break;
				
			case "carregar_alterar":
				pesqusiarParaAlterar();
				break;
		}		
		rd.forward(request, response);
	}

	private void pesqusiarParaAlterar() {
		veiculoDao = new VeiculoDaoImpl();
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		try {
			Veiculo veiculo = (Veiculo) veiculoDao.pesquisarPorId(id);		
			request.setAttribute("veiculo", veiculo);
			rd = request.getRequestDispatcher("novoVeiculo.jsp");
		} catch (Exception e) {
			System.out.println("erro ao pesquisar por id" + e.getMessage());
		}
		
	}

	private void pesquisarModelo() {
		try {
			String modelo = request.getParameter("modelo");
			veiculoDao = new VeiculoDaoImpl();
			List<Veiculo> veiculos = veiculoDao.pesquisarPorModelo(modelo);
			request.setAttribute("veiculos", veiculos);
			rd = request.getRequestDispatcher("pesquisarVeiculo.jsp");
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar veiculo por nome" + e.getMessage());
			
		}
		
	}
	
	private void remover() {
		veiculoDao = new VeiculoDaoImpl();
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			veiculoDao.excluir(id);
			request.setAttribute("sucesso", "Veículo exluido com sucesso!");
			rd = request.getRequestDispatcher("pesquisarVeiculo.jsp");
		} catch (Exception e) {
			System.out.println("Erro ao excluir veículo" + e.getMessage());
		}
	}
	
	private void salvar() {
		
		Veiculo veiculo = new Veiculo();
		veiculoDao = new VeiculoDaoImpl();

		veiculo.setTipo(request.getParameter("tipo"));
		veiculo.setModelo(request.getParameter("modelo"));
		veiculo.setFabricante(request.getParameter("fabricante"));
		
		String moeda = request.getParameter("valor");
		moeda = moeda.replace(".", "").replace(",", ".");
		veiculo.setValor(new BigDecimal(moeda));
		
		try {
			String idTela = request.getParameter("id");
			if(idTela.equals("")) {
				veiculoDao.salvar(veiculo);
			}else {
				veiculo.setId(Integer.parseInt(idTela));
				veiculoDao.alterar(veiculo);
		}
			request.setAttribute("sucesso", "Veículo salvo com sucesso!");
		} catch (Exception e) {
			request.setAttribute("erro", "Não foi possivel cadastrar veículo");
			System.out.println("Erro ao salvar veiculo " + e.getMessage());
		}
		rd = request.getRequestDispatcher("novoVeiculo.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		processarRequisicao();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		processarRequisicao();
	
		
	}
	
	public String getServletInfo() {
		return "";
	}
}