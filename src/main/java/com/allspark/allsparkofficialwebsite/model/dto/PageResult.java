package com.allspark.allsparkofficialwebsite.model.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class PageResult<T> implements Serializable {

    private List<T> records;
    private long total;
    private long size;
    private long current;
    private long pages;

    public PageResult(List<T> records, long total, long size, long current, long pages) {
        this.records = records;
        this.total = total;
        this.size = size;
        this.current = current;
        this.pages = pages;
    }
}