package com.josu.work.demo.controllers;


import com.josu.work.demo.domain.ProductDomain;
import com.josu.work.demo.exception.ProductNotFoundException;
import com.josu.work.demo.models.Product;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductDomain productDomain;

    @GetMapping("/init")
    @ApiOperation(value = "Init the database from the xml file")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully init"),
            @ApiResponse(code = 500, message = "Error on the xml")
    })
    public ResponseEntity init(){
        productDomain.init();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Gets all products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get all products")
    })
    public Iterable<Product> getProducts(){
        return productDomain.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a product using it's id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully init"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public Product getProduct(@PathVariable Integer id) {
        return productDomain.get(id).orElseThrow(() -> new ProductNotFoundException("Product not found:"+id));
    }

    @PostMapping("")
    @ApiOperation(value = "Upload a product")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully init")
    })
    public ResponseEntity addProduct(@RequestBody Product product){
        productDomain.add(product);
        return new ResponseEntity(HttpStatus.OK);
    }

}
