package com.emp.shared.interfaces;

import com.emp.shared.exceptions.ApiException;
import com.emp.shared.exceptions.InvalidDataException;
import com.emp.shared.models.Product;

public interface ProductApiClient {
    Product getProduct(String productId) throws ApiException, InvalidDataException;
}
