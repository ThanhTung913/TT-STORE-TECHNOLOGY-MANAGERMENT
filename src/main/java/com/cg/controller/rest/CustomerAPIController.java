package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Customer;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.customer.CustomerService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPIController {
    @Autowired
    private AppUtils appUtils;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        List<CustomerDTO> customerDTOList = customerService.findAllCustomerDTO();

        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable long id) {
        Optional<Customer> customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            throw new ResourceNotFoundException("Invalid customer ID");
        }

        return new ResponseEntity<>(customerOptional.get().toCustomerDTO(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody CustomerDTO customerDTO) {


        customerDTO.setId(0L);
        customerDTO.getLocationRegion().setId(0L);





        Customer newCustomer = customerService.save(customerDTO.toCustomer());

        return new ResponseEntity<>(newCustomer.toCustomerDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        customerDTO.getLocationRegion().setId(0L);

        Customer updatedCustomer = customerService.saveNoPassword(customerDTO.toCustomer());

        return new ResponseEntity<>(updatedCustomer.toCustomerDTO(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doDelete(@PathVariable Long id) {
        System.out.println("delete method");
        Optional<CustomerDTO> customer = customerService.findCustomerDTOById(id);

        if (customer.isPresent()) {
            try {
                customer.get().setDeleted(true);
                customerService.save(customer.get().toCustomer());
                return new ResponseEntity<>(HttpStatus.ACCEPTED);

            } catch (DataInputException e) {
                throw new DataInputException("Invalid suspension information");
            }
        } else {
            throw new DataInputException("Invalid apartment information");
        }
    }
}
