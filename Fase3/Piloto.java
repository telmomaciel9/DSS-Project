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
    private float niveis_pericia;
    private float cts;
    private float sva;

    //Construtores
    public Piloto()
    {
        this.nome = "";
        this.niveis_pericia = 0;
        this.cts = 0;
        this.sva = 0;
    }

    public Piloto(String nome, float niveis_pericia, float cts, float sva)
    {
        this.nome = nome;
        this.niveis_pericia = niveis_pericia;
        this.cts = cts;
        this.sva = sva;
    }

    public Piloto(Piloto p)
    {
        this.nome = p.getNome();
        this.niveis_pericia = p.getNiveisPericia();
        this.cts = p.getCts();
        this.sva = p.getSva();
    }

    //Gets e Sets
    public String getNome()
    {
        return this.nome;
    }

    public float getNiveisPericia()
    {
        return this.niveis_pericia;
    }

    public float getCts()
    {
        return this.cts;
    }

    public float getSva()
    {
        return this.sva;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setNiveis_pericia(float niveis_pericia)
    {
        this.niveis_pericia = niveis_pericia;
    }

    public void setCts(float cts)
    {
        this.cts = cts;
    }

    public void setSva(float sva)
    {
        this.sva = sva;
    }


    //Metodos usuais
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome: "); sb.append(this.nome);
        sb.append("\tNíveis Perícia: ");sb.append(this.niveis_pericia);
        sb.append("\tCritério Chuva vs. Tempo Seco: ");sb.append(this.cts);
        sb.append("\tSegurança vs. Agressividade: ");sb.append(this.sva);
        return sb.toString();
    }

    public Piloto clone()
    {
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
                (this.niveis_pericia == p.getNiveisPericia()) &&
                (this.cts == p.getCts()) &&
                (this.sva == p.getSva()));
    }
}

