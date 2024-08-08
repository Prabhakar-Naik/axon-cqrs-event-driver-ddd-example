package com.springboot.axoniq.util;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AllPageResponse {
    private Long totalRecords;
    private List<?> pageResponse;

    public AllPageResponse() {
    }

    public AllPageResponse(Long totalRecords, List<?> pageResponse) {
        this.totalRecords = totalRecords;
        this.pageResponse = pageResponse;
    }

    public AllPageResponse(AllPageResponse result) {
        this.totalRecords = result.getTotalRecords();
        this.pageResponse = result.getPageResponse();
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<?> getPageResponse() {
        return pageResponse;
    }

    public void setPageResponse(List<?> pageResponse) {
        this.pageResponse = pageResponse;
    }

    @Override
    public String toString() {
        return "AllPageResponse{" +
                "totalRecords=" + totalRecords +
                ", pageResponse=" + pageResponse +
                '}';
    }
}
