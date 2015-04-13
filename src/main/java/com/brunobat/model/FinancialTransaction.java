package com.brunobat.model;

import com.brunobat.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Bruno Baptista on 08/03/15.
 */
@Entity
public class FinancialTransaction extends BaseEntity {

    @ManyToOne
    private Owner owner;

    private Float amount;

    private String msg;

    public FinancialTransaction() {
    }

    public FinancialTransaction(String id) {
        super(id);
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
        return "FinancialTransaction{" +
                "amount=" + amount +
                ", msg='" + msg + '\'' +
                "} " + super.toString();
    }
}
