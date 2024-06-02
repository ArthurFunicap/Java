package Banco;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TesteOperacoes teste = new TesteOperacoes();
        boolean continuar = true;
        Scanner scanner = new Scanner(System.in);

        while (continuar) {
            try {
                teste.apresentarMenu();
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }

            System.out.println("Deseja continuar? (s/n)");
            String resposta = scanner.nextLine();

            if (!resposta.equalsIgnoreCase("s")) {
                scanner.close();
                continuar = false;
            }
        }
    }
}
