package com.example.moneyfriend;

import java.util.Objects;

public class Account
{
    private static final int DEFAULT_BALANCE = 0;
    private String ownerOfAccount; //계좌 소유주
    private String keyForAccount; // 계좌 key값
    private String accountNumber; // 계좌번호
    private int balance; //통장잔액

    public Account() {}

    public Account(String ownerOfAccount, String keyForAccount, String accountNumber)
    {
        this.ownerOfAccount = ownerOfAccount;
        this.keyForAccount = keyForAccount;
        this.accountNumber = accountNumber;
        this.balance = DEFAULT_BALANCE;
    }

    public Account(String ownerOfAccount, String keyForAccount, String accountNumber, int balance)
    {
        this.ownerOfAccount = ownerOfAccount;
        this.keyForAccount = keyForAccount;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }


    public String getOwnerOfAccount() {
        return ownerOfAccount;
    }

    public String getKeyForAccount() {
        return keyForAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }
}
