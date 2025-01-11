package revenue.example.revenue.migrations;

import java.util.Date;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import revenue.example.revenue.model.Revenue;

@ChangeUnit(id = "addInitialRevenue", order = "001", author = "kainom")
public class DatabaseChangelog {
    @Execution
    public void execute(MongoTemplate mongoTemplate) {
        // Inserção de um usuário inicial
        Revenue revenue = Revenue.builder()
                .nome("Tesouro Selic")
                .investimento(1000.0)
                .rendimento(50.0)
                .dataCriacao(new Date())
                .vencimento(new Date(new Date().getTime() + 365 * 24 * 60 * 60 * 1000))
                .liquidez("Diária")
                .stats(true)
                .instituition("Banco do Brasil")
                .slug("Selic")
                .build();
        mongoTemplate.insert(revenue);
    }


    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        // Exemplo de rollback (remover usuário inserido)
        mongoTemplate.remove(new Query(Criteria.where("slug").is("Selic")), Revenue.class);
    }
}
