package revenue.example.revenue.model;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class Parcela {
    private String idParcela;
    private Integer quantidadeDeParcela;
    private Integer numberParcela;
    private LocalDate dataCriacaoParcela;
    private LocalDate dataVencimento;
    private Double totalCompra;


}
