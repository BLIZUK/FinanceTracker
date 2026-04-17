package com.blizuk.financetracker.model;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private double amount;
    private TransactionType type; // INCOME / EXPENSE
    private Long categoryId;
    private String description;
    private  LocalDateTime createdAt;

    public void Id(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setType(TransactionType type)
    {
        this.type = type;
    }

    public TransactionType getType()
    {
        return type;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {return  categoryId; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCreatedAt(LocalDateTime now) {
        createdAt = now;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", type=" + type +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
