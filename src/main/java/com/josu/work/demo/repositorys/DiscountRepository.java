package com.josu.work.demo.repositorys;

import com.josu.work.demo.models.Discount;
import org.springframework.data.repository.CrudRepository;

public interface DiscountRepository extends CrudRepository<Discount, Integer> {
}
