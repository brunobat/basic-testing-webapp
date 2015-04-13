package com.brunobat.model;

import com.brunobat.model.base.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Bruno Baptista on 09/04/15.
 */
@Entity//TODO case 1
public class Owner extends BaseEntity {

    private String name;

    private Float currentAmount;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FinancialTransaction> financialTransactions;

    public Owner() {
        super();
    }

    public Owner(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Float currentAmount) {
        this.currentAmount = currentAmount;
    }

    public List<FinancialTransaction> getFinancialTransactions() {
        return financialTransactions;
    }

    public void setFinancialTransactions(List<FinancialTransaction> financialTransactions) {
        this.financialTransactions = financialTransactions;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
