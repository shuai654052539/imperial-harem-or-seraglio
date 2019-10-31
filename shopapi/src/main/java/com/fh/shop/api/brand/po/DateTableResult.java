package com.fh.shop.api.brand.po;

import java.util.List;

public class DateTableResult {
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List data;

    public DateTableResult(){

    }
    public DateTableResult(int draw, Long recordsTotal, long recordsFiltered, List data){
        this.draw=draw;
        this.recordsTotal=recordsTotal;
        this.recordsFiltered=recordsFiltered;
        this.data=data;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
