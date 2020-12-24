package com.example.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city_master")
public class City {
	@Id
	private int ctid;
	private String cname;
	private int sid;
	

}
