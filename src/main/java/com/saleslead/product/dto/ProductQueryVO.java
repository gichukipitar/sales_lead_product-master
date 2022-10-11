package com.saleslead.product.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.saleslead.product.entity.Product} entity
 */
@Data
public class ProductQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private  Long id;
    private  String productName;
    private  String productCode;
    private  Long productAmount;
}