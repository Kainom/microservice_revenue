package revenue.example.revenue.patterns.adapter.expense;

import org.springframework.stereotype.Component;

import revenue.example.revenue.dto.ExpenseDTO;
import revenue.example.revenue.model.Expense;


@Component
public class ExpenseAdapter implements IExpenseAdapter {

    @Override
    public ExpenseDTO expenseToDTO(Expense expense) {
        return  new ExpenseDTO(
            expense.getId(),
            expense.getNome(),
            expense.getValue(),
            expense.getCategory(),
            expense.getDataCriacao(),
            expense.getDescription(),
            expense.getSlug()
        );
    }

    @Override
    public Expense dtoToExpense(ExpenseDTO expenseDTO) {
        return Expense.builder()
        .category(expenseDTO.category())
        .dataCriacao(expenseDTO.dataCriacao())
        .description(expenseDTO.description())
        .nome(expenseDTO.nome())
        .value(expenseDTO.value())
        .slug(expenseDTO.slug())
        .build();
               
    }
    
}
