package ru.bmn.web.hsdb;

import javax.servlet.http.HttpSession;

public class Session {
	final private HttpSession session;

	public Session(final HttpSession session) {
		this.session = session;
	}

	public void create() {}

	public void drop() {}

	
}
