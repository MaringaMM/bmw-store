package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.co.bmw.controller.ProductController;
import za.co.bmw.entities.Product;
import za.co.bmw.exception.RestExceptionHandler;
import za.co.bmw.service.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = Arrays.asList(
                new Product(1L, "Product 1", "Description 1", 10.0),
                new Product(2L, "Product 2", "Description 2", 20.0)
        );

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product 1")))
                .andExpect(jsonPath("$[0].description", is("Description 1")))
                .andExpect(jsonPath("$[0].price", is(10.0)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Product 2")))
                .andExpect(jsonPath("$[1].description", is("Description 2")))
                .andExpect(jsonPath("$[1].price", is(20.0)));

        verify(productService, times(1)).getAllProducts();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product(1L, "Product 1", "Description 1", 10.0);

        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product 1")))
                .andExpect(jsonPath("$.description", is("Description 1")))
                .andExpect(jsonPath("$.price", is(10.0)));

        verify(productService, times(1)).getProductById(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product(null, "Product 1", "Description 1", 10.0);

        when(productService.createProduct(product)).thenReturn(product);

        String productJson = "{\"name\":\"Product 1\",\"description\":\"Description 1\",\"price\":10.0}";

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("Product 1")))
                .andExpect(jsonPath("$.description", is("Description 1")))
                .andExpect(jsonPath("$.price", is(10.0)));

        verify(productService, times(1)).createProduct(product);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product(1L, "Product 1", "Description 1", 10.0);

        when(productService.updateProduct(1L, product)).thenReturn(product);

        String productJson = "{\"id\":1,\"name\":\"Product 1\",\"description\":\"Description 1\",\"price\":10.0}";

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Musa")))
                .andExpect(jsonPath("$.lastName", is("Maringa")));
    }

}