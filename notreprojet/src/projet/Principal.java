package projet;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Principal {

    private Map<Integer, Stations> listeStation;

    public Map<Integer, Stations> getListeStation(){
        return listeStation;
    }

    public Principal() {
   
      
        listeStation = new HashMap<>();
        FileReader reader = null;
        try {
            reader = new FileReader(new File("station.csv"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader buffer = new BufferedReader(reader);

        try {

            String str = buffer.readLine();
            str = buffer.readLine();

            while (str != null) {

                String tab[] = str.split(";");
                int id = Integer.parseInt(tab[0]);
                Stations s = new Stations(id, tab[1]);
                listeStation.put(id, s);
               
                str = buffer.readLine();
            }

            buffer.close();

        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getnomstation(String station) {

        for (Stations sat : listeStation.values()) {
            if (station.equals(sat.getNomsation())) {
                return sat.getIdstation();
            }
        }
        return -1;

    }

    public boolean getDonnees(String idStation, String jour, String annee, String mois) throws FileNotFoundException, IOException {

       File fichier = new File("C:\\Users\\PC MC\\Desktop\\Projet\\synop." + annee + mois + ".csv");
        
        System.out.println("Lecture du fichier.");
        FileReader read = new FileReader(fichier);
        try (BufferedReader tompon = new BufferedReader(read)) {
            String bufread = tompon.readLine();
            bufread = tompon.readLine();
            while (bufread != null) {
                
                String tab[] = bufread.split(";");
                int idStation1 = Integer.parseInt(tab[0]);
                if (Integer.parseInt(idStation) == idStation1) {
                    
                    int an = Integer.parseInt(tab[1].substring(0, 4));
                    int mo = Integer.parseInt(tab[1].substring(4, 6));
                    int jo = Integer.parseInt(tab[1].substring(6, 8));
                    int releve = Integer.parseInt(tab[1].substring(8, 10)) / 3;
                    
                    float temeperature = (!tab[7].equals("mq")) ? Float.parseFloat(tab[7]) : 0;
                    float humidite = (!tab[9].equals("mq")) ? Float.parseFloat(tab[9]) : 0;
                    float nebulosite = (!tab[14].equals("mq")) ? Float.parseFloat(tab[14]) : 0;
                    
                    Stations getS = listeStation.get(idStation1);
                    Annees newAnnee = getS.getNewAnnee(an);
                    Mois newMois = newAnnee.getnewMois(mo);
                    Jours newJour = newMois.getNewJour(jo);
                   
                    donneechiff newReleve = newJour.newReleve(releve, temeperature, humidite, nebulosite);
                    
                }
                bufread = tompon.readLine();
                
            }
        }

        return true;
    }
    
    @Override
    public String toString() {
        return "Modele";
    }
}
