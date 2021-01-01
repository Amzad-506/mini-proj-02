package com.example.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.State;

public interface StateRepo extends JpaRepository<State, Serializable>{
	
	public List<State> findByCid(Integer cid);
	
}
