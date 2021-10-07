package com.cosmos.controller;

import com.cosmos.pojo.UserCartGist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cosmos.model.UserCart;
import com.cosmos.service.UserCartService;

@RestController
@RequestMapping("/cart")
public class UserCartController {
	@Autowired
	private UserCartService userCartService;
	@GetMapping("/{mobileNumber}")
	public UserCartGist getUserCartById(@PathVariable Long mobileNumber) {
		return userCartService.getUserCart(mobileNumber);
	}
	@PostMapping
	public UserCart saveUserCart(@RequestBody UserCart userCart) {
		return userCartService.saveUserCart(userCart);
	}
	@PutMapping("/update/{mobileNumber}")
	public void updateUserCart(@PathVariable Long mobileNumber,@RequestBody UserCart userCart) {
		userCartService.updateUserCart(mobileNumber,userCart);
	}
	@GetMapping
	public String getUserCarts(){
		return "UserCart is empty";
	}
	@DeleteMapping("/{mobileNumber}")
	public void deleteUserCartForUser(@PathVariable Long mobileNumber){
		userCartService.deleteUserCartForUser(mobileNumber);
	}

}
