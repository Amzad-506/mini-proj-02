package com.example.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.Country;

public interface CountryRepo extends JpaRepository<Country, Serializable>{

}
