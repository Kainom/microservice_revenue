package revenue.example.revenue.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;
import revenue.example.revenue.enums.CategoryExpense;

@Builder
@Data
@Document(collection = "expenses")
public class Expense {
    @Id
    private String id;

    private String nome;

    private Double value;

    private LocalDate dataCriacao;

    @Field("category")
    private CategoryExpense category;

    private String description;

    private String slug;

}
