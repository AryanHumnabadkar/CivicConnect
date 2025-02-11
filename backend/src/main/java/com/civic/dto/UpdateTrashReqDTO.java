package com.civic.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTrashReqDTO {
	private LocalDate serviceDate;
}
