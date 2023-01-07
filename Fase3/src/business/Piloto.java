package business;
/**
 * Write a description of class Piloto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;

public class Piloto implements Serializable
{
    //Variaveis de instancia
    private String nome;
    private String nacionalidade;
    private int qualidade;
    private int qualidade_chuva;
    
    //Construtores
    public Piloto()
    {
        this.nome = "";
        this.nacionalidade = "";
        this.qualidade = 0;
        this.qualidade_chuva = 0;
    }
    
    public Piloto(String nome, String nacionalidade, int qual, int qual_chuva)
    {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.qualidade = qual;
        this.qualidade_chuva = qual_chuva;
    }
    
    public Piloto(Piloto p)
    {
        this.nome = p.getNome();
        this.nacionalidade = p.getNacionalidade();
        this.qualidade = p.getQualidade();
        this.qualidade_chuva = p.getQualidadeChuva();
    }
    
    //Gets e Sets
    public String getNome()
    {
        return this.nome;
    }
    
    public String getNacionalidade()
    {
        return this.nacionalidade;
    }
    
    public int getQualidade()
    {
        return this.qualidade;
    }
    
    public int getQualidadeChuva()
    {
        return this.qualidade_chuva;
    }
    

    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public void setNacionalidade(String nacionalidade)
    {
        this.nacionalidade = nacionalidade;
    }
    
    public void setQualidade(int q)
    {
        this.qualidade = q;
    }
    
    public void setQualidadeChuva(int q)
    {
        this.qualidade_chuva = q;
    }
    

    
    //Metodos usuais
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome: "); sb.append(this.nome);
        sb.append("\tNacionalidade: ");sb.append(this.nacionalidade);
        sb.append("\tQualidade: ");sb.append(this.qualidade);
        sb.append("\tQualidade á chuva: ");sb.append(this.qualidade_chuva);
        return sb.toString();
    }

    @Override
    public Piloto clone() {
        return new Piloto(this);
    }
    
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        
        if((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        Piloto p = (Piloto) o;
        return (this.nome.equals(p.getNome()) && 
                this.nacionalidade.equals(p.getNacionalidade()) &&
                this.qualidade==p.getQualidade() &&
                this.qualidade_chuva==p.getQualidadeChuva());
    }
}
