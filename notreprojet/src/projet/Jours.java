package projet;

//import static java.lang.Math.sqrt;
import static java.lang.Math.sqrt;
import java.util.HashMap;

import java.util.Map;

public class Jours {

   
    private int jour;
    private Map<Integer, donneechiff> list;
    private float moyenneT = 0, moyenneH = 0, moyenneN = 0, moyenneTk=0;
    
     private float diffT=0, diffH=0, diffN=0;
    private float ecartTypeT=0, ecartTypeH=0, ecartTypeN=0;

    public Jours(int jour) {
        this.jour = jour;
        this.list = new HashMap<>();
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public Map<Integer, donneechiff> getList() {
        return list;
    }

    public void setList(Map<Integer, donneechiff> list) {
        this.list = list;
    }

    public donneechiff getReleve(int ordre) {
        return list.get(ordre);
    }

    public donneechiff newReleve(int releve, float temp, float nebo, float hum) {
        if (!list.containsKey(releve)) {
            list.put(releve, new donneechiff(temp, nebo, hum));
        }
        return list.get(releve);
    }

    public void CalculMoyennejour() {
        moyenneT = 0;
        moyenneH = 0;
        moyenneN = 0;
        moyenneTk=(float) 273.15;
        for (donneechiff r : list.values()) {
            moyenneT += r.getTemperature();
            moyenneH += r.getHumidite();
            moyenneN += r.getNobulosite();
            moyenneTk+=r.getTemperature();

        }

        moyenneT = moyenneT / list.size();
        moyenneH = moyenneH / list.size();
        moyenneN = moyenneN / list.size();
        moyenneTk= moyenneTk/list.size();

    }

    public float getMoyenneTk() {
        return moyenneTk;
    }

    public void setMoyenneTk(float moyenneTk) {
        this.moyenneTk = moyenneTk;
    }

    public float getMoyenneT() {
        return moyenneT;
    }

    public void setMoyenneT(float moyenneT) {
        this.moyenneT = moyenneT;
    }

    public float getMoyenneH() {
        return moyenneH;
    }

    public void setMoyenneH(float moyenneH) {
        this.moyenneH = moyenneH;
    }

    public float getMoyenneN() {
        return moyenneN;
    }

    public void setMoyenneN(float moyenneN) {
        this.moyenneN = moyenneN;
    }
   public void calculecartjour() {
    
        
         for (donneechiff t : list.values()){ 
         
          
            
          diffT += Math.pow(t.getTemperature() - getMoyenneT() , 2);
          ecartTypeT = diffT;
          
          diffH += Math.pow(t.getHumidite()- getMoyenneH() , 2);
          ecartTypeH = diffH;
          diffN += Math.pow(t.getNobulosite()- getMoyenneN() , 2);
          ecartTypeN = diffN;
          
          
        }
        ecartTypeT = (float) sqrt(ecartTypeT / list.size());
        ecartTypeH = (float) sqrt(ecartTypeH / list.size());
        ecartTypeN = (float) sqrt(ecartTypeN / list.size());

    }

    public void setEcartTypeT(float ecartTypeT) {
        this.ecartTypeT = ecartTypeT;
    }

    public void setEcartTypeH(float ecartTypeH) {
        this.ecartTypeH = ecartTypeH;
    }

    public void setEcartTypeN(float ecartTypeN) {
        this.ecartTypeN = ecartTypeN;
    }

    public float getEcartTypeT() {
        return ecartTypeT;
    }

    public float getEcartTypeH() {
        return ecartTypeH;
    }

    public float getEcartTypeN() {
        return ecartTypeN;
    }
    

}
