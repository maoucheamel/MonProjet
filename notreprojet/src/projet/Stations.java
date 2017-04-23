package projet;

import projet.Annees;
import java.util.HashMap;
import java.util.Map;


public class Stations {

    private int idStation;
    private String nomStation;
    private Map<Integer, Annees> listAnnee;

    public Stations(int idstation, String nomsation) {
        this.idStation = idstation;
        this.nomStation = nomsation;
        this.listAnnee = new HashMap<>();
    }
    
    public int getIdstation() {
        return idStation;
    }
    
      public void setIdstation(int idStation) {
        this.idStation = idStation;
          }

    public String getNomsation() {
        return nomStation;
    }
    
    
    public void setNomsation(String nomStation) {
        this.nomStation = nomStation;
    }

    public Map<Integer, Annees> getListannee() {
        return listAnnee;
    }

    public void setListannee(Map<Integer, Annees> listannee) {
        this.listAnnee = listannee;
    }

    public Annees getAnnees(int annee) {

        return this.listAnnee.get(annee);

    }

    public Annees getNewAnnee(int annee) {
        if (!listAnnee.containsKey(annee)) {
            listAnnee.put(annee, new Annees(annee));
        }
        return listAnnee.get(annee);
    }

}
