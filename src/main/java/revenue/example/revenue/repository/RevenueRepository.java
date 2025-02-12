package revenue.example.revenue.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import revenue.example.revenue.model.Revenue;

@Repository
public interface RevenueRepository extends MongoRepository<Revenue, String> {

    @Cacheable(cacheNames = "revenues", key = "#id", condition = "#result != null")
    public Revenue findRevenueById(String id);

    public Revenue findRevenueByNome(String nome);

    @Cacheable(
        cacheNames = "revenues",
        condition = "#result!= null"
    )

   
    Revenue findBySlug(String slug);
    public Revenue findRevenueByVencimento(Date vencimento);

    public Revenue findRevenueByDataCriacao(Date dataCriacao);


}
