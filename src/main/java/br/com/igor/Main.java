package br.com.igor;

import br.com.igor.entities.employee.*;
import br.com.igor.entities.sale.Sale;
import br.com.igor.entities.sale.SalesData;
import br.com.igor.services.EmployeeServices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Secretary secretary = new Secretary("Jorge Carvalho",LocalDate.of(2018,1,1));
        Secretary secretary1 = new Secretary("Maria Souza",LocalDate.of(2015,12,1));

        Vendor vendor = new Vendor("Ana Silva ",LocalDate.of(2021,12,1));
        Vendor vendor1 = new Vendor("Joao Mendes",LocalDate.of(2021,12,1));


        Manager manager = new Manager("Juliana Alves",LocalDate.of(2017,7,1));
        Manager manager1 = new Manager("Bento Albino",LocalDate.of(2014,3,1));

        List<Employee> employees = new ArrayList<>();

        employees.add(secretary);
        employees.add(secretary1);
        employees.add(vendor);
        employees.add(vendor1);
        employees.add(manager);
        employees.add(manager1);

        List<Sale> sales = SalesData.getSales(vendor,vendor1);

        List<Vendor> vendors = new ArrayList<>();
        vendors.add(vendor);
        vendors.add(vendor1);


        EmployeeServices utils = new EmployeeServices(sales);

        List<Employee> employeesWithBenefits = employees.stream()
                .filter(employee -> employee.getEmployeeType() == EmployeeType.WITH_BENEFITS)
                .collect(Collectors.toList());







        Employee topSalary = utils.getEmployeeWithTopSalary(employees, 12,2022);


        System.out.println( "O funcionario que mais recebeu em Salarios e beneficios no mes foi: "
               + "name: " + topSalary.getName()
               + " position: " + topSalary.getPosition()
               + " salary: " + topSalary.currentSalary(3,2022));

        utils.bestVendor(vendors, 12,2027);
        employees.forEach(employee -> System.out.println(employee.getName()+ " " + employee.getSalary()));

    }




}