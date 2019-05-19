package springdata.products.controller;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
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

import static org.junit.Assert.assertEquals;
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

        String expected = "[{\"id\": null, \"name\": \"test-prod-1\", \"price\": 21.99, \"quantity\": 10}," +
                "{ \"id\": null, \"name\": \"test-prod-2\", \"price\": 20.99, \"quantity\": 10}," +
                "{ \"id\": null, \"name\": \"test-prod-3\", \"price\": 19.99, \"quantity\": 10}]";

        when(productService.getAll()).thenReturn(
                Arrays.asList(new Product("test-prod-1", 21.99, 10),
                        new Product( "test-prod-2", 20.99, 10),
                        new Product( "test-prod-3", 19.99, 10))
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/products")
                .accept(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mock.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id: null, name: test-prod-1, price: 21.99, quantity:10}," +
                        "{id: null, name: test-prod-2, price: 20.99, quantity:10}," +
                        "{id: null, name: test-prod-3, price: 19.99, quantity:10}]"))
                .andReturn();

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void getByIdTest_basic() {

        String test_id = "1";
        Product product = new Product();
        product.setId("1");

        when(productService.getProduct(test_id)).thenReturn(
                java.util.Optional.of(product)
        );

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/products/find/{id}")
                .param(test_id);
    }

    @Test
    public void addProductTest_basic() throws Exception {

        Product testProduct = new Product("test-prod-1", 22.99, 10);
        JSONObject testJson = new JSONObject("{\"name\": \"test-prod-1\", \"price\": 22.99, " +
                "\"quantity\": 10}");

        when(productService.addProduct(Mockito.any(Product.class))).thenReturn(
                testProduct.toString()
        );

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/product/add")
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