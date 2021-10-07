package com.cosmos.repository;

import com.cosmos.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long>{
	
	@Query("SELECT e from Users e where e.addedDate =:date ")       
    List<Users> findByDate(@Param("date") LocalDate date);
	
	@Query("SELECT u from Users u where u.gotWhatsapp =:gotWhatsapp ")       
    List<Users> findWhatsappOnly(@Param("gotWhatsapp") boolean gotWhatsapp);

}
