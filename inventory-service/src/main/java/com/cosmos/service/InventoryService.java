package com.cosmos.service;

import com.cosmos.model.Inventory;
import com.cosmos.pojo.InventoryGist;
import com.cosmos.pojo.InventoryPojo;
import com.cosmos.pojo.Product;
import com.cosmos.pojo.ProductPojo;
import com.cosmos.repository.InventoryRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    RestTemplate restTemplate;

    private String productUrl="http://PRODUCTS-SERVICE/product/";

    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryGist getInventoryGist() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        List<InventoryPojo> inventoryPojos = new ArrayList<>();
        inventoryList.stream().forEach(i->{
            String urlUsed=productUrl+i.getProductId();
            System.out.println("Url called: "+urlUsed);
            Product productGist=restTemplate.getForObject(urlUsed, Product.class);
            ProductPojo productPojo = new ProductPojo();
            productPojo.setProductName(productGist.getProductName());
            productPojo.setProductMRP(productGist.getProductMRP());
            InventoryPojo inventoryPojo= new InventoryPojo();
            inventoryPojo.setInventory(i);
            inventoryPojo.setProduct(productPojo);
            inventoryPojos.add(inventoryPojo);
        });
        return new InventoryGist(inventoryPojos);
    }

    public Inventory saveInventory(Inventory inventory) {
        log.info("New Log for saveInventory : "+inventory.toString());
        LocalDate today = LocalDate.now();
        inventory.setPurchaseDate(today);
        Inventory newInventory = inventoryRepository.save(inventory);
        return newInventory;
    }
}
