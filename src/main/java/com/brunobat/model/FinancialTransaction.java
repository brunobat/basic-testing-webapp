package com.brunobat.model;

/**
 *
 * Created by Bruno Baptista on 08/03/15.
 */
public class FinancialTransaction {

    private String name;

    private Float amount;

    private String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", msg='" + msg + '\'' +
                '}';
    }
}
