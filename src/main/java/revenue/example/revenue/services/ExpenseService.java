package revenue.example.revenue.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import revenue.example.revenue.dto.ExpenseDTO;
import revenue.example.revenue.model.Expense;
import revenue.example.revenue.patterns.adapter.expense.IExpenseAdapter;
import revenue.example.revenue.repository.ExpenseRepository;
import revenue.example.revenue.utils.ExpenseTotalUtil;

@Service
@AllArgsConstructor
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private IExpenseAdapter expenseAdapter;
    private ExpenseTotalService expenseTotalService;

    @CacheEvict(value = "expenses", allEntries = true)
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseAdapter.dtoToExpense(expenseDTO);
        expense.setSlug(expense.getSlug() +" "+ UUID.randomUUID());

        expenseTotalService.incrementTotal(expenseDTO.dataCriacao(), expense.getValue());

        return expenseAdapter.expenseToDTO(
                expenseRepository.save(expense));
    }

    @CacheEvict(value = "expenses", allEntries = true)
    public ExpenseDTO updateExpense(String id, ExpenseDTO expenseDTO) {
        Optional<Expense> expense = expenseRepository.findById(id);

        if (!expense.isPresent()) {
            throw new RuntimeException("Expense not found");
        }

        expenseTotalService.updateTotal(expenseDTO.dataCriacao(), expenseDTO.value(), expense.get().getValue());

        if (expenseDTO.nome() != null) {
            expense.get().setNome(expenseDTO.nome());
        }
        if (expenseDTO.value() != null) {
            expense.get().setValue(expenseDTO.value());
        }
        if (expenseDTO.category() != null) {
            expense.get().setCategory(expenseDTO.category());
        }
        if (expenseDTO.slug() != null) {
            expense.get().setSlug(expenseDTO.slug());
        }
        if (expenseDTO.description() != null) {
            expense.get().setDescription(expenseDTO.description());
        }

        return expenseAdapter.expenseToDTO(expenseRepository.save(expense.get()));
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
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            expenseTotalService.incrementTotal(expense.get().getDataCriacao(), expense.get().getValue() * -1);
        }

        expenseRepository.deleteById(id);
    }
}
