package com.emp.shared.interfaces;

import com.emp.shared.models.Product;

public interface ProductApiClientInterface {
    Product getProduct(String productId);
}
