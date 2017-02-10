package ru.bmn.web.hsdb.servlet.view.ui;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class QueryFilter {
    private final List<String> items = new ArrayList<>();
    private final String selectedValue;


    public QueryFilter(String selectedValue) {
        this.selectedValue = selectedValue;
    }
}
