package revenue.example.revenue.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import revenue.example.revenue.dto.ExpenseDTO;
import revenue.example.revenue.model.Expense;
import revenue.example.revenue.patterns.adapter.expense.IExpenseAdapter;
import revenue.example.revenue.repository.ExpenseRepository;

@Service
@AllArgsConstructor
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private IExpenseAdapter expenseAdapter;

    @CacheEvict(value = "expenses", allEntries = true)
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseAdapter.dtoToExpense(expenseDTO);
        expense.setSlug(expense.getSlug() + UUID.randomUUID());
        
        return expenseAdapter.expenseToDTO(
                expenseRepository.save(expense)
        );
    }

    @CacheEvict(value = "expenses", allEntries = true)
    public ExpenseDTO updateExpense(String id, ExpenseDTO expenseDTO) {
        return expenseAdapter.expenseToDTO(
                expenseRepository.save(
                        expenseAdapter.dtoToExpense(expenseDTO)));
    }

    public ExpenseDTO getById(String id) {
        return expenseAdapter.expenseToDTO(expenseRepository.findById(id).orElse(null));
    }

    public List<ExpenseDTO> geAll() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseAdapter::expenseToDTO)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "expenses", key = "#id")
    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}
