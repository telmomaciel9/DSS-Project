package business;
/**
 * Write a description of class Circuito here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

 import java.util.Map;
 import java.util.HashMap;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 
 public class Circuito implements Serializable
 {
     /* Variaveis instancia */
     private String nome;
     private int distancia;
     private int voltas;
     //private long tempoMedio;
     private Map<String,Long> temposMedios;
     private long tempoDesvio;
     private List<String> parteMapa;
     private List<Integer> gdu;
 
     
     /* Construtores */
     public Circuito()
     {
         this.nome = "";
         this.distancia = 0;
         this.voltas = 0;
         this.temposMedios = new HashMap<String, Long>();
         this.tempoDesvio = 0;
         this.parteMapa = new ArrayList<String>();
     }
     
     public Circuito(List<String> partesMapa, String n,int d, int v, Map<String, Long> m, long ds, long b)
     {
         this.nome = n;
         this.distancia = d;
         this.voltas = v;
         List<String> aux1= new ArrayList<String>();
         for(String i : partesMapa){
             aux1.add(i);
         }
         this.parteMapa =aux1;
         HashMap<String,Long> aux = new HashMap<String, Long>();
         if(m == null)
         {
             this.temposMedios = new HashMap<String, Long>();
         }
         else
         {
             for(String g : m.keySet())
             {
                 aux.put(g, m.get(g));
             }
         }
         this.temposMedios = aux;
         this.tempoDesvio = ds;
 
     }
     
     public Circuito(Circuito c)
     {
         this.nome = c.getNome();
         this.distancia = c.getDistancia();
         this.voltas = c.getVoltas();
         this.temposMedios = c.getTemposMedios();
         this.tempoDesvio = c.getTempoDesvio();
         this.parteMapa = c.getParteMapa();
     }
     
     /* Gets e Sets */
     public String getNome()
     {
         return this.nome;
     }
     
     public int getDistancia()
     {
         return this.distancia;
     }
     
     public int getVoltas()
     {
         return this.voltas;
     }
 
     public List<Integer> getGDU(){
         List<Integer> aux = new ArrayList<Integer>();
         for (Integer i : this.gdu){
             aux.add(i);
         }
         return aux;
     }
 
     public List<String> getParteMapa(){
         List<String> aux = new ArrayList<String>();
         for (String i : this.parteMapa){
             aux.add(i);
         }
         return aux;
     }
     
     public Map<String,Long> getTemposMedios()
     {
         HashMap<String,Long> aux = new HashMap<String, Long>();
         for(String g : this.temposMedios.keySet())
         {
             aux.put(g, this.temposMedios.get(g));
         }
         return aux;
     }
     
     public long getTempoDesvio()
     {
         return this.tempoDesvio;
     }
     

     
 
     
     public void setNome(String n)
     {
         this.nome = n;
     }
     
     public void setDistancia(int d)
     {
         this.distancia = d;
     }
     
     public void setVoltas(int v)
     {
         this.voltas = v;
     }
     
     public void setTempoDesvio(long ds)
     {
         this.tempoDesvio = ds;
     }
     

     
 
     public void setTempoMedio(String categoria, long tempo)
     {
         this.temposMedios.put(categoria, tempo);
     }
 
 
     public void converteCircuitoGDU(){
         int x = 0;
         for (String p : this.parteMapa){
             if (p == "Reta"){
                 x = 1;
             }
             if (p == "Curva"){
                 x = 2;
             }
 
             if (p == "Chicane"){
                 x = 3;
             }
             this.gdu.add(x);
         }
     }
 
     
     /* Metodos usuais */
     public Circuito clone()
     {
         return new Circuito(this);
     }
     
     public String toString()
     {
         StringBuilder sb = new StringBuilder();
         sb.append("\nNome: ");sb.append(this.nome);
         sb.append("\nDistancia: ");sb.append(this.distancia);
         sb.append("\nNumero de voltas: ");sb.append(this.voltas);
         //sb.append("\nTempo Medio: ");sb.append(TimeConverter.toTimeFormat(this.tempoMedio));
         sb.append("\nDesvio Tempo: ");sb.append(TimeConverter.toTimeFormat(this.tempoDesvio));
         return sb.toString();
     }
     
     public boolean equals(Object o)
     {
        if(this == o)
        return true;
        
        if(o == null || this.getClass() != o.getClass())
        return false;
        
        Circuito c = (Circuito) o;
        return ( this.nome.equals(c.getNome()) &&
                 this.distancia == c.getDistancia() &&
                 this.voltas == c.getVoltas() &&
                 //this.tempoMedio == c.getTempoMedio() &&
                 this.tempoDesvio == c.getTempoDesvio());
     }
     
 }