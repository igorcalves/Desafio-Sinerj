package br.com.igor.entities.employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract  class Employee {


    private String position;

    private  Double salary;



    private  String name;

    private LocalDate employmentDate;

    private Double benefitPerYear;




    public Employee(String name, LocalDate employmentDate, String position, Double salary, Double benefitPerYear){
        this.name = name;
        this.employmentDate = employmentDate;
        this.position = position;
        this.salary = salary;
        this.benefitPerYear = benefitPerYear;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Double getBenefitPerYear() {
        return benefitPerYear;
    }

    public void setBenefitPerYear(Double benefitPerYear) {
        this.benefitPerYear = benefitPerYear;
    }



    public abstract Boolean haveBenefits();

    public abstract Double currentSalary(int month, int year);

    public   Double currentSalaryWithAmountPerYear(int month, int year){
        if(LocalDate.of(year,month,1).isBefore(this.getEmploymentDate())) return  0.0;
        long yearDiff  = ChronoUnit.YEARS.between(this.getEmploymentDate(), LocalDate.of(year,month,1));
        return this.getSalary() +  (this.getBenefitPerYear() *  yearDiff);
    }


    @Override
    public String toString() {
        return "Employee{" +
                "position='" + position + '\'' +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", employmentDate=" + employmentDate +
                ", benefitPerYear=" + benefitPerYear +
                '}';
    }
}
