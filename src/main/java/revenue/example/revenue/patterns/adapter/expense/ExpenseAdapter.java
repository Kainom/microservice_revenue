package revenue.example.revenue.patterns.adapter.expense;

import org.springframework.stereotype.Component;

import revenue.example.revenue.dto.ExpenseDTO;
import revenue.example.revenue.model.Expense;

@Component
public class ExpenseAdapter implements IExpenseAdapter {

    @Override
    public ExpenseDTO expenseToDTO(Expense expense) {
        return new ExpenseDTO(
                expense.getId(),
                expense.getNome(),
                expense.getValue(),
                expense.getCategory(),
                expense.getPaymentDay(),
                expense.getDescription(),
                expense.getGrove(),
                expense.getSlug(),
                expense.getParcela());
    }

    @Override
    public Expense dtoToExpense(ExpenseDTO expenseDTO) {
        return Expense.builder()
                .category(expenseDTO.category())
                .paymentDay(expenseDTO.paymentDay())
                .description(expenseDTO.description())
                .nome(expenseDTO.nome())
                .value(expenseDTO.value())
                .grove(expenseDTO.grove())
                .slug(expenseDTO.slug())
                .parcela(expenseDTO.parcela())
                .build();

    }

}
