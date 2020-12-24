package com.example.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country_master")
public class Country {
	@Id
	
	private Integer cid;
	
	private String cname;
	
	
}
