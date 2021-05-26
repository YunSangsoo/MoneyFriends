package com.example.moneyfriend;

import java.util.Objects;

public class Account
{
    private static final int DEFAULT_BALANCE = 0;
    private String ownerOfAccount; //계좌 소유주
    private String accountNumber; // 계좌번호
    private double balance; //통장잔액

    public Account() {}

    public Account(String ownerOfAccount)
    {
        this.ownerOfAccount = ownerOfAccount;
        this.balance = DEFAULT_BALANCE;
        setAccountNumber();
    }


    public String getOwnerOfAccount() {
        return ownerOfAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    private void setAccountNumber () //계좌번호 생성함수
    {
        this.accountNumber="Random number";
    }
}
