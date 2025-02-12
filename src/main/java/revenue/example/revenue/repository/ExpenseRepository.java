package revenue.example.revenue.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import revenue.example.revenue.enums.CategoryExpense;
import revenue.example.revenue.model.Expense;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    public List<Expense> getExpensesByCategory(CategoryExpense categoryExpense);
    

}
