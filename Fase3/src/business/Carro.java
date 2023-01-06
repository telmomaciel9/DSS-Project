package business;

public class Carro implements Comparable<Carro> {
    //Variaveis de instancia
    private float pac;
    private int potencia;
    private String marca;

    /* Construtores */
    public Carro() {
        this.pac = 0;
        this.potencia = 0;
        this.marca = "";
    }

    public Carro(float pac, int potencia, String marca) {
        this.pac = pac;
        this.potencia = potencia;
        this.marca = marca;
    }

    public Carro(Carro c) {
        this.pac = c.getPac();
        this.potencia = c.getPotencia();
        this.marca = c.getMarca();
    }

    /* Gets e sets */
    public float getPac() {
        return this.pac;
    }

    public int getPotencia() {
        return this.potencia;
    }

    public String getMarca() {
        return this.marca;
    }


    public void setPac(float pac) {
        this.pac = pac;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nPac: ");
        sb.append(this.pac);
        sb.append("\nPotencia: ");
        sb.append(this.potencia);
        sb.append("\nMarca: ");
        sb.append(this.marca);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        Carro c = (Carro) o;
        return (this.pac == (c.getPac()) &&
                this.potencia == (c.getPotencia()) &&
                this.marca.equals(c.getMarca()));
    }

    public int compareTo(Carro c) {
        return this.marca.compareTo(c.getMarca());
    }
}
}
