package ru.bmn.web.hsdb.site.controller.domain;

public class FilterValuesItem {
	private final int id;
	private final String name;
	private boolean selected = false;

	public FilterValuesItem(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
