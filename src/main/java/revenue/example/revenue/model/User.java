package revenue.example.revenue.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.mongock.utils.field.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import revenue.example.revenue.dto.UserDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)

    private String cpf;
    private String name;
    private String lastName;

    @Indexed(unique = true)
    private String email;
    private String password;
    private String slug;
    private String imageUrl;
    private String description;
    private String phone;
    private Double salary;
    private Double monthlyIncome;
    private String work;
    private Double investmentGoal;
    private ExpensePolicy expensePolicy;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(this.id)
                .cpf(this.cpf)
                .name(this.name)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .slug(this.slug)
                .imageUrl(this.imageUrl)
                .description(this.description)
                .phone(this.phone)
                .salary(this.salary)
                .monthlyIncome(this.monthlyIncome)
                .work(this.work)
                .investmentGoal(this.investmentGoal)
                .expensePolicy(this.expensePolicy)
                .build();
    }
}
