package revenue.example.revenue.dto;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

import revenue.example.revenue.enums.CategoryExpense;
import revenue.example.revenue.model.Parcela;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExpenseDTO(
        String id,
        String nome,
        Double value,
        CategoryExpense category,
        LocalDate paymentDay,
        String description,
        String grove,
        String slug,
        String userId,
        Parcela parcela) {

}
