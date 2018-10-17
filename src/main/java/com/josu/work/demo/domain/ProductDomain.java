package com.josu.work.demo.domain;

import com.josu.work.demo.models.Product;

import java.util.Optional;

public interface ProductDomain {
    void init();

    Iterable<Product> getAll();

    Optional<Product> get(Integer id);

    void add(Product product);
}
