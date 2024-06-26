package Banco; 
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

class NumeroAgenciaNegativoException extends Exception{
    public NumeroAgenciaNegativoException(){
        super("O número da Agência não pode ser um valor negativo!");
    }

    public NumeroAgenciaNegativoException(String mensagem){
        super(mensagem);
    }
}

class NumeroContaNegativoException extends Exception{
    public NumeroContaNegativoException(){
        super("O número da Conta não pode ser um valor negativo!");
    }

    public NumeroContaNegativoException(String mensagem){
        super(mensagem);
    }
}

class SaldoNegativoException extends Exception{
    public SaldoNegativoException(){
        super("O valor do saldo não pode ser negativo!");
    }

    public SaldoNegativoException(String mensagem){
        super(mensagem);
    }
}

public class TesteOperacoes {
    ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
    ArrayList<Conta> listaContas = new ArrayList<Conta>();
    Scanner input = new Scanner(System.in);

    private Cliente criarCliente(){
        //Dados do cliente
        System.out.print("Digite o nome do cliente: ");
        String nome = input.nextLine();
        System.out.print("Digite o endereço do cliente: ");
        String endereco = input.nextLine();
        System.out.print("Digite a profissão do cliente: ");
        String profissao = input.nextLine();

        //Criando cliente
        Cliente cliente = new Cliente(nome, endereco, profissao);

        //Adicionando o cliente na lista de clientes
        listaClientes.add(cliente);

        return cliente;
    }

    private void criarConta() throws Exception, NumeroAgenciaNegativoException, NumeroContaNegativoException, SaldoNegativoException{
        //Tipo da conta
        System.out.println("Digite 'poupanca' para criar uma Conta Poupança ou digite 'corrente' para criar uma Conta Corrente.");
        System.out.print("Qual o tipo: ");
        String tipoConta = input.nextLine();

        if (tipoConta.equals("poupanca")) {
            try {
                System.out.print("Digite o número da Agência: ");
                int numeroAgencia = input.nextInt();
                input.nextLine();

                System.out.print("Digite o número da Conta: ");
                int numeroConta = input.nextInt();
                input.nextLine();

                System.out.print("Digite o saldo: ");
                double saldo = input.nextDouble();
                input.nextLine();

                if (numeroAgencia < 0) {
                    throw new NumeroAgenciaNegativoException();
                } else if (numeroConta < 0) {
                    throw new NumeroContaNegativoException();
                } else if (saldo < 0) {
                    throw new SaldoNegativoException();
                }

                Cliente cliente = criarCliente();

                ContaPoupanca CP = new ContaPoupanca(Integer.toString(numeroAgencia), Integer.toString(numeroConta), saldo, cliente);
                listaContas.add(CP);
                System.out.println("Conta criada com sucesso!");
            } catch (InputMismatchException e) {
                System.out.println("Erro na criação da conta: Entrada inválida. Por favor, insira um valor válido.");
                System.out.println("O número da Agência e o número da Conta devem ser um valor inteiro positivo.");
                System.out.println("O saldo deve ser uma sequência de números.");
                input.nextLine();
            }
        } else if (tipoConta.equals("corrente")) {
            try {
                System.out.print("Digite o número da Agência: ");
                int numeroAgencia = input.nextInt();
                input.nextLine();

                System.out.print("Digite o número da Conta: ");
                int numeroConta = input.nextInt();
                input.nextLine();

                System.out.print("Digite o saldo: ");
                double saldo = input.nextDouble();
                input.nextLine();

                if (numeroAgencia < 0) {
                    throw new NumeroAgenciaNegativoException();
                } else if (numeroConta < 0) {
                    throw new NumeroContaNegativoException();
                } else if (saldo < 0) {
                    throw new SaldoNegativoException();
                }

                Cliente cliente = criarCliente();

                ContaCorrente CC = new ContaCorrente(Integer.toString(numeroAgencia), Integer.toString(numeroConta), saldo, cliente);
                listaContas.add(CC);
                System.out.println("Conta criada com sucesso!");
            } catch (InputMismatchException e) {
                System.out.println("Erro na criação da conta: Entrada inválida. Por favor, insira um valor válido.");
                System.out.println("O número da Agência e o número da Conta devem ser um valor inteiro positivo.");
            }
        } else {
            System.out.println("Valor inválido! Digite 'poupanca' ou 'corrente' sem as aspas simples.");
        }
    }

    private void realizarOperacoes(String numeroAgenciaEmissor, String numeroContaEmissor, String numeroAgenciaReceptor, String numeroContaReceptor) throws SaldoInsuficienteException, ValorNegativoException{
        int emissorEncontrado = 0, receptorEncontrado = 0;

        //Analisando dados do Emissor
        for (Conta c : listaContas) {
            if(c.getNumeroAgencia().equals(numeroAgenciaEmissor) && c.getNumeroConta().equals(numeroContaEmissor)){
                emissorEncontrado++;
                break;
            }
        }

        //Analisando dados do Receptor
        for (Conta c : listaContas) {
            if(c.getNumeroAgencia().equals(numeroAgenciaReceptor) && c.getNumeroConta().equals(numeroContaReceptor)){
                receptorEncontrado++;
                break;
            }
        }

        //Verificando dados
        if(emissorEncontrado == 0 && receptorEncontrado == 0){
            System.out.println("A conta do emissor e a conta do receptor não foram encontradas no sistema!");
        }
        else if(emissorEncontrado == 0){
            System.out.println("A conta do emissor não foi encontrada no sistema!");
        }
        else if(receptorEncontrado == 0){
            System.out.println("A conta do receptor não foi encontrada no sistema!");
        }
        else if(emissorEncontrado == 1 && receptorEncontrado == 1){
            Conta contaEmissor = null;
            Conta contaReceptor = null;

            //Pegando a conta do Emissor
            for (Conta c : listaContas) {
                if(c.getNumeroAgencia().equals(numeroAgenciaEmissor) && c.getNumeroConta().equals(numeroContaEmissor)){
                    contaEmissor = c;
                    break;
                }
            }

            //Pegando a conta do Receptor
            for (Conta c : listaContas) {
                if(c.getNumeroAgencia().equals(numeroAgenciaReceptor) && c.getNumeroConta().equals(numeroContaReceptor)){
                    contaReceptor = c;
                    break;
                }
            }
            contaEmissor.transferencia(contaReceptor);
        }
    }

    public void exibirSaldo(String numeroAgencia, String numeroConta){
        int contaExiste = 0;

        for (Conta conta : listaContas){

            if(conta.getNumeroAgencia().equals(numeroAgencia) && conta.getNumeroConta().equals(numeroConta)){
                contaExiste++;

                if(conta instanceof ContaPoupanca){
                    ContaPoupanca contaP = (ContaPoupanca) conta;

                    System.out.print("Digite a qtd. de meses: ");
                    int meses = input.nextInt();
                    input.nextLine();

                    contaP.simularOperacoes(meses);
                    contaP.exibirSaldo();
                }
                else if(conta instanceof ContaCorrente){
                    ContaCorrente contaC = (ContaCorrente) conta;

                    System.out.print("Digite a qtd. de meses: ");
                    int meses = input.nextInt();
                    input.nextLine();

                    contaC.simularOperacoes(meses);
                    contaC.exibirSaldo();
                }
                break;
            }
        }
        if(contaExiste == 0){
            System.out.println("Dados da conta inválidos!");
        }
    }

    public void apresentarMenu() throws NumeroAgenciaNegativoException, NumeroContaNegativoException, SaldoNegativoException, Exception{
        int op = 0;

        System.out.println("Opções:");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Realizar operações");
        System.out.println("3 - Exibir saldo");
        System.out.print("Digite uma opção: ");
        
        try{
            op = input.nextInt();
            input.nextLine();
        }catch(InputMismatchException e){
            System.out.println("Entrada inválida!");
            input.nextLine();
        }
        
        switch (op) {
            case 1:
                criarConta();
                break;
            case 2:
                System.out.print("Digite o número da sua Agência: ");
                String numeroAgenciaEmissor = input.nextLine();
                System.out.print("Digite o número da sua Conta: ");
                String numeroContaEmissor = input.nextLine();
                System.out.print("Digite o número da Agência do receptor: ");
                String numeroAgenciaReceptor = input.nextLine();
                System.out.print("Digite o número da Conta do receptor: ");
                String numeroContaReceptor = input.nextLine();

                realizarOperacoes(numeroAgenciaEmissor, numeroContaEmissor, numeroAgenciaReceptor, numeroContaReceptor);
                break;
            case 3:
                System.out.print("Digite o número da Agência: ");
                String numeroAgencia = input.nextLine();
                System.out.print("Digite o número da Conta: ");
                String numeroConta = input.nextLine();

                exibirSaldo(numeroAgencia, numeroConta);
                break;
            default:
                break;
        }
    }
}
