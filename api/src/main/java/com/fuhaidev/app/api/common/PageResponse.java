package com.fuhaidev.app.api.common;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PageResponse extends Response implements Serializable{

    long recordCount;
    int pageCount;
    int pageSize;
    int pageNo;
    List data;
    public PageResponse(){

    }
    public PageResponse(List data){
        setData(data);
    }
    public void setData(List data){
        PageInfo<Object> pageInfo=new PageInfo<Object>(data);
        this.recordCount=pageInfo.getTotal();
        this.pageCount=pageInfo.getPages();
        this.pageSize=pageInfo.getPageSize();
        this.pageNo=pageInfo.getPageNum();
        this.data=data;
    }
}
