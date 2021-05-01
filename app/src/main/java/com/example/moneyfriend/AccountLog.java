package com.example.moneyfriend;

import java.time.LocalDate;
import java.time.LocalTime;

public class AccountLog
{
    private boolean depositOrWithdrawal; //입출금 구분
    private int amount; // 거래금액
    private int balance; // 잔액
    private String dateOfTransaction; // 거래 일자
    private String timeOfTransaction; // 거래 시각

    public AccountLog () {}

    public AccountLog(boolean depositOrWithdrawal, int amount, int balance, LocalDate dateOfTransaction, LocalTime timeOfTransaction)
    {
        this.depositOrWithdrawal = depositOrWithdrawal;
        this.amount = amount;
        this.balance = balance;
        this.dateOfTransaction = dateOfTransaction.toString();
        this.timeOfTransaction = timeOfTransaction.toString();
    }

    public boolean isDepositOrWithdrawal() {
        return depositOrWithdrawal;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public String getTimeOfTransaction() {
        return timeOfTransaction;
    }
}
