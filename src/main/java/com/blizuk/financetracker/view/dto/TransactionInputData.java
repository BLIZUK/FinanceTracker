package com.blizuk.financetracker.view.dto;


import com.blizuk.financetracker.model.TransactionType;
import java.time.LocalDateTime;


public record TransactionInputData(Long userId, double amount, TransactionType type, Long categoryId, String description, LocalDateTime time) {}

