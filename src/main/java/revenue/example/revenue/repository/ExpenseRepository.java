package revenue.example.revenue.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import revenue.example.revenue.model.Expense;

public interface ExpenseRepository extends MongoRepository<Expense,String>{
    
}
