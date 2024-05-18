package br.com.igor.entities.employee;

import br.com.igor.entities.sale.Sale;
import br.com.igor.entities.sale.SalesData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee s1 = new Secretary("teste1", LocalDate.of(2000,1,1));
    Vendor vendor = new Vendor("Ana Silva ",LocalDate.of(2021,12,1));
    Vendor vendor1 = new Vendor("Joao Mendes",LocalDate.of(2021,12,1));

    Manager manager = new Manager("Juliana Alves",LocalDate.of(2017,7,1));
    Manager manager1 = new Manager("Bento Albino",LocalDate.of(2014,3,1));

    List<Sale> sales = SalesData.getSales(vendor,vendor1);
    @Test
    public void currentSalary(){
        s1.setSalary(1000.0);
        assertEquals(1000,s1.currentSalary(1,2000));
        assertEquals(37000,s1.currentSalary(1,2036));
        vendor.setSalary(1000.00);
        assertEquals(6400.0,vendor.currentSalary(12,2024));
        assertEquals(50000.0,manager1.currentSalary(3,2024));


    }

    @Test
    public void currentSalaryWithBonusSecretary(){
        Secretary s1 = new Secretary("teste1", LocalDate.of(2000,1,1));
        s1.setSalary(1000.0);
        assertEquals(13200.0,s1.calculateSecretaryBonus(1,2010));
    }

    @Test
    public void onlyBenefitsSecretary(){
        Secretary s1 = new Secretary("teste1", LocalDate.of(2000,1,1));
        s1.setSalary(1000.0);
        assertEquals(200.0,s1.getOnlyBonus(1,2000));
        assertEquals(2200.0,s1.getOnlyBonus(1,2010));
    }

    @Test
    public void filterSalesByVendor(){
        assertTrue(vendor.filterSalesByVendor(sales,11,2021).isEmpty());
        assertFalse(vendor.filterSalesByVendor(sales,12,2021).isEmpty());
        assertTrue(vendor1.filterSalesByVendor(sales,11,2021).isEmpty());
        assertFalse(vendor1.filterSalesByVendor(sales,12,2021).isEmpty());
    }

    @Test
    public  void onlyBenefitsVendor(){
        vendor.setSalary(1000.00);
        assertEquals(1560.0,vendor.getOnlyBonus(sales,12,2021));
        assertEquals(1200.0,vendor.getOnlyBonus(sales,1,2022));

    }

    @Test
    public void currentSalaryWithBonusVendor(){
        vendor.setSalary(1000.0);
        assertEquals(2560.0,vendor.currentSalaryWithVendorBonus(sales,12,2021));
        assertEquals(1000.0,vendor.currentSalaryWithVendorBonus(sales,5,2022));

    }



}