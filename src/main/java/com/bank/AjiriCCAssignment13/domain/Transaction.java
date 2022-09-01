package com.bank.AjiriCCAssignment13.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction 

{
	private Long transactionId;
	private Double amount;
	private String type;
	private LocalDateTime transactionDate;
	private Account account;

	public Transaction() 
	
	{
		
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getTransactionId() 
	
	{
		return transactionId;
	}

	public void setTransactionId(Long transactionId) 
	
	{
		this.transactionId = transactionId;
	}

	public Double getAmount() 
	
	{
		return amount;
	}

	public void setAmount(Double amount) 
	
	{
		this.amount = amount;
	}

	@Column(length=1)
	public String getType() 
	
	{
		return type;
	}

	public void setType(String type) 
	
	{
		this.type = type;
	}

	public LocalDateTime getTransactionDate() 
	
	{
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) 
	
	{
		this.transactionDate = transactionDate;
	}

	@ManyToOne
	@JoinColumn(name="account_id")
	public Account getAccount() 
	
	{
		return account;
	}

	public void setAccount(Account account) 
	
	{
		this.account = account;
	}

}
