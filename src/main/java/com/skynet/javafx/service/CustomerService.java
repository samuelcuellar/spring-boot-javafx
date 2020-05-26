package com.skynet.javafx.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skynet.javafx.model.Customer;
import com.skynet.javafx.repository.CustomerRepository;

@Service
public class CustomerService implements FrameService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);	
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> getData() {
		Iterable<Customer> it = customerRepository.findAll();
		List<Customer> result = new ArrayList<>();
		it.forEach(result::add);
		return result;
	}

	@Override
	public void delete(Long id) {
		logger.debug("deleting customer with id: {}", id);		
		customerRepository.deleteById(id);		
	}

	public void save(Customer customer) {
		logger.debug("Saving customer: {}", customer);		
		customerRepository.save(customer);
	}

}
