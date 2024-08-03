package com.springboot.axoniq.util;

import java.util.List;

public class PageRequestDto{
    private int page;
    private int size;
    private List<String> sortColumns;
    private String search;

    public PageRequestDto() {
    }

    public PageRequestDto(int page, int size, List<String> sortColumns, String search) {
        this.page = page;
        this.size = size;
        this.sortColumns = sortColumns;
        this.search = search;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(List<String> sortColumns) {
        this.sortColumns = sortColumns;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
