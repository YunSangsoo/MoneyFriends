package com.example.moneyfriend;



import com.google.firebase.Timestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class AccountLog
{
    private boolean depositOrWithdrawal; //입출금 구분
    private double amount; // 거래금액
    private double balance; // 잔액
    private String dateOfTransaction; // 거래 일자
    private String timeOfTransaction; // 거래 시각
    private Timestamp checkTime; //정렬시 사용

    public AccountLog () {}

    public AccountLog(boolean depositOrWithdrawal, double amount, double balance, LocalDate dateOfTransaction, LocalTime timeOfTransaction, Date time)
    {
        this.depositOrWithdrawal = depositOrWithdrawal;
        this.amount = amount;
        this.balance = balance;
        this.dateOfTransaction = dateOfTransaction.toString();
        this.timeOfTransaction = timeOfTransaction.toString();
        checkTime = new Timestamp(time);
    }

    public boolean isDepositOrWithdrawal() {
        return depositOrWithdrawal;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public String getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public Timestamp getTimestamp() { return checkTime; }
}
