package dataStructures;

public class Client extends User{
    private String Adresa;
    private String telefon;
    public Client(String username, String password){
        super(username,password);
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    public void setAdresa(String adresa) {
        this.Adresa = adresa;
    }
    public String getAdresa() {
        return Adresa;
    }
    public String getTelefon() {
        return telefon;
    }
    @Override
    public String toString() {
        return "Client { " + username + " } ";
    }
    public boolean equals(Object o){
        if(o instanceof Client){
            Client tmp = (Client)o;
            return tmp.username.equals(this.username);
        }
        else
            return false;
    }
}
