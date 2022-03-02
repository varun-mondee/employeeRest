package com.rest.repository;

import com.rest.model.Leave;

  import org.springframework.data.jpa.repository.JpaRepository;
  
  public interface LeaveRepository extends JpaRepository<Leave, Integer> { }
  
