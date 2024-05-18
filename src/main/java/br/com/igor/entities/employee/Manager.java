package br.com.igor.entities.employee;

import java.time.LocalDate;

public class Manager extends EmployeeWithoutBenefits {

    public Manager(String name, LocalDate employmentDate) {
        super(name, employmentDate ,"Manager", 20000.00, 3000.00);
    }



}
