package br.com.igor.services;

import br.com.igor.Exception.ListIsEmpty;
import br.com.igor.entities.employee.Employee;
import br.com.igor.entities.employee.Vendor;
import br.com.igor.entities.sale.SalesData;
import br.com.igor.entities.employee.MockData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class EmployeeDateUtilsTest {


    List<Employee> employees = MockData.getAllEmployees();
    List<Vendor> vendors = MockData.getAllVendor();

    EmployeeServices utils = new EmployeeServices(SalesData.getSales(vendors.get(0),vendors.get(1)));


    @Test
    void first(){
        assertNotNull(employees);
        assertInstanceOf(List.class, vendors);
        assertEquals(2,vendors.size());
    }

    @Test
    void getTotalSalaryPaidInMonth() {

        //maria souza 12/2025 7000 + 0.2 = 8400 + bento albino 03/2014 20000 + 3000 = 23000
        assertEquals(20000.00,utils.getTotalSalaryPaidInMonth(employees,2,2015));
        assertEquals(23000.00,utils.getTotalSalaryPaidInMonth(employees,3,2015));
        assertEquals(127180.0,utils.getTotalSalaryPaidInMonth(employees,12,2021));
        assertEquals(129310.0,utils.getTotalSalaryPaidInMonth(employees,1,2022));
        assertEquals(136600.00,utils.getTotalSalaryPaidInMonth(employees,12,2022));
    }

    @Test

    void getTotalSalaryPaidInMonthWithoutBenefits() {

        assertEquals(131600, utils.getTotalSalaryPaidInMonthWithoutBenefits(employees,12,2022));
    }
    @Test

    void getAmountOfTheBenefitsPerMonth() {
        assertEquals(5000,utils.getAmountOfTheBenefitsPerMonth(employees,12,2022) );
        assertEquals(7180,utils.getAmountOfTheBenefitsPerMonth(employees,12,2021) );
    }
    @Test

    void getEmployeeWithTopSalary() {
        assertEquals("Bento Albino",utils.getEmployeeWithTopSalary(employees,12,2022).getName());
    }
    @Test
    void highestBenefitPaid() {

        assertEquals("Maria Souza",utils.getHighestBenefitPaid(employees,12,2022));

    }

    @Test
    void bestVendor(){
        assertEquals(7700.0,utils.bestVendor(vendors,1,2022).getTotalSales(SalesData.getSales(vendors.get(0),vendors.get(1)),1,2022));
    }

    @Test
    void listEmptyException(){
        List<Employee> empty = new ArrayList<>();
        List<Vendor> vendorsEmpty = new ArrayList<>();
        assertThrows(ListIsEmpty.class, () -> {
            utils.getTotalSalaryPaidInMonth(empty, 2, 2015);
        });

        assertThrows(ListIsEmpty.class, () -> {
            utils.getTotalSalaryPaidInMonthWithoutBenefits(empty, 2, 2015);
        });

        assertThrows(ListIsEmpty.class, () -> {
            utils.getAmountOfTheBenefitsPerMonth(empty, 2, 2015);
        });

        assertThrows(ListIsEmpty.class, () -> {
            utils.getEmployeeWithTopSalary(empty, 2, 2015);
        });
       assertThrows(ListIsEmpty.class, () -> {
                utils.getHighestBenefitPaid(empty, 2, 2015);
            });
       assertThrows(ListIsEmpty.class, () -> {
                utils.bestVendor(vendorsEmpty, 2, 2015);
            });

    }
}