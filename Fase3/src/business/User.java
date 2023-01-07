package business;
public class User {

    // Variáveis de Instância
    private String username;
    private Boolean premium;
    private String password;


    // Construtores
    public User(){
        this.username = null;
        this.premium = false;
        this.password = null;
    }

    public User(String user,Boolean premium, String pass){
        this.username = user;
        this.premium = premium;
        this.password = pass;
    }

    public User (User u){
        this.username = u.getUsername();
        this.premium = u.isPremium();
        this.password = u.getPassword();

    }



    // Getters e Setters
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String user){
        this.username = user;
    }

    public Boolean isPremium(){
        return this.premium;
    }

    public void setPremium(Boolean p){
        this.premium = p;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPAssword(String p){
        this.password = p;
    }


    // Métodos
    public User clone() throws CloneNotSupportedException {
        User clone = (User) super.clone();
        return new User(this);
    }

    public boolean equals(Object o){
        if(this == o)
            return true;

        if((o == null) || (this.getClass() != o.getClass()))
            return false;


        User u = (User) o;
        return  (this.username.equals(u.getUsername()) &&
                (this.password.equals(u.getPassword())) &&
                (this.premium == u.isPremium()));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nUsername: "); sb.append(this.username);
        sb.append("\tPassword: ");sb.append(this.password);
        sb.append("\tÉ jogador premium: ");sb.append(this.premium.toString());
        return sb.toString();
    }

    public boolean validaCredenciais(String username, String password, boolean tipo){
        return (this.username.equals(username) && this.password.equals(password) && this.premium.equals(tipo));
    }


}