package com.cosmos.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.cosmos.pojo.ItemGist;
import com.cosmos.pojo.ProductGist;
import com.cosmos.pojo.UserCartGist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmos.model.UserCart;
import com.cosmos.repository.ItemRepository;
import com.cosmos.repository.UserCartRepository;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserCartService {
	@Autowired
	private UserCartRepository userCartRepository;
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	RestTemplate restTemplate;

	private String productUrl="http://PRODUCTS-SERVICE/product/";

	public UserCartGist getUserCart(Long mobileNumber) {
		log.info("getUserCart called for : "+mobileNumber);
		UserCart userCart = getUserCartById(mobileNumber);
		UserCartGist userCartGist = new UserCartGist();
		userCartGist.setMobileNumber(mobileNumber);
		Set<ItemGist> itemGistSet = new HashSet<>();
		if(userCart!=null){
			userCart.getItems().forEach(
					item->{
						ItemGist itemGist =new ItemGist();
						ProductGist productGist=restTemplate.getForObject(productUrl+item.getProductId(), ProductGist.class);
						itemGist.setItem(item);
						itemGist.setProduct(productGist);
						itemGistSet.add(itemGist);
					}
			);
			userCartGist.setItemGistSet(itemGistSet);
		}
		log.info("UserCartGist is: "+userCartGist);
		return userCartGist;
	}


	public UserCart getUserCartById(Long mobileNumber) {
		Optional<UserCart> dbcart = userCartRepository.findById(mobileNumber);
		if(dbcart.isPresent()) {
			return dbcart.get();
		}else {
			return null;
		}
	}

	public UserCart saveUserCart(UserCart userCart) {
		// TODO Auto-generated method stub
		UserCart cart = null;
		System.out.println(userCart.getMobileNumber());
		Optional<UserCart> dbcart = userCartRepository.findById(userCart.getMobileNumber());
		if(dbcart.isPresent()) {
			cart= dbcart.get();
			cart.getItems().stream()
			.forEach(i->System.out.println("Before adding UI products i.e only db items: "+i.getItemId()+" "+i.getProductId()+" "+i.getQuantityOfProduct()));
			cart.getItems().addAll(userCart.getItems());
			cart.getItems().stream()
			.forEach(item->item.setCart(userCart));
			cart =userCartRepository.save(cart);
		}else{
			userCart.getItems().stream()
			.forEach(item->item.setCart(userCart));
			cart =userCartRepository.save(userCart);
		}
		cart.getItems().stream()
		.forEach(i->System.out.println(i.getItemId()+" "+i.getProductId()));
		return cart;
	}

	public void updateUserCart(Long mobileNumber, UserCart cart) {
		System.out.println("updateUserCart called");
		Optional<UserCart> dbcart = userCartRepository.findById(mobileNumber);
		if(dbcart.isPresent()) {
			userCartRepository.delete(dbcart.get());
		}
		cart.getItems().stream()
		.forEach(i->System.out.print(i.getItemId()));
		cart.getItems().removeIf(i-> i.getQuantityOfProduct()==0);
		cart.getItems().stream()
		.forEach(item->item.setCart(cart));
		userCartRepository.save(cart);
		//cart.getItems().stream()
		//.forEach(i->itemRepository.save(i));
		//System.out.println("updateUserCart called and ended successfully");
		
	}

	public void deleteUserCartForUser(Long mobileNumber) {
		Optional<UserCart> dbcart = userCartRepository.findById(mobileNumber);
		if(dbcart.isPresent()) {
			userCartRepository.delete(dbcart.get());
		}
	}
}
