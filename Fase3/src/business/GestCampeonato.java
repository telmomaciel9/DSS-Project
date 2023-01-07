package business;

import java.util.HashMap;
import java.util.Map;

public class GestCampeonato {
    private Map<business.User,Float> classifGlobal;

    public GestCampeonato(){
        this.classifGlobal = new HashMap<business.User,Float>();
    }
    public GestCampeonato(Map<business.User,Float> cG){
        this.classifGlobal = cG;
    }

    public GestCampeonato(GestCampeonato gC){
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

    public boolean jogadorRegistado(){
        return false;
    }
}
