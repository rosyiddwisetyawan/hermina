package com.api.hermina.repository;

import com.api.hermina.model.db.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Integer> {
}