<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty error_msg}">
	<div class=error>${error_msg}</div>
</c:if>
