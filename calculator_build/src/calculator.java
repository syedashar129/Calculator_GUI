import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator extends JFrame {

    private JMenuBar menuBar;

    private JMenu file; //adding item
    private JMenu edit;
    private JMenu help;

    private JMenuItem close;
    private JMenuItem copy;
    private JMenuItem view;
    private JMenuItem about;

    private JTextArea display;

    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton zero;
    private JButton decimal;
    private JButton posneg;

    private JButton multiply;
    private JButton division;
    private JButton add;
    private JButton subtract;
    private JButton equal;
    private JButton clear;

    private double tempFirst = 0.0;
    private double tempSecond = 0.0;
    private boolean [] operation = new boolean[4];


    public static void main(String[] args) {
        try{
            //sets button theme to computer's theme
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            System.out.println("Could not load System.");
        }

        new calculator();  //creates calculator
    }

    public calculator (){
        super("Calculator");
        sendMenuBar();
        sendDisplay();
        sendButton();
        sendUI(this); //sends this to the function
    }

    private void sendMenuBar(){
        menuBar = new JMenuBar();

        file = new JMenu(" File ");
        edit = new JMenu(" Edit ");
        help = new JMenu(" Help ");

        close = new JMenuItem(" Close ");
        copy = new JMenuItem(" Copy ");
        view = new JMenuItem(" View help");
        about = new JMenuItem(" About Calculator");


        setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);

        close.addActionListener(new ActionListener(){ //to close using the "Close" button
            @Override
            public void actionPerformed (ActionEvent e){
                System.exit(0);
        }
        });

        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempDisplay = display.getText();
                StringSelection string = new StringSelection(tempDisplay);
                Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
                system.setContents(string, string);
            }
        });

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "there are no help topics", "Calculator", JOptionPane.CANCEL_OPTION);
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Copyright Syed Ashar  2021", "Calculator", JOptionPane.CANCEL_OPTION);
            }
        });

        file.add(close);
        edit.add(copy);
        help.add(view);
        help.add(about);
    }

    private void sendUI(calculator app){
        //creates the box
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(315, 440); //setting size
        app.setResizable(false); //not gonna be resizable
        app.setLayout(null);
        app.setLocationRelativeTo(null);//goes to center of screen when opens
        app.setVisible(true);//shows it
    }

    private void sendDisplay(){
        display = new JTextArea("0");
        display.setBounds(10,10,283,50);
        display.setEditable(false); //wont be edited
        display.setFont(new Font("Arial", Font.PLAIN, 32));
        add(display);
    }

    private void sendButton(){
        add = new JButton("+");
        add.setBounds(226,256,65,55);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTempFirst(Double.parseDouble(display.getText()));
                display.setText("0");
                operation[3] = true;
            }
        });
        add(add);

        subtract = new JButton("-");
        subtract.setBounds(226,194,65,55);
        subtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTempFirst(Double.parseDouble(display.getText()));
                display.setText("0");
                operation[2] = true;
            }
        });
        add(subtract);

        multiply = new JButton("x");
        multiply.setBounds(226,132,65,55);
        multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTempFirst(Double.parseDouble(display.getText()));
                display.setText("0");
                operation[1] = true;
            }
        });
        add(multiply);

        division = new JButton("/");
        division.setBounds(226,70,65,55);
        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTempFirst(Double.parseDouble(display.getText()));
                display.setText("0");
                operation[0] = true;
            }
        });
        add(division);

        equal = new JButton("=");
        equal.setBounds(10,318,137,55);
        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (operation[0]) {
                    display.setText(Double.toString(getTempFirst() / Double.parseDouble(display.getText()))) ;
                }
                else if (operation[1]){
                    display.setText(Double.toString(getTempFirst() * Double.parseDouble(display.getText())));
                }
                else if (operation[2]){
                    display.setText(Double.toString(getTempFirst() - Double.parseDouble(display.getText())));
                }
                else if (operation[3]){
                    display.setText(Double.toString(getTempFirst() + Double.parseDouble(display.getText())));
                }


                if (display.getText().endsWith(".0")){
                    display.setText(display.getText().replace(".0",""));
                }
                setTempFirst(0.0);
                for (int i = 0; i <= 3; i++){//four  operations
                    operation[i] = false;
                }
            }



        });
        add(equal);

        clear = new JButton ("C");
        clear.setBounds(154,318,137,55);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("0");
                setTempFirst(0.0);
                for (int i = 0; i <= 3; i++){//four  operations
                    operation[i] = false;
                }
            }
        });
        add(clear);


        zero = new JButton("0");
        zero.setBounds(10,256,65,55);
        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if its 0 return 0
                if (display.getText().equalsIgnoreCase("0") || display.getText().length() > 15){
                    return;
                }
                //otherwise append 0 (ex: 2->00)
                display.append("0");
            }
        });
        add(zero);

        decimal = new JButton(".");
        decimal.setBounds(82,256,65,55);
        decimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().contains("."))
                    return;
                display.append(".");
            }
        });
        add(decimal);

        posneg = new JButton("+/-");
        posneg.setBounds(154,256,65,55);
        posneg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().equalsIgnoreCase("0")){

                }
                display.setText(Double.toString(Double.parseDouble(display.getText()) * (-1)));
                if (display.getText().endsWith(".0"))
                    display.setText(display.getText().replace(".0",""));
            }

        });
        add(posneg);




        one = new JButton("1");
        one.setBounds(10,194, 65,55);
        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10){
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {//if display nothing but 0
                    display.setText("1");
                    return;
                }
                display.append("1");
            }
        });
        add(one);

        two = new JButton("2");
        two.setBounds(82,194, 65,55);
        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10){
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {//if display nothing but 0
                    display.setText("2");
                    return;
                }
                display.append("2");
            }
        });
        add(two);

        three = new JButton("3");
        three.setBounds(154,194, 65,55);
        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10){
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {//if display nothing but 0
                    display.setText("3");
                    return;
                }
                display.append("3");
            }
        });
        add(three);

        four = new JButton("4");
        four.setBounds(10,132, 65,55);
        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10){
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {//if display nothing but 0
                    display.setText("4");
                    return;
                }
                display.append("4");
            }
        });
        add(four);

        five = new JButton("5");
        five.setBounds(82,132, 65,55);
        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10){
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {//if display nothing but 0
                    display.setText("5");
                    return;
                }
                display.append("5");
            }
        });
        add(five);

        six = new JButton("6");
        six.setBounds(154,132, 65,55);
        six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10){
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {//if display nothing but 0
                    display.setText("6");
                    return;
                }
                display.append("6");
            }
        });
        add(six);

        seven = new JButton("7");
        seven.setBounds(10,70,65,55);
        seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10){
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {//if display nothing but 0
                    display.setText("7");
                    return;
                }
                display.append("7");
            }
        });
        add(seven);

        eight = new JButton("8");
        eight.setBounds(82, 70, 65,55);
        eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10) {
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {//if display nothing but 0
                    display.setText("8");
                    return;
                }
                display.append("8");
            }
        });
        add(eight);

        nine = new JButton("9");
        nine.setBounds(154, 70,65,55);
        nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().length() > 10){
                    return;
                }
                if (display.getText().equalsIgnoreCase("0")) {//if display nothing but 0
                    display.setText("9");
                    return;
                }
                display.append("9");
            }
        });
        add(nine);

    }


    public double getTempFirst() {
        return tempFirst;
    }

    public void setTempFirst(double tempFirst) {
        this.tempFirst = tempFirst;
    }

    public double getTempSecond() {
        return tempSecond;
    }

    public void setTempSecond(double tempSecond) {
        this.tempSecond = tempSecond;
    }
}
