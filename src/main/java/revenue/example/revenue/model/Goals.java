package revenue.example.revenue.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "goals")
public class Goals {
    private String id;
    private String idUser;
    private String nome;
    private String slug;
    private Double currentAmount;
    private Double goalAmount;
    private String description;
    private Boolean achieved;
    private LocalDate createdAt;
}
