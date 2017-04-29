<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id=left_menu>
	<div class=filter><b>Персонаж</b>
		<div class=values>
			<table>
			<c:forEach var="i" items="${filter_character}">
				<tr>
				<td class=progress>
					<c:if test="${not empty i.progress}">
						<span class="progress progress_${i.type}">${i.progress}%</span>
					</c:if>
				<td>
					<c:if test="${not empty i.selected}">
						<b>${i.name}</b>
					</c:if>
					<c:if test="${empty i.selected}">
						<a href="?character_id=${i.id}&${current_filter_wo_character_id}">
							${i.name}
						</a>
					</c:if>
			</c:forEach>
			</table>
		</div>
	</div>
	
	<div class=filter>
		<b>Редкость</b>
		<c:if test="${not empty rarity_id}">
			<a class=reset href="?rarity_id=0&${current_filter_wo_rarity_id}">сбросить</a>
		</c:if>

		<div class=values>
			<c:forEach var="i" items="${filter_rarity}">
				<c:if test="${not empty i.selected}">
					<div class="rarity_filter rarity_filter_${i.id}_selected"></div>
				</c:if>
				<c:if test="${empty i.selected}">
					<a class=rarity_filter href="?rarity_id=${i.id}&${current_filter_wo_rarity_id}">
						<div class="rarity_filter rarity_filter_${i.id} selected"></div>
					</a>
				</c:if>
			</c:forEach>
		</div>
	</div>

	<div class=filter>
		<b>Набор</b>
		<c:if test="${not empty set_id}">
			<a class=reset href="?set_id=0&${current_filter_wo_set_id}">сбросить</a>
		</c:if>

		<div class=values>
			<table>
			<c:forEach var="i" items="${filter_set}">
				<tr>
				<td class=progress>
					<c:if test="${not empty i.progress}">
						<span class="progress progress_${i.type}">${i.progress}%</span>
					</c:if>
				<td>
					<c:if test="${not empty i.selected}">
						<b>${i.name}</b>
					</c:if>
					<c:if test="${not empty i.selected}">
						<a href="?set_id=${i.id}&${current_filter_wo_set_id}">
							${i.name}
						</a>
					</c:if>
			</c:forEach>
			</table>
		</div>
	</div>
</div>