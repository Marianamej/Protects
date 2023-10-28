package com.gamertx.domain;

import com.gamertx.persistence.entity.EnumContent;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Order {
    private int orderId;
    private String userEmail;
    private LocalDateTime purchaseDate;
    private LocalDateTime deliveryDate;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private EnumContent.estadoActual status;
    private List<Item> items;
}
