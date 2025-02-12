package revenue.example.revenue.migrations;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;

@ChangeUnit(id = "RenameFieldExpense", order = "005", author = "kainom")
public class RenameFieldExpense {

    @Execution
    public void renameField(MongoTemplate mongoTemplate) {
        Query query = new Query();
        Update update = new Update().rename("dataCriacao", "paymentDay");
        mongoTemplate.updateMulti(query, update, "expenses");
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        Query query = new Query();
        Update update = new Update().rename("dataCriacao", "paymentDay");
        mongoTemplate.updateMulti(query, update, "expenses");
    }

}
