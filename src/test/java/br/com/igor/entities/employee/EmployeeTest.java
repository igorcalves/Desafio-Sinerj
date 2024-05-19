package br.com.igor.entities.employee;

import br.com.igor.entities.sale.Sale;
import br.com.igor.entities.sale.SalesData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Secretary secretary = new Secretary("Jorge Carvalho",LocalDate.of(2018,1,1));
    Secretary secretary1 = new Secretary("Maria Souza",LocalDate.of(2015,12,1));

    Vendor vendor = new Vendor("Ana Silva ",LocalDate.of(2021,12,1));
    Vendor vendor1 = new Vendor("Joao Mendes",LocalDate.of(2021,12,1));


    Manager manager = new Manager("Juliana Alves",LocalDate.of(2017,7,1));
    Manager manager1 = new Manager("Bento Albino",LocalDate.of(2014,3,1));
    List<Sale> sales = SalesData.getSales(vendor,vendor1);
    @Test
    public void currentSalary(){
        assertEquals(11000,secretary.currentSalary(12,2022));
        assertEquals(13000,secretary1.currentSalary(12,2021));
        assertEquals(13800,vendor.currentSalary(12,2022));
        assertEquals(50000.0,manager1.currentSalary(12,2024));


    }

    @Test
    public void currentSalaryWithBonusSecretary(){
        assertEquals(13200.0,secretary.calculateSecretaryBonus(12,2022));
        assertEquals(16800.0,secretary1.calculateSecretaryBonus(12,2022));
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
        assertEquals(13560.0,vendor.currentSalaryWithVendorBonus(sales,12,2021));
        assertEquals(13800.0,vendor.currentSalaryWithVendorBonus(sales,12,2022));
        assertEquals(13800.0,vendor1.currentSalaryWithVendorBonus(sales,12,2022));

    }



}