package br.com.igor.entities.employee;

import java.time.LocalDate;

public class Secretary extends EmployeeWithBenefits implements Salary{


    public Secretary(String name, LocalDate employmentDate) {
        super(name,employmentDate, "secretary", 7000.00, 1000.00,0.20);
    }



}
