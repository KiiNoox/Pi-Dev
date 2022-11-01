/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.PayementService;
import entities.payement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PayementsHistoryController implements Initializable {

    @FXML
    private TableView<payement> table;
    @FXML
    private TableColumn<payement, String> userCell;
    @FXML
    private TableColumn<payement, String> dateCell;
    @FXML
    private TableColumn<payement, String> objetCell;
    @FXML
    private TableColumn<payement, Float> montantCell;
    @FXML
    private ImageView back;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        payement p = new payement();
        PayementService pc = new PayementService();
      
        
        
        //userCell.setCellValueFactory(new PropertyValueFactory<>("taille_sportif"));
        dateCell.setCellValueFactory(new PropertyValueFactory<>("date_pay"));
        //objetCell.setCellValueFactory(new PropertyValueFactory<>("age_sportif"));
        montantCell.setCellValueFactory(new PropertyValueFactory<>("montant"));
   
        
        table.setItems(  pc.afficherPso());
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<payement> filteredData = new FilteredList<>(pc.afficherPso(), b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(payement -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				
				 if (String.valueOf(payement.getMontant()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<payement> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }    

    @FXML
    private void clickRetour(MouseEvent event) throws IOException {
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void search(ActionEvent event) {
    }
    
}
