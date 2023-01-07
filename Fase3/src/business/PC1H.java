package business;
/**
 * Write a description of class PC1H here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Random;

public class PC1H extends PC1 implements Hibrido
{
    private int motor_eletrico;
    
    public PC1H()
    {
       super();
       this.motor_eletrico = 0;
    }
    
    public PC1H(int id, String marca, String modelo, int cilindrada, int potencia, Piloto p, int eletrico,String tipoPneu,String modoMotor)
    {
        super(id,marca,modelo,cilindrada,potencia,p,tipoPneu,modoMotor);
        this.motor_eletrico = eletrico;
    }
    
    public PC1H(PC1H p)
    {
        super(p);
        this.motor_eletrico = p.getPotenciaMotorEletrico();
    }
    
    public PC1H clone()
    {
        return new PC1H(this);
    }
    
    public int getPotenciaMotorEletrico()
    {
        return this.motor_eletrico;
    }
    
    public void setPotenciaMotorEletrico(int potencia)
    {
        this.motor_eletrico = potencia;
    }
    
    public boolean DNF(int volta,int totalvoltas,int clima)
    {
       Random rand=new Random();
       int x=rand.nextInt(85);
       int motorh = this.getPotenciaMotorEletrico()/20;
       int desgaste = (int)(volta * 0.5);
       if (super.getModoMotor() == "Agressivo"){
        desgaste = 2 * desgaste;
       }
       if (super.getModoMotor() == "Conservador"){
        desgaste = desgaste/2;
       }
       return (x > (super.getFiabilidade()-motorh-desgaste));
       //return false;
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        PC1H c = (PC1H) o;
        return ( super.equals(c) && this.motor_eletrico == c.getPotenciaMotorEletrico());
    }    
}
