package com.company;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Bank {
    private int internal_fund = 1000000;

    public void incBalance(int x) {
        internal_fund += x;
    }

    public void decBalance(int x) {
        internal_fund -= x;
    }

    public int getInternal_fund() {
        return internal_fund;
    }
}


class Account {
    private String name;
    private int balance = 0;
    private int age = 0;
    private int loan = 0;
    private int req_loan_amount = 0;
    private double loan_interest_rate = 0.1;
    private double deposit_interest_rate = 0;
    private boolean loan_approve = false, req_loan = false;
    private String accountType = "";
    private int maxLoan = 0;
    ///SETTER
    public void depositBalance(int x, Bank b) {
        balance += x;
        b.incBalance(x);
    }
    public void setMaxLoan(int x) {
        maxLoan = x;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void incAge() {
        age++;
    }

    public void approveLoan(boolean statement) {
        loan_approve = statement;
        req_loan = false;
        loan = req_loan_amount;
        req_loan_amount = 0;
        balance += loan;
    }

    public void change_deposit_interest_rate(double x) {
        deposit_interest_rate = x;
    }

    public void setAccountType(String x) {
        accountType = x;
    }

    public void request_loan(int x) {
        req_loan = true;
        req_loan_amount = x;
    }

    public void yearlyBalanceDeduct() {
    }

    public void setBalance(int x) {
        balance = x;
    }

    ///GETTER
    public String getName() {
        return name;
    }
    public int getMaxLoan(){
        return maxLoan;
    }
    ///When query is called
    public int getBalance() {
        return balance;
    }

    public int getAge() {
        return age;
    }

    public int getLoan() {
        return loan;
    }

    public String getAccountType() {
        return accountType;
    }

    public boolean check_req_loan_status() {
        return req_loan;
    }

    public void withdrawBalance(int x, Bank bank) {

    }

    public void loan_settlement_per_year() {
        double value = loan;
        value =  loan * loan_interest_rate;
        int x = (int) value;
        balance -= x;
    }

    public void accountSettlement_perYear() {

    }

}

class Employees {

    private String name;

    ///ALSO NEED TO ADD LOAN IN LOOKUP
    public void lookUp(String name, ArrayList<Account> accountHolder) {
        int balance, loan;
        for (Account acc : accountHolder) {
            if (acc.getName().equals(name)) {
                balance = acc.getBalance();
                loan = acc.getLoan();
                System.out.println(acc.getName() + "'s " + "current account balance is " + balance + " , loan " + loan);
            }
        }
    }

    public void setName(String x) {
        name = x;
    }

    public String getName() {
        return name;
    }

}

class Managing_Director extends Employees {
    Managing_Director(String x) {
        setName(x);
    }

    public int showInternalFund(Bank b) {
        return b.getInternal_fund();
    }

    public void loan_approval(ArrayList<Account> accountHolder) {
//        for (Account acc : accountHolder) {
//            if (acc.getName().equals(name)) {
//                acc.approveLoan(true);
//            }
//        }
        for (Account acc : accountHolder) {
            if (acc.check_req_loan_status()) {
                acc.approveLoan(true);
            }
        }
    }

    public void changeInterestRate(String accountType, ArrayList<Account> accountHolder, double rate) {
        for (Account acc : accountHolder) {
            if (acc.getAccountType().equals(accountType)) {
                acc.change_deposit_interest_rate(rate);
            }
        }
    }
}

class Officer extends Employees {
    Officer(String x) {
        setName(x);
    }

    public void loan_approval(ArrayList<Account> accountHolder) {
        for (Account acc : accountHolder) {
            if (acc.check_req_loan_status()) {
                acc.approveLoan(true);
                System.out.println("Loan for "+ acc.getName() +" approved");

            }
        }
    }
}

class Cashier extends Employees {
    Cashier(String x) {
        setName(x);
    }
}


class Savings_Account extends Account {
    private double deposit_interest_rate = 0.1;
    private int maxLoan = 10000;

    Savings_Account(String name) {
        change_deposit_interest_rate(deposit_interest_rate);
        setAccountType("savings");
        setName(name);
        setMaxLoan(maxLoan);
    }

    @Override
    public void withdrawBalance(int x, Bank bank) {
        if (getBalance() >= 1000) {
            bank.decBalance(x);
            setBalance(getBalance() - x);
            System.out.println(x + "$ withdrawn. Current Balance " + getBalance());
        }
    }

    @Override
    public void yearlyBalanceDeduct() {
        int bal = getBalance();
        bal -= 500;
        setBalance(bal);
        if (getLoan() > 0) {
            loan_settlement_per_year();
        }
    }

    @Override
    public void accountSettlement_perYear() {
        int newBalance = getBalance();
        newBalance = newBalance + (int) (newBalance * deposit_interest_rate);
        setBalance(newBalance);
    }

}

class Student_Account extends Account {
    private double deposit_interest_rate = .05;
    private int maxLoan = 1000;

    Student_Account(String name) {
        change_deposit_interest_rate(deposit_interest_rate);
        setAccountType("student");
        setName(name);
        setMaxLoan(maxLoan);
    }

    @Override
    public void withdrawBalance(int x, Bank bank) {
        if (x <= 10000) {
            bank.decBalance(x);
            setBalance(getBalance() - x);
            System.out.println(x + "$ withdrawn. Current Balance " + getBalance());
        } else if (x > getBalance()) {
            System.out.println("Invalid Transaction.Current Balance " + getBalance());
        } else {
            System.out.println("Invalid Transaction.Current Balance " + getBalance());
        }
    }

    @Override
    public void accountSettlement_perYear() {
        int newBalance = getBalance();
        newBalance = newBalance + (int) (newBalance * deposit_interest_rate);
        setBalance(newBalance);


    }


    @Override
    public void yearlyBalanceDeduct() {
        if (getLoan() > 0) {
            loan_settlement_per_year();
        }
    }

}

class FixedDeposit_account extends Account {
    private double deposit_interest_rate = .15;
    private int maxLoan = 100000;

    FixedDeposit_account(String name) {
        change_deposit_interest_rate(deposit_interest_rate);
        setAccountType("fixed");
        setName(name);
        setMaxLoan(maxLoan);
    }

    @Override
    public void withdrawBalance(int x, Bank bank) {
        if (getAge() >= 1) {
            setBalance(getBalance() - x);
            bank.decBalance(x);
            System.out.println(x + "$ withdrawn. Current Balance " + getBalance());
        }
    }

    @Override
    public void yearlyBalanceDeduct() {
        int bal = getBalance();
        bal -= 500;
        setBalance(bal);
        if (getLoan() > 0) {
            loan_settlement_per_year();
        }
    }

    @Override
    public void accountSettlement_perYear() {
        int newBalance = getBalance();
        newBalance = newBalance + (int) (newBalance * deposit_interest_rate);
        setBalance(newBalance);
    }
}


public class Main {


    public static void create_account(String commands, ArrayList<Account> accountHolder, Bank bank) {
        String[] command = commands.split(" ");
        String accountType = command[2];

        if (accountType.equals("Student")) {           ///STUDENT ACCOUNT CREATE
            int val = Integer.parseInt(command[3]);
            Student_Account student = new Student_Account(command[1]);
            student.depositBalance(val, bank);
            accountHolder.add(student);
            System.out.println("Student Account for " + student.getName() + " created. Initial balance " + student.getBalance());

        } else if (accountType.equals("Savings")) {       ///SAVINGS ACCOUNT CREATE
            int val = Integer.parseInt(command[3]);
            Savings_Account savings = new Savings_Account(command[1]);
            savings.depositBalance(val, bank);
            accountHolder.add(savings);
            System.out.println("Savings Account for " + savings.getName() + " created. Initial balance " + savings.getBalance());
        } else {
            int val = Integer.parseInt(command[4]);
            if (val < 100000) {
                System.out.println("You must initial deposit of at least 100000");
            } else {
                FixedDeposit_account fixed_deposit = new FixedDeposit_account(command[1]);
                fixed_deposit.depositBalance(val, bank);
                accountHolder.add(fixed_deposit);
                System.out.println("Savings Account for " + fixed_deposit.getName() + " created. Initial balance " + fixed_deposit.getBalance());
            }
        }
    }


    public static void main(String[] args) {

        int flag = 0;               /// FLAG -> 0 FOR ACCOUNT_HOLDER AND FLAG -> 1 FOR OFFICER
        Bank B = new Bank();
        ArrayList<Account> accountHolder = new ArrayList<Account>();
        Managing_Director MD = new Managing_Director("MD");

        Officer S1 = new Officer("S1");
        Officer S2 = new Officer("S2");

        Cashier C1 = new Cashier("C1");
        Cashier C2 = new Cashier("C2");
        Cashier C3 = new Cashier("C3");
        Cashier C4 = new Cashier("C4");
        Cashier C5 = new Cashier("C5");

        Scanner scan = new Scanner(System.in);

        while (true) {
            String desig = "";
            String command = scan.nextLine();
            String[] check = command.split(" ");
            String accName = "";
            int accIndex = -1;
            ///ACCOUNT CREATE

            if (check[0].equals("Create")) {
                create_account(command, accountHolder, B);
                accIndex++;
                flag = 1;
            } else if (check[0].equals("Open")) {
                if (check[1].equals("C1") || check[1].equals("C2") || check[1].equals("C3") || check[1].equals("C4") || check[1].equals("C5") ||
                        check[1].equals("S1") || check[1].equals("S2") || check[1].equals("MD")) {
                    flag = 2;
                    desig = check[1];
                    System.out.println(desig + " " + "is active");
                    if (desig.equals("C1") || desig.equals("C2")) {
                    } else {
                        for (Account acc : accountHolder) {
                            if (acc.check_req_loan_status()) {
                                System.out.println("There are loan approval requests pending");
                                break;
                            }
                        }
                    }

                } else {
                    accName = check[1];
                    flag = 1;

                    for (int i = 0; i < accountHolder.size(); i++) {
                        if (accountHolder.get(i).getName().equals(accName)) {
                            accIndex = i;
                            break;
                        }
                    }
                    System.out.println("Welcome back, " + accountHolder.get(accIndex).getName());
                }
            }
            while (flag == 1) {
                String cmd = scan.nextLine();
                check = cmd.split(" ");
                if (check[0].equals("Deposit")) {
                    int val = Integer.parseInt(check[1]);
                    accountHolder.get(accIndex).depositBalance(val, B);
                    System.out.println(val + "$ deposited; Current balance " + accountHolder.get(accIndex).getBalance());
                }
                ///WITHDRAW
                else if (check[0].equals("Withdraw")) {
                    int val = Integer.parseInt(check[1]);
                    String accType = accountHolder.get(accIndex).getAccountType();
                    accountHolder.get(accIndex).withdrawBalance(val, B);

                } else if (check[0].equals("Query")) {
                    System.out.println("Current Balance " + accountHolder.get(accIndex).getBalance());
                } else if (check[0].equals("INC")) {
                    for (Account acc : accountHolder) {
                        acc.incAge();
                        acc.yearlyBalanceDeduct();
                        acc.accountSettlement_perYear();
                        System.out.println("1 Year Passed");
                    }
                } else if (check[0].equals("Request")) {
                    int val = Integer.parseInt(check[1]);
                    int maxLoan = accountHolder.get(accIndex).getMaxLoan();
                    if(val <= maxLoan){
                        accountHolder.get(accIndex).request_loan(val);
                        System.out.println("Loan Request Successful. Sent for Approval");
                    }
                    else{
                        System.out.println("You cannot request more than "+maxLoan+"$");
                    }

                } else if (check[0].equals("Close")) {
                    System.out.println("Transactions closed for " + accountHolder.get(accIndex).getName());
                    flag = 0;
                }
            }

            while (flag == 2) {
                if (check[0].equals("Lookup")) {
                    S1.lookUp(check[1], accountHolder);
                } else if (check[0].equals("Change")) {
                    if (desig.equals("MD")) {
                        if (check[1].equals("Student")) {
                            double rate = Double.parseDouble(check[2]);
                            rate = rate / 100;
                            MD.changeInterestRate("student", accountHolder, rate);
                        }
                    } else {
                        System.out.println("You don't have permission for this operation");
                    }
                } else if (check[0].equals("See")) {
                    if (desig.equals("MD")) {
                        System.out.println("Current Bank Internal Fund is " + MD.showInternalFund(B));
                    } else {
                        System.out.println("You don't have permission for this operation");
                    }
                } else if (check[0].equals("INC")) {

                    for (Account acc : accountHolder) {
                        acc.incAge();
                        acc.yearlyBalanceDeduct();
                        acc.accountSettlement_perYear();
                    }
                    System.out.println("1 Year Passed");

                } else if (check[0].equals("Approve")) {
                    if (desig.equals("C1") || desig.equals("C2")) {
                    } else {
                        S1.loan_approval(accountHolder);
                    }

                } else if (check[0].equals("Close")) {
                    System.out.println("Operations for " + desig + " closed.");
                    flag = 0;
                    break;
                }
                String cmd = scan.nextLine();
                check = cmd.split(" ");
            }
        }
    }
}
