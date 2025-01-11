package revenue.example.revenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import revenue.example.revenue.dto.RevenueDTO;
import revenue.example.revenue.services.RevenueService;

@RestController
public class RevenueController {

    @Autowired
    RevenueService revenueService;

    @GetMapping("/")
    public ResponseEntity<List<RevenueDTO>> getAll() {
        List<RevenueDTO> revenues = revenueService.getRevenues();
        return ResponseEntity.ok(revenues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RevenueDTO> getById(@PathVariable("id") String id) {
        RevenueDTO revenue = revenueService.getById(id);
        return ResponseEntity.ok(revenue);

    }

}