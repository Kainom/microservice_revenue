package revenue.example.revenue.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import revenue.example.revenue.dto.ExpensesTotalByMonthDTO;
import revenue.example.revenue.model.ExpenseTotalByMonth;

@Repository
public class ExpenseTotalImpl implements ExpenseTotal {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ExpensesTotalByMonthDTO> buscarTop3MesesComMaisGastos(Integer ano) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("_id").regex("^" + ano + "-")), // Filtra pelo ano
                Aggregation.group("_id").sum("total").as("total"), // Agrupa por mês e soma os totais
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "total")), // Ordena pelos maiores gastos
                Aggregation.limit(3) // Pega os 3 primeiros meses com maior gasto
        );

        List<Document> list = mongoTemplate.aggregate(aggregation, "month_expenses", Document.class).getMappedResults();
        
        return list.stream().map(doc -> new ExpensesTotalByMonthDTO(doc.getString("_id"), doc.getDouble("total"))).toList();
    }

}
