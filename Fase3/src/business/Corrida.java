package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Corrida {
    private List<User> classCorrida;

    public Corrida(){
        this.classCorrida = new ArrayList<User>();
    }
    public Corrida(List<User> user){
        this.classCorrida = user;
    }

    public Corrida(Corrida c){
        setClassCorrida(c.getclassCorrida());
    }
    public List<User> getclassCorrida(){
        return this.classCorrida;
    }

    public void setClassCorrida(List<User> classificacao){
        this.classCorrida = classificacao;
    }

    public void simulacao(){}
    public void simulacaoPremium(){}
    public Boolean ultrapassagem(User user1, User user2, int gdu, int diferenca){
        return false;
    }
    public void newCorrida(User user, Carro carro, Carro.ModoMotor modoMotor, Carro.TipoPneu pneu, float pac){

    }

    public void trocaPosicao(User user1, User user2){
        int posicaoUser1 = 0;
        int posicaoUser2 = 0;
        int c = 0;
        boolean flag1 = true;
        boolean flag2 = true;
        for(User u: this.classCorrida){
            if(!flag1 && !flag2)
                break;
            if(u.equals(user1)) {
                posicaoUser1 = c;
                flag1 = false;
            }
            if(u.equals(user2)) {
                posicaoUser2 = c;
                flag2 = false;
            }

        }

        Collections.swap(classCorrida,posicaoUser1,posicaoUser2);
    }

    public int posicaoAtual(User user1){
        return classCorrida.indexOf(user1);
    }


    public Boolean comparaCarro(Carro carro1, Carro carro2){
        return carro1.equals(carro2);
    }

    public Boolean comparaCarroPremium(Carro carro1, Carro carro2, float t1, float t2){
        return false;
    }

}
