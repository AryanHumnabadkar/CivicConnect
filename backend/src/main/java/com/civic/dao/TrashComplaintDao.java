package com.civic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civic.pojos.TrashComplaint;

public interface TrashComplaintDao extends JpaRepository<TrashComplaint, Long>{
	//may also handle some fxnality related to permits and receipts thru jpql?

}
