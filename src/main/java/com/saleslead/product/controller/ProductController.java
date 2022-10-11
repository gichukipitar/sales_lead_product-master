package com.saleslead.product.controller;

import com.saleslead.product.dto.ProductDTO;
import com.saleslead.product.dto.ProductVO;
import com.saleslead.product.entity.Product;
import com.saleslead.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping()
    public String addProduct(@RequestBody ProductVO vo){
        return productService.addProduct(vo).toString();
    }
    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") Long id){
        return productService.getProduct(id);
    }
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable ("id") Long id, @RequestBody ProductVO vo){
        productService.updateProduct(id,vo);
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> listAllProducts(){
        return productService.getProducts();
        
    }
    @GetMapping("/productname/{productName}")
    public ResponseEntity<Product> getProductByName(@PathVariable ("productName") String productName){
        return productService.getProductByName(productName);
    }
    @GetMapping("/productcode/{productCode}")
    public ResponseEntity<Product> getProductByCode(@PathVariable ("productCode") String productCode){
        return productService.getProductByCode(productCode);
    }

}
