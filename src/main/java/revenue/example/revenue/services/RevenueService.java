package revenue.example.revenue.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import revenue.example.revenue.dto.RevenueDTO;
import revenue.example.revenue.model.Revenue;
import revenue.example.revenue.patterns.adapter.revenue.AdapterRevenue;
import revenue.example.revenue.patterns.adapter.revenue.IAdapterRevenue;
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

    public RevenueDTO getBySlug(String slug) {
        return adapterRevenue.toRevenueDTO(
                revenueRepository.findRevenueBySlug(slug));
    }

    public List<RevenueDTO> getRevenues() {
        return revenueRepository.findAll()
                .stream()
                .map(adapterRevenue::toRevenueDTO)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "revenues", allEntries = true)
    public RevenueDTO storeRevenue(RevenueDTO revenue) {
        Revenue revenueModel = adapterRevenue.toRevenue(revenue);
        revenueModel.setSlug(revenueModel.getSlug() + UUID.randomUUID().toString());
        return adapterRevenue.toRevenueDTO(revenueRepository.save(revenueModel));
    }
 
    @CacheEvict(value = "revenues", key = "#id")
    public void deleteRevenue(String id) {
        revenueRepository.deleteById(id);
    }

}
