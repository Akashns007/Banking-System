package com.tnsif.BankingProject;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.*;

public class BankingServiceImpleTest {

    BankingService service = new BankingServiceImple();

    @Test
    public void testCustomer() {
        Customer customer = new Customer(101, "akash", "bengaluru", "990033");
        service.addCustomer(customer);

        assertEquals(customer, service.findCustomerById(101));
        assertTrue(service.getAllCustomers().contains(customer));

        service.getAllCustomers().forEach(System.out::println);
    }

    @Test
    public void testAccount() {
        Account account = new Account(1, 101, "savings", 50000);
        service.addAccount(account);

        assertEquals(account, service.findAccountById(1));
        assertTrue(service.getAllAccounts().contains(account));
    }

    @Test
    public void testTransaction() {
        Account account = new Account(1, 101, "savings", 50000);
        service.addAccount(account);
        
        LocalDateTime timestamp = LocalDateTime.now();
        Transaction transaction = new Transaction(1, 1, "deposit", 10000, timestamp );
        service.addTransaction(transaction);

        assertEquals(transaction, service.findTransactionById(1));
        assertTrue(service.getAllTransactions().contains(transaction));
        assertEquals(60000.0, account.getBalance());
    }

    @Test
    public void testBeneficiary() {
        Beneficiary beneficiary = new Beneficiary(102, 101, "akash", 1, "savings");
        service.addBeneficiary(beneficiary);

        assertEquals(beneficiary, service.findBeneficiaryById(102));
        assertTrue(service.getAllBeneficiaries().contains(beneficiary));
    }
}
