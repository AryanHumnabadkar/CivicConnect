package com.civic.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import com.civic.pojos.EventType;
import com.civic.pojos.PermitStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePermitResponseDTO {
    private Long id;
    private String name;
    private String description;
    private EventType eventType;
    private LocalDate date;
    private LocalTime time;
    private String userName;
    private String sectorName;
    private PermitStatus status;
    private Double receiptAmount;
}