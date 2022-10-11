package com.saleslead.product.service;

import com.saleslead.product.dto.ProductDTO;
import com.saleslead.product.dto.ProductVO;
import com.saleslead.product.entity.Product;
import com.saleslead.product.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Long addProduct(ProductVO vo){
        Product bean = new Product();
        BeanUtils.copyProperties(vo,bean);
        productRepository.save(bean);
        return bean.getProductId();
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    public ProductDTO getProduct(Long id){
       Product originalProduct = checkProduct(id);
       return toDTO(originalProduct);
    }
    private ProductDTO toDTO (Product originalProduct){
        ProductDTO bean =new ProductDTO();
        BeanUtils.copyProperties(originalProduct, bean);
        return bean;
    }

    public void updateProduct(Long id, ProductVO vo){
        Product bean = checkProduct(id);
        BeanUtils.copyProperties(vo, bean);
        productRepository.save(bean);
    }

    private Product checkProduct(Long id){
    return productRepository.findById(id)
            .orElseThrow(()-> new NoSuchElementException("the product is not available" +id));
    }
    //get a list of all products
    public ResponseEntity<List<Product>> getProducts(){
        try {
            return  new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public ResponseEntity<Product> getProductByName(String productName){
        try {
            Optional<Product> product = Optional.ofNullable(productRepository.findByProductName(productName));
            //check if product exists in DB
            if(product.isPresent()){
                return new ResponseEntity<>(product.get(),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Product> getProductByCode(String productCode){
        try {
            Optional<Product> productC = Optional.ofNullable(productRepository.findByProductCode(productCode));
            //check if product code exists in DB
            if(productC.isPresent()){
                return new ResponseEntity<>(productC.get(),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
