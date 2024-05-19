package br.com.igor.entities.employee;


import br.com.igor.entities.sale.Sale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Vendor extends Employee{


    private static final Double bonusRate = 0.3;


    public Vendor(String name, LocalDate employmentDate) {
        super(name, employmentDate, "Vendor", 12000.00, 1800.0, EmployeeType.WITH_BENEFITS);
    }


    public Double currentSalaryWithVendorBonus(List<Sale> sales, int month, int year){
        return currentSalary(month,year)  + getOnlyBonus(sales ,month,year);
    }

    public Double getOnlyBonus(List<Sale> sales,int month, int year){
        List<Sale> ownSales = filterSalesByVendor(sales, month, year);
        if(!ownSales.isEmpty()){
            return ownSales.stream()
                    .mapToDouble(sale -> sale.getSaleAmount() * bonusRate)
                    .sum();        }
        return  0.0;
    }

    public Double getTotalSales(List<Sale> sales,int month, int year){
        List<Sale> ownSales = filterSalesByVendor(sales, month, year);

        if(!ownSales.isEmpty()){
            return ownSales.stream()
                    .mapToDouble(Sale::getSaleAmount)
                    .sum();        }
        return  0.0;
    }

    public List<Sale> filterSalesByVendor(List<Sale> sales,int month, int year){
        List<Sale> filteredSales = new ArrayList<>(sales);

        return filteredSales.stream().filter
        (sale -> sale.getTransactionDate().getYear() == year &&
                sale.getTransactionDate().getMonthValue() == month &&
                sale.getVendor().getName().equals(this.getName())).collect(Collectors.toList());

    }



}
