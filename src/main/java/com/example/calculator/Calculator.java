package com.example.calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.lang.invoke.MethodHandles;
import java.util.InputMismatchException;
import java.util.Iterator;

public class Calculator extends Application {

    @Override
    public void start(Stage primaryStage) {

        Background monitorFill = new Background( new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY));
        Background buttonFill = new Background( new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY));

        Label monitor = new Label ("Inputs");
        monitor.setFont(new Font(50));
        monitor.setPrefSize(400, 100);


//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
        // HBox to denote the monitor.
        HBox r1 = new HBox();

        r1.getChildren().add(monitor);
        r1.setBackground(monitorFill);

        // numpad + operators = controller
        HBox controller = new HBox();


        // VBox to denote the interactive numpad.
        VBox numPad = new VBox();
        numPad.setBackground(buttonFill);

        // HBox to denote row one of the numpad.
        HBox br1 = new HBox();
        Button clear = new Button("C");

        br1.getChildren().addAll(clear);

        // HBox to denote row two of the numpad.

        HBox br2 = new HBox();
        Button one = new Button ("1");
        Button two = new Button ("2");
        Button three = new Button ("3");

        br2.getChildren().addAll(one, two, three);

        // HBox to denote row three of the numpad.
        HBox br3 = new HBox();
        Button four = new Button ("4");
        Button five = new Button ("5");
        Button six = new Button ("6");
        br3.getChildren().addAll(four, five, six);

        // HBox to denote row four of the numpad.
        HBox br4 = new HBox();
        Button seven = new Button("7");
        Button eight = new Button ("8");
        Button nine = new Button ("9");
        br4.getChildren().addAll(seven, eight, nine);

        // HBox to denote row five of the numpad.
        HBox br5 = new HBox();
        Button zero = new Button("0");
        Button dot = new Button (".");
        br5.getChildren().addAll(zero, dot);

        numPad.getChildren().addAll(br1, br2, br3, br4, br5);

        for(Node br: numPad.getChildren()){
            buttonSizer((Pane)br);
        }
        controller.getChildren().addAll(numPad);

        // VBox to house operators
        VBox operators = new VBox();

        Button plus = new Button("+");
        Button minus = new Button("-");
        Button divide = new Button("/");
        Button multiply = new Button("x");
        Button equals = new Button("=");

        operators.getChildren().addAll(plus, minus, divide, multiply, equals);

        buttonSizer(operators);

        controller.getChildren().addAll(operators);
        VBox app = new VBox();
        app.getChildren().addAll(r1, controller);

        StackPane root = new StackPane();
        root.getChildren().add(app);

        Scene scene = new Scene(root, 400, 700);

        primaryStage.setTitle("Calculator by Chris");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private static void buttonSizer(Pane parent){

        for (Node button : parent.getChildren()) {
            System.out.println(button.getClass());
            if (button instanceof Button) {
                ((Button) button).setPrefSize(100, 120);
                ((Button) button).setFont(new Font(18));
            } else {
                System.out.println("ohhhhh naurrrrr" + button.getClass());
                //break;
            }
        }
    };
}