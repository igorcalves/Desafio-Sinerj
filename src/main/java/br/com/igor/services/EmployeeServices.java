package br.com.igor.services;

import br.com.igor.Exception.ThereWereNoSales;
import br.com.igor.entities.employee.Employee;
import br.com.igor.entities.employee.EmployeeType;
import br.com.igor.entities.employee.Secretary;
import br.com.igor.entities.employee.Vendor;
import br.com.igor.entities.sale.Sale;

import java.util.Comparator;
import java.util.List;

public class EmployeeServices {

    private final List<Sale> sales;

    public EmployeeServices(List<Sale> sales) {
        this.sales = sales;

    }

    public Double getTotalSalaryPaidInMonth(List<Employee> employees, int month, int year) {

        validations.isValidMonth(month);
        validations.isValidYear(year);
        validations.ListNotEmpty(employees);

        Double totalAmount = 0.0;
        for (Employee e: employees){
            if(e instanceof Vendor){
                totalAmount += ((Vendor) e).currentSalaryWithVendorBonus(sales,month,year);
            }else if(e instanceof Secretary) {
                totalAmount +=  ((Secretary) e).calculateSecretaryBonus(month,year);

            }else {
                totalAmount += e.currentSalary(month, year);
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

        validations.isValidMonth(month);
        validations.isValidYear(year);
        validations.ListNotEmpty(employees);

        Double totalAmount = employees.stream().map(employee -> employee.currentSalary(month, year))
                .reduce(0.0, Double::sum);

        Double totalSales = getTotalSales(month, year);

        return totalAmount + (0.3 * totalSales);
    }


    public Double getAmountOfTheBenefitsPerMonth(List<Employee> employees, int month, int year) {
        validations.isValidMonth(month);
        validations.isValidYear(year);
        validations.ListNotEmpty(employees);

        List<Employee> employeesWithBenefits = employees.stream().filter(employee -> employee.getEmployeeType() == EmployeeType.WITH_BENEFITS).toList();

        return employeesWithBenefits.stream()
                .map(employee ->
                        employee instanceof Vendor ? ((Vendor) employee).getOnlyBonus(sales,month,year): ((Secretary) employee).getOnlyBonus(month,year))
                .reduce(0.0, Double::sum);
    }

    public Employee getEmployeeWithTopSalary(List<Employee> employees, int month, int year) {

        validations.isValidMonth(month);
        validations.isValidYear(year);
        validations.ListNotEmpty(employees);

        return employees.stream()
                .max(Comparator.comparingDouble(e -> {
                    if (e instanceof Vendor) {
                        return ((Vendor) e).currentSalaryWithVendorBonus(sales, month, year);
                    } else if (e instanceof Secretary) {
                        return ((Secretary) e).calculateSecretaryBonus(month, year);
                    } else {
                        return e.currentSalary(month, year);
                    }
                }))
                .orElseThrow();
    }

    public String getHighestBenefitPaid(List<Employee> employees, int month, int year) {
        validations.isValidMonth(month);
        validations.isValidYear(year);
        validations.ListNotEmpty(employees);


        List<Employee> employeesWithBenefits = employees.stream().filter(employee -> employee.getEmployeeType() == EmployeeType.WITH_BENEFITS).toList();


        return employeesWithBenefits.stream()
                .max(Comparator.comparingDouble(e -> {
                    if (e instanceof Vendor) {
                        return ((Vendor) e).getOnlyBonus(sales, month, year);
                    } else {
                        return ((Secretary) e).getOnlyBonus(month, year);
                    }
                }))
                .orElseThrow().getName();
    }

    public Vendor bestVendor(List<Vendor> vendors,int month, int year){
        validations.isValidMonth(month);
        validations.isValidYear(year);
        validations.ListNotEmpty(vendors);
        Vendor bestVendor = vendors.stream()
                .max(Comparator.comparingDouble(e -> e.getTotalSales(sales,month,year)))
                .orElseThrow();
        if(bestVendor.getTotalSales(sales,month,year) != 0.0) return bestVendor;
        throw new ThereWereNoSales("There Were no Sales for this date");
    }








}




