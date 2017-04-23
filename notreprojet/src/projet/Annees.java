package projet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Annees {
    
//******************************************Declaration des variables*****************************
    private int annee;
    
//une année represente une liste de mois 
    
    private Map<Integer, Mois> listmois;
   
    
//constructueur pour année et la hashmap pour la liste des mois 
    
    Annees(int annee) {
        this.annee = annee;
        listmois = new HashMap<>();
    }
// geters & setters
    public Map<Integer, Mois> getListmois() {
        return listmois;
    }

    public void setListmois(Map<Integer, Mois> listmois) {
        this.listmois = listmois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Mois getnewMois(int mois) {
        
         if (!listmois.containsKey(mois)) {
            listmois.put(mois, new Mois(mois));
        }
        
        return listmois.get(mois);
    }

}