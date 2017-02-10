<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty errorMsg}">
	<div class=error>${errorMsg}</div>
</c:if>