package business;
/**
 * Write a description of class PC1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import business.Carro;

import java.util.Map;
import java.util.Random;

public class PC1 extends Carro
{
    public PC1()
    {
        super();
    }
    
    public PC1(int id,String marca, String modelo, int cilindrada, int potencia,Piloto p,String tipoPneu,String modoMotor)
    {
        super(id,marca,modelo,cilindrada,potencia,95,p,tipoPneu,modoMotor);
    }
    
    public PC1(PC1 p)
    {
        super(p);
    }
    
    public PC1 clone()
    {
        return new PC1(this);
    }
    
    public boolean DNF(int volta,int totalvoltas,int clima)
    {
       Random rand=new Random();
       int x=rand.nextInt(87);
       int desgaste = (int)(volta * 0.5);
       if (super.getModoMotor() == "Agressivo"){
        desgaste = 2 * desgaste;
       }
       if (super.getModoMotor() == "Conservador"){
        desgaste = desgaste/2;
       }
       return (x > super.getFiabilidade()-desgaste);
       //return false;
    }
    
    public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        PC1 c = (PC1) o;
        return ( super.equals(c));
    }
}
