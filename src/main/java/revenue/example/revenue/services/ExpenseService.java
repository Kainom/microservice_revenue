package revenue.example.revenue.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import revenue.example.revenue.dto.ExpenseDTO;
import revenue.example.revenue.enums.CategoryExpense;
import revenue.example.revenue.model.Expense;
import revenue.example.revenue.patterns.adapter.expense.IExpenseAdapter;
import revenue.example.revenue.repository.ExpenseRepository;

@Service
@AllArgsConstructor
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private IExpenseAdapter expenseAdapter;

    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .map(expenseAdapter::expenseToDTO)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "expenses", allEntries = true)
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = expenseAdapter.dtoToExpense(expenseDTO);
        expense.setSlug(expense.getSlug() + " " + UUID.randomUUID());

        String parcelaCommumId = UUID.randomUUID().toString();
        Double totalCompra = 0d;
	Integer quantidadeDeParcela = 0;
	if(expense.getParcela() != null){
         quantidadeDeParcela = expense.getParcela().getQuantidadeDeParcela();
	 totalCompra = (expense.getValue() * expense.getParcela().getQuantidadeDeParcela());

	}
        LocalDate dataCriacaoParcela = expense.getPaymentDay();
        LocalDate vencimento = expense.getPaymentDay().plusMonths(quantidadeDeParcela);

        if (expense.getParcela() != null) {

            for (int i = 0; i < expense.getParcela().getQuantidadeDeParcela(); i++) {
                Expense expenseParcelado = Expense.builder()
                        .category(expense.getCategory())
                        .paymentDay(expense.getPaymentDay().plusMonths((i + 1)))
                        .description(expense.getDescription())
                        .nome(expense.getNome() + " " + (i + 1))
                        .grove(expense.getGrove())
                        .parcela(expense.getParcela())
                        .value(expense.getValue())
                        .slug(expense.getSlug() + " " + UUID.randomUUID())
                        .build();

                expenseParcelado.getParcela().setDataCriacaoParcela(dataCriacaoParcela);
                expenseParcelado.getParcela().setDataVencimento(vencimento);

                expenseParcelado.getParcela().setIdParcela(parcelaCommumId);
                expenseParcelado.getParcela().setTotalCompra(totalCompra);
                expense.getParcela().setNumberParcela((i + 1));

                expenseRepository.save(expenseParcelado);
            }

            return expenseAdapter.expenseToDTO(
                    expense);

        }

        return expenseAdapter.expenseToDTO(
                expenseRepository.save(expense));
    }

    @CacheEvict(value = "expenses", allEntries = true)
    public ExpenseDTO updateExpense(String id, ExpenseDTO expenseDTO) {
        Optional<Expense> expense = expenseRepository.findById(id);

        if (!expense.isPresent()) {
            throw new RuntimeException("Expense not found");
        }

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
        return expenseAdapter.expenseToDTO(expenseRepository.findById(id).orElseThrow());
    }

    public List<ExpenseDTO> geAll() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseAdapter::expenseToDTO)
                .collect(Collectors.toList());
    }

    public ExpenseDTO getExpenseBySlug(String slug) {

        return expenseAdapter.expenseToDTO(expenseRepository.findBySlug(slug));

    }
    
    public List<ExpenseDTO> getExpenseByParcelaId(String parcelaId) {
        return expenseRepository.findByParcelaId(parcelaId)
               .stream()
               .map(expenseAdapter::expenseToDTO)
               .collect(Collectors.toList());
    }

    public List<ExpenseDTO> getAllByYearAndMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();

        // Data de início do mês
        calendar.set(year, month - 1, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date init = calendar.getTime();

        // Data de fim (primeiro dia do próximo mês)
        calendar.set(year, month, 1, 0, 0, 0);
        Date finall = calendar.getTime();

        return expenseRepository.findByMonthAndYear(init, finall)
                .stream().map(expenseAdapter::expenseToDTO).collect(Collectors.toList());
    }

    public List<ExpenseDTO> findByCategoryAndYearAndMonth(CategoryExpense category, Integer year, Integer month) {
        return this.getAllByYearAndMonth(year, month)
                .stream()
                .filter(expense -> expense.category().equals(category)).toList();
    }

    @CacheEvict(value = "expenses", key = "#id")
    public void deleteExpense(String id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (!expense.isPresent()) {
            throw new NoSuchElementException();

        }
        expenseRepository.deleteById(id);

    }

    @CacheEvict(value = "expenses", key = "#idInstallment")
    public void deleteInstallment(String idInstallment) {
        List<Expense> expenses = expenseRepository.findByParcelaId(idInstallment);
        if (expenses.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        expenseRepository.deleteByParcelaId(idInstallment);
        
    }
}
