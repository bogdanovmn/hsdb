<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@include file="inc/header_main.jsp"%>


<c:if test="${not empty cards}">
	<c:forEach var="i" items="${cards}">
		<div id="${i.id}" class=card>
			<img src="http://wow.zamimg.com/images/hearthstone/cards/ruru/small/${i.image_url}.png?9786">
			<br>
			<div id='norm_${i.id}' class="counter norm_count">${i.norm_count}</div>
			<div id='gold_${i.id}' class="counter gold_count">${i.gold_count}</div>
		</div>
	</c:forEach>
</c:if>
<c:if test="${empty cards}">
	<h2>Пусто</h2>
</c:if>

<script>

	var changes = {};

	$('div.card div.counter').click(
		function(event) {
			var type = event.target.id.split("_")[0];
			var id   = event.target.id.split("_")[1];
			var count= parseInt(event.target.innerHTML) + 1;

			if (count == 3) {
				count = 0;
			}

			if (typeof changes[id] == 'undefined') { 
				//console.log("init change for id=" + id + " type=" + type + " count=" + count);
				setTimeout(
					function() {
						apply_changes(id);
					},
					1500
				);
				changes[id] = {};
			}
		
			changes[id][type] = count;
			$('#' + type + '_' + id).text(count);
		}
	);

	function apply_changes(id) {
		console.log(id);
		for (var type in changes[id]) {
			var url = '/count/' + type + '/' + id + '/' + changes[id][type] + '/';
			$.ajax({ 
				url: url,
				success: function(result) {
					//$('#' + type + '_' + id).text(count);
					//console.log('[OK] ' + url);
				}
			});
		}
		delete changes[id];
	}


</script>

<%@include file="inc/footer_main.jsp"%>