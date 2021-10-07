package com.cosmos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "ContactsList")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5511025153830068925L;
	@Id
	private Long mobileNumber;
	private String userName;
	private String location;
	private String userSource;
	private boolean gotWhatsapp;
	private boolean usefull;
	private LocalDate addedDate;

}
