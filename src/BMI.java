import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sebastian on 30.04.2017.
 */
public class BMI extends JFrame{
    private JButton okButton;
    private JRadioButton kobietaButton;
    private JRadioButton facetButton;
    private JPanel okPrzycisk;
    private JTextField wzrostField;
    private JTextField wagaField;
    private JComboBox aktywnoscBox1;
    private JTextField wiekField;
    private String plec;

    public BMI() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wzrost,waga;
                int wspolczynnik;
                wzrost = wzrostField.getText();
                //System.out.println(wzrost);
                waga = wagaField.getText();
                if(facetButton.isSelected())
                {
                    plec="mężczyzną";
                    wspolczynnik = 5;
                }
                else
                    {
                        plec="kobietą";
                        wspolczynnik = -161;
                    }
                //System.out.println("Wybrałeś aktywność: "+ aktywnoscBox1.getSelectedIndex());
                double BMI = obliczBMI(przelicznikWagi(wagaField.getText()),przelicznikWzrost(wzrostField.getText()));
              //  System.out.println(BMI);

                double BMR = (9.99*przelicznikWagi(wagaField.getText())+(6.25*przelicznikWzrost(wzrostField.getText()))-(4.92*przelicznikWiek(wiekField.getText()))+wspolczynnik);
              //  System.out.println(BMR);

                //BMWFacet = (9.99 * wagaField)+(6.25*wzrostField)-(4.92*wiek) + 5
                //BMWKobieta = (9.99 * wagaField)+(6.25*wzrostField)-(4.92*wiek) -161

               // System.out.println(aktywnoscWskaznik(aktywnoscBox1.getSelectedIndex(),BMR));
                BMR=BMR+aktywnoscWskaznik(aktywnoscBox1.getSelectedIndex(),BMR);




                JOptionPane.showMessageDialog(null, "Twoja waga to "+ waga +"kg,wzrost to "+ wzrost+"cm, jesteś "
                                                                            + plec+"\nTwój wiek to"+wiekField.getText()+", poziom Twojej aktywności to:"
                                                                            + aktywnoscBox1.getSelectedItem()+"\nTwoje BMI wynosi "+ BMI
                                                                            + "\nTwoje dziennie zapotrzebowanie kaloryczne to "+BMR+"kcal");

            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
      //          System.out.println("  ");
            }
        };
        kobietaButton.addActionListener(listener);
        facetButton.addActionListener(listener);
        aktywnoscBox1.addItem("Prawie brak");
        aktywnoscBox1.addItem("Lekka aktywność");
        aktywnoscBox1.addItem("Umiarkowana aktywność");
        aktywnoscBox1.addItem("Duża aktywność");
        aktywnoscBox1.addItem("Bardzo duża aktywność");
        setContentPane(okPrzycisk);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kalkulator BMI oraz BMR");

        setLocationRelativeTo(null);
        setResizable(false);
       setSize(400,300);
        pack();
        setVisible(true);
    }

    public int przelicznikWagi(String waga){
        int wagaInt = Integer.valueOf(waga);
        //System.out.println(wagaInt);
        return wagaInt;
    }
    public int przelicznikWzrost(String wzrost){
        int wzrostInt = Integer.valueOf(wzrost);
       // System.out.println(wzrostInt);
        return wzrostInt;
    }
    public int przelicznikWiek(String wiek){
        int wiekInt = Integer.valueOf(wiek);
       // System.out.println(wiekInt);
        return wiekInt;
    }
    public double obliczBMI(int waga,int wzrost){
        double bmi = (double)waga/(double)(wzrost*wzrost)*10000;
       // System.out.println(bmi);
        return bmi;
    }

    public double aktywnoscWskaznik(int aktywnosc, double bmr){
        double epoc=0;
        double tea = 0;
        switch (aktywnosc){
            case 0:
                epoc = 1*(bmr/100*7);
                break;
            case 1:
                epoc = 2*(bmr/100*7);
                break;
            case 2:
                epoc = 3*(bmr/100*7);
                break;
            case 3:
                epoc = 4*(bmr/100*7);
                break;
            case 4:
                epoc = 5*(bmr/100*7);
                break;
        }
        tea = (9*(60*4)+epoc)/7;
        return tea;
    }




    public static void main(String[] args) {
        new BMI();
    }
}
