package com.cg.model.dto;


import com.cg.model.Customer;
import com.cg.model.LocationRegion;
import com.cg.model.Role;
import com.cg.service.role.IRoleService;
import com.cg.service.role.RoleServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerDTO {
    private Long id;

    private String fullName;

    private String userName;

    private String password;

    private String email;

    private String phone;

    private LocationRegionDTO locationRegion;

    private Long role_id;

    private String role;

    private boolean deleted;

    public CustomerDTO(Long id, String fullName, String userName, String password,
                       String email, String phone, LocationRegion locationRegion, Long role_id, String role) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
        this.role_id = role_id;
        this.role = role;

    }


    public Customer toCustomer(){
        IRoleService roleService = new RoleServiceImpl();
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setUserName(userName)
                .setPassword(password)
                .setEmail(email)
                .setPhone(phone)
                .setLocationRegion(locationRegion.toLocationRegion())
                .setRole(roleService.findById(role_id).get())
                .setDeleted(deleted);
    }
}
