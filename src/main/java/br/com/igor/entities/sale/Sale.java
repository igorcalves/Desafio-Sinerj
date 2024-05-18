package br.com.igor.entities.sale;

import br.com.igor.entities.employee.Vendor;

import java.time.LocalDate;

public class Sale {

    private final Vendor vendor;
    private final LocalDate transactionDate;

    private final Double saleAmount;

    public Sale(Vendor vendor,LocalDate transactionDate, Double saleAmount){
        this.vendor = vendor;
        this.transactionDate = transactionDate;
        this.saleAmount = saleAmount;

    }

    public Vendor getVendor() {
        return vendor;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }
}
