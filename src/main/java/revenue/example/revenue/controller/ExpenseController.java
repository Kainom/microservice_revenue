package revenue.example.revenue.controller;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import revenue.example.revenue.dto.ExpenseDTO;
import revenue.example.revenue.dto.ExpensesTotalByMonthDTO;
import revenue.example.revenue.enums.CategoryExpense;
import revenue.example.revenue.services.ExpenseService;
import revenue.example.revenue.services.ExpenseTotalService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/expenses")
@AllArgsConstructor
@RestController
public class ExpenseController {

    ExpenseService expenseService;
    ExpenseTotalService expenseTotalService;

    @GetMapping("/all")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.geAll());
    }

    @GetMapping("/")
    public ResponseEntity<List<ExpenseDTO>> getAllExpensesByYearAndMonth(@RequestParam("year") Integer year,
            @RequestParam("month") Integer month) {
        System.out.println(year);
        System.out.println(month);
        return ResponseEntity.ok(expenseService.getAllByYearAndMonth(year, month));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExpenseDTO>> getAllExpensesByCategoryAndYearAndMonth(
            @PathVariable("category") CategoryExpense category, @RequestParam("year") Integer year,
            @RequestParam("month") Integer month) {

        return ResponseEntity.ok(expenseService.findByCategoryAndYearAndMonth(category, year, month));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable("id") String id) {
        // Implement logic to retrieve expense by id
        try {
            return ResponseEntity.ok(expenseService.getById(id));

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ExpenseDTO> getExpenseBySlug(@PathVariable("slug") String slug) {
        try {
            return ResponseEntity.ok(expenseService.getExpenseBySlug(slug));

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("parcela/{idParcela}")
    public ResponseEntity<List<ExpenseDTO>> getExpensesByParcela(@PathVariable("idParcela") String idParcela) {
        try {
            return ResponseEntity.ok(
                    expenseService.getExpenseByParcelaId(idParcela));
        } catch (Exception err) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/total-three/{year}")
    public ResponseEntity<List<ExpensesTotalByMonthDTO>> getTotalExpensesByThreeExpensiveMonths(
            @PathVariable("year") Integer year, @RequestParam(name = "month", required = false) Integer month) {
        // Implement logic to retrieve total expenses for the top 3 months in the given
        // year or until the current Month
        return ResponseEntity.ok(expenseTotalService.getExpenseTotalByThreeExpenseMonths(year, month));
    }

    @GetMapping("/total-at-month/{year}-{month}")
    public ResponseEntity<ExpensesTotalByMonthDTO> getExpenseTotalMonthById(@PathVariable("year") Integer year,
            @PathVariable(name = "month") Integer month) {
        try {
            System.out.println("HellO World");
            return ResponseEntity.ok(expenseTotalService.getTotalExpensesAtMonth(
                    year, month));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/total-year/{year}")
    public ResponseEntity<List<ExpensesTotalByMonthDTO>> getTotalExpensesByYear(@PathVariable("year") Integer year,
            @RequestParam(name = "month", required = false) Integer month) {
        return ResponseEntity.ok(expenseTotalService.getAllTotalExpensesAtMonthByYearOrUntilCurrentMonth(year, month));
    }

    @PostMapping("/")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expense) {
        // Implement logic to create new expense
        return ResponseEntity.ok(
                expenseService.createExpense(expense));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable("id") String id, @RequestBody ExpenseDTO expense) {
        // Implement logic to update existing expense
        return ResponseEntity.ok(
                expenseService.updateExpense(id, expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable("id") String id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException err) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("installment/{idParcela}")
    public ResponseEntity<Void> deleteInstallment(@PathVariable("idParcela") String idParcela) {
        try {
            expenseService.deleteInstallment(idParcela);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException err) {
            return ResponseEntity.notFound().build();
        }
    }
}
