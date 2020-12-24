package com.example.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.City;

public interface CityRepo extends JpaRepository<City, Serializable>{

}
