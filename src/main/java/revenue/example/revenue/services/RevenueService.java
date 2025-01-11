package revenue.example.revenue.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import revenue.example.revenue.dto.RevenueDTO;
import revenue.example.revenue.model.Revenue;
import revenue.example.revenue.patterns.adapter.AdapterRevenue;
import revenue.example.revenue.patterns.adapter.IAdapterRevenue;
import revenue.example.revenue.repository.RevenueRepository;

@Service
public class RevenueService {
    private RevenueRepository revenueRepository;
    private IAdapterRevenue adapterRevenue;

    public RevenueService(RevenueRepository revenueRepository, IAdapterRevenue adapterRevenue) {
        this.revenueRepository = revenueRepository;
        this.adapterRevenue = adapterRevenue;
    }

    public RevenueDTO getById(String id) {
        return adapterRevenue.toRevenueDTO(
                revenueRepository.findRevenueById(id));
    }

    public List<RevenueDTO> getRevenues() {
        return revenueRepository.findAll()
                .stream()
                .map(adapterRevenue::toRevenueDTO)
                .collect(Collectors.toList());
    }

}
