package business;

import java.util.HashMap;
import java.util.Map;

public class Campeonato {
    private String nome;
    private boolean premium;
    private Map<business.User, Integer> classificCampeonato;

    public Campeonato()
    {
        this.nome = "";
        this.premium = false;
        this.classificCampeonato = new HashMap<business.User, Integer>();
    }

    public Campeonato(String nome, boolean premium, Map<business.User, Integer> c)
    {
        this.nome = nome;
        this.premium = premium;
        HashMap<business.User, Integer> aux = new HashMap<business.User, Integer>();
        if(c == null)
        {
            this.classificCampeonato = new HashMap<business.User, Integer>();
        }
        else
        {
            for(business.User s : c.keySet())
            {
                aux.put(s, c.get(s));
            }
        }
        this.classificCampeonato = aux;

    }

    public Campeonato(business.Campeonato c)
    {
        this.nome = c.getNome();
        this.premium = c.getPremium();
        this.classificCampeonato = c.getClassificCampeonato();
    }

    /* Gets e Sets */
    public String getNome()
    {
        return this.nome;
    }

    public boolean getPremium()
    {
        return this.premium;
    }

    public Map<business.User, Integer> getClassificCampeonato()
    {
        HashMap<business.User, Integer> aux;
        aux = new HashMap<business.User, Integer>();
        for(business.User g : this.classificCampeonato.keySet())
        {
            aux.put(g, this.classificCampeonato.get(g));
        }
        return aux;
    }

    public void setNome(String n)
    {
        this.nome = nome;
    }

    public void setPremium(boolean premium)
    {
        this.premium = premium;
    }

    public void setClassificCampeonato(business.User jogador, Integer classificacao)
    {
        this.classificCampeonato.put(jogador, classificacao);
    }

    /* Metodos usuais */
    public business.Campeonato clone()
    {
        return new business.Campeonato(this);
    }

    public String toString()  //
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNome: ");sb.append(this.nome);
        sb.append("\nPremium: ");sb.append(this.premium);
        sb.append("{");
        for (Map.Entry<business.User, Integer> entry : this.classificCampeonato.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o)
    {
        if(this == o)
            return true;

        if(o == null || this.getClass() != o.getClass())
            return false;

        business.Campeonato c = (business.Campeonato) o;
        return ( this.nome.equals(c.getNome()) &&
                 this.premium == (c.getPremium()) &&
                 this.classificCampeonato.equals(c.getClassificCampeonato()));
    }

}

