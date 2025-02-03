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
import revenue.example.revenue.repository.ExpenseTotal;
import revenue.example.revenue.repository.ExpenseTotalRepository;
import revenue.example.revenue.utils.ExpenseTotalUtil;

@Service
public class ExpenseTotalService {

    @Autowired
    private MongoTemplate mongo;

    @Autowired
    private ExpenseTotalRepository expenseTotal;

    @Autowired
    private ExpenseTotal expenseTotalCustom;
    // Implement logic for ExpenseTotalService

    public List<ExpensesTotalByMonthDTO> getExpenseTotalByThreeExpenseMonths(Integer year) {
        return expenseTotalCustom.buscarTop3MesesComMaisGastos(year);
    }

    public void incrementTotal(LocalDate date, Double value) {
        // Update ExpenseTotal by calculating total expenses for each month
        // and storing the result in ExpenseTotal collection

        int year = date.getYear();
        int month = date.getMonthValue();
        String id = String.format("%04d-%02d", year, month);

        Query query = new Query(Criteria.where("_id").is(id));

        ExpenseTotalByMonth expense = mongo.findOne(query, ExpenseTotalByMonth.class);

        if (expense != null) {
            Update update = new Update().inc("total", value);
            mongo.upsert(query, update, ExpenseTotalByMonth.class);
            return;
        }

        mongo.save(ExpenseTotalByMonth.builder().id(id).total(value).build());

    }

    public void updateTotal(LocalDate date, Double value, Double oldValue) {
        int year = date.getYear();
        int month = date.getMonthValue();
        String id = String.format("%04d-%02d", year, month);
        ExpenseTotalByMonth expenseTotalByMonth = expenseTotal.findById(id).get();

        if (oldValue - value == 0) {
            return;
        }

        if (value > oldValue) {
            expenseTotalByMonth.setTotal(expenseTotalByMonth.getTotal() + (value - oldValue));
        }

        if (value < oldValue) { // 10,153 153
            expenseTotalByMonth.setTotal(expenseTotalByMonth.getTotal() - (oldValue - value));
        }

        // Query query = new Query(Criteria.where("_id").is(id));
        // Update update = new Update().set("total", expenseTotalByMonth.getTotal());
        // mongo.upsert(query, update, ExpenseTotalByMonth.class);
        expenseTotal.save(expenseTotalByMonth);

    }

}
