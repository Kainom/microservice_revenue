package revenue.example.revenue.patterns.adapter;

import revenue.example.revenue.dto.RevenueDTO;
import revenue.example.revenue.model.Revenue;

public interface IAdapterRevenue {
    public RevenueDTO toRevenueDTO(Revenue revenue);

    public Revenue toRevenue(RevenueDTO revenueDTO);

}
