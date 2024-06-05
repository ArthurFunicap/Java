package Banco;

import java.util.Scanner;

class DepositoInvalidoException extends Exception{
    public DepositoInvalidoException(){
        super("Valor para depósito inválido!");
    }

    public DepositoInvalidoException(String mensagem){
        super(mensagem);
    }
}

class SaqueInvalidoException extends Exception{
    public SaqueInvalidoException(){
        super("Saque negado!");
    }

    public SaqueInvalidoException(String mensagem){
        super(mensagem);
        System.out.println("Não é possível sacar os centavos do saldo.");
        System.out.println("Não é possível realizar um saque de uma quantia maior que o disponível na conta.");
    }
}

class ContaInexistenteException extends Exception{
    public ContaInexistenteException(){
        super("A conta informada não existe!");
    }

    public ContaInexistenteException(String mensagem){
        super(mensagem);
    }
}

class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException(){
        super("Saldo insuficente!");
    }

    public SaldoInsuficienteException(String mensagem){
        super(mensagem);
    }
}

class ValorNegativoException extends Exception{
    public ValorNegativoException(){
        super("O valor informado não pode ser negativo!");
    }

    public ValorNegativoException(String mensagem){
        super(mensagem);
    }
}

public class Conta {
    private String numeroAgencia;
    private String numeroConta;
    private double saldo;
    private Cliente cliente;

    public Conta(String numeroAgencia, String numeroConta, double saldo, Cliente cliente) {
        this.numeroAgencia = numeroAgencia;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public String getNumeroAgencia() {
        return numeroAgencia;
    }
    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }
    public String getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    void deposito(Double quantia) throws DepositoInvalidoException{
        if(quantia < 0){
            throw new DepositoInvalidoException();
        }

        this.saldo += quantia;
        System.out.println("Depósito realizado com sucesso!");
    }

    void saque(Double quantia) throws SaqueInvalidoException{
        if(quantia > this.saldo){
            throw new SaqueInvalidoException();
        }

        this.saldo -= quantia;
        System.out.println("Saque realizado com sucesso!");
    }

    void transferencia(Conta conta) throws SaldoInsuficienteException, ValorNegativoException{
        Scanner input = new Scanner(System.in);
        double valorTransferencia;

        System.out.print("Digite um valor: ");
        valorTransferencia = input.nextDouble();

        if(valorTransferencia > this.saldo){
            throw new SaldoInsuficienteException();
        }
        else if(valorTransferencia < 0){
            throw new ValorNegativoException();
        }

        conta.setSaldo(conta.getSaldo()+valorTransferencia);
        this.setSaldo(this.getSaldo()-valorTransferencia);
        System.out.println("Transferência realizada com sucesso!");
    }

    void exibirSaldo(){
        System.out.println("Nome: " + this.cliente.getNome());
        System.out.printf("Saldo: %.2f\n", this.saldo);
    }
}
