package com.example.easybazaar.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
    private Integer pageNo;
    private Integer pageSize;
    private String keyword;
}
