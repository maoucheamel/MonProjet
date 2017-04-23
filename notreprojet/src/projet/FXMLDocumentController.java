
package projet;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;


/**
 *
 * @author maouche
 */
public class FXMLDocumentController implements Initializable{
   

   
    @FXML
    private DatePicker dateur;
    @FXML
    private ComboBox ville;
    @FXML
    private TableView<Donnees> tableau;
    @FXML
    private TableColumn<Donnees, String> Date;
    @FXML
    private TableColumn<Donnees, String> Temeperature;
    @FXML
    private TableColumn<Donnees, String> Humidite;
    @FXML
    private TableColumn<Donnees, String> Nebulosite;
    @FXML
    private LineChart<String, Number> graphique;
    
  
    private List<String> liste = new ArrayList<>();

    private ObservableList<String> villelist;
    
    
   

    @FXML
    public void afficher(ActionEvent event) throws IOException {
        
        
        tableau.getItems().clear();
        graphique.getData().clear();
        Principal modele = new Principal();
        
//*************************************** affichage des courbes******************************************************
     // température
        XYChart.Series<String, Number> seriesTemp = new XYChart.Series<String, Number>();
        seriesTemp.setName("Tem");
        // nebulosité
        XYChart.Series<String, Number> seriesNeb = new XYChart.Series<String, Number>();
        seriesNeb.setName("Neb");
        // humidité
        XYChart.Series<String, Number> seriesHum = new XYChart.Series<String, Number>();
        seriesHum.setName("Hum");
        
       //***************************************************************************************************************
        
        String idStation = Integer.toString(modele.getnomstation((String) ville.getSelectionModel().getSelectedItem()));
          
        
        
        LocalDate date = dateur.getValue();
      
        
        String jour= ""+ date.getDayOfMonth();
        String mois = ((date.getMonthValue() < 12) ? "0" : "") + date.getMonthValue();
        String annee = "" + date.getYear();
         Telechargement_Fichier f = new Telechargement_Fichier();
        f.telechargement(jour ,mois, annee);
        f.Ouvrir_fichier(jour, mois, annee);
        
        
        modele.getDonnees(idStation,jour, annee, mois);
        Map<Integer, Stations> listeStation = modele.getListeStation();
        ObservableList<Donnees> listeAffiche = FXCollections.observableArrayList();
        
        
        if (listeStation.containsKey(Integer.parseInt(idStation))) {
           Map<Integer, Jours> listjour = listeStation.get(Integer.parseInt(idStation)).getListannee().get(Integer.parseInt(annee)).getListmois().get(Integer.parseInt(mois)).getListjour();
           
           
           
           for (Entry<Integer, Jours> aff: listjour.entrySet()) {
                aff.getValue().CalculMoyennejour();
               
 //*****************************************************Graphe*******************************************************************
               seriesTemp.getData().add(new XYChart.Data<String, Number>(String.valueOf(aff.getKey()),aff.getValue().getMoyenneT()));
               seriesHum.getData().add(new XYChart.Data<String, Number>(String.valueOf(aff.getKey()),aff.getValue().getMoyenneH()));
               seriesNeb.getData().add(new XYChart.Data<String, Number>(String.valueOf(aff.getKey()),aff.getValue().getMoyenneN()));
//******************************************************************************************************************************
               
               //affichage sur le tableau 
                listeAffiche.add(new Donnees(aff.getKey() + "/"+mois+"/"+annee, ""+aff.getValue().getMoyenneT(), 
                     ""+aff.getValue().getMoyenneH(), ""+aff.getValue().getMoyenneN()));
            }
        }
     
        
        // affichage sur le tableau 
        tableau.getItems().addAll(listeAffiche);
        
      // affichage sur le graphe
        graphique.getData().add(seriesTemp);
        graphique.getData().add(seriesHum);
        graphique.getData().add(seriesNeb);
      
    }
     @FXML
    public void aff(ActionEvent event) throws IOException {
        
        
        tableau.getItems().clear();
        graphique.getData().clear();
        Principal modele = new Principal();
        
//*************************************** affichage des courbes******************************************************
     // température
        XYChart.Series<String, Number> seriesTemp = new XYChart.Series<String, Number>();
        seriesTemp.setName("Tem");
        // nebulosité
        XYChart.Series<String, Number> seriesNeb = new XYChart.Series<String, Number>();
        seriesNeb.setName("Neb");
        // humidité
        XYChart.Series<String, Number> seriesHum = new XYChart.Series<String, Number>();
        seriesHum.setName("Hum");
        
       //***************************************************************************************************************
        
        String idStation = Integer.toString(modele.getnomstation((String) ville.getSelectionModel().getSelectedItem()));
          
        
        
        LocalDate date = dateur.getValue();
      
        
        String jour= ""+ date.getDayOfMonth();
        String mois = ((date.getMonthValue() < 12) ? "0" : "") + date.getMonthValue();
        String annee = "" + date.getYear();
         Telechargement_Fichier f = new Telechargement_Fichier();
        f.telechargement(jour ,mois, annee);
        f.Ouvrir_fichier(jour, mois, annee);
        
        
        modele.getDonnees(idStation,jour, annee, mois);
        Map<Integer, Stations> listeStation = modele.getListeStation();
        ObservableList<Donnees> listeAffiche = FXCollections.observableArrayList();
        
        
        if (listeStation.containsKey(Integer.parseInt(idStation))) {
           Map<Integer, Jours> listjour = listeStation.get(Integer.parseInt(idStation)).getListannee().get(Integer.parseInt(annee)).getListmois().get(Integer.parseInt(mois)).getListjour();
           
           
           
           for (Entry<Integer, Jours> aff: listjour.entrySet()) {
                aff.getValue().calculecartjour();
               
 //*****************************************************Graphe*******************************************************************
               seriesTemp.getData().add(new XYChart.Data<String, Number>(String.valueOf(aff.getKey()),aff.getValue().getEcartTypeT()));
               seriesHum.getData().add(new XYChart.Data<String, Number>(String.valueOf(aff.getKey()),aff.getValue().getEcartTypeH()));
               seriesNeb.getData().add(new XYChart.Data<String, Number>(String.valueOf(aff.getKey()),aff.getValue().getEcartTypeN()));
//******************************************************************************************************************************
               
               //affichage sur le tableau 
                listeAffiche.add(new Donnees(aff.getKey() + "/"+mois+"/"+annee, ""+aff.getValue().getEcartTypeT(), 
                     ""+aff.getValue().getEcartTypeH(), ""+aff.getValue().getEcartTypeN()));
            }
        }
     
        
        // affichage sur le tableau 
        tableau.getItems().addAll(listeAffiche);
        
      // affichage sur le graphe
        graphique.getData().add(seriesTemp);
        graphique.getData().add(seriesHum);
        graphique.getData().add(seriesNeb);
      
    }
    
    
    @FXML
    public void kelvin(ActionEvent event) throws IOException {
        
        
        tableau.getItems().clear();
        graphique.getData().clear();
        Principal modele = new Principal();
        
//*************************************** affichage des courbes******************************************************
     // température
        XYChart.Series<String, Number> seriesTemp = new XYChart.Series<String, Number>();
        seriesTemp.setName("Tem");
        // nebulosité
        XYChart.Series<String, Number> seriesNeb = new XYChart.Series<String, Number>();
        seriesNeb.setName("Neb");
        // humidité
        XYChart.Series<String, Number> seriesHum = new XYChart.Series<String, Number>();
        seriesHum.setName("Hum");
        
       //***************************************************************************************************************
        
        String idStation = Integer.toString(modele.getnomstation((String) ville.getSelectionModel().getSelectedItem()));
          
        
        
        LocalDate date = dateur.getValue();
      
        
        String jour= ""+ date.getDayOfMonth();
        String mois = ((date.getMonthValue() < 12) ? "0" : "") + date.getMonthValue();
        String annee = "" + date.getYear();
         Telechargement_Fichier f = new Telechargement_Fichier();
        f.telechargement(jour ,mois, annee);
        f.Ouvrir_fichier(jour, mois, annee);
        
        
        modele.getDonnees(idStation,jour, annee, mois);
        Map<Integer, Stations> listeStation = modele.getListeStation();
        ObservableList<Donnees> listeAffiche = FXCollections.observableArrayList();
        
        
        if (listeStation.containsKey(Integer.parseInt(idStation))) {
           Map<Integer, Jours> listjour = listeStation.get(Integer.parseInt(idStation)).getListannee().get(Integer.parseInt(annee)).getListmois().get(Integer.parseInt(mois)).getListjour();
           
           
           
           for (Entry<Integer, Jours> aff: listjour.entrySet()) {
                aff.getValue().CalculMoyennejour();
               
 //*****************************************************Graphe*******************************************************************
               seriesTemp.getData().add(new XYChart.Data<String, Number>(String.valueOf(aff.getKey()),aff.getValue().getMoyenneTk()));
               seriesHum.getData().add(new XYChart.Data<String, Number>(String.valueOf(aff.getKey()),aff.getValue().getMoyenneH()));
               seriesNeb.getData().add(new XYChart.Data<String, Number>(String.valueOf(aff.getKey()),aff.getValue().getMoyenneN()));
//******************************************************************************************************************************
               
               //affichage sur le tableau 
                listeAffiche.add(new Donnees(aff.getKey() + "/"+mois+"/"+annee, ""+aff.getValue().getMoyenneTk(), 
                     ""+aff.getValue().getMoyenneH(), ""+aff.getValue().getMoyenneN()));
            }
        }
     
        
        // affichage sur le tableau 
        tableau.getItems().addAll(listeAffiche);
        
      // affichage sur le graphe
        graphique.getData().add(seriesTemp);
        graphique.getData().add(seriesHum);
        graphique.getData().add(seriesNeb);
      
    }

    

    
    
//***********************************************Recuperer la liste des stations***************************************************
    @FXML
    public void Recuperer_station() {
     
        Principal modele = new Principal();
        for (Entry<Integer, Stations> sta : modele.getListeStation().entrySet()) {
            
            liste.add(sta.getValue().getNomsation()); 
        }
        villelist = FXCollections.observableArrayList(liste);
        ville.setItems(villelist);
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Recuperer_station();
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Temeperature.setCellValueFactory(new PropertyValueFactory<Donnees, String>("temeprature"));
        Humidite.setCellValueFactory(new PropertyValueFactory<Donnees, String>("humidite"));
        Nebulosite.setCellValueFactory(new PropertyValueFactory<Donnees, String>("nebulosite"));

    }

}
