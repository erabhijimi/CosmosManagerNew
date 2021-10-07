package com.cosmos.controller;

import com.cosmos.pojo.Purchase;
import com.cosmos.pojo.Products;
import com.cosmos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/quick/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public Optional<Purchase> getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping
    public Products getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping
    public Purchase saveProduct(@RequestBody Purchase product) {
        return productService.saveProduct(product);
    }

}
