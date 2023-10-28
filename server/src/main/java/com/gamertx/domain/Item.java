package com.gamertx.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Item {
    private int itemId;
    private int quantity;
    private BigDecimal total;
}
