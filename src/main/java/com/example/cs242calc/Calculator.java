package com.example.cs242calc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Calculator extends Application
{
    private ArrayList<Button> numberButtons;

    private ArrayList<Button> opButtons;

    private String num1, num2;

    private String textOutput;

    public void start(Stage primaryStage) throws Exception
    {
        numberButtons = new ArrayList<Button>();
        opButtons = new ArrayList<Button>();
        textOutput = num1 = num2 = "";
        GridPane grid = new GridPane();

        // Result text box
        TextField resultTextField = new TextField();
        resultTextField.setEditable(false);

        int i = 1;
        for (int r = 0; r < 5; r++)
        {
            for (int c = 0; c < 2; c++)
            {
                Button btn = new Button(String.valueOf(i));
                int finalI = i;
                btn.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent arg)
                    {
                        textOutput += String.valueOf(finalI);
                        resultTextField.setText(textOutput);

                        if (checkOperand())
                        {
                            num2 += String.valueOf(finalI);
                        } else
                        {
                            num1 += String.valueOf(finalI);
                        }
                    }
                });
                numberButtons.add(btn);
                grid.add(btn, c, r);

                if (i == 9) i = 0;
                else i++;
            }
        }

        // Visuals
        StackPane root = new StackPane();
        root.getChildren().add(resultTextField);
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 600, 700);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // true = operand 2, false = operand 1
    public boolean checkOperand()
    {
        return (textOutput.contains("+") || textOutput.contains("-") ||
                textOutput.contains("*") || textOutput.contains("/"));
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
