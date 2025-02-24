<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Departamentos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2 class="mt-4">Lista de Departamentos</h2>
    <form action="departamento" method="post">
        <input type="text" name="nombre" placeholder="Nombre" required>
        <input type="text" name="descripcion" placeholder="Descripción" required>
        <input type="hidden" name="action" value="add">
        <button type="submit" class="btn btn-primary">Agregar</button>
    </form>
    <ul class="list-group mt-3">
        <c:forEach var="dep" items="${departamentosAll}">
            <li class="list-group-item">${dep.nombre} - ${dep.descripcion}</li>
        </c:forEach>
    </ul>


</div>
</body>
</html>