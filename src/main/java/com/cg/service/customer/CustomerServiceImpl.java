package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.LocationRegion;
import com.cg.model.dto.CustomerDTO;
import com.cg.repository.CustomerRepository;
import com.cg.repository.LocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LocationRegionRepository locationRegionRepository;


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.getById(Long.valueOf(id));
    }

    @Override
    public List<CustomerDTO> findAllCustomerDTO() {
        return null;
    }

    @Override
    public Optional<CustomerDTO> findCustomerDTOById(Long id) {
        return customerRepository.findCustomerDTOById(id);
    }


    @Override
    public Customer save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        LocationRegion locationRegion = locationRegionRepository.save(customer.getLocationRegion());
        customer.setLocationRegion(locationRegion);
        return customerRepository.save(customer);
    }

    @Override
    public Customer saveNoPassword(Customer customer) {
        LocationRegion locationRegion = locationRegionRepository.save(customer.getLocationRegion());
        customer.setLocationRegion(locationRegion);
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void remove(String id) {

    }


}
