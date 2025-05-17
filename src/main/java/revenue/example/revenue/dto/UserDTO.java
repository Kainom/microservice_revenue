package revenue.example.revenue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import revenue.example.revenue.model.ExpensePolicy;
import revenue.example.revenue.model.User;

@Data
@Builder
public class UserDTO {
    private String id;
    private String cpf;
    private String name;
    private String lastName;
    private String email;
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String slug;
    private String imageUrl;
    private String description;
    private String phone;
    private Double salary;
    private Double monthlyIncome;
    private String work;
    private Double investmentGoal;
    private ExpensePolicy expensePolicy;

    public User toEntity() {
        return new User(
                this.id,
                this.cpf,
                this.name,
                this.lastName,
                this.email,
                this.password,
                this.slug,
                this.imageUrl,
                this.description,
                this.phone,
                this.salary,
                this.monthlyIncome,
                this.work,
                this.investmentGoal,
                this.expensePolicy);
    }
}
