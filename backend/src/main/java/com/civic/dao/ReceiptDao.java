package com.civic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.Receipt;

public interface ReceiptDao extends JpaRepository<Receipt, Long>{

}
