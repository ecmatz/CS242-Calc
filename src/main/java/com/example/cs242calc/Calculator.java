package com.example.cs242calc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator extends Application
{
    private String num1, num2;

    private String textOutput;

    private static final String[] operators = { "+","-","*","/", "=" };

    private boolean finishedCalc = false;

    public void start(Stage primaryStage) throws Exception
    {
        textOutput = num1 = num2 = "";
        GridPane grid = new GridPane();

        // Result text box
        TextField resultTextField = new TextField();
        resultTextField.setEditable(false);
        resultTextField.setText("0");
        resultTextField.setAlignment(Pos.TOP_LEFT);
        resultTextField.setPrefSize(100,50);
        resultTextField.setFont(Font.font("Arial",35));

        int i = 1;
        for (int r = 0; r < 5; r++)
        {
            for (int c = 0; c < 2; c++)
            {
                Button btn = new Button(String.valueOf(i));
                btn.setPrefSize(50,50);
                int finalI = i;
                btn.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent arg)
                    {
                        if (finishedCalc)
                        {
                            textOutput = "";
                            num1 = num2 = "";
                            resultTextField.setText(textOutput);
                            finishedCalc = false;
                        }

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
                grid.add(btn, c, r);

                if (i == 9) i = 0;
                else i++;
            }
        }

        for (int r = 0; r < operators.length; r++)
        {
            String op = operators[r];
            Button opbtn = new Button(op);
            opbtn.setPrefSize(50,50);
            if (op.equals("="))
            {
                opbtn.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent arg)
                    {
                        int x = Integer.parseInt(num1);
                        int y = Integer.parseInt(num2);
                        String resultOp = textOutput.replace(num1, "").replace(num2, "");
                        switch (resultOp)
                        {
                            case "+":
                                textOutput += "=" + String.valueOf(x + y);
                                break;
                            case "-":
                                textOutput += "=" + String.valueOf(x - y);
                                break;
                            case "*":
                                textOutput += "=" + String.valueOf(x * y);
                                break;
                            case "/":
                                textOutput += "=" + String.valueOf(x / y);
                                break;
                            default:
                                finishedCalc = true;
                                resultTextField.setText("Invalid operators provided to calculator.");
                                throw new IllegalArgumentException("Invalid operators provided to calculator.");
                        }
                        resultTextField.setText(textOutput);
                        finishedCalc = true;
                    }
                });
            }
            else
            {
                opbtn.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent arg)
                    {
                        textOutput += op;
                        resultTextField.setText(textOutput);
                    }
                });
            }

            grid.add(opbtn, 2, r);
            grid.setHgap(10);
            grid.setVgap(10);
        }

        // Visuals
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        root.getChildren().add(resultTextField);
        resultTextField.setAlignment(Pos.TOP_LEFT);
        grid.setAlignment(Pos.BOTTOM_CENTER);
        Scene scene = new Scene(root, 300, 670);
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
