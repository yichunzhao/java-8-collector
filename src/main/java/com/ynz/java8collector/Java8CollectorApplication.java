package com.ynz.java8collector;

import com.ynz.java8collector.domain.Company;
import com.ynz.java8collector.domain.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.Map;
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

        log.info("total number employees: " + String.valueOf(company.getAllEmployees().size()));

        log.info("+++++ find out the most common employee name in the company. +++++");
        company.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .max((e1, e2) -> e1.getValue().compareTo(e1.getValue()))
                .ifPresent(entry -> log.info("the most common name in the company is " + entry.getKey() + " having " + entry.getValue()));

        log.info("+++++ find the highest salaried employee of the company. +++++");
        //find max/min element in a Stream using max(Comparator)/min(Comparator)
        company.getAllEmployees()
                .stream()
                .max((e1, e2) -> e1.getSalary().compareTo(e2.getSalary()))
                .ifPresent(employee -> log.info("the most salaried employee is " + employee));

        log.info("+++++ Find the sum of salaries for all men. +++++");
        double totalSalary = company.getAllEmployees()
                .stream()
                .mapToDouble(employee -> employee.getSalary().doubleValue())
                .sum();
        log.info("total salary: " + totalSalary);

        log.info("+++++ Find the most popular grade/band in the company. +++++");

        //find a max
        //Collectors.groupingBy(r=f(t),Collectors) directly counting the number in the list
        company.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getBand, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(bandListEntry -> log.info("??The Most popular band is " + bandListEntry.getKey() + " having " + bandListEntry.getValue() + " members"));
    }
}
