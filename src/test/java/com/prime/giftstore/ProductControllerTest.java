package com.prime.giftstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("geode.client")
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void generateData() throws Exception {
        mockMvc.perform(post("/rest/products/generate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void addProduct() throws Exception {
        String request = getResource("product1.json");
        assertNotNull(request);

        mockMvc.perform(post("/rest/products")
                        .content(request)
                        .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("89f44224"))
                .andExpect(jsonPath("$.name").value("prod-89f44224"))
                .andExpect(jsonPath("$.description").value("dsc-89f44224"))
                .andExpect(jsonPath("$.price").value("3.0"));
    }

    @Test
    void getProduct() throws Exception {
        String request = getResource("product2.json");
        assertNotNull(request);

        mockMvc.perform(post("/rest/products")
                .content(request)
                .contentType((MediaType.APPLICATION_JSON)));

        mockMvc.perform(get("/rest/products/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("mock-prod-name"));
    }

    @Test
    void findAll() throws Exception {
        generateData();
        mockMvc.perform(get("/rest/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", greaterThanOrEqualTo(3)));
    }

    @Test
    void deleteById() throws Exception {
        String request = getResource("product2.json");
        assertNotNull(request);

        mockMvc.perform(post("/rest/products")
                .content(request)
                .contentType((MediaType.APPLICATION_JSON)));

        mockMvc.perform(delete("/rest/products/12345"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rest/products/12345"))
                .andExpect(status().isNotFound());
    }

    private String getResource(String resource) throws IOException {
        try(InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(resource)) {
            assertNotNull(resourceAsStream);
            return new String(resourceAsStream.readAllBytes());
        }
    }
}
