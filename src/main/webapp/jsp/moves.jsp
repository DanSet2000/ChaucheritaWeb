<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

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
<title>Movimientos</title>
</head>

<body class="bg-gray">
	<header
		class="bg-yellow d-flex align-items-center flex-wrap justify-content-center p-3">
		<a href="DashboardController?ruta=dashboard"
			class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
			<img class="" src="${pageContext.request.contextPath}/img/logo.png"
			alt="Imagen de una billetera">
			<h1 class="font px-3 c-darkgray">Mi Chaucherita Web</h1>
		</a>
		<div class="d-flex gap-2 align-items-center">
            <p class="m-0 fs-5 c-darkgray">Bienvenido: </p>
            <a href="" class="fs-5 c-darkgray nav-link fw-bold me-4">${sessionScope.nameUser}</a>
            <a href="./DashboardController?ruta=salir" class="nav-link"><i
                    class="c-darkgray fa-solid fa-right-from-bracket fa-2xl i-hover"></i></a>
        </div>
	</header>
	<nav class="bg-darkgray" style="height: 46px;"></nav>



	<div class="container py-5">
		<div class="card mb-4 rounded-3 shadow-sm">
			<div
				class="card-header bg-dark-subtle d-flex justify-content-center py-3">

				<c:choose>
					<c:when test="${verTipo eq 'Todas'}">
						<form action="DashboardController?ruta=verPorTodosMovimientos"
							method="POST" class="text-center">
							<input type="date"
								class="text-center bg-dark-subtle border-0 form-control font-s-30"
								value="${date}" name="fecha" id="fecha">
							<h1 class=" my-3">${accountName}</h1>


							<button type="submit" class="btn btn-hover bg-yellow fw-bold">Actualizar</button>
						</form>
					</c:when>
					<c:when test="${verTipo eq 'Cuenta'}">
						<form
							action="DashboardController?ruta=verPorCuenta&cuentaID=${cuentaID}"
							method="POST" class="text-center">
							<input type="date"
								class="text-center bg-dark-subtle border-0 form-control font-s-30"
								value="${date}" name="fecha" id="fecha">
							<h1 class=" my-3">${accountName}</h1>


							<button type="submit" class="btn btn-hover bg-yellow fw-bold">Actualizar</button>
						</form>
					</c:when>
					<c:when test="${verTipo eq 'Cat'}">
						<form
							action="DashboardController?ruta=verPorCategoria&catID=${catID}"
							method="POST" class="text-center">
							<input type="date"
								class="text-center bg-dark-subtle border-0 form-control font-s-30"
								value="${date}" name="fecha" id="fecha">
							<h1 class="fw-bold my-3">${accountName}</h1>


							<button type="submit" class="btn btn-hover bg-yellow fw-bold">Actualizar</button>
						</form>
					</c:when>
				</c:choose>


			</div>
			<table class="table">
				<thead>
					<tr>
						<th class="fw-bold fs-4" scope="col">Fecha</th>
						<th class="fw-bold fs-4" scope="col">Cuenta</th>
						<th class="fw-bold fs-4" scope="col">Monto</th>
						<th class="fw-bold fs-4" scope="col">Categoría</th>
						<th class="fw-bold fs-5" scope="col">Descripción</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${movimientos}" var="movimiento">
						<tr>
							<th class="fs-5" scope="row">${movimiento.date}</th>
							<td class="fs-5">${movimiento.accountO.accountName}</td>


							<td
								class="fs-5 ${movimiento.category.type == 'INCOME' ? 'text-success' : movimiento.category.type == 'SPENT' ? 'text-danger' : 'text-dark'}">${movimiento.amount}</td>


							<td class="fs-5">${movimiento.category.categoryName}</td>
							<td class="fs-5">${movimiento.description}</td>
							<td>
								<button type="button" class="btn btn-sm delete-btn"
									data-id="${movimiento.id}" data-amount="${movimiento.amount}"
									data-accountO-id="${movimiento.accountO.id}"
									data-accountD-id="${movimiento.accountD.id}"
									data-verTipo="${verTipo}"
									data-category-id="${movimiento.category.id}">
									<i class="fa-solid fa-trash fa-2xl text-danger"></i>
								</button>
							</td>

						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		const deleteButtons = document.querySelectorAll('.delete-btn');

		deleteButtons
				.forEach(function(deleteButton) {
					deleteButton
							.addEventListener(
									'click',
									function() {
										const deleteUrl = 'DashboardController?ruta=eliminarMovimiento&movimientoId='
												+ this.getAttribute('data-id')
												+ '&amount='
												+ this
														.getAttribute('data-amount')
												+ '&accountIdO='
												+ this
														.getAttribute('data-accountO-id')
												+ '&accountIdD='
												+ this
														.getAttribute('data-accountD-id')
												+ '&verTipo='
												+ this
														.getAttribute('data-verTipo')
												+ '&categoryId='
												+ this
														.getAttribute('data-category-id');

										window.location.href = deleteUrl;
									});
				});
	</script>



	<script
		src=" https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>

</html>