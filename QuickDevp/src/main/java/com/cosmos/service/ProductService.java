package com.cosmos.service;

import com.cosmos.pojo.Inventory;
import com.cosmos.pojo.Product;
import com.cosmos.pojo.Purchase;
import com.cosmos.pojo.Products;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    @Autowired
    RestTemplate restTemplate;

    private String productUrl="http://PRODUCTS-SERVICE/product/";
    private String inventoryUrl="http://INVENTORY-SERVICE/inventory/";

    public Optional<Purchase> getProductById(int productId) {
        return null;
    }

    public Products getAllProducts() {
        return null;
    }

    public Purchase saveProduct(Purchase purchase) {
        log.info(purchase.toString());
        Product newProduct = restTemplate.postForObject(productUrl,purchase.getProduct(), Product.class);
        log.info(newProduct.toString());
        Inventory oldInventory = purchase.getInventoryCreate();
        oldInventory.setProductId(newProduct.getProductId());
        Inventory newInventory = createAndSave(oldInventory);
        log.info(newInventory.toString());
        return null;
    }
    private Inventory createAndSave(Inventory inventory){
        inventory.setPurchaseDate(LocalDate.now());
        String duration = inventory.getBestBeforeDays();
        LocalDate mfgDate = inventory.getMfgDate();
        LocalDate expiryDate = null;
        switch (duration){
            case "15 Days":
                expiryDate = mfgDate.plusDays(15);
                break;
            case "30 Days":
                expiryDate = mfgDate.plusDays(30);
                break;
            case "3 Months":
                expiryDate = mfgDate.plusMonths(3);
                break;
            case "6 Months":
                expiryDate = mfgDate.plusMonths(6);
                break;
            case "1 Year":
                expiryDate = mfgDate.plusYears(1);
                break;
            case "Other":
                expiryDate = mfgDate.plusYears(3);
                break;
            default:
                expiryDate = mfgDate.plusYears(2);
                break;
        }
        inventory.setExpiryDate(expiryDate);
        log.info("New Expiry date is"+inventory.toString());
        Inventory newInventory = restTemplate.postForObject(inventoryUrl,inventory, Inventory.class);
        return newInventory;
    }
}
