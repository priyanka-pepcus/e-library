package com.pepcus.crud.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.pepcus.crud.entity.Address;
import com.pepcus.crud.entity.Employee;

@Component
public class EmployeeSpecsUtil {

  public static Specification<Employee> getEmployeesByNameSpec(String firstName) {
    return ((root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get("firstName"), firstName);
    });

  }

  public static Specification<Employee> getEmployeesByAddressSpec(List<Address> address) {
    return ((root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get("address"), address);
    });

  }

  public static Specification<Employee> getEmployeesByAgeSpec(int age) {
    return ((root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get("age"), age);
    });

  }

  public static Specification<Employee> getEmployeesByPincodeSpec(String pincode) {
    return ((root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      Join<Employee, Address> addressJoin = root.join("addressList", JoinType.INNER);
//      System.out.println("======pincode========"+pincode);
//      return (pincode != null && !pincode.isEmpty()) ? criteriaBuilder.equal(addressJoin.get("pincode"), pincode)
//          : null;
      if (pincode != null && !pincode.isEmpty()) {
        predicates.add(criteriaBuilder.equal(addressJoin.get("pincode"), pincode));
    }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    });

  }
}
