package br.com.igor.entities.employee;

import java.time.LocalDate;

public class Secretary extends Employee  {

    private static final Double bonusRate = 0.2;

    public Secretary(String name, LocalDate employmentDate) {
        super(name, employmentDate, "Secretary", 7000.00, 1000.0, EmployeeType.WITH_BENEFITS);
    }


    public Double calculateSecretaryBonus(int month, int year){
        return currentSalary(month,year) * (1 + bonusRate);
    }

    public Double getOnlyBonus(int month, int year){
        return currentSalary(month,year) *  bonusRate;
    }
}
