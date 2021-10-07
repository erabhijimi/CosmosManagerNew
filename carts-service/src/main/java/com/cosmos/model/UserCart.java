package com.cosmos.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "usercart001")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCart {
	@Id
	private Long mobileNumber;
	@OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Item> items;

	
}
