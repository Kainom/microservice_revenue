package revenue.example.revenue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ExpensePolicy {
    private Double investmentPercentage;
    private Double expenseNotEssentialPercentage;
    private Double expenseEssentialPercentage;
    
}
