package springdata.products.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springdata.products.entities.Product;
import springdata.products.services.ProductService;

import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mock;

    @MockBean
    private ProductService productService;

    @Test
    public void getAllTest_basic() throws Exception{

        String expected = "[{\"id\": 1, \"name\": \"test-prod-1\", \"price\": 21.99, \"quantity\": 10}," +
                "{\"id\": 2, \"name\": \"test-prod-2\", \"price\": 20.99, \"quantity\": 10}," +
                "{\"id\": 3, \"name\": \"test-prod-3\", \"price\": 19.99, \"quantity\": 10}]";

        when(productService.getAll()).thenReturn(
                Arrays.asList(new Product(1, "test-prod-1", 21.99, 10),
                        new Product(2, "test-prod-2", 20.99, 10),
                        new Product(3, "test-prod-3", 19.99, 10))

        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/products")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id: 1, name: test-prod-1, price: 21.99, quantity: 10}," +
                        "{id: 2, name: test-prod-2, price: 20.99, quantity: 10}, " +
                        "{id: 3, name: test-prod-3, price: 19.99, quantity: 10}]"))
                .andReturn();

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }
}