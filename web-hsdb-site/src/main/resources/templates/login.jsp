<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="inc/header_min.jsp"%>

<h1>Вход</h1>

<%@include file="inc/error_msg.jsp"%>
<div class=login>
	<form method=post>
		<table>
		<tr>
			<td>E-mail
			<td><input type=text name=email>
		<tr>
			<td>Пароль
			<td><input type=password name=password value=''>
		<tr>
			<td><input type=submit name=login_submit value='  Войти  '>
			<td><a href='../register/'>Регистрация</a>
		</table>
	</form>
</div>

<%@include file="inc/footer_min.jsp"%>