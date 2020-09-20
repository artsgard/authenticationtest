package com.artsgard.athenticationtest.repository;

import com.artsgard.athenticationtest.model.TestResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultRepository extends JpaRepository<TestResultEntity, Long> {

}
