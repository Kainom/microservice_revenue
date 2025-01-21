package revenue.example.revenue.dto;

import java.util.Date;

import lombok.Builder;
import revenue.example.revenue.enums.CategoryExpense;

public record ExpenseDTO(
        String id,
        String nome,
        Double value,
        CategoryExpense category,
        Date dataCriacao,
        String description,
        String slug
        ) {

}
