package com.cosmos.service;

import com.cosmos.pojo.Address;
import com.cosmos.pojo.Invoice;
import com.cosmos.pojo.Product;
import com.cosmos.pojo.UserCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class InvoiceService {
    private String addressUrl="http://ADDRESS-SERVICE/address/";
    private String cartUrl="http://CARTS-SERVICE/cart/";
    private String productUrl="http://PRODUCTS-SERVICE/product/";
    @Autowired
    private RestTemplate restTemplate;
    public Invoice getInvoiceByMobileNumber(Long mobileNumber) {
        Invoice invoice = new Invoice();
        invoice.setContactNumber(mobileNumber);
        log.info("getInvoiceByMobileNumber called for :"+mobileNumber);
        Address address = restTemplate.getForObject(addressUrl+mobileNumber, Address.class);
        log.info("Address for the mobile Number is :"+address);
        invoice.setAddress(address);
        UserCart userCart=restTemplate.getForObject(
                cartUrl+mobileNumber,UserCart.class
        );
        userCart.getItems().stream().forEach(
                item -> {
                    Product product =restTemplate.getForObject(productUrl+item.getProductId(), Product.class);
                    item.setProduct(product);
                }
        );
        invoice.setItems(userCart.getItems());
        return invoice;
    }
}
