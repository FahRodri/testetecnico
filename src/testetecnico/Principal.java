package testetecnico;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


public class Principal {
    
	   
public static void main(String[] args) {
	    
	    
        List<Funcionario> funcionarios = new ArrayList<>();
        
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);

        System.out.println("Todos os funcionários:");
        System.out.println();
        funcionarios.forEach(funcionario -> {
        String salarioFormatado = df.format(funcionario.getSalario());
        System.out.println("Nome: " + funcionario.getNome() + ", Data Nascimento: " + funcionario.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", Salário: " + salarioFormatado + ", Função: " + funcionario.getFuncao());
        });
        
        funcionarios = funcionarios.stream().filter(funcionario->!funcionario.getNome().equals("João"))
				.collect(Collectors.toList());
        System.out.println();
        System.out.println("Todos os funcionários sem o Joâo:");
        System.out.println();
        funcionarios.forEach(funcionario -> {
            String salarioFormatado = df.format(funcionario.getSalario());
            System.out.println("Nome: " + funcionario.getNome() + ", Data Nascimento: " + funcionario.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", Salário: " + salarioFormatado + ", Função: " + funcionario.getFuncao());
            });
            System.out.println();
        
            
            funcionarios.forEach(funcionario -> {
            	BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
                funcionario.setSalario(novoSalario);
            });

            System.out.println("Todos os funcionários sem o Joâo e com 10% de aumento:");
            System.out.println();
            funcionarios.forEach(funcionario -> {
                String salarioFormatado = df.format(funcionario.getSalario());
                System.out.println("Nome: " + funcionario.getNome() + ", Data Nascimento: " + funcionario.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", Salário: " + salarioFormatado + ", Função: " + funcionario.getFuncao());
                });
            System.out.println();
            
            Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                    .collect(Collectors.groupingBy(Funcionario::getFuncao));
            
            
            System.out.println("\nFuncionários agrupados por função:");
            System.out.println();
            
            funcionariosPorFuncao.forEach((funcao, lista) -> {
            	lista.forEach(funcionario -> {
                    String salarioFormatado = df.format(funcionario.getSalario());
                    System.out.println("Nome: " + funcionario.getNome() + ", Data Nascimento: " + funcionario.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", Salário: " + salarioFormatado + ", Função: " + funcionario.getFuncao());
                    });
            System.out.println();
            
            });
            
            System.out.println("\nFuncionários que fazem aniversário em outubro (10) e dezembro (12):");
            System.out.println();        
            funcionarios.stream().filter(funcionario -> {
                            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
                            return mesAniversario == 10 || mesAniversario == 12;
                        })
                        .forEach(funcionario -> {
                            String salarioFormatado = df.format(funcionario.getSalario());
                            System.out.println("Nome: " + funcionario.getNome() + ", Data Nascimento: " + funcionario.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", Salário: " + salarioFormatado + ", Função: " + funcionario.getFuncao());
                            });

            
            LocalDate dataAtual = LocalDate.now();
            
            Funcionario funcionarioMaisVelho = funcionarios.stream()
            	    .max(Comparator.comparing(f -> ChronoUnit.YEARS.between(f.getDataNascimento(), dataAtual)))
            	    .orElse(null);

            	if (funcionarioMaisVelho != null) {
            	    long idade = ChronoUnit.YEARS.between(funcionarioMaisVelho.getDataNascimento(), dataAtual);
            	    System.out.println("\nFuncionário mais velho: " + funcionarioMaisVelho.getNome() + ", Idade: " + idade + " anos");
            	}
            	
            	funcionarios.sort(Comparator.comparing(Funcionario::getNome));
            	System.out.println("\nLista de funcionários ordenada por ordem alfabética:");
            	System.out.println();
            	funcionarios.forEach(funcionario -> {
                    String salarioFormatado = df.format(funcionario.getSalario());
                    System.out.println("Nome: " + funcionario.getNome() + ", Data Nascimento: " + funcionario.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", Salário: " + salarioFormatado + ", Função: " + funcionario.getFuncao());
                    });

            	
            	BigDecimal totalSalarios = funcionarios.stream()
                        .map(Funcionario::getSalario)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
            	
                    System.out.println("\nTotal dos salários dos funcionários: " + df.format(totalSalarios));
            	
                    
                    System.out.println("\nSalários em múltiplos de salário mínimo:");
                    funcionarios.forEach(funcionario -> {
                          
                    BigDecimal salarioMinimo = new BigDecimal("1212.00");
                    BigDecimal salarioFuncionario = funcionario.getSalario();
                    
                    int multiplicador = salarioFuncionario.intValue() / salarioMinimo.intValue();
                    System.out.println(funcionario.getNome() + ": " + multiplicador + " salários mínimos");
                    });
        }
       
}