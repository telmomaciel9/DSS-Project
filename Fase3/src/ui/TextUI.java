package ui;

import business.IGC;

import java.util.Scanner;

public class TextUI {
    private Menu menu;
    private Scanner in;
    private IGC modelo;

    // Implementar as cenas para dar

    public TextUI(){
        String[] opcoes = { "Configurar Campeonato",
                            "Configurar Corrida",
                            "Simular Corrida"};
        this.menu = new Menu(opcoes);
        this.in   = new Scanner(System.in);
    }

    public void run(){
        do{
            menu.executa();
            switch (menu.getOp()){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;

            }
        } while(menu.getOp() != 0);
        System.out.println("Terminando Aplicação!");
    }
}
