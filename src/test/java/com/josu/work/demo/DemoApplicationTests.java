package com.josu.work.demo;

import com.josu.work.demo.models.Discount;
import com.josu.work.demo.models.Product;
import com.josu.work.demo.repositorys.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class DemoApplicationTests {

	private TestRestTemplate testRestTemplate = new TestRestTemplate("user","password");

	String url = "http://localhost:8080/products";

	@Autowired
	ProductRepository productRepository;

	@Before
	public void setUp() {

	}

	@Test
	public void initTest() {
		ResponseEntity<String> response = testRestTemplate.getForEntity(url+"/init", String.class);
		assertThat(response.getStatusCodeValue(),is(200));
	}

	@Test
	public void getAll(){
		initTest();

		ResponseEntity<List<Product>> response =
				testRestTemplate.exchange(url+"/all",
						HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
						});
		assertThat(response.getStatusCodeValue(),is(200));
		assertThat(response.getBody(),is(notNullValue()));
		assertThat(response.getBody().size(),is(2));
		assertThat(response.getBody().get(0).getName(),is("Camiseta"));
	}

	@Test
	public void get(){
		initTest();
		ResponseEntity<Product> response = testRestTemplate.getForEntity(url+"/1", Product.class);
		assertThat(response.getStatusCodeValue(),is(200));
		assertThat(response.getBody(),is(notNullValue()));
		assertThat(response.getBody().getName(),is("Camiseta"));
	}

	@Test
	public void add(){
		initTest();
		Product product = new Product();
		product.setId(3);
		product.setName("Polo");
		product.setPrice(100d);
		product.setUpc(10011003d);

		Discount discount = new Discount();
		discount.setId(3);
		discount.setValue(0d);

		product.setDiscount(discount);

		ResponseEntity<String> response = testRestTemplate.postForEntity(url+"/add", product ,String.class);
		assertThat(response.getStatusCodeValue(),is(200));

		ResponseEntity<Product> responseGet = testRestTemplate.getForEntity(url+"/3", Product.class);
		assertThat(responseGet.getStatusCodeValue(),is(200));
		assertThat(responseGet.getBody(),is(notNullValue()));
		assertThat(responseGet.getBody().getName(),is("Polo"));
	}

	@After
	public void tearDown() {

	}

}
