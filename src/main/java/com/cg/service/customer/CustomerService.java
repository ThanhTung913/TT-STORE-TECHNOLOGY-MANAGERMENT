package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CustomerService extends IGeneralService<Customer> {

    List<CustomerDTO> findAllCustomerDTO();

    Optional<CustomerDTO> findCustomerDTOById(Long id);


    Customer saveNoPassword(Customer customer);
}