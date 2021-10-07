package com.cosmos.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.cosmos.pojo.ProductPojo;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.model.Product;
import com.cosmos.pojo.Products;
import com.cosmos.repository.ProuctRepository;

@Service
@Slf4j
public class ProductService {
	@Autowired
	private ProuctRepository productRepository;
	/*
	 * @Autowired private Category category;
	 */

	public Product saveProduct(Product product) {
		product.setActive(true);
		product.setNow(LocalDate.now());
		product.setAddedToCart(false);
		return productRepository.save(product);
	}

	public Products getAllProducts() {
		// TODO Auto-generated method stub
		log.info("getAllProducts called");
		Products product = new Products();
		List<Product> products = productRepository.findAllEnabled();
		log.info("Number of active products =" +products.size());
		product.setProducts(products);
		return product;
	}

	public Product updateProduct(int productId, Product product) {
		return productRepository.save(product);
	}

	public Product cloneProduct(Product product) {
		System.out.println(product.toString());
		product.setProductId(0L);
		product.setNow(LocalDate.now());
		product.setAddedToCart(false);
		return productRepository.save(product);
	}

	public String deleteProduct(Long productId) {
		// TODO Auto-generated method stub
		
		Product product = productRepository.findById(productId).get();
		product.setActive(false);
		productRepository.save(product);
		String response = "Successfully Deleted the product "+product.getProductName() +". It will be available in DB till 30 days incase u want to use in future";
		return response;
	}

	public Product getProductById(Long productId) {
		// TODO Auto-generated method stub
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent()){
			log.info("Found it:"+optionalProduct.get());
			return optionalProduct.get();
		}
		return new Product();
	}

	public Products getAllDeletedProducts() {
		Products products = new Products();
		products.setProducts(productRepository.findAllDissabled());
		return products;
	}

	public String undoProduct(Long productId) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(productId).get();
		product.setActive(true);
		productRepository.save(product);
		String response = "Successfully undone the product "+product.getProductName() ;
		return response;
	}

	public Products getAllStoreProducts() {
		// TODO Auto-generated method stub
		Products products = new Products();
		products.setProducts(productRepository.findAllEnabledAndStore());
		return products;
	}

	public String readCsv() throws FileNotFoundException {
		String path ="D:\\AngulCosmosProject\\ProductList.csv";
		List<ProductPojo> beans = new CsvToBeanBuilder(new FileReader(path))
				.withType(ProductPojo.class)
				.build()
				.parse();
		beans.forEach(s->{
			Product product = new Product();
			product.setProductName(s.getProductName());
			product.setProductUnit(s.getProductType());
			Optional<String> productAmount = Optional.of(s.getProductAmount());
			Optional<String> ProductMRP = Optional.of(s.getProductMRP());
			Optional<String> ProductRealPrice = Optional.of(s.getProductRealPrice());
			Optional<String> ProductSellingPrice = Optional.of(s.getProductSellingPrice());
			productAmount.ifPresent(sp->product.setProductAmount(Float.parseFloat(s.getProductAmount())));
			ProductMRP.ifPresent(sp->product.setProductMRP(Float.parseFloat(s.getProductMRP())));
			ProductRealPrice.ifPresent(sp->product.setProductRealPrice(Float.parseFloat(s.getProductRealPrice())));
			ProductSellingPrice.ifPresent(sp->product.setProductSellingPrice(Float.parseFloat(s.getProductSellingPrice())));
			product.setActive(true);
			System.out.println(product);
			productRepository.save(product);
		});
		return "Success";
	}
}
