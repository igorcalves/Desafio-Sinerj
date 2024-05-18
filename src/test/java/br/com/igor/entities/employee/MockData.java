package br.com.igor.entities.employee;



import br.com.igor.entities.employee.Employee;
import br.com.igor.entities.employee.Manager;
import br.com.igor.entities.employee.Secretary;
import br.com.igor.entities.employee.Vendor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockData {


    public static List<Employee> getAllEmployees() {
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

        return employees;
    }

    public static List<Vendor> getAllVendor() {
        return getAllEmployees().stream().filter(employee -> {
                            return employee instanceof Vendor;
                        }
                ).map(employee -> (Vendor) employee)
                .collect(Collectors.toList());


    }
}
