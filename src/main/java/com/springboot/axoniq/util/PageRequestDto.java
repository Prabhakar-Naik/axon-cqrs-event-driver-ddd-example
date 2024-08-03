package com.springboot.axoniq.util;


import java.util.List;

public class PageRequestDto{
    private int page;
    private int size;
    private List<String> sortColumns;
    private String search;
}
