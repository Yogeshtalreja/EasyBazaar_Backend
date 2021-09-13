package com.example.easybazaar.commonResponseModel;


import java.util.List;

public class CommonResponseModel<T> {

    private Boolean hasError;
    private Integer totalCount;
    private List<T> data;
    private String message;

}
