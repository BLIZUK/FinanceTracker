package com.blizuk.financetracker.view.dto;
import com.blizuk.financetracker.model.TransactionType;

public record TransactionInputData(double amount, TransactionType type, Long categoryId, String description) {}

