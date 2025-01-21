package revenue.example.revenue.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@Document(collection = "revenue")
public class Revenue {
    @Id
    private String id;
    private String nome;
    private Double investimento;
    private Double rendimento;
    private Date dataCriacao;
    private Date vencimento;
    private String liquidez;
    private Boolean stats;
    private String instituition;
    private String slug;
    private String description;
    private String tipo;
    private String indexado;
    private Date carencia;
    private Double finalInvestimento;
}
