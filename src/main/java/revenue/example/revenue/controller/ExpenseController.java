package revenue.example.revenue.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import revenue.example.revenue.dto.ExpenseDTO;
import revenue.example.revenue.services.ExpenseService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/expenses")
@AllArgsConstructor
@RestController
public class ExpenseController {

    ExpenseService expenseService;

    @GetMapping("/")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {

        return ResponseEntity.ok(expenseService.geAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable("id") String id) {
        // Implement logic to retrieve expense by id
        return ResponseEntity.ok(expenseService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expense) {
        // Implement logic to create new expense
        return ResponseEntity.ok(
            expenseService.createExpense(expense)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable("id") String id, @RequestBody ExpenseDTO expense) {
        // Implement logic to update existing expense
        return ResponseEntity.ok(
            expenseService.updateExpense(id, expense)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable("id") String id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
