<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="pt-BR"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Template</title>
    <meta name="description" content="Ela Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
    <link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
    <link rel="stylesheet" href="assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
    <link href="https://cdn.jsdelivr.net/npm/chartist@0.11.0/dist/chartist.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/jqvmap@1.5.1/dist/jqvmap.min.css" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/weathericons@2.1.0/css/weather-icons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@3.9.0/dist/fullcalendar.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>

  
</head>

<body>

	<%@include file="menu.html"%>

    <!-- Right Panel -->
    <div id="right-panel" class="right-panel">
        <!-- Header-->
        <header id="header" class="header">
            <div class="top-left">
                <div class="navbar-header">
                    <a class="navbar-brand" href="./"><img src="images/logo.png" alt="Logo"></a>
                    <a class="navbar-brand hidden" href="./"><img src="images/logo2.png" alt="Logo"></a>
                    <a id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
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
                                <h1 class="box-title">Gerenciar Veículo</h1>
                            </div>                        
                            
                            <c:if test="${sucesso != null}">
								<div class="alert alert-primary alerta_centralizado" role="alert">
									${sucesso}
								</div>
							</c:if>
							
							<c:if test="${erro != null}">
								<div class="alert alert-danger alerta_centralizado" role="alert">
									${erro}
								</div>
							</c:if>
                                
                            <div class="card-body">
                            
                            <form id="form_pesquisa" method="post" action="do_veiculo?cmd=salvar">
                            	<input type="hidden" name="id" value="${veiculo.id}" />
                         
                           		<div class="form-group row">
    							  <label for="tipo" class="col-sm-2 col-form-label">Tipo</label>
    							  <div class="col-sm-2">
     								 	<select id="tipo" name="tipo" class="form-control" required="required">
     								 		 <c:if test="${veiculo == null} }"> 
       									 		 <option selected value="">Escolher</option>
       									 	 </c:if>
       									 	 <c:if test="${veiculo == null} }"> 
       									 		 <option selected value="${veiculo.tipo}">${veiculo.tipo}</option>
       									 	 </c:if>
     										 <option value="Automóvel">Automóvel</option>
     									 	 <option value="Moto">Moto</option>
     									 	 <option value="Caminhão">Caminhão</option>
     										 <option value="Ônibus">Ônibus</option>
     								 </select>
     								</div>
   								 </div>
    
							 <div class="form-group row">
   									 <label for="modelo" class="col-sm-2 col-form-label">Modelo:</label>
   									 <div class="col-sm-10">
     									 <input type="text" class="form-control" id="modelo" value="${veiculo.modelo}"
     									 		placeholder="Digite o Modelo" name="modelo">
  									</div>
 							 </div>
 								 <div class="form-group row">
    								<label for="fabricante" class="col-sm-2 col-form-label">Fabricante:</label>
    						<div class="col-sm-10">
     								 <input type="text" class="form-control" id="fabricante" name="fabricante" value="${veiculo.fabricante}"
     								 		placeholder="Digite o Fabricante">
    							</div>
  							</div>
  							
  							 <div class="form-group row">
    								<label for="valor" class="col-sm-2 col-form-label">Valor:</label>
    						<div class="col-sm-10">
     								 <input type="text" class="form-control" id="valor" name="valor" value="${veiculo.valor}"								 
     								 onkeypress="$(this).mask('R$ #.##0,00', {reverse: true});"  placeholder="Digite o Valor" />
    							</div>
  							</div>
								
								<button type="submit" class="btn btn-primary">Salvar</button>
							
							</form>
                                
                                
                            </div>
                        </div>
                    </div><!-- /# column -->
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
                    <div class="col-sm-6">
                        Copyright &copy; 2018 Ela Admin
                    </div>
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
    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
    <script src="assets/js/main.js"></script>

    <!--  Chart js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.7.3/dist/Chart.bundle.min.js"></script>

    <!--Chartist Chart-->
    <script src="https://cdn.jsdelivr.net/npm/chartist@0.11.0/dist/chartist.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartist-plugin-legend@0.6.2/chartist-plugin-legend.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/jquery.flot@0.8.3/jquery.flot.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/flot-pie@1.0.0/src/jquery.flot.pie.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/flot-spline@0.0.1/js/jquery.flot.spline.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/simpleweather@3.1.0/jquery.simpleWeather.min.js"></script>
    <script src="assets/js/init/weather-init.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/moment@2.22.2/moment.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@3.9.0/dist/fullcalendar.min.js"></script>
    <script src="assets/js/init/fullcalendar-init.js"></script>

    <!--Local Stuff-->
    <script>
        jQuery(document).ready(function($) {
            "use strict";
         }
            
    </script>
</body>
</html>