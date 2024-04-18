package com.retail.e_com.entity;

import com.retail.e_com.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table (name = "users")
@Entity
@Inheritance ( strategy =  InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY )
	private int userId;

	@Column ( unique = true)
	private String username;
	
	private String displayname;
	
	@Column ( unique = true)
	private String email;
	
	
	private String password;

	private UserRole role;

	private boolean isEmailVerified;
	
	private boolean isDeleted;
}