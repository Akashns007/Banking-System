package com.tnsif.BankingProject;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class Demo {
    private static BankingService bankingService = new BankingServiceImple();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
        	System.out.println("\n");
        	System.out.println("Banking System");
        	System.out.println("=================================================");
            System.out.println("1. Add Customers");
            System.out.println("2. Add Accounts");
            System.out.println("3. Add Beneficiary");
            System.out.println("4. Add Transaction");
            System.out.println("5. Find Customer by Id");
            System.out.println("6. List all Accounts of specific Customer");
            System.out.println("7. List all transactions of specific Account");
            System.out.println("8. List all beneficiaries of specific customer");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println();
            
            
            
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    addAccount();
                    break;
                case 3:
                    addBeneficiary();
                    break;
                case 4:
                    addTransaction();
                    break;
                case 5:
                    findCustomerById();
                    break;
                case 6:
                    listAccountsByCustomerId();
                    break;
                case 7:
                    listTransactionsByAccountId();
                    break;
                case 8:
                    listBeneficiariesByCustomerId();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    

    private static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int customerID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();
        Customer customer = new Customer(customerID, name, address, contact);
        bankingService.addCustomer(customer);
        System.out.println("Customer added successfully.");
    }

    private static void addAccount() {
    	System.out.print("Enter Customer ID: ");
        int customerID = scanner.nextInt();
        
        //validate customerID
//        assertFalse(bankingService.accountValidation(customerID));
        if (! bankingService.accountValidation(customerID)) {
        	return;
        }
        
        System.out.print("Enter Account ID: ");
        int accountID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Account Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Balance: ");
        double balance = scanner.nextDouble();
        Account account = new Account(accountID, customerID, type, balance);
        bankingService.addAccount(account);
        System.out.println("Account added successfully.");
    }

    private static void addBeneficiary() {
        System.out.print("Enter Beneficiary ID: ");
        int beneficiaryID = scanner.nextInt();
        System.out.print("Enter Customer ID: ");
        int customerID = scanner.nextInt();
        
        //customerID validation for beneficiary
        if (! bankingService.accountValidation(customerID)) {
        	return;
        }
        
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter Bank Details: ");
        String bankDetails = scanner.nextLine();
        Beneficiary beneficiary = new Beneficiary(beneficiaryID, customerID, name, accountNumber, bankDetails);
        //beneficiary
        bankingService.addBeneficiary(beneficiary); //bankingservice for adding beneficiary
        System.out.println("Beneficiary added successfully.");
    }

    private static void addTransaction() {
        int transactionID = 0;
        transactionID = transactionID + 1;
        //random number for tansactionID
        
        System.out.print("Enter Account ID: ");
        int accountID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        //validating
        if (! bankingService.transactionValidation(accountID)) {
        	return;
        }
        
        System.out.print("Enter Transaction Type (deposit/withdraw): ");
        String type = scanner.nextLine();
        
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        
        //format time stamp
        LocalDateTime timestamp = LocalDateTime.now();
        Transaction transaction = new Transaction(transactionID, accountID, type, amount, timestamp);
        bankingService.addTransaction(transaction);
        System.out.println("Transaction added successfully.");
    }

    private static void findCustomerById() {
        System.out.print("Enter Customer ID: ");
        int customerID = scanner.nextInt();
        Customer customer = bankingService.findCustomerById(customerID);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void listAccountsByCustomerId() {
        System.out.print("Enter Customer ID: ");
        int customerID = scanner.nextInt();
        List<Account> accounts = bankingService.getAccountsByCustomerId(customerID);
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                System.out.println(account);
            }
        } else {
            System.out.println("No accounts found for this customer.");
        }
    }

    private static void listTransactionsByAccountId() {
        System.out.print("Enter Account ID: ");
        int accountID = scanner.nextInt();
        
        //validating the accountID
        if (! bankingService.transactionValidation(accountID)) {
        	return;
        }
        
        List<Transaction> transactions = bankingService.getTransactionsByAccountId(accountID);
        if (!transactions.isEmpty()) {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("No transactions found for this account.");
        }
    }

    private static void listBeneficiariesByCustomerId() {
        System.out.print("Enter Customer ID: ");
        int customerID = scanner.nextInt();
        
        //validating customerID
        if (! bankingService.accountValidation(customerID)) {
        	return;
        }
        
        List<Beneficiary> beneficiaries = bankingService.getBeneficiariesByCustomerId(customerID);
        if (!beneficiaries.isEmpty()) {
            for (Beneficiary beneficiary : beneficiaries) {
                System.out.println(beneficiary);
            }
        } else {
            System.out.println("No beneficiaries found for this customer.");
        }
    }
}

