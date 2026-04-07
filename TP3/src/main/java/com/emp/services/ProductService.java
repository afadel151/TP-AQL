package com.emp.services;

import com.emp.shared.interfaces.ProductApiClientInterface;
import com.emp.shared.exceptions.ApiException;
import com.emp.shared.exceptions.InvalidDataException;
import com.emp.shared.models.Product;

public class ProductService {

    private final ProductApiClientInterface productApiClient;

    public ProductService(ProductApiClientInterface productApiClient) {
        this.productApiClient = productApiClient;
    }

    public Product getProduct(String productId) {
        if (productId == null || productId.isBlank()) {
            throw new InvalidDataException("Product ID must not be null or blank");
        }

        Product product = productApiClient.getProduct(productId);

        if (product == null) {
            throw new ApiException("API returned no product for id: " + productId);
        }
        if (product.getName() == null || product.getPrice() < 0) {
            throw new InvalidDataException("API returned malformed product data");
        }

        return product;
    }
}