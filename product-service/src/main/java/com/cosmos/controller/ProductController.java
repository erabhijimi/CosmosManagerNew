package com.cosmos.controller;

import java.io.FileNotFoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmos.model.Product;
import com.cosmos.pojo.Products;
import com.cosmos.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
		
	@GetMapping("/{productId}")
	public Product getProductById(@PathVariable Long productId) {
		return productService.getProductById(productId);
	}
	
	@GetMapping
	public Products getAllProducts(){
		return productService.getAllProducts();
	}
	@GetMapping("/store")
	public Products getAllStreProducts(){
		return productService.getAllStoreProducts();
	}
	@GetMapping("/deleted")
	public Products getAllDeletedProducts(){
		return productService.getAllDeletedProducts();
	}
	
	@PostMapping
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	@PostMapping("/clone")
	public Product cloneProduct(@RequestBody Product product) {
		return productService.cloneProduct(product);
	}
	
	@PutMapping("/update/{productId}")
	public Product updateProduct(@PathVariable int productId,@RequestBody Product product) {
		return productService.updateProduct(productId,product);
	}
	
	@DeleteMapping("/{productId}")
	public String deleteProduct(@PathVariable Long productId) {
		return productService.deleteProduct(productId);
	}
	@DeleteMapping("/undo/{productId}")
	public String undoProduct(@PathVariable Long productId) {
		return productService.undoProduct(productId);
	}

	@GetMapping("/readCsv")
	public String readCsv() throws FileNotFoundException {
		return productService.readCsv();
	}
}
