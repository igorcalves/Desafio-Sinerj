package br.com.igor.services;

import br.com.igor.entities.employee.EmployeeWithBenefits;
import br.com.igor.entities.employee.Employee;
import br.com.igor.entities.employee.Vendor;
import br.com.igor.entities.sale.Sale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDateUtils {

    private final List<Sale> sales;

    public EmployeeDateUtils(List<Sale> sales) {
        this.sales = sales;

    }

    public Double getTotalSalaryPaidInMonth(List<Employee> employees, int month, int year) {

        Double totalAmount = employees.stream().map(employee -> employee.currentSalary(month, year)).reduce(0.0, Double::sum);

        Double totalSales = sales.stream()
                .filter(sale -> sale.getTransactionDate().getYear() == year && sale.getTransactionDate().getMonthValue() == month)
                .map(Sale::getSaleAmount)
                .reduce(0.0, Double::sum);


        return totalAmount + (0.3 * totalSales);

    }

    public Double getTotalSalaryPaidInMonthWithoutBenefits(List<Employee> employees, int month, int year) {
        Double totalAmount = employees.stream().map(employee -> employee.currentSalaryWithAmountPerYear(month, year))
                .reduce(0.0, Double::sum);

        Double totalSales = sales.stream()
                .filter(sale -> sale.getTransactionDate().getYear() == year && sale.getTransactionDate().getMonthValue() == month)
                .map(Sale::getSaleAmount)
                .reduce(0.0, Double::sum);

        return totalAmount + (0.3 * totalSales);
    }


    public Double getAmountOfTheBenefitsPerMount(List<EmployeeWithBenefits> employeesWithBenefits, int month, int year) {
        return employeesWithBenefits.stream()
                .map(employee ->
                        employee.currentSalary(month, year) - employee.currentSalaryWithAmountPerYear(month, year))
                .reduce(0.0, Double::sum);
    }

    public Employee getEmployeeWithTopSalary(List<Employee> employees, int month, int year) {

        List<Employee> newList = new ArrayList<>(employees);
        newList.forEach(employee -> {
            if(employee instanceof Vendor){
               employee.setSalary(
                       employee.currentSalaryWithAmountPerYear(month, year) +
                               (0.30 *  sales.stream()
                                       .filter(
                                               sale -> sale.getTransactionDate().getYear() == year
                                                       && sale.getTransactionDate().getMonthValue() == month
                                                       &&  sale.getVendor().getName().equals(employee.getName()))
                                       .map(Sale::getSaleAmount)
                                       .reduce(0.0, Double::sum))
               );
            }
        });


        return newList.stream()
                .max(Comparator.comparingDouble(e -> e.currentSalaryWithAmountPerYear(month,year)))
                .orElseThrow();






    }

}




