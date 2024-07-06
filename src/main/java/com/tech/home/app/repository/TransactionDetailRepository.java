package com.tech.home.app.repository;

import com.tech.home.app.domain.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TransactionDetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {}
