package revenue.example.revenue.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import lombok.val;
import revenue.example.revenue.dto.ExpensesTotalByMonthDTO;
import revenue.example.revenue.model.ExpenseTotalByMonth;
import revenue.example.revenue.patterns.adapter.expense.IExpenseAdapter;
import revenue.example.revenue.patterns.adapter.expense_total.IAdapterExpenseTotal;
import revenue.example.revenue.repository.ExpenseTotal;
import revenue.example.revenue.utils.ExpenseTotalUtil;

@Service
public class ExpenseTotalService {

    @Autowired
    private MongoTemplate mongo;

    @Autowired
    private ExpenseTotal expenseTotalCustom;

    @Autowired
    private IAdapterExpenseTotal expenseTotalAdapter;

    // Implement logic for ExpenseTotalService

    public List<ExpensesTotalByMonthDTO> getExpenseTotalByThreeExpenseMonths(Integer year,Integer month) {
        return expenseTotalCustom.buscarTop3MesesComMaisGastos(year,month);
    }

    public List<ExpensesTotalByMonthDTO> getAllTotalExpensesAtMonthByYearOrUntilCurrentMonth(Integer year,Integer month) {
        return expenseTotalCustom.geTotalExpenseAtYearOrAtCurrentMonth(year,month);
    }

    public ExpensesTotalByMonthDTO getTotalExpensesAtMonth(Integer year, Integer month) {
        return expenseTotalCustom.getTotalExpensesAtMonth(year, month);
    }

    // pega o total de gastos de cada mes de um dado ano

}
