package revenue.example.revenue.repository;

import java.time.LocalDate;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import revenue.example.revenue.dto.ExpensesTotalByMonthDTO;

@Repository
public class ExpenseTotalImpl implements ExpenseTotal {

        @Autowired
        private MongoTemplate mongoTemplate;

        @Override
        public ExpensesTotalByMonthDTO getTotalExpensesAtMonth(Integer year, Integer month) {
                Aggregation aggregation = Aggregation.newAggregation(
                                // 1. Filtra pelo ano e mês especificados
                                Aggregation.match(
                                                Criteria.where("paymentDay")
                                                                .gte(LocalDate.of(year, month, 1))
                                                                .lt(LocalDate.of(year, month, 1).plusMonths(1))),
                                // 2. Soma os valores
                                Aggregation.group().sum("value").as("total"));

                List<Document> list = mongoTemplate.aggregate(aggregation, "expenses", Document.class)
                                .getMappedResults();

                Double total = list.isEmpty() ? 0.0 : list.get(0).getDouble("total");
                String numeroFormatado = String.format("%.2f", total);
                total = Double.parseDouble(numeroFormatado);

                // Retorna o total ou 0 caso não haja despesas
                return new ExpensesTotalByMonthDTO((year + "-" + month), total);

        }

        @Override
        public List<ExpensesTotalByMonthDTO> geTotalExpenseAtYearOrAtCurrentMonth(Integer ano, Integer currentMonth) {
                // 1. Filtra pelo ano especificado
                // or
                // 2. Filtra pelo ano ate o presente momemento

                Integer plus = 1;
                Integer monthQuery = 1;
                if (currentMonth != null) {
                        plus = currentMonth != 12 ? 0 : 1;
                        // i need get the previous months and current month,however i set the limit one
                        // month more
                        monthQuery = currentMonth != 12 ? currentMonth + 1 : 1;
                        // no mes 12 eu avanço um ano e deixo o mes em janeiro para pegar todos os meses
                        // do ano
                        // que esta se findando
                }
                Aggregation aggregation = Aggregation.newAggregation(
                                // 1. Filtra apenas os documentos do ano especificado
                                Aggregation.match(Criteria.where("paymentDay").gte(LocalDate.of(ano, 1, 1))
                                                .lt(LocalDate.of(ano, monthQuery, 1).plusYears(plus))),
                                // 2. Extrai ano e mês usando project()
                                Aggregation.project()
                                                .andExpression("year(paymentDay)").as("year")
                                                .andExpression("month(paymentDay)").as("month")
                                                .and("value").as("value"),
                                // 3. Agrupa por ano e mês e soma os valores
                                Aggregation.group("year", "month").sum("value").as("total"),
                                // 4. Ordena os resultados por ano e mês
                                Aggregation.sort(Sort.by(Sort.Direction.ASC, "_id.year", "_id.month")));

                List<Document> list = mongoTemplate.aggregate(aggregation, "expenses", Document.class)
                                .getMappedResults();

                return list.stream()
                                .map(doc -> {
                                        Document id = (Document) doc.get("_id"); // _id é um objeto
                                        Integer year = id.getInteger("year");
                                        Integer month = id.getInteger("month");
                                        Double total = doc.getDouble("total");
                                        String numeroFormatado = String.format("%.2f", total);
                                        total = Double.parseDouble(numeroFormatado);

                                        return new ExpensesTotalByMonthDTO(year + "-" + month, total);
                                })
                                .toList();
        }

        @Override
        public List<ExpensesTotalByMonthDTO> buscarTop3MesesComMaisGastos(Integer ano, Integer currentMonth) {
                Integer plus = 1;
                Integer monthQuery = 1;
                if (currentMonth != null) {
                        plus = currentMonth != 12 ? 0 : 1;
                        // i need get the previous months and current month,however i set the limit one
                        // month more
                        monthQuery = currentMonth != 12 ? currentMonth + 1 : 1;
                        // no mes 12 eu avanço um ano e deixo o mes em janeiro para pegar todos os meses
                        // do ano
                        // que esta se findando
                }

                Aggregation aggregation = Aggregation.newAggregation(
                                Aggregation.match(
                                                Criteria.where("paymentDay")
                                                                .gte(LocalDate.of(ano, 1, 1))
                                                                .lt(LocalDate.of(ano, monthQuery, 1).plusYears(plus))),
                                Aggregation.project()
                                                .andExpression("year(paymentDay)").as("year")
                                                .andExpression("month(paymentDay)").as("month")
                                                .and("value").as("value"),

                                Aggregation.group("year", "month").sum("value").as("total"),
                                Aggregation.sort(Sort.by(Sort.Direction.DESC, "total")),
                                Aggregation.limit(3));

                List<Document> list = mongoTemplate.aggregate(aggregation, "expenses", Document.class)
                                .getMappedResults();

                return list.stream()
                                .map(doc -> {
                                        Document id = (Document) doc.get("_id"); // _id é um objeto
                                        Integer year = id.getInteger("year");
                                        Integer month = id.getInteger("month");
                                        Double total = doc.getDouble("total");
                                        String numeroFormatado = String.format("%.2f", total);
                                        total = Double.parseDouble(numeroFormatado);
                                        return new ExpensesTotalByMonthDTO(year + "-" + month, total);
                                })
                                .toList();
        }

        public Double getTotalAmountByYear(Integer year) {
                LocalDate startDate = LocalDate.of(year, 1, 1);
                LocalDate endDate = LocalDate.of(year + 1, 1, 1);

                return mongoTemplate.aggregate(Aggregation.newAggregation(
                                Aggregation.match(Criteria.where("paymentDay").gte(startDate)
                                                .lt(endDate)),
                                Aggregation.group().sum("value").as("total")),
                                "expenses", Document.class)
                                .getMappedResults()
                                .stream()
                                .map(doc -> doc.getDouble("total"))
                                .findFirst()
                                .orElse(0.0);

        }

}
