package revenue.example.revenue.repository;

import java.util.List;

import revenue.example.revenue.dto.ExpensesTotalByMonthDTO;

public interface ExpenseTotal {
     public List<ExpensesTotalByMonthDTO> buscarTop3MesesComMaisGastos(Integer ano,Integer month);

     public List<ExpensesTotalByMonthDTO> geTotalExpenseAtYearOrAtCurrentMonth(Integer ano,Integer month);

     public ExpensesTotalByMonthDTO getTotalExpensesAtMonth(Integer year, Integer month);

}
