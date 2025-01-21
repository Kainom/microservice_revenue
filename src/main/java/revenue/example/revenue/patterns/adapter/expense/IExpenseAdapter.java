package revenue.example.revenue.patterns.adapter.expense;

import revenue.example.revenue.dto.ExpenseDTO;
import revenue.example.revenue.model.Expense;

public interface IExpenseAdapter {
    // TODO: Implement methods to adapt Expense model to ExpenseDTO and vice versa.

    public ExpenseDTO expenseToDTO(Expense expense);

    public Expense dtoToExpense(ExpenseDTO expenseDTO);
    
}
