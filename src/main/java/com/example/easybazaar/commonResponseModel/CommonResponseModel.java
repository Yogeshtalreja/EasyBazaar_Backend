package com.example.easybazaar.commonResponseModel;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CommonResponseModel<T> {

    private Boolean hasError;
    private Integer totalCount;
    private List<T> data;
    private String message;

}
