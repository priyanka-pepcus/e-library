package com.pepcus.crud.controller;

import com.pepcus.crud.entity.Product;
import com.pepcus.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService service;

  @PostMapping("/upload")
  public String uploadData(@RequestParam("file") MultipartFile file) throws IOException {
    return service.uploadData(file);
  }

  @PostMapping("/addProduct")
  public Product addProduct(@RequestBody Product product) {
    return service.saveProduct(product);
  }

  @PostMapping("/addProducts")
  public List<Product> addProducts(@RequestBody List<Product> products) {
    return service.saveProducts(products);
  }

  @GetMapping("/")
  public List<Product> findAllProducts() {
    return service.getProducts();
  }

  @GetMapping("/{id}")
  public Product findProductById(@PathVariable int id) {
    return service.getProductById(id);
  }

  @GetMapping("/{name}")
  public Product findProductByName(@PathVariable String name) {
    return service.getProductByName(name);
  }

  @PutMapping("/")
  public Product updateProduct(@RequestBody Product product) {
    return service.updateProduct(product);
  }

  @DeleteMapping("/{id}")
  public String deleteProduct(@PathVariable int id) {
    return service.deleteProduct(id);
  }

}
