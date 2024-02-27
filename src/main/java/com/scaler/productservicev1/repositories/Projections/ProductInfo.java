package com.scaler.productservicev1.repositories.Projections;

import lombok.Getter;

/**
 * Projection for {@link com.scaler.productservicev1.models.Product}
 */

public interface ProductInfo {
    Long getId();

    String getTitle();

    Double getPrice();
}