package com.emp.services;

import com.emp.shared.exceptions.ApiException;
import com.emp.shared.exceptions.InvalidDataException;
import com.emp.shared.interfaces.ProductApiClient;
import com.emp.shared.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SimpleProductServiceTest {

    @Mock
    private ProductApiClient productApiClient;

    @Test
    public void testGetProductSuccessfulRetrieval() {
        // Arrange
        Product expectedProduct = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(100.0)
                .stock(10)
                .build();

        SimpleProductService productService = new SimpleProductService(productApiClient);

        when(productApiClient.getProduct("123")).thenReturn(expectedProduct);

        // Act
        Product result = productService.getProduct("123");

        // Assert
        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals(100.0, result.getPrice());

        // Verify that productApiClient.getProduct was called with the correct argument
        verify(productApiClient, times(1)).getProduct("123");
    }

    @Test
    public void testGetProductIncompatibleDataFormat() {
        // Arrange
        SimpleProductService productService = new SimpleProductService(productApiClient);

        when(productApiClient.getProduct("123"))
                .thenThrow(new InvalidDataException("Incompatible data format"));

        // Act & Assert
        assertThrows(InvalidDataException.class, () -> {
            productService.getProduct("123");
        });

        // Verify that productApiClient.getProduct was called with the correct argument
        verify(productApiClient, times(1)).getProduct("123");
    }

    @Test
    public void testGetProductApiCallFailure() {
        // Arrange
        SimpleProductService productService = new SimpleProductService(productApiClient);

        when(productApiClient.getProduct("123"))
                .thenThrow(new ApiException("API call failed"));

        // Act & Assert
        assertThrows(ApiException.class, () -> {
            productService.getProduct("123");
        });

        // Verify that productApiClient.getProduct was called with the correct argument
        verify(productApiClient, times(1)).getProduct("123");
    }

    @Test
    public void testGetProductNotFound() {
        // Arrange
        SimpleProductService productService = new SimpleProductService(productApiClient);

        when(productApiClient.getProduct("999")).thenReturn(null);

        // Act
        Product result = productService.getProduct("999");

        // Assert
        assertNull(result);

        // Verify that productApiClient.getProduct was called with the correct argument
        verify(productApiClient, times(1)).getProduct("999");
    }
}
