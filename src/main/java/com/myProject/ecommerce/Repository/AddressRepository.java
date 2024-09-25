package com.myProject.ecommerce.Repository;

import com.myProject.ecommerce.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
