<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->

<!--[if gt IE 8]><!-->
<html class="no-js" lang="pt-BR">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Pesquisar</title>
<meta name="description" content="Ela Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
<link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
<link rel="stylesheet" href="assets/css/cs-skin-elastic.css">
<link rel="stylesheet" href="assets/css/style.css">
<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
<link
	href="https://cdn.jsdelivr.net/npm/chartist@0.11.0/dist/chartist.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/jqvmap@1.5.1/dist/jqvmap.min.css"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/weathericons@2.1.0/css/weather-icons.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/fullcalendar@3.9.0/dist/fullcalendar.min.css"
	rel="stylesheet" />


</head>

<body>

	<%@include file="menu.html"%>

	<!-- Right Panel -->
	<div id="right-panel" class="right-panel">
		<!-- Header-->
		<header id="header" class="header">
			<div class="top-left">
				<div class="navbar-header">
					<a class="navbar-brand" href="./"><img src="images/logo.png"
						alt="Logo"></a> <a class="navbar-brand hidden" href="./"><img
						src="images/logo2.png" alt="Logo"></a> <a id="menuToggle"
						class="menutoggle"><i class="fa fa-bars"></i></a>
				</div>
			</div>
			<div class="navbar-header" style="width: 100%; text-align: right;">
				<span class="navbar-brand">Olá! Seja Bem Vindo!</span>

			</div>

		</header>
		<!-- /#header -->

		<div class="content">
			<!-- Animated -->
			<div class="animated fadeIn">

				<!-- formulários - Content -->


				<!-- formulário -->
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<h1 class="box-title">Pesquisa de Cliente</h1>
							</div>
							
							<c:if test="${clientes != null and empty clientes}">
								<div class="alert alert-primary alerta_centralizado" role="alert">
									Não foi encontrado nenhum registro
								</div>
							</c:if>
							
							<c:if test="${sucesso != null}">
								<div class="alert alert-primary alerta_centralizado" role="alert">
									${sucesso}
								</div>
							</c:if>	
							
							
							<div class="card-body">
								<form id="form_pesquisa" method="post" action="do_cliente?cmd=pesquisarPorNome">
									<div class="form-group row">

										<label for="nome" class="col-sm-2 col-form-label">Nome</label>

										<div class="col-sm-6">
											<input type="text" name="nome" class="form-control"
												id="nome" placeholder="Digite o Nome">
										</div>
										<button type="submit" class="btn btn-primary mb-2">Pesquisar</button>
									</div>

								</form>

								<c:if test="${not empty clientes}">
										<div class="form-group row">
											<table class="table table-striped table-responsive-md">
												<thead>
													<tr>
														<th scope="col">#</th>
														<th scope="col">Nome</th>
														<th scope="col">Cpf</th>
														<th scope="col">Rg</th>
														<th scope="col">Telefone</th>
														<th scope="col">Email</th>
														<th scope="col">Ação</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="cliente" items="${clientes}" varStatus="linha">
														<tr>
															<th scope="row">${linha.count}</th>
															<td>${cliente.nome}</td>
															<td>${cliente.cpf}</td>
															<td>${cliente.rg}</td>
															<td>${cliente.telefone}</td>
															<td>${cliente.email}</td>
															
															<td>
																<a href="do_cliente?cmd=carregar_alterar&id=${cliente.id}" title="Alterar"><i class="fa fa-pencil fa-fw" aria-hidden="true"></i></a>
															&nbsp;
															<a href="do_cliente?cmd=excluir&id=${cliente.id}" title="Remover"><i class="fa fa-trash fa-fw" aria-hidden="true"></i></a>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>	
										</div>
									</c:if>

							</div>
						</div>
					</div>
					<!-- /# column -->
				</div>
				<!-- fim formulário -->

			</div>
			<!-- .animated -->
		</div>
		<!-- /.content -->


		<div class="clearfix"></div>


		<!-- Footer -->
		<footer class="site-footer">
			<div class="footer-inner bg-white">
				<div class="row">
					<div class="col-sm-6">Copyright &copy; 2018 Ela Admin</div>
					<div class="col-sm-6 text-right">
						Designed by <a href="https://colorlib.com">Colorlib</a>
					</div>
				</div>
			</div>
		</footer>
		<!-- /.site-footer -->
	</div>
	<!-- /#right-panel -->

	<!-- Scripts -->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
	<script src="assets/js/main.js"></script>

	<!--  Chart js -->
	<script
		src="https://cdn.jsdelivr.net/npm/chart.js@2.7.3/dist/Chart.bundle.min.js"></script>

	<!--Chartist Chart-->
	<script
		src="https://cdn.jsdelivr.net/npm/chartist@0.11.0/dist/chartist.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/chartist-plugin-legend@0.6.2/chartist-plugin-legend.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery.flot@0.8.3/jquery.flot.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/flot-pie@1.0.0/src/jquery.flot.pie.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/flot-spline@0.0.1/js/jquery.flot.spline.min.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/simpleweather@3.1.0/jquery.simpleWeather.min.js"></script>
	<script src="assets/js/init/weather-init.js"></script>

	<script src="https://cdn.jsdelivr.net/npm/moment@2.22.2/moment.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/fullcalendar@3.9.0/dist/fullcalendar.min.js"></script>
	<script src="assets/js/init/fullcalendar-init.js"></script>

	<!--Local Stuff-->
	<script>
        jQuery(document).ready(function($) {
            "use strict";
         }
            
</script>
