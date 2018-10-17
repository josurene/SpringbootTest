package com.josu.work.demo.domain.implementation;

import com.josu.work.demo.domain.ProductDomain;
import com.josu.work.demo.exception.InitException;
import com.josu.work.demo.models.Discount;
import com.josu.work.demo.models.Product;
import com.josu.work.demo.models.Products;
import com.josu.work.demo.repositorys.DiscountRepository;
import com.josu.work.demo.repositorys.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Optional;
import java.util.logging.Logger;

@Slf4j
public class ProductDomainImpl implements ProductDomain {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Override
    public void init() {
        try{
            File xml = new File(getClass().getClassLoader().getResource("products.xml").getFile());
            JAXBContext jc = JAXBContext.newInstance(new Class[]{Products.class,Product.class, Discount.class});

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Products products = (Products) unmarshaller.unmarshal(xml);

            products.getProduct().forEach(product -> {
                if(!discountRepository.existsById(product.getId())){
                    discountRepository.save(product.getDiscount());
                }
            });

            productRepository.saveAll(products.getProduct());

        }catch (NullPointerException e){
            log.error("Product Domain",e);
            throw new InitException("File not found",e);
        }catch (JAXBException d){
            log.error("Product Domain",d);
            throw new InitException("Parse error",d);
        }
    }

    @Override
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> get(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void add(Product product) {

        if(!discountRepository.existsById(product.getId())){
            discountRepository.save(product.getDiscount());
        }

        productRepository.save(product);
    }
}
