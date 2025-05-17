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
                expense.getUserId(),
                expense.getParcela());
    }

    @Override
    public Expense dtoToExpense(ExpenseDTO expenseDTO) {
        String formatedValue = String.format("%.2f", expenseDTO.value());
        Double value = Double.parseDouble(formatedValue);
        return Expense.builder()
                .category(expenseDTO.category())
                .paymentDay(expenseDTO.paymentDay())
                .description(expenseDTO.description())
                .nome(expenseDTO.nome())
                .value(value)
                .grove(expenseDTO.grove())
                .slug(expenseDTO.slug())
                .userId(expenseDTO.userId())
                .parcela(expenseDTO.parcela())
                .build();

    }

}
