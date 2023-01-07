package business;

public class Carro implements Comparable<Carro> {
    public enum TipoPneu {Macio,Duro,Chuva};
    public enum ModoMotor{Conservador,Normal,Agressivo};

    //Variaveis de instancia
    
    private String marca;
    private int ID;
    private String modelo;
    private int potencia;
    private float pac;
    private String tipoPneu;
    private String modoMotor;

    /* Construtores */
    public Carro() {
        this.ID = 0;
        this.pac = 0;
        this.potencia = 0;
        this.marca = "";
        this.modelo = "";
        this.tipoPneu = "";
        this.modoMotor = "";
    }

    public Carro(int id,float pac, int potencia, String marca, String modelo, String pneu, String motor) {
        this.ID = id;
        this.pac = pac;
        this.potencia = potencia;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoPneu = pneu;
        this.modoMotor = motor;
    }

    public Carro(Carro c) {
        this.ID = c.getID();
        this.pac = c.getPac();
        this.potencia = c.getPotencia();
        this.marca = c.getMarca();
        this.modelo = c.getModelo();
        this.tipoPneu = c.getTipoPneu();
        this.modoMotor = c.getModoMotor();
    }

    /* Gets e sets */

    public int getID(){return this.ID; }

    public float getPac() {
        return this.pac;
    }

    public int getPotencia() {
        return this.potencia;
    }

    public String getMarca() {
        return this.marca;
    }

    public Integer getModelo() {
        return this.modelo;
    }

    public String getTipoPneu(){
        return this.tipoPneu;
    }

    public String getModoMotor(){
        return this.modoMotor;
    }

    public void setID(int id){
        this.ID = id;
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

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTipoPneu(String tipoPneu) {
        this.tipoPneu = tipoPneu;
    }

    public void setModoMotor(String modoMotor) {
        this.modoMotor = modoMotor;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nID: ");
        sb.append(this.ID);
        sb.append("\nPac: ");
        sb.append(this.pac);
        sb.append("\nPotencia: ");
        sb.append(this.potencia);
        sb.append("\nMarca: ");
        sb.append(this.marca);
        sb.append("\nModelo: ");
        sb.append(this.modelo);
        sb.append("\nTipo Pneu: ");
        sb.append(this.tipoPneu);
        sb.append("\nModo Motor: ");
        sb.append(this.modoMotor);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        Carro c = (Carro) o;
        return (this.ID == (c.getID()) &&
                this.pac == (c.getPac()) &&
                this.potencia == (c.getPotencia()) &&
                this.marca.equals(c.getMarca()) &&
                this.modelo.equals(c.getModelo()) &&
                this.modelo.equals(c.getTipoPneu()) &&
                this.modelo.equals(c.getModoMotor()));
    }

    public int compareTo(Carro c) {
        return this.marca.compareTo(c.getMarca());
    }
}

