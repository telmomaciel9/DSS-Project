package business;

import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Circuito implements Serializable
{
    public enum GDU {possivel, dificil, impossivel};
    /* Variaveis instancia */
    private String nome;
    private double nr_km;
    private int nr_curvas;
    private int nr_chicane;
    private int nr_retas;
    private int nr_voltas;
    private Map<String,GDU> GDUcircuito;

    /* Construtores */
    public Circuito()
    {
        this.nome = "";
        this.nr_km = 0;
        this.nr_curvas = 0;
        this.nr_chicane = 0;
        this.nr_retas = 0;
        this.nr_voltas = 0;
        this.GDUcircuito = new HashMap<String, GDU>();
    }

    public Circuito(String nome, double km, int curvas, int chicane, int retas, int voltas, Map<String, GDU> g)
    {
        this.nome = nome;
        this.nr_km = km;
        this.nr_curvas = curvas;
        this.nr_chicane = chicane;
        this.nr_retas = retas;
        this.nr_voltas = voltas;
        HashMap<String,GDU> aux = new HashMap<String, GDU>();
        if(g == null)
        {
            this.GDUcircuito = new HashMap<String, GDU>();
        }
        else
        {
            for(String s : g.keySet())
            {
                aux.put(s, g.get(s));
            }
        }
        this.GDUcircuito = aux;

    }

    public Circuito(Circuito c)
    {
        this.nome = c.getNome();
        this.nr_km = c.getNr_km();
        this.nr_curvas = c.getNr_curvas();
        this.nr_chicane = c.getNr_chicane();
        this.nr_retas = c.getNr_retas();
        this.nr_voltas = c.getNr_voltas();
        this.GDUcircuito = c.getDGUcircuito();
    }

    /* Gets e Sets */
    public String getNome()
    {
        return this.nome;
    }

    public double getNr_km()
    {
        return this.nr_km;
    }

    public int getNr_curvas()
    {
        return this.nr_curvas;
    }

    public int getNr_chicane()
    {
        return this.nr_chicane;
    }

    public int getNr_retas()
    {
        return this.nr_retas;
    }

    public int getNr_voltas()
    {
        return this.nr_voltas;
    }

    public Map<String,GDU> getDGUcircuito()
    {
        HashMap<String,GDU> aux = new HashMap<String, GDU>();
        for(String g : this.GDUcircuito.keySet())
        {
            aux.put(g, this.GDUcircuito.get(g));
        }
        return aux;
    }

    public void setNome(String n)
    {
        this.nome = n;
    }

    public void setNr_km(double km)
    {
        this.nr_km = km;
    }

    public void setNr_curvas(int curvas)
    {
        this.nr_curvas = curvas;
    }

    public void setNr_chicane(int chicane)
    {
        this.nr_chicane = chicane;
    }

    public void setNr_retas(int retas)
    {
        this.nr_retas = retas;
    }

    public void setNr_voltas(int voltas)
    {
        this.nr_voltas = voltas;
    }
// MUDAR A categoria E O tempo
    public void setGDUcircuito(String categoria, GDU tempo)
    {
        this.GDUcircuito.put(categoria, tempo);
    }

    /* Metodos usuais */
    public Circuito clone()
    {
        return new Circuito(this);
    }

    public String toString()  //
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome: ");sb.append(this.nome);
        sb.append("\nKilometros: ");sb.append(this.nr_km);
        sb.append("\nCurvas: ");sb.append(this.nr_curvas);
        sb.append("\nChicane: ");sb.append(this.nr_chicane);
        sb.append("\nRetas: ");sb.append(this.nr_retas);
        sb.append("\nVoltas: ");sb.append(this.nr_voltas);
        // ver o map
        return sb.toString();
    }

    public boolean equals(Object o)
    {
        if(this == o)
            return true;

        if(o == null || this.getClass() != o.getClass())
            return false;

        Circuito c = (Circuito) o; // nome km curvas chicane retas voltas
        return ( this.nome.equals(c.getNome()) &&
                this.nr_km == (c.getNr_km()) &&
                this.nr_curvas == (c.getNr_curvas()) &&
                this.nr_chicane == (c.getNr_chicane()) &&
                this.nr_retas == (c.getNr_retas()) &&
                this.nr_voltas == (c.getNr_voltas()));
        // ver o map
    }

}