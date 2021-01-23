/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing2;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author MariuszJanowski
 */
public class Gra extends javax.swing.JFrame implements ActionListener{
    Karta karta1, karta2;
    int ileKart = 0, liczbaPar = 0, liczbaKroków=0;
    Karta[] karty = new Karta[36];
    boolean obrazki;
    Icon[] ikony;
    
    
    void tasujKarty(){
        Random r = new Random();
        for (int i = 0; i < 36; i ++){
            int k = r.nextInt(16);
            int pom = karty[k].numer;
            karty[k].numer = karty[i].numer;
            karty[i].numer = pom;
            }
        for (int i = 0; i < 36; i++) karty[i].zakryj();
    }
    /**
     * Creates new form Gra
     */
    public Gra() {
        this.ikony = new Icon[]{
            new ImageIcon(getClass().getResource(("k1.gif"))),
            new ImageIcon(getClass().getResource(("k2.gif"))),
            new ImageIcon(getClass().getResource(("k3.gif"))),
            new ImageIcon(getClass().getResource(("k4.gif"))),
            new ImageIcon(getClass().getResource(("k5.gif"))),
            new ImageIcon(getClass().getResource(("k6.gif"))),
            new ImageIcon(getClass().getResource(("k7.gif"))),
            new ImageIcon(getClass().getResource(("k8.gif"))),
            new ImageIcon(getClass().getResource(("k9.gif"))),
            new ImageIcon(getClass().getResource(("k10.gif"))),
            new ImageIcon(getClass().getResource(("k11.gif"))),
            new ImageIcon(getClass().getResource(("k12.gif"))),
            new ImageIcon(getClass().getResource(("k13.gif"))),
            new ImageIcon(getClass().getResource(("k14.gif"))),
            new ImageIcon(getClass().getResource(("k15.gif"))),
            new ImageIcon(getClass().getResource(("k16.gif"))),
            new ImageIcon(getClass().getResource(("k17.gif"))),
            new ImageIcon(getClass().getResource(("k18.gif")))
        };
        for (int i = 0; i < 36; i++){
            Karta karta = new Karta(i/2);
            karty[i]=karta;
            add(karta);
            karty[i].addActionListener(this);
        }
        tasujKarty();
        initComponents();
        setSize(1000,1000);
        setLayout(new GridLayout(6,6,10,10));
    }
    class Karta extends JButton {
        int numer;
        boolean zakryta;
        Color[] kolory = // tablica zawiera 8 stałych kolorów, które przypiszemy odpowiednim przyciskom
                        {Color.LIGHT_GRAY, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.WHITE, Color.PINK,
                         Color.LIGHT_GRAY, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.WHITE, Color.PINK,
                         Color.LIGHT_GRAY, Color.BLUE};
        Karta(int n){// konstruktor klasy Karta
            super();// wywołanie konstruktora klasy JButton
            numer = n;
            setFont(new Font("Serif", Font.BOLD, 45));
        }
        void zakryj(){
            zakryta = true;
            setBackground(Color.GRAY);// kolor zakrytej karty
            setText("");
            setIcon(null);
        }
        void odkryj(){
            zakryta = false;
            if (obrazki){
                setIcon(ikony[numer]);
            }
            else{
                setBackground(kolory[numer]); // kolor odkrytej karty
                setText("" +(numer+1));
            }
        }
    }
    public void actionPerformed(ActionEvent e){
        if (obrazki){
            URL sound = this.getClass().getResource("cat_meow_x.wav");
            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (IOException ex) {
                Logger.getLogger(Gra.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Gra.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Gra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Karta wybrana = (Karta)e.getSource();
        if (wybrana.zakryta){
            wybrana.odkryj();
            ileKart++;
            switch(ileKart){
                case 3: karta1.zakryj();karta2.zakryj();
                        ileKart = 1;
                case 1: karta1 = wybrana;
                        break;
                case 2: karta2 = wybrana;
                        liczbaKroków++;
                        if(karta1.numer==karta2.numer){
                            liczbaPar++;
                            JOptionPane.showMessageDialog(this,"Jest para!!!");
                            ileKart = 0;
                            }
                        }
                if (liczbaPar== 18){
                    URL gif = this.getClass().getResource("kermit.gif");
                    ImageIcon giff  = new ImageIcon(gif);
                    JOptionPane.showMessageDialog(this,"Skonczyłeś grę po "+liczbaKroków+" krokach","Gratulacje!",JOptionPane.INFORMATION_MESSAGE,giff);
                }
            }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gra w pary");
        setName("Gra w pary"); // NOI18N
        setResizable(false);

        jMenu1.setText("Nowa gra");

        jMenuItem1.setText("Kolorowe karty");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Kotki");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void start(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start
        // TODO add your handling code here:
        obrazki = evt.getSource().equals(jMenuItem2);
        ileKart = liczbaPar = liczbaKroków = 0;
        tasujKarty();
        //obrazki = evt.getSource().equals(jMenuItem2);
    }//GEN-LAST:event_start

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables
}
