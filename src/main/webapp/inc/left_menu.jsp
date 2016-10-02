<div id=left_menu>
	<div class=filter><b>Персонаж</b>
		<div class=values>
			<table>
			<TMPL_LOOP filter_character>
				<tr>
				<td class=progress>
					<% if (progress) { %>
						<span class="progress progress_${type}">${progress}%</span>
					<% } %>
				<td>
					<% if (selected) { %>
						<b>${name}</b>
					<% } else { %>
						<a href="?character_id=${id}&${current_filter_wo_character_id}">
							${name}
						</a>
					<% } %>
			</TMPL_LOOP>
			</table>
		</div>
	</div>
	
	<div class=filter>
		<b>Редкость</b>
		<% if (rarity_id) { %>
			<a class=reset href="?rarity_id=0&${current_filter_wo_rarity_id}">сбросить</a>
		<% } %>

		<div class=values>
			<TMPL_LOOP filter_rarity>
				<% if (selected) { %>
					<div class="rarity_filter rarity_filter_${id}_selected"></div>
				<% } else { %>
					<a class=rarity_filter href="?rarity_id=${id}&${current_filter_wo_rarity_id}">
						<div class="rarity_filter rarity_filter_${id} selected"></div>
					</a>
				<% } %>
			</TMPL_LOOP>
		</div>
	</div>

	<div class=filter>
		<b>Набор</b>
		<% if (set_id) { %>
			<a class=reset href="?set_id=0&${current_filter_wo_set_id}">сбросить</a>
		<% } %>

		<div class=values>
			<table>
			<TMPL_LOOP filter_set>
				<tr>
				<td class=progress>
					<% if (progress) { %>
						<span class="progress progress_${type}">${progress}%</span>
					<% } %>
				<td>
					<% if (selected) { %>
						<b>${name}</b>
					<% } else { %>
						<a href="?set_id=${id}&${current_filter_wo_set_id}">
							${name}
						</a>
					<% } %>
			</TMPL_LOOP>
			</table>
		</div>
	</div>
</div>
