package com.example.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.City;

public interface CityRepo extends JpaRepository<City, Serializable>{
	public List<City> findBySid(Integer sid);
}
