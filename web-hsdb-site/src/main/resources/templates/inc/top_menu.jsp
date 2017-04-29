<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${user_id}">
	<div id=top_menu>
		<div id=links>
			.::<c:forEach var="i" items="${menu}">
				<c:if test="${not empty current}">
					<b>${title}</b>
				</c:if>
				<c:if test="${empty current}">
					<a href='${url}?${current_filter}'>${title}</a>
				</c:if>
				<c:if test="${__first__}">
					<span class=progress>${collection_percent}%</span>
				</c:if>
			::</c:forEach>.
		</div>
		<div id=user_info>
			.::
			${user_name}
			::
			<a class=logout href='/logout/'>X</a>
			::.
		</div>
	</div>
</c:if>
