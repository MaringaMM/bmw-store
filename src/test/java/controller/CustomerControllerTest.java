package controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.co.bmw.controller.CustomerController;
import za.co.bmw.entity.Customer;
import za.co.bmw.service.CustomerService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;



import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private String mockJsonData = "{ \"id\": 1, \"firstName\": \"Musa\", \"lastName\": \"Maringa\", \"email\": \"king@gmail.com\" }";
    private String mockUpdateJsonData = "{ \"id\": 1, \"firstName\": \"Musa\", \"lastName\": \"Maringa\", \"email\": \"cube.musa@gmail.com\" }";


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "John", "Doe", "johndoe@example.com"),
                new Customer(2L, "Jane", "Smith", "janesmith@gmail.com")
        );

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = new Customer(1L, "Musa", "Maringa", "king@gmail.com");

        when(customerService.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Musa")))
                .andExpect(jsonPath("$.lastName", is("Maringa")))
                .andExpect(jsonPath("$.email", is("king@gmail.com")));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = new Customer(1L, "Musa", "Maringa", "king@gmail.com");

        when(customerService.createCustomer(customer)).thenReturn(customer);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockJsonData))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Musa")))
                .andExpect(jsonPath("$.lastName", is("Maringa")))
                .andExpect(jsonPath("$.email", is("king@gmail.com")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer customer = new Customer(1L, "Musa", "Maringa", "cube.musa@gmail.com");


        when(customerService.updateCustomer(1L, customer)).thenReturn(customer);

        mockMvc.perform(put("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockUpdateJsonData))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Musa")))
                .andExpect(jsonPath("$.lastName", is("Maringa")))
                .andExpect(jsonPath("$.email", is("cube.musa@gmail.com")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        when(customerService.deleteCustomerById(1L)).thenReturn(true);

        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
