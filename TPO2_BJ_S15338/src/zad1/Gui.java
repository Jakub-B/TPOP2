package zad1;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Gui extends Application {
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parameters params = getParameters();
		List<String> list = params.getRaw();
		for(String each : list){
		    System.out.println(each);
		}
		Label pogoda = new Label("Temperatura na teraz to "+list.get(0));
		

		Label waluta = new Label("Stosunek waluty kraju do pytanej to "+list.get(1));
		
		Label doPLN = new Label("Waluta kraju do PLN to "+list.get(2));

		
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load("https://pl.wikipedia.org/wiki/"+list.get(3));
		
		GridPane root = new GridPane();
		root.add(pogoda,0,0,1,1);
		root.add(waluta,0,1,1,1);
		root.add(doPLN,0,2,1,1);
		root.add(browser,0,3,1,1);
		Scene scene = new Scene(root, 300, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
