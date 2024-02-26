package appswing;import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class JavaFxTest extends Application {
	ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "./data/mydatabase.db4o");
	public static class Person {
	    private String name;
	    private int age;

	    public Person(String name, int age) {
	        this.name = name;
	        this.age = age;
	    }
	    
	    public String getName() {
	    	return this.name;
	    }
	    
	    public int getAge() {
	    	return this.age;
	    }

	    // getters and setters (pode ser gerado automaticamente em algumas IDEs)
	}
    
	@Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Consultar Banco de Dados");
        btn.setOnAction(e -> consultarBancoDeDados());

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("JavaFX com db4o");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void consultarBancoDeDados() {
        // Executa operações de banco de dados em uma Thread separada
        new Thread(() -> {
            // Operações de banco de dados aqui
            System.out.println("Consultando o banco de dados...");
            

            
                // Adiciona alguns objetos ao banco de dados
            db.store(new Person("Alice", 25));
            db.store(new Person("Bob", 30));

                // Consulta para obter todas as pessoas no banco de dados
            ObjectSet<Person> result = db.queryByExample(Person.class);

                // Exibe os resultados
                System.out.println("Pessoas no banco de dados:");
                while (result.hasNext()) {
                    Person person = result.next();
                    System.out.println("Nome: " + person.getName() + ", Idade: " + person.getAge());
                }
            
                // Fecha o banco de dados quando não for mais necessário
            db.close();
            
            System.out.println("Consulta concluída.");
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
