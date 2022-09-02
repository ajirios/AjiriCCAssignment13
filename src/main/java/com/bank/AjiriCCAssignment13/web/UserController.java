package com.bank.AjiriCCAssignment13.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.AjiriCCAssignment13.domain.Account;
import com.bank.AjiriCCAssignment13.domain.Address;
import com.bank.AjiriCCAssignment13.domain.User;
import com.bank.AjiriCCAssignment13.service.AccountService;
import com.bank.AjiriCCAssignment13.service.UserService;

@Controller
public class UserController 

{
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/")
	public String getRoot(ModelMap model)
	
	{
		return "users";
	}
	
	@GetMapping("/users")
	public String getUsers(ModelMap modelMap)
	
	{
		List<User> users = this.userService.findAll();
		modelMap.put("users", users);
		return "users";
	}
	
	@GetMapping("/users/{userId}")
	public String getUser(ModelMap modelMap, @PathVariable Long userId)
	
	{
		User user = this.userService.findById(userId);
		modelMap.put("users", Arrays.asList(user));
		modelMap.put("user", user);
		return "users";
	}
	
	@PostMapping("/users/{userId}")
	public String postUser(User user, Account account)
	
	{
		if (account != null)
			
		{
			this.userService.addAccount(user.getUserId(), account);
		}
		
		this.userService.saveUser(user);
		return "redirect:/users/" + user.getUserId();
	}
	
	@PostMapping("/users/{userId}/delete")
	public String postUserDeletion(@PathVariable Long userId)
	
	{
		this.userService.delete(userId);
		return "redirect:/users";
	}
	
	@GetMapping("/register")
	public String getUserRegistration(ModelMap modelMap)
	
	{
		modelMap.put("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String postUserRegistration(User user)
	
	{
		this.userService.saveUser(user);
		return "redirect:/register";
	}
	
	@PostMapping("/users/{userId}/accounts")
	public String getAccount(ModelMap modelMap, @PathVariable Long userId)
	
	{
		Account account = new Account();
		User user = this.userService.findById(userId);
		int numberOfAccounts = 1;
		
		if (user.getAccounts() != null)
			
		{
			numberOfAccounts = user.getAccounts().size() + 1;
		}
		
		account.setAccountName("Account #" + numberOfAccounts);
		this.userService.addAccount(userId, account);
		int size = this.userService.findById(userId).getAccounts().size();
		Account re = this.userService.findById(userId).getAccounts().get(size - 1);
		return "redirect:/users/" + userId + "/accounts/" + re.getAccountId();
	}
	
	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String getUserAccount(ModelMap modelMap, @PathVariable Long userId, @PathVariable Long accountId)
	
	{
		modelMap.put("user", this.userService.findById(userId));
		modelMap.put("account", this.accountService.findById(accountId));
		return "account";
	}
	
	@PostMapping("/users/{userId}/accounts/{accountId}")
	public String postUserAccount(@PathVariable Long userId, @PathVariable Long accountId, Account account)
	
	{
		this.accountService.saveAccount(account);
		return "redirect:/users/" + userId + "/accounts/" + accountId;
	}
}
