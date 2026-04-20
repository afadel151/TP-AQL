package com.emp.services;

import com.emp.shared.exceptions.ApiException;
import com.emp.shared.exceptions.InvalidDataException;
import com.emp.shared.interfaces.ProductApiClient;
import com.emp.shared.models.Product;

public class SimpleProductService {
    private final ProductApiClient productApiClient;

    public SimpleProductService(ProductApiClient productApiClient) {
        this.productApiClient = productApiClient;
    }

    public Product getProduct(String productId) throws ApiException, InvalidDataException {
        return productApiClient.getProduct(productId);
    }
}
