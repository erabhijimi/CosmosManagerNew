package com.cosmos.controller;

import com.cosmos.model.Users;
import com.cosmos.model.UsersList;
import com.cosmos.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;

	// Get All users
	@GetMapping
	public UsersList getAllUsers() {
		log.info("getAllUsers called");
		return userService.getAllUsers();
	}

	// Save/Update a user
	@PostMapping
	public Users saveUser(@RequestBody Users user) {
		log.info("saveUser got called");
		return userService.saveUser(user);
	}

	// Get user by mobileNumber
	@GetMapping("/{mobileNumber}")
	public Optional<Users> getUserByMobileNumber(@PathVariable Long mobileNumber) {
		log.info("getUserByMobileNumber called for :"+mobileNumber);
		return userService.getUserByMobileNumber(mobileNumber);
	}


	// Get all users added yesterday
	@GetMapping("/yesterday")
	public Optional<List<Users>> getAllUsersAddedYesterday() {
		return userService.getAllUsersAddedYesterday();
	}
	@GetMapping("/vwhatsapponly/{booleanVal}")
	public UsersList getAllUsersWhatsappOnly(@PathVariable boolean booleanVal) {
		log.info("getAllUsers called for whatsapp"+booleanVal);
		return userService.getAllUsersWhatsappOnly(booleanVal);
	}
	@GetMapping("/vbyaddeddate/{addedDate}")
	public List<Users> getAllUsersByAddedDate(@PathVariable LocalDate addedDate) {
		return userService.getAllUsersByAddedDate(addedDate);
	}
	@GetMapping("/readCsv")
	public String readCsv() throws FileNotFoundException {
		return userService.readCsv();
	}

}
