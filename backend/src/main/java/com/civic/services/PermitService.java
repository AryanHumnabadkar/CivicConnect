package com.civic.services;

import com.civic.pojos.Permit;
import com.civic.pojos.PermitStatus;
import com.civic.pojos.Receipt;

public interface PermitService {
	Permit issuePermit(Permit permitDetails);
	String updatePermitStatus(long permitId, PermitStatus status);

}
