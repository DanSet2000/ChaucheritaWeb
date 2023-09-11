<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Login</title>
</head>

<body class="d-flex flex-column bg-gray">

    <header class="bg-yellow d-flex flex-wrap justify-content-center mb-4 p-3">
        <a href="" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
            <img class="" src="${pageContext.request.contextPath}/img/logo.png" alt="Imagen de una billetera">
            <h1 class="font px-3 c-darkgray">Mi Chaucherita Web</h1>
        </a>
    </header>

    <div class="d-flex py-5 justify-content-center">
        <main class="form-signin w-100 ">
            <form action="../LoginController?ruta=ingresar" method="POST" class="text-center">


                <h1 class="h3 mb-3 c-white font fw-normal">Iniciar Sesión </h1>

                <div class="form-floating ">
                    <input type="text" name="usuario" class="form-control" id="floatingInput" placeholder="User">
                    <label class="font" for="floatingInput">Usuario</label>
                </div>
                <div class="form-floating">
                    <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
                    <label class="font" for="floatingPassword">Contraseña</label>
                </div>


                <button class="font rounded border-0 btn-hover bg-yellow hover  w-100 py-2"
                    type="submit">Ingresar</button>

            </form>
        </main>

    </div>






    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>

</html>