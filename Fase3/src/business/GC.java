package business;

import data.CarroDAO;
import data.UserDAO;

import java.util.HashMap;
import java.util.Map;

public class GC {
    private Map<User,Float> classifGlobal;
    private Map<String,User> users;
    private Map<Integer,Carro> carros;
    private Map<String,Piloto> piloto;


    public GC(){

        this.classifGlobal = new HashMap<User,Float>();
        this.users = UserDAO.getInstance();
        this.carros = CarroDAO.getInstance();
    
    }
    public GC(Map<business.User,Float> cG){
        this.classifGlobal = cG;
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

    public void newCampeonato(String nome){

    }

    public void atualizarClassG(){

    }

    public boolean jogadorRegistado(String user,String pass){
        return true;
    }
}
