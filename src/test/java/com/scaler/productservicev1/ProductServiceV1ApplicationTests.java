package com.scaler.productservicev1;

import com.scaler.productservicev1.repositories.CategoryRepository;
import com.scaler.productservicev1.repositories.ProductRepository;
import com.scaler.productservicev1.repositories.Projections.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ProductServiceV1ApplicationTests {
	private ProductRepository productRepository ;
	private CategoryRepository categoryRepository;
	@Autowired
	public ProductServiceV1ApplicationTests(ProductRepository productRepository,
									   CategoryRepository categoryRepository){
		this.productRepository = productRepository ;
		this.categoryRepository = categoryRepository ;
	}

	@Test
	@Transactional
	@Commit
	void contextLoads() {

			List<ProductInfo> productInfoList = productRepository.someQuery(1L) ;


			for( ProductInfo productInfo : productInfoList){
				System.out.println(productInfo.getId());
				System.out.println(productInfo.getTitle() );
				System.out.println(productInfo.getPrice() );
			}

	}

}
