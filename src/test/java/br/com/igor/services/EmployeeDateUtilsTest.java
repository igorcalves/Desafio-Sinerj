package br.com.igor.services;

import br.com.igor.entities.employee.Employee;
import br.com.igor.entities.employee.Vendor;
import br.com.igor.entities.sale.SalesData;
import br.com.igor.entities.employee.MockData;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class EmployeeDateUtilsTest {




    List<Employee> employees = MockData.getAllEmployees();
    List<Vendor> vendors = MockData.getAllVendor();

    EmployeeDateUtils utils = new EmployeeDateUtils(SalesData.getSales(vendors.get(0),vendors.get(1)));


    @Test
    void first(){
        assertNotNull(employees);
        assertTrue(vendors instanceof List<Vendor>);
    }

    @Test
    void getTotalSalaryPaidInMonth() {

        //maria souza 12/2025 7000 + 0.2 = 8400 + bento albito 03/2014 20000 + 3000 = 23000
        assertEquals(20000.00,utils.getTotalSalaryPaidInMonth(employees,12,2015));

    }

    @Test

    void getTotalSalaryPaidInMonthWithoutBenefits() {
    }
    @Test

    void getAmountOfTheBenefitsPerMount() {
    }
    @Test

    void getEmployeeWithTopSalary() {
    }
    @Test

    void highestBenefitPaid() {
    }
}