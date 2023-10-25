
package com.ctel.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctel.entity.Customer;
import com.ctel.repository.CustomerRepo;

@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	CustomerRepo custrepo;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	public ResponseEntity<?> custRegister(Customer customer) {
		// Encode Password
		customer.setPassword(pwdEncoder.encode(customer.getPassword()));

		if (!customer.getFirstName().isEmpty() && !customer.getLastName().isEmpty() && !customer.getAddress().isEmpty()
				&& customer.getContactNo() > 999999999 && customer.getContactNo() < 10000000000L
				&& !customer.getCity().isEmpty()
				&& (customer.getGender().equalsIgnoreCase("male") || customer.getGender().equalsIgnoreCase("female"))
				&& !customer.getEmailId().isEmpty() && customer.getPassword().length() > 8) {
//			customer.setRoles(new HashSet<String>(Arrays.asList("customer")));
			Customer cust = custrepo.save(customer);
			return ResponseEntity.ok().body("customer registered successfully" + cust);
		} else
			return ResponseEntity.ok().body(
					"Check the input Values : First Name & Last Name cant be null Invalid Phone Number/Email & password length should be > 8 chars");
	}

	public Customer findByEmailId(String emailId) {
		return custrepo.findByEmailId(emailId);
	}

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

		System.out.println(emailId);
		Customer cust = custrepo.findByEmailId(emailId);
//		System.out.println(cust.toString());
		System.out.println(">>>>>>>>>>>>>>");
		
		if (cust == null) {
			throw new UsernameNotFoundException(emailId + "not found");
		}
		
		System.out.println(">>>>>>>>>>>>>><<<<<<<<<<<<<<<<");
		return new org.springframework.security.core.userdetails.User(emailId, cust.getPassword(),
				cust.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
	}
	
	public Customer updateCustomer(Integer cid, Customer customer)
	{
		Optional<Customer> cust = custrepo.findById(cid);
		if(cust.isPresent()==true)
		{
			Customer cst = cust.get();
			if(!customer.getFirstName().equals(cst.getFirstName()))
					cst.setFirstName(customer.getFirstName());
			if(!cst.getLastName().equals(customer.getLastName()))
				cst.setLastName(customer.getLastName());
			if(!customer.getAddress().equals(cst.getAddress()))
				cst.setAddress(customer.getAddress());
			if(!customer.getCity().equals(cst.getCity()))
				cst.setCity(customer.getCity());
			if(!customer.getContactNo().equals(cst.getContactNo()))
				cst.setContactNo(customer.getContactNo());
			if(!customer.getState().equals(cst.getState()))
				cst.setState(customer.getState());
			if(!customer.getCountry().equals(cst.getCountry()))
				cst.setCountry(customer.getCountry());
			if(!customer.getGender().equals(cst.getGender()))
				cst.setGender(customer.getGender());
			if(!customer.getEmailId().equals(cst.getEmailId()))
				cst.setEmailId(customer.getEmailId());
			if(!customer.getPassword().equals(cst.getPassword()))
				cst.setPassword(pwdEncoder.encode(customer.getPassword()));
			Customer  custt = custrepo.save(cst);
			return custt;	
		}
		else
			return null;
	}
	
	
	public Customer viewCustDetails(Integer cid)
	{
		Optional<Customer> cust = custrepo.findById(cid);
		if(cust.isPresent()==true)
		{
			Customer customer = cust.get();
			return customer;
		}
		else
			return null;
		
	}
	
	public List<Customer> viewAllCusts()
	{
		List<Customer> custList = custrepo.findAll();
		if(custList != null)
			return custList;
		else
			return null;
	}
	
	public Customer deleteCust(Integer cid)
	{
		Optional<Customer> cust = custrepo.findById(cid);
		if(cust.isPresent()==true) {
			Customer customer = cust.get();
			custrepo.delete(customer);
	      return customer;
		}
		else
			return null;
			
	
	}
}
