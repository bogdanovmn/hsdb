<%@include file="inc/header_min.jsp"%>

<h1>Регистрация</h1>

<%@include file="inc/error_msg.jsp"%>

<div class=register>
	<form method=post>
		<table>
		<tr>
			<td>E-mail
			<td><input type=text name=email>
		<tr>
			<td>Пароль (не от мыла)
			<td><input type=password name=password value=''>
		<tr>
			<td>Пароль еще разок
			<td><input type=password name=password_check value=''>
		<tr>
			<td>Два минус три плюс пять
			<td><input type=text name=zombi_check>
		<tr>
			<td>Как к Вам обращаться
			<td><input type=text name=name>
		<tr>
			<td colspan=2><input type=submit name=register_submit value='  Готово  '>
		</table>
	</form>
</div>

<%@include file="inc/footer_min.jsp"%>
