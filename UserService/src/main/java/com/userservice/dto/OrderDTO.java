package com.userservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private int orderId;

	private double amountPaid;

	private int customerId;

	private String modeOfPayment;

	private Date orderDate;

	private String orderStatus;

	private int cartId;
}
