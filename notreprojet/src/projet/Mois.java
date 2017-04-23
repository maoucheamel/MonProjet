package projet;

import projet.Jours;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mois {

    private int mois;
    private Map<Integer, Jours> listjour;
    private float moyenneMT, moyenneMH, moyenneMN = 0;

    public Mois(int mois) {
        this.mois = mois;
        this.listjour = new HashMap<Integer, Jours>();
    }

    public Map<Integer, Jours> getListjour() {
        return listjour;
    }

    public void setListjour(Map<Integer, Jours> listjour) {
        this.listjour = listjour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public Jours getJour(int jour) {
        return getNewJour(jour);
    }

    public Jours getNewJour(int jour) {
        if (!listjour.containsKey(jour)) {
            listjour.put(jour, new Jours(jour));
        }
        return listjour.get(jour);
    }

   

   
}
