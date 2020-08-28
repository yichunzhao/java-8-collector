package com.ynz.java8collector;

import com.ynz.java8collector.domain.Company;
import com.ynz.java8collector.domain.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
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

        log.info("+++++ find out the most common employee name in the company. +++++");
        company.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getName))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .findFirst()
                .ifPresent(entry -> log.info(entry.toString()));

        log.info("+++++ find the highest salaried employee of the company. +++++");
        company.getAllEmployees()
                .stream()
                .sorted((e1, e2) -> e2.getSalary().compareTo(e1.getSalary()))
                .findFirst()
                .ifPresent(employee -> log.info("Employee with the highest salary: " + employee.toString()));

        //using Collectors.maxBy()/minBy() to do the same as the above;
        company.getAllEmployees()
                .stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(employee -> employee.getSalary().doubleValue())))
                .ifPresent(employee -> log.info("most salaried employee: " + employee));

        log.info("+++++ Find the sum of salaries for all men. +++++");
        Double totalSalary = company.getAllEmployees().stream()
                .mapToDouble(employee -> employee.getSalary().doubleValue()).sum();
        log.info("total salary: " + totalSalary);

        //using Collector.summingDouble to do the same
        Double sumSalary = company.getAllEmployees().stream()
                .collect(Collectors.summingDouble(employee -> employee.getSalary().doubleValue()));
        log.info("total salary: " + sumSalary);


        log.info("+++++ Find the most popular grade/band in the company. +++++");
        company.getAllEmployees().stream()
                .collect(Collectors.groupingBy(employee -> employee.getBand()))
                .entrySet()
                .stream()
                .sorted((b1, b2) -> Integer.compare(b2.getValue().size(), b1.getValue().size()))
                .findFirst()
                .ifPresent(bandListEntry -> log.info("The Most popular band: " + bandListEntry.toString()));

        company.getAllEmployees().stream()
                .collect(Collectors.groupingBy(employee -> employee.getBand()))
                .entrySet()
                .stream()
                .collect(Collectors.maxBy((b1, b2) -> Integer.compare(b1.getValue().size(), b2.getValue().size())))
                .ifPresent(bandListEntry -> log.info("The Most popular band: " + bandListEntry.toString()));

        company.getAllEmployees().stream()
                .collect(Collectors.groupingBy(employee -> employee.getBand()))
                .entrySet()
                .stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(bandListEntry -> bandListEntry.getValue().size())))
                .ifPresent(bandListEntry -> log.info("The Most popular band: " + bandListEntry.toString()));

        company.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getBand, Collectors.counting()))
                .entrySet()
                .stream()
                .collect(Collectors.maxBy((b1, b2) -> Long.compare(b1.getValue(), b2.getValue())))
                .ifPresent(bandLongEntry -> log.info("The Most popular band: " + bandLongEntry.toString()));


    }
}
