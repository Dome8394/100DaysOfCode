package springdata.products.controller;

import com.mongodb.util.JSON;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

        String expected = "[{\"id\": \"1\", \"name\": \"test-prod-1\", \"price\": 21.99, \"quantity\": 10}," +
                "{\"id\": \"2\", \"name\": \"test-prod-2\", \"price\": 20.99, \"quantity\": 10}," +
                "{\"id\": \"3\", \"name\": \"test-prod-3\", \"price\": 19.99, \"quantity\": 10}]";

        when(productService.getAll()).thenReturn(
                Arrays.asList(new Product("1", "test-prod-1", 21.99, 10),
                        new Product("2", "test-prod-2", 20.99, 10),
                        new Product("3", "test-prod-3", 19.99, 10))

        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/products")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("[{id: 1, name: test-prod-1, price: 21.99, quantity: 10}," +
                        "{id: 2, name: test-prod-2, price: 20.99, quantity: 10}, " +
                        "{id: 3, name: test-prod-3, price: 19.99, quantity: 10}]"))
                .andReturn();

        //JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
    }

    @Test
    public void addProductTest_basic() throws Exception {

        Product testProduct = new Product("1", "test-prod-1", 22.99, 10);
        JSONObject testJson = new JSONObject("{\"id\": \"1\", \"name\": \"test-prod-1\", \"price\": 22.99, " +
                "\"quantity\": 10}");

        when(productService.addProduct(Mockito.any(Product.class))).thenReturn(
                testProduct.toString()
        );

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/product/new")
                .accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON)
                .content(testJson.toString());

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(testProduct.toString()))
                .andReturn();
    }
}