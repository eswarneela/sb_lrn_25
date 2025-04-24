package com.ne.sb.learn._5.sb_lrn.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ne.sb.learn._5.sb_lrn.models.JsonToDo;
import com.ne.sb.learn._5.sb_lrn.models.ProductsRecords;
import com.ne.sb.learn._5.sb_lrn.models.ProductsRecords.Products;
import com.ne.sb.learn._5.sb_lrn.services.FetchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DealOutsideController {

    private final FetchService fetchService;

    public DealOutsideController(FetchService fetchService) {
        this.fetchService = fetchService;
    }

    @GetMapping("/fetch")
    public List<JsonToDo> fetchDetail(@RequestParam String url){
        return fetchService.outputFromFetchedData(url);
    }

    @GetMapping("/get/products")
    public List<Products> fetchAllProducts() throws JsonProcessingException {
        return fetchService.getProducts();
    }
}
