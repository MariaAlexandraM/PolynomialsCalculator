package gui;

import data.models.Polynomial;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static business.logic.Operations.*;


public class MainFrame {

    protected JFrame frame;
    protected JPanel containerPanel, polynomialsPanel, operationsPanel, resultPanel;
    protected JTextArea filler, p1Input, p2Input, resultOutput;
    protected JLabel p1Label, p2Label, resultLabel;
    protected JButton addBtn, subBtn, mulBtn, divBtn, derivBtn, integrBtn;


    public void contPanel() {
        containerPanel = new JPanel();
        containerPanel.setLayout(new GridLayout(3, 1));

        polynomialsPanel = new JPanel();
        polynomialsPanel.setLayout(new GridLayout(4, 4));
        polynomialsPanel.setBackground(new Color(247, 218, 229));
        containerPanel.add(polynomialsPanel);

        operationsPanel = new JPanel();
        operationsPanel.setLayout(new GridLayout(3, 2));
        operationsPanel.setBackground(new Color(195, 226, 230));
        containerPanel.add(operationsPanel);

        resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        resultPanel.setBackground(new Color(208, 204, 224));
        containerPanel.add(resultPanel);

        containerPanel.setVisible(true);
        frame.getContentPane().add(containerPanel);
    }

    public void polPanel() {

        filler = new JTextArea(2, 40);
        filler.setEditable(false);
        // filler.setBackground(new Color(195, 226, 230));
        filler.setBackground(new Color(247, 218, 229));
        //  polynomialsPanel.add(filler);

        p1Label = new JLabel("P(x)");
        p1Label.setFont(new Font("Times New Roman", Font.BOLD, 14));
        polynomialsPanel.add(p1Label);

        p1Input = new JTextArea(2, 20);
        p1Input.setBackground(new Color(255, 248, 231));
        p1Input.setPreferredSize(new Dimension(2, 20));
        p1Input.setTabSize(0);
        //p1Input.setWrapStyleWord(false);
        polynomialsPanel.add(p1Input);

        p2Label = new JLabel("Q(x)");
        p2Label.setFont(new Font("Times New Roman", Font.BOLD, 14));
        polynomialsPanel.add(p2Label);

        p2Input = new JTextArea(2, 20);
        p2Input.setBackground(new Color(255, 248, 231));
        p2Input.setPreferredSize(new Dimension(2, 20));
        p2Input.setTabSize(0);
        polynomialsPanel.add(p2Input);

        polynomialsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
    }

    public void btnPanel() {
        addBtn = new JButton("P(x)+Q(x)");
        addBtn.setBackground(new Color(255, 219, 224));
        addBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        addBtn.setPreferredSize(new Dimension(50, 50));

        subBtn = new JButton("P(x)-Q(x)");
        subBtn.setBackground(new Color(245, 209, 214));
        subBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        subBtn.setPreferredSize(new Dimension(50, 50));

        mulBtn = new JButton("P(x)*Q(x)");
        mulBtn.setBackground(new Color(235, 199, 204));
        mulBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        mulBtn.setPreferredSize(new Dimension(50, 50));

        divBtn = new JButton("P(x)/Q(x)");
        divBtn.setBackground(new Color(225, 189, 194));
        divBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        divBtn.setPreferredSize(new Dimension(50, 50));

        derivBtn = new JButton("P'(x)");
        derivBtn.setBackground(new Color(215, 179, 184));
        derivBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        derivBtn.setPreferredSize(new Dimension(50, 50));

        integrBtn = new JButton("∫P(x)");
        integrBtn.setBackground(new Color(205, 169, 174));
        integrBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        integrBtn.setPreferredSize(new Dimension(50, 50));

        operationsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        operationsPanel.add(addBtn);
        operationsPanel.add(subBtn);
        operationsPanel.add(mulBtn);
        operationsPanel.add(divBtn);
        operationsPanel.add(derivBtn);
        operationsPanel.add(integrBtn);
    }

    public void btnListeners() {

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = p1Input.getText();
                String input2 = p2Input.getText();
                if (validatePolynomial(input1)) {
                    if (validatePolynomial(input2)) {
                        try {
                            Polynomial p1 = stringToPolynomial(input1);
                            Polynomial p2 = stringToPolynomial(input2);
                            Polynomial res = addPolynomials(p1, p2);
                            resultOutput.setText(res.toString());
                            System.out.println(res.toString());
                        } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null, "Ceva nu a mers bine!", "Oops", JOptionPane.ERROR_MESSAGE);
                        exc.printStackTrace();
                    }
                    } else {
                        JOptionPane.showMessageDialog(null, "Q(x) nu e un polinom!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "P(x) nu e un polinom!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        subBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = p1Input.getText();
                String input2 = p2Input.getText();
                if (validatePolynomial(input1)) {
                    if (validatePolynomial(input2)) {
                        try {
                        Polynomial p1 = stringToPolynomial(input1);
                        Polynomial p2 = stringToPolynomial(input2);
                        Polynomial res = subtractPolynomials(p1, p2);
                        resultOutput.setText(res.toString());
                        System.out.println(res.toString());
                        } catch (Exception exc) {
                            JOptionPane.showMessageDialog(null, "Ceva nu a mers bine!", "Oops", JOptionPane.ERROR_MESSAGE);
                            exc.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Q(x) nu e un polinom!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "P(x) nu e un polinom!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mulBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = p1Input.getText();
                String input2 = p2Input.getText();
                if (validatePolynomial(input1)) {
                    if (validatePolynomial(input2)) {
                        try {
                            Polynomial p1 = stringToPolynomial(input1);
                            Polynomial p2 = stringToPolynomial(input2);
                            Polynomial res = multiplyPolynomials(p1, p2);
                            resultOutput.setText(res.toString());
                            System.out.println(res.toString());
                        } catch (Exception exc) {
                            JOptionPane.showMessageDialog(null, "Ceva nu a mers bine!", "Oops", JOptionPane.ERROR_MESSAGE);
                            exc.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Q(x) nu e un polinom!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "P(x) nu e un polinom!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        divBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = p1Input.getText();
                String input2 = p2Input.getText();
                if (validatePolynomial(input1)) {
                    if (validatePolynomial(input2)) {
                        try {
                            Polynomial p1 = stringToPolynomial(input1);
                            Polynomial p2 = stringToPolynomial(input2);
                            String res = dividePolynomials(p1, p2);
                            resultOutput.setText(res);
                            System.out.println(res);
                        } catch (Exception exc) {
                            JOptionPane.showMessageDialog(null, "Ceva nu a mers bine!", "Oops", JOptionPane.ERROR_MESSAGE);
                            exc.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Q(x) nu e un polinom!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "P(x) nu e un polinom!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        derivBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = p1Input.getText();
                if (validatePolynomial(input1)) {
                    try {
                        Polynomial p1 = stringToPolynomial(input1);
                        Polynomial res = derivePolynomial(p1);
                        resultOutput.setText(res.toString());
                        System.out.println(res.toString());
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null, "Ceva nu a mers bine!", "Oops", JOptionPane.ERROR_MESSAGE);
                        exc.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "P(x) nu e un polinom!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        integrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = p1Input.getText();
                if (validatePolynomial(input1)) {
                    try {
                    Polynomial p1 = stringToPolynomial(input1);
                    Polynomial res = integratePolynomial(p1);
                    resultOutput.setText(res.toString());
                    System.out.println(res.toString());
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null, "Ceva nu a mers bine!", "Oops", JOptionPane.ERROR_MESSAGE);
                        exc.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "P(x) nu e un polinom!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public void resPanel() {
        Border emptyBorder = BorderFactory.createEmptyBorder(20, 50, 20, 50);
        resultPanel.setBorder(emptyBorder);

        resultLabel = new JLabel("Result");
        resultLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        resultPanel.add(resultLabel);

        resultOutput = new JTextArea(4, 20);
        resultOutput.setBackground(new Color(232, 255, 245));
        resultOutput.setEditable(false);
        resultPanel.add(resultOutput);

        btnListeners();
    }

    public void init() {

        frame = new JFrame("Calculator de polinoame");
        frame.setSize(344, 648);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        contPanel();
        polPanel();
        btnPanel();
        resPanel();
    }

    public MainFrame() {
        init();
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                try {
                    MainFrame window = new MainFrame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });
    }
}
