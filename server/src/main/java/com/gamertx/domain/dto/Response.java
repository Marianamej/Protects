package com.gamertx.domain.dto;

import com.gamertx.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Response {
    private List<Product> content;
    private int pageNumber;
    private int sizeContent;
    private Long productsTotal;
    private int pagesTotal;
    private boolean last;
}
