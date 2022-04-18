package com.hoadaknong.web_shop_online;

import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.entities.ProductCategory;
import com.hoadaknong.web_shop_online.repositories.ProductRepository;
import com.hoadaknong.web_shop_online.services.UploadFileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class WebShopOnlineApplicationTests {
	@Autowired
	ProductRepository repo;

	@Autowired
	UploadFileService uploadFileService;

	@Test
	void contextLoads() {
	}

	@Test
	public void addProduct(){
		Product p = new Product();
		p.setName("Main");
		repo.save(p);
	}

	@Test
	public void deleteFile(){
		uploadFileService.deleteFile("fac415beb41d4b588b0908c7f3ca9944.jpg");
	}
}
