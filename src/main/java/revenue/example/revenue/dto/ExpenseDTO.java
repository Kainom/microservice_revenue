package revenue.example.revenue.dto;

import java.time.LocalDate;
import java.util.Date;

import revenue.example.revenue.enums.CategoryExpense;

public record ExpenseDTO(
        String id,
        String nome,
        Double value,
        CategoryExpense category,
        LocalDate dataCriacao,
        String description,
        String slug
        ) {

}

