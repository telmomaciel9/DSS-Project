package business;
/**
 * Write a description of class PC2H here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;

public class PC2H extends PC2 implements Hibrido
{
    private int motor_eletrico;
    
    public PC2H()
    {
        super();
        this.motor_eletrico = 0;
    }
    
    public PC2H(int id, String marca, String modelo, int cilindrada, int potencia, Piloto p,int p_mecanica, int eletrico, String tipoPneu,String modoMotor)
    {
        super(id,marca,modelo,cilindrada,potencia,p_mecanica,p,tipoPneu,modoMotor);
        this.motor_eletrico = eletrico;
    }
    
    public PC2H(PC2H p)
    {
        super(p);
        this.motor_eletrico = p.getPotenciaMotorEletrico();
    }
    
    public PC2H clone()
    {
        return new PC2H(this);
    }
    
    public int getPotenciaMotorEletrico()
    {
        return this.motor_eletrico;
    }
    
    public void setPotenciaMotorEletrico(int e)
    {
        this.motor_eletrico = e; 
    }
    
    public boolean DNF(int volta,int totalvoltas,int clima)
    {
       Random rand=new Random();
       int x=rand.nextInt(85);
       int motorh = this.getPotenciaMotorEletrico()/20;
       //no maximo fiabilidade de 85%
       int fiabilidade = super.getFiabilidade() + (super.getCilindrada()/1200) + (super.getPreparacaoMecaninca()/10);
       int desgaste = (int)(volta * 0.5);
       if (super.getModoMotor() == "Agressivo"){
        desgaste = 2 * desgaste;
       }
       if (super.getModoMotor() == "Conservador"){
        desgaste = desgaste/2;
       }
       return (x > (fiabilidade-motorh-desgaste));
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        PC2H c = (PC2H) o;
        return ( super.equals(c) && this.motor_eletrico == c.getPotenciaMotorEletrico());
    }   
}
