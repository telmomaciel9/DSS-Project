package business;

import data.CarroDAO;
import data.UserDAO;

import java.util.HashMap;
import java.util.Map;

public class GC {
    private Map<User,Float> classifGlobal;
    private Map<String,User> users;
    private Map<Integer,Carro> carros;

    public GC(){
        this.classifGlobal = new HashMap<User,Float>();
        this.users = UserDAO.getInstance();
        this.carros = CarroDAO.getInstance();
        UserDAO.povoa();
    
    }
    public GC(Map<business.User,Float> gc){
        this.classifGlobal = gc;
    }

    public GC(GC gC){
        this.classifGlobal = gC.getClassifGlobal();
    }

    public Map<business.User,Float> getClassifGlobal(){
        return this.classifGlobal;
    }

    public void setClassifGlobal(Map<business.User,Float> cg){
        this.classifGlobal = cg;
    }


    public void atualizarClassG(){

    }

    public void simulacaoCorrida(){
    }
    public void configurarCorrida(){}

    public void configurarCampeonato(){

    }

    public boolean jogadorRegistado(String user,String pass,boolean tipo){
        return users.containsValue(new User(user,tipo, pass));
    }
}
