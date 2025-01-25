package revenue.example.revenue.patterns.adapter.expense_total;

import revenue.example.revenue.dto.ExpensesTotalByMonthDTO;
import revenue.example.revenue.model.ExpenseTotalByMonth;

public interface IAdapterExpenseTotal {
    // TODO: Implement methods to adapt ExpenseTotal model to ExpenseTotalDTO and
    // vice versa.
    public ExpensesTotalByMonthDTO expenseTotalToDTO(ExpenseTotalByMonth expenseTotal);

    public ExpenseTotalByMonth dtoToExpenseTotal(ExpensesTotalByMonthDTO expenseTotalDTO);

}
