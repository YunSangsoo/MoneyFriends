package com.example.moneyfriend;

import java.util.Random;

public class Account
{
    private static final int DEFAULT_BALANCE = 0;
    private String ownerOfAccount; //계좌 소유주
    private int accountNumber; // 계좌번호
    private int balance; //통장잔액

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

    public void setOwnerOfAccount(String data){
        this.ownerOfAccount = data;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    private void setAccountNumber () //계좌번호 생성함수
    {
        int max_num_value = 99999999;
        int min_num_value = 10000000;

        Random random = new Random();

        int randomNum = random.nextInt(max_num_value - min_num_value + 1) + min_num_value;
        this.accountNumber=randomNum;
    }
    public void setAccountNumber(int number){
        this.accountNumber = number;
    }
}
