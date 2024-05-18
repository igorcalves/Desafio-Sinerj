package br.com.igor.entities.employee;

import java.time.LocalDate;

public class Vendor extends EmployeeWithBenefits {

    public Vendor(String name, LocalDate employmentDate) {
        super(name,employmentDate, "Vendor", 12000.00, 1800.00,0.30);
    }




}
