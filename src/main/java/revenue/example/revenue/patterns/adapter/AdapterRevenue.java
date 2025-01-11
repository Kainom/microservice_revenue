package revenue.example.revenue.patterns.adapter;

import org.springframework.stereotype.Component;

import revenue.example.revenue.dto.RevenueDTO;
import revenue.example.revenue.model.Revenue;

@Component
public class AdapterRevenue implements IAdapterRevenue {
    public RevenueDTO toRevenueDTO(Revenue revenue) {
        return new RevenueDTO(
                revenue.getId(),
                revenue.getNome(),
                revenue.getInvestimento(),
                revenue.getRendimento(),
                revenue.getDataCriacao(),
                revenue.getVencimento(),
                revenue.getLiquidez(),
                revenue.getStats(),
                revenue.getInstituition(),
                revenue.getSlug());
    }

    public Revenue toRevenue(RevenueDTO revenueDTO) {
        return Revenue.builder()
                .id(revenueDTO.id())
                .nome(revenueDTO.nome())
                .investimento(revenueDTO.investimento())
                .rendimento(revenueDTO.rendimento())
                .dataCriacao(revenueDTO.dataCriacao())
                .vencimento(revenueDTO.vencimento())
                .liquidez(revenueDTO.liquidez())
                .stats(revenueDTO.stats())
                .instituition(revenueDTO.instituition())
                .slug(revenueDTO.slug())
                .build();
    }

}
