package ru.bmn.web.hsdb.servlet.ui;

import java.util.ArrayList;
import java.util.List;

public class HeadMenu {
	private final String current;
	private List<HeadMenuItem> items;
	private boolean isPrepared = false;
	
	public HeadMenu(String current) {
		this.current = current;
		
	}

	public List<HeadMenuItem> items() {
		for (HeadMenuItem headMenuItem : items) {
			if (headMenuItem.is(this.current)) {
				headMenuItem.select();
			}
			
		}
		return null;
	}
	
	private void _prepare() {
		if(!this.isPrepared) {
			this.items = new ArrayList<HeadMenuItem>(3); 
			this.items.add(new HeadMenuItem("collection_in" , "/collection/in/" , "Моя коллекция"));
			this.items.add(new HeadMenuItem("collection_out", "/collection/out/", "Пополнить коллекцию"));
			this.items.add(new HeadMenuItem("booster"       , "/booster/"       , "Купить бустер"));
		}
	}
}
