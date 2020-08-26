package com.ynz.java8collector;

import com.ynz.java8collector.domain.Company;
import com.ynz.java8collector.domain.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
                company.getAllEmployees().stream()
                        .collect(Collectors.groupingBy(employee -> employee.getName()))
                        .entrySet().stream()
                        .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                        .findFirst();

        common.ifPresent(entry -> log.info(common.toString()));

        //find the Find the highest salaried employee of the company.


		//Find the sum of salaries for all men.


		//Find the most popular grade/band in the company.




    }
}
