package com.example.cs242calc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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

        for (int i = 1; i <= 9; i++)
        {
            Button btn = new Button(String.valueOf(i));
            int finalI = i;
            btn.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent arg)
                {
                    textOutput += String.valueOf(finalI);
                    if (checkOperand())
                    {
                        num2 += String.valueOf(finalI);
                    }
                    else
                    {
                        num1 += String.valueOf(finalI);
                    }
                }
            });
            numberButtons.add(btn);
        }
        // add 0 button separately because it's at the end / out of order
        Button zeroButton = new Button("0");
        zeroButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg)
            {
                textOutput += "0";
                if (checkOperand())
                {
                    num2 += "0";
                }
                else
                {
                    num1 += "0";
                }
            }
        });
        numberButtons.add(zeroButton);


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
