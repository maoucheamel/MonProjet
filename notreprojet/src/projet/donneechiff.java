package projet;



public class donneechiff {
    
    private float temperature;
    private float humidite;
    private float nobulosite;

    public donneechiff( float temperature, float humidite, float nobulosite) {
    
        this.temperature = temperature;
        this.humidite = humidite;
        this.nobulosite = nobulosite;
       
    }


    public float getTemperature() {
        return temperature;
    }

    public void setTemperture(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidite() {
        return humidite;
    }

    public void setHumidite(float humidite) {
        this.humidite = humidite;
    }

    public float getNobulosite() {
        return nobulosite;
    }

    public void setNobulosite(float nobulosite) {
        this.nobulosite = nobulosite;
    }

}
