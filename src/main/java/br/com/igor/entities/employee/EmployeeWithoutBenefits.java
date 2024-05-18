package br.com.igor.entities.employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmployeeWithoutBenefits extends Employee {


    public EmployeeWithoutBenefits(String name, LocalDate employmentDate, String position, Double salary, Double benefitPerYear) {
        super(name, employmentDate, position, salary, benefitPerYear);
    }

    @Override
    public Boolean haveBenefits() {
        return false;
    }

    @Override
    public Double currentSalary(int month, int year) {
        if(LocalDate.of(year,month,1).isBefore(this.getEmploymentDate())) return  0.0;
        long yearDiff  = ChronoUnit.YEARS.between(this.getEmploymentDate(), LocalDate.of(year,month,1));
        return this.getSalary() +  (this.getBenefitPerYear() *  yearDiff);
    }




}
