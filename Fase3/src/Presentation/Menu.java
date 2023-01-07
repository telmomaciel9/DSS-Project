package Presentation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Scanner in = new Scanner(System.in);
    private final List<String> opcoes;
    private int op;

    //Construtotor
    public Menu(String[] op){
        this.opcoes = Arrays.asList(op);
        this.op = 0;
    }

    // Método que interpreta a escolha do utilizador
    public void executa(){
        do{
            fazMenu();
            this.op = lerOpcao();
        } while(this.op == -1);
    }

    // Método que apresenta o menu com as opções
    private void fazMenu(){
        System.out.println("\n--------------------------------------");
        System.out.println("  Sistema de Simulaçao de Corridas ");
        System.out.println("--------------------------------------");
        for(int i = 0; i<this.opcoes.size(); i++){
            System.out.println("  " + (i+1) + " | " + this.opcoes.get(i));
        }
        System.out.println("  0 | Sair");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("  Atenção! Antes de qualquer operçao precisa de executar o login");
        System.out.println("--------------------------------------------------------------------");

    }

    private void mensagemErro(){
        System.out.println("Opção Inválida!");
    }

    private void erroException(Exception e){
        System.out.println(e.toString());
    }
    // Método que interpreta a opçao escolhida
    private int lerOpcao(){
        int op = 0;
        System.out.print("Escolha Opção: ");
        try{
            op = Integer.parseInt(in.nextLine());
            if(op<0 || op> this.opcoes.size()){
                mensagemErro();
                op = -1;
            }
        }
        catch (NumberFormatException e){
            op = -1;
           erroException(e);
        }

        return op;
    }

    // Método para obter a opção
    public int getOp(){
        return this.op;
    }

    public void login(){
        System.out.println("\n ------------------------------------");
        System.out.println("               LOGIN             ");
        System.out.println(" ------------------------------------");
    }
    public  void user(){
        System.out.println(" Introduzir Username: ");
    }

    public  void pass(){
        System.out.println(" Introduzir Password: ");
    }

    public  void tipo(){
        System.out.println(" Jogador Premium(true/false): ");
    }

}
