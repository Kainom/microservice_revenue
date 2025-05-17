package revenue.example.revenue.migrations;

import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import revenue.example.revenue.model.ExpensePolicy;
import revenue.example.revenue.model.User;

@ChangeUnit(id = "AddUser", order = "006", author = "kainom")
public class AddUser {
    @Execution
    public void execute(MongoTemplate mongoTemplate) {
        // Inserção de um usuário inicial
        User user = new User();
        user.setName("Kainom");
        user.setLastName("Eleutério");
        user.setEmail("eleuteriokaina@gmail.com");
        user.setCpf("13293806902");
        user.setDescription("Sou o desenvolvedor do projeto");
        user.setPassword("123456");
        user.setPhone("42999410467");
        user.setSalary(1226d);
        user.setMonthlyIncome(1226d);
        user.setWork("Desenvolvedor");
        user.setInvestmentGoal(10000d);
        user.setExpensePolicy(new ExpensePolicy(30d,30d,40d));
        String slug = user.getName() + UUID.randomUUID().toString();
        user.setSlug(slug);

        mongoTemplate.insert(user);

    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
    }

}

// private String imageUrl;