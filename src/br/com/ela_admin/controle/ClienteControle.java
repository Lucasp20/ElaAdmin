package br.com.ela_admin.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ela_admin.dao.ClienteDao;
import br.com.ela_admin.dao.ClienteDaoImpl;
import br.com.ela_admin.pojo.Cliente;

@WebServlet(name = "ClienteControle", urlPatterns = {"/do_cliente"})
public class ClienteControle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ClienteDao clienteDao;
	private RequestDispatcher rd;

	protected void processarRequisicao() throws ServletException, IOException {
		String comando = request.getParameter("cmd");
		switch (comando) {
		case "pesquisarPorNome":
			pesquisarPorNome();
			break;
			
		case "salvar":
			salvar();
			break;

		case "excluir":
			remover();
			break;

		case "carregar_alterar":
			pesqusiarParaAlterar();
			break;
		}
		rd.forward(request, response);

	}

	private void pesqusiarParaAlterar() {
		clienteDao = new ClienteDaoImpl();
		Integer id = Integer.parseInt(request.getParameter("id"));

		try {
			Cliente cliente = (Cliente) clienteDao.pesquisarPorId(id);
			request.setAttribute("cliente", cliente);
			rd = request.getRequestDispatcher("novoCliente.jsp");
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar por id" + e.getMessage());
		}

	}
	
	private void pesquisarPorNome() {
		try {
			String nome = request.getParameter("nome");
			clienteDao = new ClienteDaoImpl();
			List<Cliente> clientes = clienteDao.pesquisarPorNome(nome);
			request.setAttribute("clientes", clientes);
			rd = request.getRequestDispatcher("pesquisarCliente.jsp");
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar cliente por nome " + e.getMessage());
		}

	}

	private void remover() {
		clienteDao = new ClienteDaoImpl();
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		try {
			clienteDao.excluir(id);
			request.setAttribute("sucesso", "Cliente excluido com sucesso!");
			rd = request.getRequestDispatcher("pesquisarCliente.jsp");
			
		} catch (Exception e) {
			System.out.println("Erro ao excluir cliente" + e.getMessage());
		}
	}

	private void salvar() {
		
		Cliente cliente = new Cliente();
		clienteDao = new ClienteDaoImpl();
		
		cliente.setNome(request.getParameter("nome"));
		cliente.setCpf(request.getParameter("cpf"));
		cliente.setRg(request.getParameter("rg"));
		cliente.setTelefone(request.getParameter("telefone"));
		cliente.setEmail(request.getParameter("email"));
	
		try {
			String idTela = request.getParameter("id");
			if(idTela.equals("")) {
				clienteDao.salvar(cliente);				
			}else {
				cliente.setId(Integer.parseInt(idTela));
				clienteDao.alterar(cliente);
			}
				request.setAttribute("sucesso", "Cliente salvo com sucesso!");			
		} catch (Exception e) {
			request.setAttribute("erro", "Não foi possivel cadastrar cliente");
			System.out.println("Erro ao salvar cliente " + e.getMessage());
		}
		rd = request.getRequestDispatcher("novoCliente.jsp");
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
		processarRequisicao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.request = request;
		this.response = response;
		processarRequisicao();

	}

	public String getServletInfo() {
		return "";
	}
}