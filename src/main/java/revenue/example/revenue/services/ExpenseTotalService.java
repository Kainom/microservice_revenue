package revenue.example.revenue.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import revenue.example.revenue.dto.ExpensesTotalByMonthDTO;
import revenue.example.revenue.model.Expense;
import revenue.example.revenue.patterns.adapter.expense_total.IAdapterExpenseTotal;
import revenue.example.revenue.repository.ExpenseRepository;
import revenue.example.revenue.repository.ExpenseTotal;

@Service
public class ExpenseTotalService {

    @Autowired
    private MongoTemplate mongo;

    @Autowired
    private ExpenseTotal expenseTotalCustom;

    @Autowired
    private IAdapterExpenseTotal expenseTotalAdapter;

    @Autowired
    private ExpenseRepository expenseRepository;

    // Implement logic for ExpenseTotalService

    public List<ExpensesTotalByMonthDTO> getExpenseTotalByThreeExpenseMonths(Integer year, Integer month) {
        return expenseTotalCustom.buscarTop3MesesComMaisGastos(year, month);
    }

    public List<ExpensesTotalByMonthDTO> getAllTotalExpensesAtMonthByYearOrUntilCurrentMonth(Integer year,
            Integer month) {
        return expenseTotalCustom.geTotalExpenseAtYearOrAtCurrentMonth(year, month);
    }

    public ExpensesTotalByMonthDTO getTotalExpensesAtMonth(Integer year, Integer month) {
        return expenseTotalCustom.getTotalExpensesAtMonth(year, month);
    }

    public Double findTotalAmountByYear(Integer year) {
        // Calendar calendar = Calendar.getInstance();
        // // Data de início do mês
        // calendar.set(year, 1, 1, 0, 0, 0);
        // calendar.set(Calendar.MILLISECOND, 0);
        // Date init = calendar.getTime();

        // // Data de fim (primeiro dia do próximo mês)
        // calendar.set(year + 1, 1, 1, 0, 0, 0);
        // Date finall = calendar.getTime();

        // return expenseRepository.findByMonthAndYear(init, finall).stream()
        //         .mapToDouble(Expense::getValue).sum();
        return expenseTotalCustom.getTotalAmountByYear(year);
    }

    // pega o total de gastos de cada mes de um dado ano

}
