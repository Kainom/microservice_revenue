package revenue.example.revenue.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import revenue.example.revenue.model.ExpenseTotalByMonth;

public interface ExpenseTotalRepository extends MongoRepository<ExpenseTotalByMonth,String> {

     
}
