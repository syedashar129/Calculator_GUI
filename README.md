# Calculator_GUI

Developed a calculator device that performs arithmetic operations on numbers. These operations include addition, subtraction, multiplication, and division. 

Implemented elements of Graphic User Interface (GUI). The Java AWT swing package was used. 

* GUI Component classes, such as Button, TextField, and Label.
* GUI Container classes, such as Frame and Panel.
* Layout managers, such as FlowLayout, BorderLayout and GridLayout.
```java
import javax.swing.*;
```
Used sendButton functions to create the calculator buttons and used actionListener to properly track movements. 

```java
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
