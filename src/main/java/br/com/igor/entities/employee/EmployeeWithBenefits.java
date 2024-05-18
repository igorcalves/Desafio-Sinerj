package br.com.igor.entities.employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmployeeWithBenefits extends  Employee{

    private Double benefits;


    public EmployeeWithBenefits(String name, LocalDate employmentDate, String position, Double salary, Double benefitPerYear, Double benefits) {
        super(name, employmentDate, position, salary, benefitPerYear);
        this.benefits = benefits;
    }

    @Override
    public Boolean haveBenefits() {
        return true;
    }

    @Override
    public Double currentSalary(int month, int year) {
        if(LocalDate.of(year,month,1).isBefore(this.getEmploymentDate())) return  0.0;
        long yearDiff  = ChronoUnit.YEARS.between(this.getEmploymentDate(), LocalDate.of(year,month,1));

        double newSalary =  this.getSalary() +  (this.getBenefitPerYear() *  yearDiff);
        return  newSalary * (1 + this.getBenefits());
    }



    public Double getBenefits() {
        return benefits;
    }

    public void setBenefits(Double benefits) {
        this.benefits = benefits;
    }

}
