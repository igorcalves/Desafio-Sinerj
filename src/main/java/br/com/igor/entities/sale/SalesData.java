package br.com.igor.entities.sale;

import br.com.igor.entities.employee.Vendor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesData {

    public static List<Sale> getSales(Vendor vendor, Vendor vendor1) {
        Sale saleAna = new Sale(vendor, LocalDate.of(2021,12,1), 5200.0);
        Sale saleAna1 = new Sale(vendor,LocalDate.of(2022,1,1), 4000.0);
        Sale saleAna2 = new Sale(vendor,LocalDate.of(2022,2,1), 4200.0);
        Sale saleAna3 = new Sale(vendor,LocalDate.of(2022,3,1), 5850.0);
        Sale saleAna4 = new Sale(vendor,LocalDate.of(2022,4,1), 7000.0);

        Sale saleJao = new Sale(vendor1,LocalDate.of(2021,12,1), 3400.0);
        Sale saleJao1 = new Sale(vendor1,LocalDate.of(2022,1,1), 7700.0);
        Sale saleJao2 = new Sale(vendor1,LocalDate.of(2022,2,1), 5000.0);
        Sale saleJao3 = new Sale(vendor1,LocalDate.of(2022,3,1), 5900.0);
        Sale saleJao4 = new Sale(vendor1,LocalDate.of(2022,4,1), 6500.0);

        List<Sale> sales = new ArrayList<>(); 


        sales.add(saleAna);
        sales.add(saleAna1);
        sales.add(saleAna2);
        sales.add(saleAna3);
        sales.add(saleAna4);

        sales.add(saleJao);
        sales.add(saleJao1);
        sales.add(saleJao2);
        sales.add(saleJao3);
        sales.add(saleJao4);
        return sales;
    }
}
