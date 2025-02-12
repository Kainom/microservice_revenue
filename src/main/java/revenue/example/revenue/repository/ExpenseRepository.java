package revenue.example.revenue.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import revenue.example.revenue.enums.CategoryExpense;
import revenue.example.revenue.model.Expense;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    public List<Expense> getExpensesByCategory(CategoryExpense categoryExpense);

    @Query("{ 'paymentDay': { '$gte': ?0, '$lt': ?1 } }")
    public List<Expense> findByMonthAndYear(Date init, Date finall);

    public Expense findBySlug(String slug);

    @Query(value = "{'parcela.idParcela':?0}", delete = true)
    public void deleteByParcelaId(String idParcela);

    @Query(value = "{'parcela.idParcela':?0}")
    public List<Expense> findByParcelaId(String idParcela); 
}
