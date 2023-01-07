package business;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;
import java.io.Serializable;

public class Campeonato implements Serializable
{
    private Map<String,Carro> usersCarro; //username associado a Carro
    private List<Corrida> corridas;
    private Map<String,Integer> classificacao;
    private int prova; //incrementa a cada prova realizada (aponta para a prova a realizar)
    
    public Campeonato()
    {
        this.usersCarro = new HashMap<String,Carro>();
        this.corridas = new ArrayList<Corrida>();
        this.classificacao = new HashMap<String,Integer>();
        this.prova = 0;
    }
    
    public Campeonato(Map<String,Carro> uCar, List<Corrida> cor, Map<String,Integer> cla, Map<String,Integer> claH, int prova)
    {
        this();
        HashMap<String,Carro> aux5 = new HashMap<String,Carro>();
        for(String u: uCar.keySet()){
            aux5.put(u, uCar.get(u));
        }
        this.usersCarro = aux5;

        ArrayList<Corrida> aux = new ArrayList<Corrida>();
        for(Corrida co : cor)
        {
            aux.add(co.clone());
        }
        this.corridas = aux;

        HashMap<String,Integer> aux2 = new HashMap<String,Integer>();
        for(String c: cla.keySet())
        {
            aux2.put(c, cla.get(c));
        }
        HashMap<String,Integer> aux3 = new HashMap<String,Integer>();
        for(String c: claH.keySet())
        {
            aux3.put(c, claH.get(c));
        }
        this.classificacao = aux3;
        this.prova = prova;
    }
    
    public Campeonato(Campeonato c)
    {   
        this.usersCarro = c.getUserCarro();
        this.corridas = c.getCorridas();
        this.classificacao = c.getClassificacao();
        this.prova = c.getProva();
    }
    


    public Map<String,Carro> getUserCarro(){
            HashMap<String,Carro> aux = new HashMap<String,Carro>();
            for(String u : this.usersCarro.keySet())
            {
                aux.put(u, this.usersCarro.get(u));
            }
            return aux;
        }
        

    public List<Corrida> getCorridas()
    {
        ArrayList<Corrida> aux = new ArrayList<Corrida>();
        for(Corrida co : this.corridas)
        {
            aux.add(co.clone());
        }
        return aux;
    }
    
    public Map<String, Integer> getClassificacao()
    {
        HashMap<String,Integer> aux = new HashMap<String,Integer>();
        for(String c : this.classificacao.keySet())
        {
            aux.put(c, this.classificacao.get(c));
        }
        return aux;
    }
    







    
    public int getProva()
    {
        return this.prova;
    }




    //Metodos
    /**
     * Adicionar corrida ao campeonato
     */
    public void addCorrida(Corrida c)
    {
        this.corridas.add(c.clone());
    }
    

    
    /**
     * Simular proxima corrida
     */
    public String simularProximaCorrida()
    {
        //StringBuilder sb = new StringBuilder();
        String res;
        if(this.corridas.size() == this.prova)
        {
            //sb.append("\nNÃO HÁ CORRIDAS POR REALIZAR!!!");
            res = "\nNÃO HÁ CORRIDAS POR REALIZAR!!!";
        }
        else
        {
            this.corridas.get(this.prova).simulaCorridaPremium();
            //sb.append(this.corridas.get(this.prova).printResultados());
            res = this.corridas.get(this.prova).printResultados();
            this.prova++;
        }
        
        //return sb.toString();
        return res;
    }



    
    /**
     * Obter corrida nr x
     */
    public Corrida getCorrida(int x)
    {
        return this.corridas.get(x-1).clone();
    }


    
    /**
     * Lista a classificacao atual
     */
    public String printClassificacao()
    {
        //chamo o ordena e faço print!!
        List<Map.Entry<String, Integer>> aux = this.ordenaClassificacao(this.classificacao);
        StringBuilder sb = new StringBuilder();
        sb.append("\nClassificacao Geral");
        sb.append("\n=========================");
        for(int i=0;i<aux.size();i++)
        {
            sb.append("\n");
            sb.append(i+1);sb.append("º: ");sb.append(aux.get(i));
            //sb.append("\t");sb.append(aux.get(i));
            //i++;
        }
        return sb.toString();
    }
    
    /**
     * Atualizar classificacao campeonato
     */
    public void atualizarClassificacao()
    {
            int i = this.prova-1;
            Set<Carro> aux = this.corridas.get(i).getResultados();
            int x=4, old_value;
            String user = "";
            for(Carro c : aux)
            {    

                old_value = 0;

                for (Map.Entry<String,Carro> u : this.usersCarro.entrySet()){
                    if(u.getValue().equals(c)) {
                        user = u.getKey();
                    }
                }
                if(this.classificacao.containsKey(user))
                {
                old_value = this.classificacao.get(user);
                }
                if(x==4)
                {
                    this.classificacao.put(user, old_value+16);
                }
                if(x==3)
                {
                   this.classificacao.put(user, old_value+8);
                }
                if(x==2)
                {
                   this.classificacao.put(user, old_value+4);
                }
                if(x==1)
                {
                   this.classificacao.put(user, old_value+2);
                }
                if(x==0)
                {
                   this.classificacao.put(user, old_value+1);
                }
                if(x<0)
                {
                   this.classificacao.put(user, 0+old_value); 
                }
                x--;
                }
            
            
            Map<Carro,Integer> aux2 = this.corridas.get(i).getDNF();
            for(Carro q : aux2.keySet())
            {
                
                for (Map.Entry<String,Carro> u : this.usersCarro.entrySet()){
                    if(u.getValue().equals(q)) {
                        user = u.getKey();
                    }
                }
                old_value = 0;


                if(this.classificacao.containsKey(user)){
                    old_value = this.classificacao.get(user);
                    this.classificacao.put(user,0+old_value);
                }
                
            }
    }
    





    

    

    private List<Map.Entry<String, Integer>> ordenaClassificacao(Map<String,Integer> classificacao)
    {
        List<Map.Entry<String, Integer>> ordenado = new ArrayList<Map.Entry<String, Integer>>(classificacao.entrySet());
        Collections.sort(ordenado, new Comparator<Map.Entry<String, Integer>>() 
        {
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) 
            {
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        return ordenado;
    }
    
    /**
     * Info corrida x
     */
    public String resultadosCorrida(int x)
    {
        //StringBuilder sb = new StringBuilder();
        //if(this.prova < x)
        //{
          //  String s = ("\nA prova escolhida não existe ou ainda não foi realizada!");
            //return s;
        //}
        //else
        //{
        return this.getCorrida(x).printResultados();
        //}
        //return sb.toString();
    }
    
    /**
     * Verificar se corrida já foi realizada
     */
    public boolean corridaRealizada(int x)
    {
        return ((x-1) < this.prova);
    }
    
    /**
     * Lista Carros a participar em proxa nr x
     */
    public String listaParticipantes(int x)
    {
        StringBuilder sb = new StringBuilder();
        Corrida aux = this.corridas.get((x-1));
        sb.append(aux.listaCarrosParticipantes());
        return sb.toString();
    }
    
    /**
     * Lista Circuitos
     */
    public String listaCircuitos()
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<this.corridas.size();i++)
        {
            sb.append("\n");
            sb.append(i+1);sb.append("- ");sb.append(this.corridas.get(i).getCircuito().getNome());
        }
        return sb.toString();
    }
}
