package com.scaler.productservicev1.services;

import com.scaler.productservicev1.DTOs.FakeStoreProductDTO;
import com.scaler.productservicev1.models.Category;
import com.scaler.productservicev1.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate ;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }


    private Product convertFakeStoreProductDTOtoProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product() ;

        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageURL(fakeStoreProductDTO.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());

        return product ;
    }
    public FakeStoreProductDTO getFakeStoreProductDTO(Product product){
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO() ;

        fakeStoreProductDTO.setTitle(product.getTitle()) ;
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setCategory( product.getCategory().getName() );
        fakeStoreProductDTO.setImage(product.getImageURL());

        return fakeStoreProductDTO ;
    }
    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);


        return convertFakeStoreProductDTOtoProduct(fakeStoreProductDTO) ;
    }

    @Override
    public List<Product> getAllProducts(){

        FakeStoreProductDTO[] fakeStoreProductDTOs = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class) ;

        List<Product> products = new ArrayList<>() ;
        for( FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOs){
            products.add(convertFakeStoreProductDTOtoProduct(fakeStoreProductDTO)) ;
        }

        return products ;
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = getFakeStoreProductDTO(product) ;


        RequestCallback requestCallback = restTemplate.httpEntityCallback(
                fakeStoreProductDTO, FakeStoreProductDTO.class );
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class,
                        restTemplate.getMessageConverters());

        FakeStoreProductDTO response = restTemplate.execute(
                "https://fakestoreapi.com/products", HttpMethod.POST, requestCallback, responseExtractor);

        return convertFakeStoreProductDTOtoProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = getFakeStoreProductDTO(product) ;

        RequestCallback requestCallback = restTemplate.httpEntityCallback(
                                                fakeStoreProductDTO ,
                                                    FakeStoreProductDTO.class);

        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class,
                        restTemplate.getMessageConverters());

        FakeStoreProductDTO response = restTemplate.execute(
                "https://fakestoreapi.com/products/"+id,
                HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductDTOtoProduct(response);
    }


}
