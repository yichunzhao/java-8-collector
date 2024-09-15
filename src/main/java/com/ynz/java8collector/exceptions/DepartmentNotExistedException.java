package com.ynz.java8collector.exceptions;

public class DepartmentNotExistedException extends RuntimeException {
    public DepartmentNotExistedException(String s) {
        super(s);
    }
}
