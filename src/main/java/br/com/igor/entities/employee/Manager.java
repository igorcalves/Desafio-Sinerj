package br.com.igor.entities.employee;

import java.time.LocalDate;

public class Manager extends Employee {


    public Manager(String name, LocalDate employmentDate) {
        super(name, employmentDate, "Manager", 20000.00, 3000.0, EmployeeType.WITHOUT_BENEFITS);
    }



}
