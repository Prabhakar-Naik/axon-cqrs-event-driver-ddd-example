package com.springboot.axoniq.util;


import java.util.List;

public class AllPageResponse {
    private Long totalRecords = 0L;
    private List<?> pageResponse = null;

    public AllPageResponse() {
    }

    public AllPageResponse(Long totalRecords, List<?> pageResponse) {
        this.totalRecords = totalRecords;
        this.pageResponse = pageResponse;
    }
}
