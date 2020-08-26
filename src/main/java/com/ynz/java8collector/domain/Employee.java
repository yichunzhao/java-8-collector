package com.ynz.java8collector.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Employee {
    private String name;
    private Gender gender;
    private BigDecimal salary;
    private Band band;
}
