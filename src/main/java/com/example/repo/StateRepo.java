package com.example.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.State;

public interface StateRepo extends JpaRepository<State, Serializable>{

}
