package com.retail.e_com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table (name = "seller")
@Entity
@Getter
@Setter

public class Seller extends User{

	
}
