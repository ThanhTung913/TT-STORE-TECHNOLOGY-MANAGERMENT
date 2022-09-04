package com.cg.repository;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT new com.cg.model.dto.CustomerDTO (" +
            "c.id, " +
            "c.fullName, " +
            "c.userName, " +
            "c.email, " +
            "c.password, " +
            "c.phone, " +
            "c.locationRegion, " +
            "c.role.id, " +
            "c.role.name " +
            ") " +
            "FROM Customer AS c WHERE c.deleted = false"
    )
    List<CustomerDTO> findAllCustomerDTO();

    Boolean existsByUserName(String email);

    Boolean existsByUserNameAndIdIsNot(String username, Long id);

    @Query("SELECT new com.cg.model.dto.CustomerDTO (" +
            "c.id, " +
            "c.fullName, " +
            "c.userName, " +
            "c.email, " +
            "c.password, " +
            "c.phone, " +
            "c.locationRegion, " +
            "c.role.id, " +
            "c.role.name " +
            ") " +
            "FROM Customer AS c WHERE c.deleted = false AND c.id = :id"
    )
    Optional<CustomerDTO> findCustomerDTOById(@Param("id") Long id);


}
