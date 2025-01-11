package revenue.example.revenue.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import revenue.example.revenue.model.Revenue;

@Repository
public interface RevenueRepository extends MongoRepository<Revenue, String> {

    public Revenue findRevenueById(String id);

    public Revenue findRevenueByNome(String nome);

    public Revenue findRevenueBySlug(String slug);

    public Revenue findRevenueByVencimento(Date vencimento);

    public Revenue findRevenueByDataCriacao(Date dataCriacao);

}
