package revenue.example.revenue.migrations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import revenue.example.revenue.model.Revenue;

@ChangeUnit(id = "addAnotherRevenue", order = "002", author = "kainom")
public class AddAnotherRevenue {

    @Execution
    public void execute(MongoTemplate mongoTemplate) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String key = UUID.randomUUID().toString();
        try {
            Date dataVencimento = formato.parse("24/02/2025");

            Revenue revenue = Revenue.builder()
                    .nome("CDB BMG")
                    .investimento(43.63)
                    .rendimento(11.84)
                    .dataCriacao(new Date())
                    .vencimento(dataVencimento)
                    .liquidez("Vencimento")
                    .stats(true)
                    .instituition("Rico Plataforma")
                    .slug("CDB BMG" + key)
                    .build();
            mongoTemplate.insert(revenue);
            ;
        } catch (Exception ex) {
            System.out.println("Erro ao converter a data: " + ex.getMessage());
        }
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        // Exemplo de rollback (remover usuário inserido)
        mongoTemplate.remove(new Query(Criteria.where("slug").in("CDB BMG")), Revenue.class);
    }

}
