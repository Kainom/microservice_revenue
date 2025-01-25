package revenue.example.revenue.patterns.adapter.expense_total;

import revenue.example.revenue.dto.ExpensesTotalByMonthDTO;
import revenue.example.revenue.model.ExpenseTotalByMonth;

public class AdapterExpenseTotal implements IAdapterExpenseTotal {

    @Override
    public ExpensesTotalByMonthDTO expenseTotalToDTO(ExpenseTotalByMonth expenseTotal) {
        return new ExpensesTotalByMonthDTO(
                expenseTotal.getId(),
                expenseTotal.getTotal());
    }

    @Override
    public ExpenseTotalByMonth dtoToExpenseTotal(ExpensesTotalByMonthDTO expenseTotalDTO) {
        return ExpenseTotalByMonth.builder()
                .id(expenseTotalDTO.id())
                .total(expenseTotalDTO.total())
                .build();
    }

}
