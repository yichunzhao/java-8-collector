package com.ynz.java8collector;

import com.ynz.java8collector.domain.Band;
import com.ynz.java8collector.domain.Company;
import com.ynz.java8collector.domain.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Find out the most common name (Employee name) in the company.
 * Find the highest salaried employee of the company.
 * Find the sum of salaries for all men.
 * Find the most popular grade/band in the company.
 */

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Java8CollectorApplication implements CommandLineRunner {
    private final Company company;


    public static void main(String[] args) {
        SpringApplication.run(Java8CollectorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //log.info(company.toString());
        log.info("total number employees: " + String.valueOf(company.getAllEmployees().size()));

        //find out the most common employee name in the company.
        Optional<Map.Entry<String, List<Employee>>> common =
                company.getAllEmployees()
                        .stream()
                        .collect(Collectors.groupingBy(employee -> employee.getName()))
                        .entrySet()
                        .stream()
                        .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                        .findFirst();

        common.ifPresent(entry -> log.info(common.toString()));

        log.info("+++++ find the highest salaried employee of the company. +++++");

        Optional<Employee> employeeHighSalary = company.getAllEmployees()
                .stream()
                .sorted((e1, e2) -> e2.getSalary().compareTo(e1.getSalary()))
                .findFirst();

        employeeHighSalary.ifPresent(
                employee -> log.info("Employee with the highest salary: " + employeeHighSalary.get().toString()));

        //how to use Collectors.maxBy()/minBy() to do the same as the above;
        Optional<Employee> mostSalariedEmployee = company.getAllEmployees().stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(employee -> employee.getSalary().doubleValue())));

        mostSalariedEmployee.ifPresent(employee -> log.info("most salaried employee: " + employee));


        log.info("+++++ Find the sum of salaries for all men. +++++");
        Double totalSalary = company.getAllEmployees().stream()
                .mapToDouble(employee -> employee.getSalary().doubleValue()).sum();
        log.info("total salary: " + totalSalary);


        //Find the most popular grade/band in the company.
        Optional<Map.Entry<Band, List<Employee>>> mostPopularBand = company.getAllEmployees().stream()
                .collect(Collectors.groupingBy(employee -> employee.getBand()))
                .entrySet().stream().sorted((b1, b2) -> Integer.compare(b2.getValue().size(), b1.getValue().size()))
                .findFirst();

        mostPopularBand.ifPresent(bandListEntry -> log.info("The Most popular band: " + mostPopularBand.get().toString()));


    }
}
