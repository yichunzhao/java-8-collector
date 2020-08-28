package com.ynz.java8collector.domain;

import com.ynz.java8collector.exceptions.DepartmentNotExistedException;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@ToString
public class Company {
    //a company consists of departments and employees
    private Map<Department, Set<Employee>> departmentEmployees = new HashMap<>();

    public void addDepartment(Department department) {
        departmentEmployees.put(department, new HashSet<>());
    }

    public Set<Department> getAllDepartment() {
        return departmentEmployees.keySet();
    }

    public List<Employee> getAllEmployees() {
        return departmentEmployees.values().stream()
                .flatMap(employees -> employees.stream()).collect(Collectors.toList());
    }

    public void addNewEmployee(Employee employee, Department department) {
        if (departmentEmployees.containsKey(department)) {
            departmentEmployees.get(department).add(employee);
        } else {
            throw new DepartmentNotExistedException("The department is not existed ");
        }
    }

    public List<Employee> findEmployeeByName(String name) {
        return getAllEmployees().stream().filter(employee -> employee.getName().equals(name)).
                collect(Collectors.toList());
    }

    public List<Employee> findEmployeeByDepartmentAndName(Department department, String name) {
        return departmentEmployees.get(department).stream().filter(employee -> employee.getName().equals(name))
                .collect(Collectors.toList());
    }

    @PostConstruct
    private void init() {
        Department hr = new Department("HR");
        Department admin = new Department("ADMIN");
        Department sales = new Department("SALES");

        this.addDepartment(hr);
        this.addDepartment(admin);
        this.addDepartment(sales);

        this.addNewEmployee(Employee.builder().band(Band.B3).gender(Gender.MALE).name("John")
                .salary(BigDecimal.valueOf(1000)).build(), hr);

        this.addNewEmployee(Employee.builder().band(Band.B2).gender(Gender.MALE).name("Joe")
                .salary(BigDecimal.valueOf(1500)).build(), hr);

        this.addNewEmployee(Employee.builder().band(Band.B2).gender(Gender.FEMALE).name("Susan")
                .salary(BigDecimal.valueOf(500)).build(), hr);


        this.addNewEmployee(Employee.builder().band(Band.B4).gender(Gender.MALE).name("John")
                .salary(BigDecimal.valueOf(1200)).build(), admin);

        this.addNewEmployee(Employee.builder().band(Band.B2).gender(Gender.MALE).name("Joe")
                .salary(BigDecimal.valueOf(1500)).build(), admin);

        this.addNewEmployee(Employee.builder().band(Band.B1).gender(Gender.MALE).name("Midhum")
                .salary(BigDecimal.valueOf(1500)).build(), admin);


        this.addNewEmployee(Employee.builder().band(Band.B4).gender(Gender.MALE).name("John")
                .salary(BigDecimal.valueOf(1200)).build(), sales);

        this.addNewEmployee(Employee.builder().band(Band.B2).gender(Gender.MALE).name("Arun")
                .salary(BigDecimal.valueOf(1500)).build(), sales);

        this.addNewEmployee(Employee.builder().band(Band.B3).gender(Gender.MALE).name("Sajjn")
                .salary(BigDecimal.valueOf(2000)).build(), sales);
    }

}
