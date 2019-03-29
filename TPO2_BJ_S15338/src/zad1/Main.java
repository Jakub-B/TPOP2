/**
 *
 *  @author Burakowski Jakub S15338
 *
 */

package zad1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sun.launcher.resources.launcher;

public class Main  {
  public static void main(String[] args) {
    Service s = new Service("Germany");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    String[] a = {s.getTemperatur(),s.wzgledemPLN,s.walutaKurs,s.getKraj()};
    Application.launch(Gui.class, a);
  }

}
