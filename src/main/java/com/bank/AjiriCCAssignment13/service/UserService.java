package com.bank.AjiriCCAssignment13.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.AjiriCCAssignment13.domain.Account;
import com.bank.AjiriCCAssignment13.domain.Address;
import com.bank.AjiriCCAssignment13.domain.User;
import com.bank.AjiriCCAssignment13.repository.AccountRepository;
import com.bank.AjiriCCAssignment13.repository.AddressRepository;
import com.bank.AjiriCCAssignment13.repository.UserRepository;

@Service
public class UserService 

{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public void save(User user)
	
	{
		this.userRepository.save(user);
	}
	
	public List<User> findAll()
	
	{
		return this.userRepository.findAll();
	}
	
	public User findById(Long userId)
	
	{
		Optional<User> optionalUser = this.userRepository.findById(userId);
		return optionalUser.orElse(new User());
	}
	
	public Address findAddress(Long userId)
	
	{
		Address address = this.findById(userId).getAddress();
		
		if (address == null)
			
		{
			return new Address();
		}
		
		return address;
	}

	public User saveUser(User user) 
	
	{
		if (user.getUserId() != null)
			
		{
			Address address = user.getAddress();
			
			if (address != null)
				
			{
				address.setUserId(user.getUserId());
				address.setUser(user);
				user.setAddress(address);
			}
		}
		
		return this.userRepository.save(user);
	}

	public void delete(Long userId) 
	
	{
		User user = findById(userId);
		
		if (user.getAddress() != null)
			
		{
			this.addressRepository.delete(user.getAddress());
		}
		
		/*
		if (user.getAccounts().size() > 0)
			
		{
			for (Account account : user.getAccounts())
			{
				Long accountId = account.getAccountId();
				
				if (account.getUsers().size() < 2)
				{
					this.accountRepository.deleteById(accountId);
				}
			}
		}
		*/
		
		this.userRepository.deleteById(userId);
	}

	public void addAccount(Long userId, Account account) 
	
	{
		User user = findById(userId);
		account.getUsers().add(user);
		user.getAccounts().add(account);
		this.accountRepository.save(account);
	}
	
}
