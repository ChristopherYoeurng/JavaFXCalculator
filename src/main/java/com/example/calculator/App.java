package com.example.calculator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        MonitorValue monitorValue = new MonitorValue();

        Background monitorFill = new Background( new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY));
        Background buttonFill = new Background( new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY));

        Text monitor = new Text (" " + (monitorValue.getVal()));
        monitor.setFont(new Font(50));

        // HBox to denote the monitor.
        HBox r1 = new HBox();

        r1.getChildren().add(monitor);
        r1.setBackground(monitorFill);
        r1.setPrefSize(900, 150);
        r1.setMinSize(400, 120);
        monitor.wrappingWidthProperty().bind(r1.widthProperty());

        // numpad + operators = controller
        HBox controller = new HBox();

        // VBox to denote the interactive numpad.
        VBox numPad = new VBox();
        numPad.setBackground(buttonFill);

        // HBox to denote row one of the numpad.
        HBox br1 = new HBox();
        Button power = new Button ("OFF");
        Button backspace = new Button("<-");
        Button clear = new Button("C");

        br1.getChildren().addAll(power, backspace, clear);

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
        Button negative = new Button("");
        Button zero = new Button("0");
        Button dot = new Button (".");
        br5.getChildren().addAll(negative, zero, dot);

        numPad.getChildren().addAll(br1, br2, br3, br4, br5);

        for(Node br: numPad.getChildren()){
            buttonSizer((Pane)br, monitorValue, monitor);
        }
        controller.getChildren().addAll(numPad);

        // VBox to house operators
        VBox operators = new VBox();

        Button plus = new Button(" + ");
        Button minus = new Button(" - ");
        Button divide = new Button(" / ");
        Button multiply = new Button(" * ");
        Button equals = new Button("=");

        operators.getChildren().addAll(plus, minus, divide, multiply, equals);

        buttonSizer(operators, monitorValue, monitor);

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

    private static void buttonSizer(Pane parent, MonitorValue monitorValue, Text monitor){

        for (Node button : parent.getChildren()) {
            if (button instanceof Button) {
                ((Button) button).setPrefSize(9000, 9000);
                ((Button) button).setMinSize(90, 65);
                ((Button) button).setFont(new Font(25));

                ((Button) button).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String buttonText = ((Button)button).getText();

                        switch(buttonText){
                            case "OFF":
                                Platform.exit();
                                break;
                            case "<-":
                                monitorValue.popVal(monitor);
                                break;
                            case "C":
                                monitorValue.setVal("", monitor);
                                break;
                            case "=":
                                String result;
                                try{
                                    result = evaluate(monitorValue.getVal());
                                } catch(Exception e){
                                    monitorValue.setVal("ERROR: " + e, monitor);
                                    break;
                                }

                                monitorValue.setVal(result, monitor);
                                break;
                            default:
                                monitorValue.setVal(monitorValue.getVal() + ((Button)button).getText(), monitor);
                                break;
                        }

                    }
                });
            } else {
                System.out.println("ohhhhh naurrrrr" + button.getClass());
                break;
            }
        }
    }

    private static String evaluate(String expression) throws Exception{
        List<String> exp = new ArrayList<>(Arrays.asList(expression.split(" ")));
        double op1, op2;
        while(exp.contains("*") || exp.contains("/")){
            for(int i = 0; i < exp.size(); i++){
                switch(exp.get(i)){
                    case "*":
                        op2 = Double.parseDouble(exp.remove(i+1));
                        op1 = Double.parseDouble(exp.remove(i-1));
                        exp.set(i-1, Double.toString(op1*op2));
                        break;
                    case "/":
                        op2 = Double.parseDouble(exp.remove(i+1));
                        op1 = Double.parseDouble(exp.remove(i-1));
                        exp.set(i-1, Double.toString(op1/op2));
                        break;
                    default:
                        continue;
                }
                break;
            }
        }

        while(exp.contains("+") || exp.contains("-")){
            for(int i = 0; i < exp.size(); i++){
                switch(exp.get(i)){
                    case "+":
                        op2 = Double.parseDouble(exp.remove(i+1));
                        op1 = Double.parseDouble(exp.remove(i-1));
                        exp.set(i-1, Double.toString(op1+op2));
                        break;
                    case "-":
                        op2 = Double.parseDouble(exp.remove(i+1));
                        op1 = Double.parseDouble(exp.remove(i-1));
                        exp.set(i-1, Double.toString(op1-op2));
                        break;
                    default:
                        continue;
                }
                break;
            }
        }
        if (exp.size()>1){
            throw new Error("waaaaa");
        }
        return exp.get(0);
    }
}