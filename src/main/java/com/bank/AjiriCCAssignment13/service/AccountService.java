package com.bank.AjiriCCAssignment13.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.AjiriCCAssignment13.domain.Account;
import com.bank.AjiriCCAssignment13.repository.AccountRepository;

@Service
public class AccountService 

{
	@Autowired
	private AccountRepository accountRepository;
	
	public void saveAccount(Account account)
	
	{
		this.accountRepository.save(account);
	}
	
	public Account findById(Long accountId)
	
	{
		Optional<Account> optionalAccount = this.accountRepository.findById(accountId);
		return optionalAccount.orElse(new Account());
	}
}
