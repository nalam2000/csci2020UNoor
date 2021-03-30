package midterm2021;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;

public class Main extends Application {
    private Canvas canvas;
    private final int frameWidth = 50;
    private final int frameHeight = 50;
    private final int numFrames = 6;
    private int sourceHeightOffset = 200;
    private final int sourceWidthOffset = 200;
    private int frameIndex = 0;




    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("CSCI2020U - Midterm");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);


//      Creating the menu buttons
        Button btApp1 = new Button("Animation");
        btApp1.setPrefWidth(200);
        Button btApp2 = new Button("2D Graphics");
        btApp2.setPrefWidth(200);
        Button btApp3 = new Button("About");
        btApp3.setPrefWidth(200);
        Button btApp4 = new Button("Back");
        btApp4.setPrefWidth(200);
        Button btApp5 = new Button("Back");
        btApp5.setPrefWidth(200);
        Button btApp6 = new Button("Back");
        btApp6.setPrefWidth(200);

//        setting the Event handlers for each buttons
        btApp1.setOnAction(new EventHandler<ActionEvent>() {
            public void drawAnimation(Group root){
                GraphicsContext gc = canvas.getGraphicsContext2D();
                Image image = new Image(getClass().getClassLoader().getResource("midterm2021/ducks.png").toString());

                Timeline timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent actionEvent) {
//                background rect fpr the characters

                        gc.fillRect(400, 50, frameWidth, frameHeight);

                        gc.drawImage(image, sourceWidthOffset, sourceHeightOffset, frameWidth, frameHeight, 400, 50, frameWidth, frameHeight);

//                we want to vary frameIndex from 0 to numFrames (not included) using the %
                        frameIndex = (frameIndex+1) % numFrames;

//                calculating the offset height based on the frameIndex
                        sourceHeightOffset = frameHeight*frameIndex;

                    }
                }));
                timeline.playFromStart();
            }

            @Override
            public void handle(ActionEvent actionEvent) {
//                TODO: Replace the scene or the root
//                      Display the "Animation" in the CENTER,
//                      and a "Back to Main" on the TOP
                 System.out.println("Clicked on Animation button");
                Group root = new Group();
                Scene scene = new Scene(root, 800, 600);

                //        Create Canvas object and add it into the scene
                canvas = new Canvas();
                canvas.widthProperty().bind(primaryStage.widthProperty());
                canvas.heightProperty().bind(primaryStage.heightProperty());
                root.getChildren().add(canvas);

                primaryStage.setTitle("Graphics - Hello World");
                primaryStage.setScene(scene);
                drawAnimation(root);

            }
        });

        btApp2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                TODO: Replace the scene or the root
//                    Display the "2D Drawing" in the CENTER,
//                    and a "Back to Main" on the TOP
                System.out.println("Clicked on Graphics 2D button");
                Group root = new Group();
                Scene scene = new Scene(root,800, 600);
                canvas = new Canvas();
                canvas.widthProperty().bind(primaryStage.widthProperty());
                canvas.heightProperty().bind(primaryStage.heightProperty());
                root.getChildren().add(canvas);
                System.out.println("Clicked on 2D Drawing");
                primaryStage.setScene(scene);
                draw(root);

            }
        });

        btApp3.setOnAction(new EventHandler<ActionEvent>() {
            public void read_file() {
                try{

                    File file = new File("midterm2021/info.xml");
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
                    Document document = docBuilder.parse(file);
                    document.getDocumentElement().normalize();

                    NodeList itemNodes = document.getElementsByTagName("Student");

                    for (int i = 0; i < itemNodes.getLength(); i++) {

                        Element itemElement = (Element)itemNodes.item(i);

                        // reading the properties of each of station objs
                        String student = getTagValue("Student:", itemElement);
                        String name = getTagValue("name", itemElement);
                        String email = getTagValue("email", itemElement);
                        String sD = getTagValue("software-description",itemElement);

                        Label output1 = new Label(student);
                        Label output2 = new Label(name);
                        Label output3 = new Label(email);
                        Label output4 = new Label(sD);
                        BorderPane.setAlignment(output1,Pos.TOP_CENTER);
                        BorderPane.setAlignment(output2,Pos.CENTER);
                        BorderPane.setAlignment(output3,Pos.CENTER_LEFT);
                        BorderPane.setAlignment(output4,Pos.BOTTOM_CENTER);






                    }

                } catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void handle(ActionEvent actionEvent) {
//                TODO: Replace the scene or the root
//                    Display the "About" in the CENTER,
//                    and a "Back to Main" on the TOP
                System.out.println("Clicked on About button");


                BorderPane about = new BorderPane();
                Scene scene = new Scene(about);


                primaryStage.setScene(scene);
                read_file();
                primaryStage.show();


            }
        });



//        Add the menu buttons to the grid
        grid.add(btApp1, 0,1);
        grid.add(btApp2, 0,2);
        grid.add(btApp3, 0,3);

        // main App Scene
        Scene mainScene = new Scene(grid, 300, 275);

        primaryStage.setScene(mainScene);
        primaryStage.show();


        
    }




    public void draw(Group root) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //Drawing the K (Each section has multiple draws that slightly over to give the letter thickness)

        gc.strokeLine(176,150,176,300);
        gc.strokeLine(276,150,276,300);
        gc.strokeLine(176,150,276,300);
        gc.fillOval(300,295,10,10);

        gc.strokeLine(370,150,330,300);
        gc.strokeLine(370,150,410,300);
        gc.strokeLine(350,220,390,220);
    }


    public static String getTagValue(String tagName, Element elem){

        NodeList tags = elem.getElementsByTagName(tagName);
        if (tags.getLength()>0){
            return tags.item(0).getTextContent();
        }
        return null;

    }




        public static void main(String[] args) {
        launch(args);
    }
}

