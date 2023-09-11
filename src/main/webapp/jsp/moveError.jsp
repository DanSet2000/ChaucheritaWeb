<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<script src="https://kit.fontawesome.com/ae4e5d458c.js"
	crossorigin="anonymous"></script>
<title>Error Movimiento</title>
</head>
<body>
	<header
		class="bg-yellow d-flex align-items-center flex-wrap justify-content-center p-3">
		<a href="../DashboardController?ruta=dashboard"
			class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
			<img class="" src="${pageContext.request.contextPath}/img/logo.png"
			alt="Imagen de una billetera">
			<h1 class="font px-3 c-darkgray">Mi Chaucherita Web</h1>
		</a>
		<div class="d-flex gap-2 align-items-center">
			<p class="m-0 fs-5 c-darkgray">Bienvenido:</p>
			<a href="" class="fs-5 c-darkgray nav-link fw-bold me-4">${sessionScope.nameUser}</a>
			<a href="./DashboardController?ruta=salir" class="nav-link"><i
				class="c-darkgray fa-solid fa-right-from-bracket fa-2xl i-hover"></i></a>
		</div>
	</header>
	<nav class="bg-darkgray" style="height: 46px;"></nav>
	<div
		class="modal modal-sheet position-static d-block bg-gray p-4 py-md-5"
		tabindex="-1" role="dialog" id="modalTour">
		<div class="modal-dialog" role="document">
			<div class="modal-content rounded-4 shadow">
				<div class="modal-body p-5">
					<h2 class="fw-bold mb-0">
						Error <i class="fa-solid text-danger fa-triangle-exclamation"></i>
					</h2>

					<ul class="d-grid gap-4 my-5 list-unstyled small">

						<li class="d-flex flex-column gap-3">

							<h2 class="mb-0">No se pudo realizar tu movimiento</h2> Saldo
							insuficiente

						</li>
					</ul>
					<a href="../DashboardController?ruta=dashboard" type="button"
						class="btn btn-hover bg-yellow fw-bold btn btn-lg mt-1 w-100"
						data-bs-dismiss="modal">Volver</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>