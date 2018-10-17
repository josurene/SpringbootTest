package com.josu.work.demo.controllers;


import com.josu.work.demo.domain.ProductDomain;
import com.josu.work.demo.exception.ProductNotFoundException;
import com.josu.work.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@Controller("/products")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductDomain productDomain;

    @GetMapping("/init")
    public ResponseEntity init(){
        productDomain.init();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Product> getProducts(){
        return productDomain.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productDomain.get(id).orElseThrow(() -> new ProductNotFoundException("Product not found:"+id));
    }

    @PostMapping("")
    public ResponseEntity addProduct(@RequestBody Product product){
        productDomain.add(product);
        return new ResponseEntity(HttpStatus.OK);
    }

}
