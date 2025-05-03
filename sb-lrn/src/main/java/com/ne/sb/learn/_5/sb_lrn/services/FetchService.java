package com.ne.sb.learn._5.sb_lrn.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ne.sb.learn._5.sb_lrn.models.JsonToDo;
import com.ne.sb.learn._5.sb_lrn.models.ProductsRecords;
import com.ne.sb.learn._5.sb_lrn.models.ProductsRecords.Products;
import com.ne.sb.learn._5.sb_lrn.wrapper.ToDoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ne.sb.learn._5.sb_lrn.utils.Constants.OBJECT_MAPPER;

@Service
@Slf4j
public class FetchService {

    private final RestClient restClient;

    @Value("${products.url}")
    private String productsUrl;

    public FetchService(RestClient restClient) {
        this.restClient = restClient;
    }
//    Optional<List<JsonToDo>> optionalJsonToDo;
    public List<JsonToDo> outputFromFetchedData(String url){
//        orestClient.get().uri(url)
//                .retrieve()
//                .body(ToDoResponse.class)
//                .getToDos());

        Optional<List<JsonToDo>> optionalJsonToDo = Optional.ofNullable(restClient.get()
                .uri(url)
                .retrieve()
                .body(ToDoResponse.class).getTodos());

        return optionalJsonToDo.map(list -> list.stream()
                    .filter(todo -> todo.getTodo().startsWith("B"))
                    .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public List<Products> getProducts() throws JsonProcessingException {
        log.info("Fetching products");
        List<Products> productList = null;
        try {
            ResponseEntity<String> response = restClient.get().uri(productsUrl).retrieve().toEntity(String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                productList = OBJECT_MAPPER.readValue(response.getBody(), new TypeReference<List<Products>>() {
                });
            } else {
                throw new RuntimeException("Exception occurred and response code is " + response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Exception occurred  while fetching all products", e);
            throw e;
        }
        return productList;
    }
}
