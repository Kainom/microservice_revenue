package revenue.example.revenue.migrations;

import java.util.Date;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import revenue.example.revenue.model.Revenue;

@ChangeUnit(id = "addNewFields", order = "003", author = "kainom")
public class AddNewFieldsRevenue {

    @Execution
    public void addNewField(MongoTemplate mongoTemplate) {
        Query query = new Query();
        Update update = new Update();

        update.set("description", "Investimento para render");
        update.set("tipo", "FIXA");
        update.set("carencia", new Date());
        update.set("indexado", "defina");
        update.set("finalInvestimento", 0000d);

        mongoTemplate.updateFirst(query, update, "revenue");

    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        // Exemplo de rollback (remover usuário inserido)
        mongoTemplate.remove(new Query(Criteria.where("description").in("Investimento para render")), Revenue.class);
    }
}
