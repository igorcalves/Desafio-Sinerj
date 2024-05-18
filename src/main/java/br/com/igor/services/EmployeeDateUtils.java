package br.com.igor.services;

import br.com.igor.entities.employee.Employee;
import br.com.igor.entities.employee.Secretary;
import br.com.igor.entities.employee.Vendor;
import br.com.igor.entities.sale.Sale;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeDateUtils {

    private final List<Sale> sales;

    public EmployeeDateUtils(List<Sale> sales) {
        this.sales = sales;

    }

    public Double getTotalSalaryPaidInMonth(List<Employee> employees, int month, int year) {
        Double totalAmount = 0.0;
        for (Employee e: employees){
            if(e instanceof Vendor){
                totalAmount += ((Vendor) e).currentSalaryWithVendorBonus(sales,month,year);
            }else if(e instanceof Secretary) {
                totalAmount +=  ((Secretary) e).calculateSecretaryBonus(month,year);
            }else {
                totalAmount +=   e.currentSalary(month, year);
            }
        }
        return  totalAmount;
    }

    private Double getTotalSales(int month, int year) {

        return sales.stream()
                .filter(sale -> sale.getTransactionDate().getYear() == year && sale.getTransactionDate().getMonthValue() == month)
                .map(Sale::getSaleAmount)
                .reduce(0.0, Double::sum);
    }

    public Double getTotalSalaryPaidInMonthWithoutBenefits(List<Employee> employees, int month, int year) {

        Double totalAmount = employees.stream().map(employee -> employee.currentSalary(month, year))
                .reduce(0.0, Double::sum);

        Double totalSales = getTotalSales(month, year);

        return totalAmount + (0.3 * totalSales);
    }


    public Double getAmountOfTheBenefitsPerMount(List<Employee> employeesWithBenefits, int month, int year) {

        return employeesWithBenefits.stream()
                .map(employee ->
                        getOnlyBenefits(month,year,employee))
                .reduce(0.0, Double::sum);
    }

    public Employee getEmployeeWithTopSalary(List<Employee> employees, int month, int year) {


        List<Employee> newList = normalizeVendorsSalary(employees, month,year);

        return newList.stream()
                .max(Comparator.comparingDouble(e -> e.currentSalary(month,year)))
                .orElseThrow();
    }

    public String highestBenefitPaid(List<Employee> employees, int month, int year) {


        List<Employee> newList = new ArrayList<>(employees);

        newList = normalizeVendorsSalary(newList, month,year);



        System.out.println((employees.get(3)).currentSalary(month,year));

        return newList.stream()
                .max(Comparator.comparingDouble(e -> getOnlyBenefits(month,year,e)))
                .orElseThrow().getName();
    }

    private static double getOnlyBenefits(int month, int year, Employee employee) {
        return employee.currentSalary(month, year);
    }

    private List<Employee> normalizeVendorsSalary(List<Employee> employees, int month , int year){
        List<Employee> newList = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee instanceof Vendor) {
                Employee newEmployee = new Vendor(employee.getName(),employee.getEmploymentDate());
                newEmployee.setSalary(
                        newEmployee.currentSalary(month, year) +
                                (0.30 * sales.stream()
                                        .filter(sale -> sale.getTransactionDate().getYear() == year &&
                                                sale.getTransactionDate().getMonthValue() == month &&
                                                sale.getVendor().getName().equals(employee.getName()))
                                        .mapToDouble(Sale::getSaleAmount)
                                        .sum())
                );
                newList.add(newEmployee);
            } else {
                newList.add(employee);
            }
        }
        return newList;
    }







}




