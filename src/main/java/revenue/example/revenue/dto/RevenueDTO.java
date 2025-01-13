package revenue.example.revenue.dto;

import java.util.Date;

public record RevenueDTO(
                String id,
                String nome,
                Double investimento,
                Double rendimento,
                Date dataCriacao,
                Date vencimento,
                String liquidez,
                Boolean stats,
                String instituition,
                String slug,
                String description,
                String tipo,
                String indexado,
                Date carencia,
                Double finalInvestimento) {

}
