package revenue.example.revenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import revenue.example.revenue.dto.RevenueDTO;
import revenue.example.revenue.services.RevenueService;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/slug/{slug}")
    public ResponseEntity<RevenueDTO> create (@PathVariable("slug")String slug){
        RevenueDTO revenue = revenueService.getBySlug(slug);
        return ResponseEntity.ok(revenue);
    }

    @PostMapping("/")
    public ResponseEntity<RevenueDTO> create(@RequestBody RevenueDTO revenue) {
        RevenueDTO savedRevenue = revenueService.storeRevenue(revenue);
        return ResponseEntity.ok(savedRevenue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevenue(@PathVariable("id") String id) {
        revenueService.deleteRevenue(id);
        return ResponseEntity.noContent().build();
    }

}