public class Modelo{
    private String nome;

    public Modelo(){
        this.nome = "";
    }

    public Modelo(String n){
        this.nome = n;
    }

    private Modelo(Modelo m){
        setNome(m.getNome());
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }
}