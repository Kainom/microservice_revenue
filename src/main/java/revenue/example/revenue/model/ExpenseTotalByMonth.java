package revenue.example.revenue.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "month_expenses")
public class ExpenseTotalByMonth {
    @Id
    private String id;

    @Field
    @Builder.Default
    private Double total = 0.0d;

}
