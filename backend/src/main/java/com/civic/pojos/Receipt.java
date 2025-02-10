package com.civic.pojos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Receipt extends BaseEntity {

	@Temporal(TemporalType.DATE)
	private LocalDate receipt_date;

	private String razorpay_payment_id;

	private String razorpay_order_id;

}
