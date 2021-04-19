package br.com.ela_admin.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ela_admin.dao.UsuarioDao;
import br.com.ela_admin.dao.UsuarioDaoImpl;
import br.com.ela_admin.pojo.Usuario;

@WebServlet(name = "UsuarioControle", urlPatterns = {"/do_usuario"})
public class UsuarioControle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private UsuarioDao usuarioDao;
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
		usuarioDao = new UsuarioDaoImpl();
		Integer id = Integer.parseInt(request.getParameter("id"));

		try {
			Usuario usuario = (Usuario) usuarioDao.pesquisarPorId(id);
			request.setAttribute("usuario", usuario);
			rd = request.getRequestDispatcher("novoUsuario.jsp");

		} catch (Exception e) {
			System.out.println("Erro ao pesquisar por id" + e.getMessage());
		}
	}

	private void pesquisarPorNome() {
		try {
			String nome = request.getParameter("nome");
			usuarioDao = new UsuarioDaoImpl();
			List<Usuario> usuarios = usuarioDao.pesquisarPorNome(nome);
			request.setAttribute("usuarios", usuarios);
			rd = request.getRequestDispatcher("pesquisarUsuario.jsp");
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar usuário por nome " + e.getMessage());
		}
	}

	private void remover() {
		usuarioDao = new UsuarioDaoImpl();
		Integer id = Integer.parseInt(request.getParameter("id"));

		try {

			usuarioDao.excluir(id);
			request.setAttribute("sucesso", "Usuário excluido com sucesso!");
			rd = request.getRequestDispatcher("pesquisarUsuario.jsp");

		} catch (Exception e) {
			System.out.println("Erro ao excluir usuario" + e.getMessage());
		}
	}

	private void salvar() {
		Usuario usuario = new Usuario();
		usuarioDao = new UsuarioDaoImpl();

		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setNome(request.getParameter("nome"));
		usuario.setUltimoLogin(request.getParameter("ultimologin"));
		
		try {
			String idTela = request.getParameter("id");
				if(idTela.equals("")) {
					usuarioDao.salvar(usuario);
				}else {
					usuario.setId(Integer.parseInt(idTela));
					usuarioDao.alterar(usuario);
				}
					request.setAttribute("sucesso", "Usuario salvo com sucesso!");
		} catch (Exception e) {
			request.setAttribute("erro", "Não foi possivel cadastrar usuário");
			System.out.println("Erro ao salvar usuário " + e.getMessage());
		}
		rd = request.getRequestDispatcher("novoUsuario.jsp");
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
