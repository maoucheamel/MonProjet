
package projet;


public class Donnees {

 
    private String temeprature;
    private String nebulosite;
    private String humidite;
    private String date;

    
 //*******************************************************Constructeur****************************************************
    public Donnees(String date, String temperature, String nubiliosite, String humidite) {
        this.date = date;
        this.temeprature = temperature;
        this.nebulosite = nubiliosite;
        this.humidite = humidite;
        
    }

    public String getTemeprature() {
        return temeprature;
    }

    public void setTemeprature(String temeprature) {
        this.temeprature = temeprature;
    }

    public String getNebulosite() {
        return nebulosite;
    }

    public void setNebulosite(String nebulosite) {
        this.nebulosite = nebulosite;
    }

    public String getHumidite() {
        return humidite;
    }

    public void setHumidite(String humidite) {
        this.humidite = humidite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
