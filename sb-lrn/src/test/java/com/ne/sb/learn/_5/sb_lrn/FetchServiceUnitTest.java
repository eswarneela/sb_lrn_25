package com.ne.sb.learn._5.sb_lrn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ne.sb.learn._5.sb_lrn.models.ProductsRecords.Products;
import com.ne.sb.learn._5.sb_lrn.services.FetchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchServiceUnitTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @InjectMocks
    private FetchService fetchService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(fetchService, "productsUrl", "http://test.com");
		when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("http://test.com"))).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
    }

    @Test
    void testFetchProducts_SuccessCase() throws JsonProcessingException {

        String expectedOutput = """
                [{
                      "id": 1,
                      "title": "Essence Mascara Lash Princess",
                      "description": "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
                      "category": "beauty",
                      "price": 9.99,
                      "discountPercentage": 7.17,
                      "rating": 4.94,
                      "stock": 5,
                      "tags": [
                        "beauty",
                        "mascara"
                      ],
                      "brand": "Essence",
                      "sku": "RCH45Q1A",
                      "weight": 2,
                      "dimensions": {
                        "width": 23.17,
                        "height": 14.43,
                        "depth": 28.01
                      },
                      "warrantyInformation": "1 month warranty",
                      "shippingInformation": "Ships in 1 month",
                      "availabilityStatus": "Low Stock",
                      "reviews": [
                        {
                          "rating": 2,
                          "comment": "Very unhappy with my purchase!",
                          "date": "2024-05-23T08:56:21.618Z",
                          "reviewerName": "John Doe",
                          "reviewerEmail": "john.doe@x.dummyjson.com"
                        },
                        {
                          "rating": 2,
                          "comment": "Not as described!",
                          "date": "2024-05-23T08:56:21.618Z",
                          "reviewerName": "Nolan Gonzalez",
                          "reviewerEmail": "nolan.gonzalez@x.dummyjson.com"
                        },
                        {
                          "rating": 5,
                          "comment": "Very satisfied!",
                          "date": "2024-05-23T08:56:21.618Z",
                          "reviewerName": "Scarlett Wright",
                          "reviewerEmail": "scarlett.wright@x.dummyjson.com"
                        }
                      ],
                      "returnPolicy": "30 days return policy",
                      "minimumOrderQuantity": 24,
                      "meta": {
                        "createdAt": "2024-05-23T08:56:21.618Z",
                        "updatedAt": "2024-05-23T08:56:21.618Z",
                        "barcode": "9164035109868",
                        "qrCode": "https://assets.dummyjson.com/public/qr-code.png"
                      },
                      "images": [
                        "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/1.png"
                      ],
                      "thumbnail": "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png"
                    }]
                """;

        ResponseEntity<String> response = new ResponseEntity<>(expectedOutput, HttpStatus.OK);
        when(responseSpec.toEntity(eq(String.class))).thenReturn(response);

        List<Products> actualOutput = fetchService.getProducts();

        assertNotNull(actualOutput);
        assertEquals(1, actualOutput.size());
    }

    @Test
    void testFetchProducts_IfExceptionIsThrown_whileFetching() {
        when(responseSpec.toEntity(eq(String.class))).thenThrow(new RuntimeException());
         Assertions.assertThrows(RuntimeException.class, () -> fetchService.getProducts());
    }

     @Test
    void testFetchProducts_IfDifferentResonseCodeIsReceived() {
        ResponseEntity<String> response = new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        when(responseSpec.toEntity(eq(String.class))).thenReturn(response);
         Assertions.assertThrows(RuntimeException.class, () -> fetchService.getProducts());
    }
}
