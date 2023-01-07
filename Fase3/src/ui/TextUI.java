package ui;

import business.IGC;

import java.util.Scanner;

public class TextUI {
    private Menu menu;
    private Scanner in;
    private IGC cg;

    // Implementar as cenas para dar

    public TextUI(){
        String[] opcoes = { "Iniciar - Login",
                            "Configurar Campeonato",
                            "Configurar Corrida",
                            "Simular Corrida"};
        this.menu = new Menu(opcoes);
        this.in   = new Scanner(System.in);
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
                    System.out.println("2");
                    break;
                case 3:
                    if(!flag) break;
                    System.out.println("3");
                    break;
                case 4:
                    if(!flag) break;
                    System.out.println("4");
                    break;
                }
        } while(menu.getOp() != 0);
        System.out.println("Terminando Aplicação!");
    }


    public boolean login(){
        boolean flag = false;
        String username;
        String password;
        do{
            menu.login();
            menu.user();
            username = in.nextLine();
            menu.pass();
            password = in.nextLine();
        } while(!(cg.jogadorRegistado(username,password)));
        if(cg.jogadorRegistado(username,password)){
            flag = true;
        }
        return flag;
    }
}
