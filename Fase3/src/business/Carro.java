package business;

import business.Piloto;

import java.util.Map;
import java.io.Serializable;

public abstract class Carro implements Comparable<Carro>,Serializable
{
    //Variaveis de instancia
    private int id;
    private String marca;
    private String modelo;
    private int cilindrada;
    private int potencia;
    private Piloto piloto;
    private int fiabilidade;
    private long tempo;
    private boolean dnf;
    private String tipoPneu;
    private String modoMotor;

    /* Construtores */
    public Carro()
    {
        this.id = 0;
        this.marca = "";
        this.modelo = "";
        this.cilindrada = 0;
        this.potencia = 0;
        this.piloto = new Piloto();
        this.fiabilidade = 0;
        this.tempo = 0;
        this.dnf = false;
        this.tipoPneu ="";
        this.modoMotor = "";
    }
    
    public Carro(int id, String marca, String modelo, int cilindrada, int potencia,  int fiabilidade, Piloto p , String tipoPneu, String modoMotor)
    {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.piloto = p.clone();
        this.fiabilidade = fiabilidade; 
        this.tempo = 0;
        this.dnf = false;
        this.tipoPneu = tipoPneu;
        this.modoMotor = modoMotor;
    }
    
    public Carro(Carro c)
    {
       this.id = c.getID();
       this.marca = c.getMarca();
       this.modelo = c.getModelo();
       this.cilindrada = c.getCilindrada();
       this.potencia = c.getPotencia();
       this.piloto = c.getPiloto();
       this.fiabilidade = c.getFiabilidade();
       this.tempo = c.getTempo();
       this.dnf = c.getDNF();
       this.tipoPneu = c.getTipoPneu();
       this.modoMotor = c.getModoMotor();
    }


    /* Gets e sets */

    public int getID(){
        return this.id;
    }

    public long getTempo()
    {
        return this.tempo;
    }
    
    
    public String getMarca()
    {
        return this.marca;
    }

    public String getModelo() {

        return this.modelo;
    }
    
    public int getCilindrada()
    {
        return this.cilindrada;
    }
    
    public int getPotencia()
    {
        return this.potencia;
    }
    
    public Piloto getPiloto()
    {
        return this.piloto.clone();
    }
    
    public int getFiabilidade()
    {
        return this.fiabilidade;
    }
    
    public boolean getDNF()
    {
        return this.dnf;
    }

    public String getTipoPneu(){
        return this.tipoPneu;
    }

    public String getModoMotor(){
        return this.modoMotor;
    }
    
    public void setID(int id)
    {
        this.id = id;
    }
    
    public void setMarca(String marca)
    {
        this.marca = marca;
    }
    
    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }
    
    public void setCilindrada(int cilindrada)
    {
        this.cilindrada = cilindrada;
    }
    
    public void setPotencia(int potencia)
    {
        this.potencia = potencia;
    }
    
    public void setPiloto(Piloto p)
    {
        this.piloto = piloto.clone();
    }
    
    public void setTempo(long t)
    {
        this.tempo = t;
    }
    
    public void setDNF(boolean b)
    {
        this.dnf = b;
    }

    public void setTipoPneu(String tipoPneu){
        this.tipoPneu = tipoPneu;
    }

    public void setModoMotor(String modoMotor){
        this.modoMotor = modoMotor;
    }


    /* Metodos usuais */
    public abstract Carro clone();
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nID: ");sb.append(this.id);
        sb.append("\nMarca: ");sb.append(this.marca);
        sb.append("\nModelo: ");sb.append(this.modelo);
        sb.append("\nCilindrada: ");sb.append(this.cilindrada);
        sb.append("\nPotencia: ");sb.append(this.potencia);
        sb.append("\nFiabiliade: ");sb.append(this.fiabilidade);
        sb.append("\nTempo: ");sb.append(this.tempo);
        sb.append("\nDNF: ");sb.append(this.dnf);
        sb.append("\nTipo Pneu: ");sb.append(this.tipoPneu);
        sb.append(this.piloto.toString());
        return sb.toString();
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
            return true;
        
        if(o==null || this.getClass()!=o.getClass())
            return false;
        
        Carro c = (Carro) o;
        return( this.id == c.getID() &&
                this.marca.equals(c.getMarca()) &&
                this.modelo.equals(c.getModelo()) &&
                this.cilindrada == c.getCilindrada() &&
                this.potencia == c.getPotencia() &&
                this.piloto.equals(c.getPiloto()) &&
                this.fiabilidade == c.getFiabilidade() &&
                this.tempo == c.getTempo() &&
                this.dnf == c.getDNF() &&
                this.tipoPneu == c.getTipoPneu());
    }
    
    public int compareTo(Carro c)
    {
        if(this.tempo < c.getTempo())
            return -1;
        if(this.tempo > c.getTempo())
            return 1;
        else 
            return 0;
    }
    
    //Outros metodos
    /**
     * Tempo em milisegundos de uma volta
     */
    public long tempoProximaVolta(business.Circuito c, int clima, int volta)
    {
        Map<String,Long> aux = c.getTemposMedios();
        long t_medio = aux.get(this.getClass().getName());
        long t_chuva = c.getTempoDesvio();
        long minimum = 500;
        long maximum = 5000;
        long fator_sorte = minimum + Double.valueOf(Math.random()*(maximum-minimum)).intValue();
        long maximum_chuva = 2000;
        long fator_sorte_chuva= minimum + Double.valueOf(Math.random()*(maximum_chuva-minimum)).intValue();
        int desgaste = (int)((volta+1)*10); //1 a cada volta


        if (this.tipoPneu == "Chuva" && clima == 1){
            fator_sorte = (10 * fator_sorte)-desgaste; 
        }
        if (this.tipoPneu != "Chuva" && clima == 1){
            fator_sorte = fator_sorte/10 -desgaste; 
        }
        if (this.tipoPneu == "Macio" && clima == 0){
            fator_sorte = fator_sorte * 2 -desgaste;
        }
        if (this.tipoPneu == "Chuva" && clima == 0){
            fator_sorte = fator_sorte / 3 -desgaste;
        }

        if (this.modoMotor == "Agressivo"){
            fator_sorte += fator_sorte/2 + (volta * 20);  
        }
        if (this.modoMotor == "Conservador"){
            fator_sorte -= fator_sorte / 2 + (volta * 5);
        }

        long tempoL = (t_medio + ((this.getCilindrada()/this.getPotencia())-this.piloto.getQualidade())* 1000L) - fator_sorte
                + (clima*(t_chuva - this.piloto.getQualidadeChuva()* 1000L)) - fator_sorte_chuva;
        System.out.println("Marca: " + this.marca + " | Tempo: " + tempoL);
        return tempoL;
    }

    
    /**
     * define se o carro desiste (true desiste, false continua em prova)
     */
    public abstract boolean DNF(int volta,int totalvoltas,int clima);

}
