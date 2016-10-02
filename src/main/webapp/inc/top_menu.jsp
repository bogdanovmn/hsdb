<% if (user_id) { %>
	<div id=top_menu>
		<div id=links>
			.::<TMPL_LOOP menu>
				<% if (current) { %>
					<b>${title}</b>
				<% } else { %>
					<a href='${url}?${current_filter}'>${title}</a>
				<% } %>
				<% if (__first__) { %>
					<span class=progress>${collection_percent}%</span>
				<% } %>
			::</TMPL_LOOP>.
		</div>
		<div id=user_info>
			.::
			${user_name}
			::
			<a class=logout href='/logout/'>X</a>
			::.
		</div>
	</div>
<% } %>
