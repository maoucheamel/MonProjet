package projet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class Telechargement_Fichier {
    
//****************************************Recuperer toutes les informations du site de météo france***********************************************************************************    
    

    public void telechargement(String jour, String mois, String annee) throws IOException {

        URL url = new URL("https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/Archive/synop." + annee  + mois  + ".csv.gz");
        // ouverture de la connexion
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        
        //mettre le fichier telecharger dans mon projet 
        File file = new File ( "C:\\Users\\PC MC\\Desktop\\Projet\\synop." + annee + mois + ".csv.gz" );
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buf = new byte[512];
        while (true) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }
            fos.write(buf, 0, len);
        }
        in.close();
        fos.flush();
        fos.close();


    }
    //************************************deziper les fichier telechargé***************************************************
    public void Ouvrir_fichier(String jour,String mois, String annee) {
        
        String gzipFile = "C:\\Users\\PC MC\\Desktop\\Projet\\synop." + annee  + mois  + ".csv.gz";
        String newFile = "C:\\Users\\PC MC\\Desktop\\Projet\\synop." + annee  + mois  + ".csv";
        
        System.out.println(gzipFile);
        
        try {
            FileInputStream fis = new FileInputStream(gzipFile);
            GZIPInputStream gis = new GZIPInputStream(fis);
            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            int len;
            while((len = gis.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
            fos.close();
            gis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
