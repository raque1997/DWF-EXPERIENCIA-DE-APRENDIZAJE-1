<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contrataciones</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Lista de Contrataciones</h1>
    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>
    <form action="contrataciones" method="post">
        <!-- Campos del formulario para agregar contrataciones -->
        <div class="form-group">
            <label for="idDepartamento">ID Departamento:</label>
            <input type="text" class="form-control" id="idDepartamento" name="idDepartamento">
        </div>
        <div class="form-group">
            <label for="idEmpleado">ID Empleado:</label>
            <input type="text" class="form-control" id="idEmpleado" name="idEmpleado">
        </div>
        <div class="form-group">
            <label for="idCargo">ID Cargo:</label>
            <input type="text" class="form-control" id="idCargo" name="idCargo">
        </div>
        <div class="form-group">
            <label for="idTipoContratacion">ID Tipo Contratación:</label>
            <input type="text" class="form-control" id="idTipoContratacion" name="idTipoContratacion">
        </div>
        <div class="form-group">
            <label for="fechaContratacion">Fecha de Contratación:</label>
            <input type="date" class="form-control" id="fechaContratacion" name="fechaContratacion">
        </div>
        <div class="form-group">
            <label for="salario">Salario:</label>
            <input type="number" step="0.01" class="form-control" id="salario" name="salario">
        </div>
        <div class="form-group">
            <label for="estado">Estado:</label>
            <input type="checkbox" class="form-control" id="estado" name="estado">
        </div>
        <button type="submit" class="btn btn-primary">Guardar Contratación</button>
    </form>
    <h2>Contrataciones Existentes</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Departamento</th>
            <th>Empleado</th>
            <th>Cargo</th>
            <th>Tipo Contratación</th>
            <th>Fecha de Contratación</th>
            <th>Salario</th>
            <th>Estado</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contratacion" items="${listaContrataciones}">
            <tr>
                <td>${contratacion.idContratacion}</td>
                <td>${contratacion.idDepartamento}</td>
                <td>${contratacion.idEmpleado}</td>
                <td>${contratacion.idCargo}</td>
                <td>${contratacion.idTipoContratacion}</td>
                <td>${contratacion.fechaContratacion}</td>
                <td>${contratacion.salario}</td>
                <td>${contratacion.estado}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

