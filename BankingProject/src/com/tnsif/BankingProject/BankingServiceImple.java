package com.tnsif.BankingProject;

import java.util.*;

public class BankingServiceImple implements BankingService {
    private Map<Integer, Customer> customers = new HashMap<>();
    private Map<Integer, Account> accounts = new HashMap<>();
    private Map<Integer, Transaction> transactions = new HashMap<>();
    private Map<Integer, Beneficiary> beneficiaries = new HashMap<>();

    @Override
    public void addCustomer(Customer customer) {
    	//put method is used to add info to the Hashmap
        // add Customer to customers map, Key - customerID
        customers.put(customer.getCustomerID(), customer);
    }

    @Override
    public void addAccount(Account account) {
        // add Account to accounts map, Key - accountID
        accounts.put(account.getAccountID(), account);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        // add transaction to transactions map, key - transactionID
        // and based on transaction type (deposit or withdraw) update the account balance
        transactions.put(transaction.getTransactionID(), transaction);
        
        Account account = accounts.get(transaction.getAccountID());
        if (transaction.getType().equals("deposit")) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        } else if (transaction.getType().equals("withdraw")) {
            account.setBalance(account.getBalance() - transaction.getAmount());
        }
    }

    @Override
    public void addBeneficiary(Beneficiary beneficiary) {
        // add beneficiary to beneficiaries map, key - beneficiaryID
        beneficiaries.put(beneficiary.getBeneficiaryID(), beneficiary);
    }

    @Override
    public Customer findCustomerById(int id) {
        return customers.get(id);
    }

    @Override
    public Account findAccountById(int id) {
        return accounts.get(id);
    }

    @Override
    public Transaction findTransactionById(int id) {
        return transactions.get(id);
    }

    @Override
    public Beneficiary findBeneficiaryById(int id) {
        return beneficiaries.get(id);
    }
    
    
    
    
    
    //list of all the values

    @Override
    public List<Account> getAccountsByCustomerId(int customerId) {
        List<Account> result = new ArrayList<>();
        for (Account account : accounts.values()) {
            if (account.getCustomerID() == customerId) {
                result.add(account);
            }
        }
        return result;
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getAccountID() == accountId) {
                result.add(transaction);
            }
        }
        return result;
    }

    @Override
    public List<Beneficiary> getBeneficiariesByCustomerId(int customerId) {
        List<Beneficiary> result = new ArrayList<>();
        for (Beneficiary beneficiary : beneficiaries.values()) {
            if (beneficiary.getCustomerID() == customerId) {
                result.add(beneficiary);
            }
        }
        return result;
    }
    
    
    
    
    
    //for getting all details
    @Override
    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    @Override
    public Collection<Transaction> getAllTransactions() {
        return transactions.values();
    }

    @Override
    public Collection<Beneficiary> getAllBeneficiaries() {
        return beneficiaries.values();
    }
    
    
    
    
    
    
    // for validations
	@Override
	public boolean transactionValidation(int accountID) {
		// TODO Auto-generated method stub
		Account account = findAccountById(accountID);
        if (account == null) {
            System.out.println("Account not found for ID: " + accountID);
            return false;
        }
		return true;
		
	}

	@Override
	public boolean accountValidation(int customerID) {
		// TODO Auto-generated method stub
		Customer customer = findCustomerById(customerID);
        if (customer == null) {
            System.out.println("The CustomerID "+customerID+" does not exist. Please add the Customer details first!");
            return false;
        }
		return true;
	}

	@Override
	public boolean beneficiaryValidation(int customerID) {
		// TODO Auto-generated method stub
		Customer customer = findCustomerById(customerID);
        if (customer == null) {
            System.out.println("The CustomerID "+customerID+" does not exist!");
            return false;
        }
		return true;
	}
}

