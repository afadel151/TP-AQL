package com.emp;

import com.emp.services.ProductService;
import com.emp.shared.interfaces.ProductApiClientInterface;
import com.emp.shared.exceptions.ApiException;
import com.emp.shared.exceptions.InvalidDataException;
import com.emp.shared.models.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductApiClientInterface mockApiClient;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        mockApiClient = mock(ProductApiClientInterface.class);
        productService = new ProductService(mockApiClient);
    }

    // sc 1
    @Test
    void getProduct_shouldReturnProduct_whenApiRespondsSuccessfully() {
        Product expected = new Product("P1", "Laptop", 999.99);
        when(mockApiClient.getProduct("P1")).thenReturn(expected);

        Product result = productService.getProduct("P1");

        verify(mockApiClient).getProduct("P1"); 
        assertEquals(expected, result);
    }

    // sc 2 failure 
    @Test
    void getProduct_shouldThrowApiException_whenApiCallFails() {
        when(mockApiClient.getProduct("P2")).thenThrow(new ApiException("Service unavailable"));

        ApiException ex = assertThrows(ApiException.class, () -> productService.getProduct("P2"));

        verify(mockApiClient).getProduct("P2");
        assertEquals("Service unavailable", ex.getMessage());
    }

    // 3 date format
    @Test
    void getProduct_shouldThrowInvalidDataException_whenProductDataIsMalformed() {
        Product malformed = new Product("P3", null, -1.0);
        when(mockApiClient.getProduct("P3")).thenReturn(malformed);

        assertThrows(InvalidDataException.class, () -> productService.getProduct("P3"));

        verify(mockApiClient).getProduct("P3");
    }

    // 4 null response
    @Test
    void getProduct_shouldThrowApiException_whenApiReturnsNull() {
        when(mockApiClient.getProduct("P4")).thenReturn(null);

        ApiException ex = assertThrows(ApiException.class, () -> productService.getProduct("P4"));

        verify(mockApiClient).getProduct("P4");
        assertTrue(ex.getMessage().contains("P4"));
    }

    // 5 blank id
    @Test
    void getProduct_shouldThrowInvalidDataException_whenProductIdIsBlank() {
        assertThrows(InvalidDataException.class, () -> productService.getProduct("  "));

        verifyNoInteractions(mockApiClient); // no call
    }
}