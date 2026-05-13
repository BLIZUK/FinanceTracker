package com.blizuk.financetracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private Long id;
    private Long userId;
    private double amount;
    private TransactionType type; // INCOME / EXPENSE
    private Long categoryId;
    private String description;
    private LocalDateTime createdAt;

    // ДОБАВИТЬ КОНСТРУКТОР

    public void setId(Long id)
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = createdAt.format(formatter);
        return "Транзакция {" +
                "id=" + id +
                ", Сумма=" + amount +
                ", Тип=" + type +
                ", КатегорияId=" + categoryId +
                ", Комментарий='" + description + '\'' +
                ", Время создания=" + formattedDate +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
