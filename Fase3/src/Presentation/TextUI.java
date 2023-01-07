package Presentation;

import business.GC;

import java.util.Scanner;

public class TextUI {
    private Menu menu;
    private Scanner in;
    private GC gc;

    // Implementar as cenas para dar

    public TextUI(){
        String[] opcoes = { "Iniciar - Login",
                            "Configurar Campeonato",
                            "Configurar Corrida",
                            "Simular Corrida"};
        this.menu = new Menu(opcoes);
        this.in   = new Scanner(System.in);
        this.gc = new GC();
    }

    public void run(){
        boolean flag = false;
        do{
            menu.executa();
            switch (menu.getOp()){
                case 1:
                    login();
                    flag = login();
                    break;
                case 2:
                    if(!flag) break;
                    //configurarCampeonato();
                    break;
                case 3:
                    if(!flag) break;
                    //configurarCorrida();
                    break;
                case 4:
                    if(!flag) break;
                    System.out.println("2");
                    simularCorrida();
                    break;
                }
        } while(menu.getOp() != 0);
        System.out.println("Terminando Aplicação!");
    }


    public boolean login(){
        boolean flag = false;
        String username;
        String password;
        boolean tipo;
        do{
            menu.login();
            menu.user();
            username = in.nextLine();
            menu.pass();
            password = in.nextLine();
            menu.tipo();
            tipo = in.nextBoolean();
        } while(!(gc.jogadorRegistado(username,password,tipo)));
        if(gc.jogadorRegistado(username,password,tipo)){
            flag = true;
        }
        return flag;
    }

    public void simularCorrida(){
        this.gc.simulacaoCorrida();
    }
}
