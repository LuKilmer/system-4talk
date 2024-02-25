package appswing;import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFxTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Minha Aplicação JavaFX");

        Button btn = new Button();
        btn.setText("Clique-me");

        btn.setOnAction(e -> System.out.println("Olá, JavaFX!"));

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));

        primaryStage.show();
    }
}
