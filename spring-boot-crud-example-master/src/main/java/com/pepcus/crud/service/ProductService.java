package com.pepcus.crud.service;

import com.pepcus.crud.entity.Product;
import com.pepcus.crud.repository.ProductRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  public String uploadData(MultipartFile file) throws IOException {
    List<Product> productList = new ArrayList<>();
    // get all inputStream from file
    InputStream inputStream = file.getInputStream();
    CsvParserSettings settings = new CsvParserSettings();
    // remove header feed in db
    settings.setHeaderExtractionEnabled(true);
    CsvParser parser = new CsvParser(settings);
    // @parser and get all records from inputStream
    List<Record> parseAllRecord = parser.parseAllRecords(inputStream);
    parseAllRecord.forEach(record -> {
      Product product = new Product();
      product.setName(record.getString("name"));
      product.setPrice(Double.parseDouble(record.getString("quantity")));
      product.setQuantity(Integer.parseInt(record.getString("price")));
      productList.add(product);
    });
    repository.saveAll(productList);

    return "Upload Successfull !!";
  }

  public Product saveProduct(Product product) {
    return repository.save(product);
  }

  public List<Product> saveProducts(List<Product> products) {
    return repository.saveAll(products);
  }

  public List<Product> getProducts() {
    return repository.findAll();
  }

  public Product getProductById(int id) {
    return repository.findById(id).orElse(null);
  }

  public Product getProductByName(String name) {
    return repository.findByName(name);
  }

  public String deleteProduct(int id) {
    repository.deleteById(id);
    return "product removed !! " + id;
  }

  public Product updateProduct(Product product) {
    Product existingProduct = repository.findById(product.getId()).orElse(null);
    existingProduct.setName(product.getName());
    existingProduct.setQuantity(product.getQuantity());
    existingProduct.setPrice(product.getPrice());
    return repository.save(existingProduct);
  }

}
