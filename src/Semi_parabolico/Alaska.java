package Semi_parabolico;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/*
 * Alaska.java
 *
 * Created on 27 de septiembre de 2009, 10:24 AM
 * @author  River
 */
public class Alaska extends javax.swing.JFrame {

    private ImageIcon imagen;
    private ImageIcon movil;
    int x=-10;
int y=0;   
Rescate r;
    int w;
     int h;
    int cAlto;
     int cAncho;
     int orgX;
     int orgY;
     double escalaY;
     double escalaX;
     PanelAvion pa=new PanelAvion(null,true);
     Avion a=new Avion();
    float AltAvion;
    float VelAvion;
    double dt;
     double t;
     double xAvion;
    int hFigura;
     int wFigura;
     double tDisparo;
     double xMarca;
     double xBomba;
     double yBomba;
     double tiempo;
     double xFinal;
    /** Creates new form Alaska */
    public Alaska() {
        initComponents();
        pa.setVisible(true);
        this.CargaAvion();
        this.CargarImagenes();
        t = 0.0D;
        dt=0.02D;
        tDisparo =-10D;
        r=new Rescate(this);
       r.start();
       
    }

    public void Mover(){
        t += dt;
        xAvion = VelAvion * t;
       xAvion = Math.floor(xAvion * (double)10) / (double)10;
       xMarca=xAvion;
        
        xBomba = xMarca + VelAvion * (t-0.5);
        yBomba = AltAvion - (10D * (t - tDisparo) * (t-0.5)) / (double)2;
       
       repaint();
    
    }
    
    public void CargarImagenes() {

imagen = new ImageIcon("src/Semi_parabolico/avion.gif");
movil = new ImageIcon("src/Semi_parabolico/movil.gif");
}
    @Override
    public void paint(Graphics g) {
super.paint(g);
dibujaEjes(g);        
Dibujar(g);
}

 
 public void Dibujar(Graphics g){
        int x1 = orgX + (int)(xAvion * escalaX);
        int y1 = orgY - (int)(AltAvion * escalaY);
        wFigura = imagen.getImage().getWidth(this);
        hFigura = imagen.getImage().getHeight(this);
     if(imagen!=null) {     
    g.drawImage(imagen.getImage(),x1 - wFigura / 2, y1 - hFigura,this);
     }
       x1 = orgX-4 +(int)(xBomba * escalaX);
            y1 = orgY-4 -(int)(yBomba * escalaY);
            wFigura = movil.getImage().getWidth(this);
            hFigura = movil.getImage().getHeight(this);
            g.drawImage(movil.getImage(), x1 , y1, this); 
 
                        if(x1 + wFigura > w)
            {
                fracaso();
                return;
            }
            if(y1 > orgY)
            {
                fracaso();
                return;
            }
 
 }
    
    void origenEscalas(Graphics g)
    {
        w = jPanel1.getSize().width;
        h = jPanel1.getSize().height;
        cAlto = g.getFontMetrics().getHeight();
        cAncho = g.getFontMetrics().stringWidth("-100");
        orgX = 5 + cAncho;
        orgY = h - 3 * cAlto;
        escalaY = (double)h / (double)500;
        escalaX = (double)(w - orgX) / (double)700;
    }

    void dibujaEjes(Graphics g)
    {
        origenEscalas(g);
        g.setColor(Color.gray);
        g.fillRect(0, orgY, w, h - orgY);
        g.setColor(Color.black);
        g.drawLine(orgX, h, orgX, 0);
        g.drawString("Y(m)", orgX + 4, cAlto);
        for(int i = 0; i <= 300; i += 100)
        {
            int x2 = orgX - g.getFontMetrics().stringWidth(String.valueOf(i)) - 3;
            int y2 = (orgY - (int)((double)i * escalaY)) + cAlto / 2;
            g.drawString(String.valueOf(i), x2, y2);
            y2 = orgY - (int)((double)i * escalaY);
            g.drawLine(orgX - 2, y2, orgX + 2, y2);
            y2 = orgY - (int)((double)(i + 50) * escalaY);
            g.drawLine(orgX - 1, y2, orgX + 1, y2);
        }

        g.drawLine(orgX, orgY, w, orgY);
        g.drawString("X(m)", w - g.getFontMetrics().stringWidth("X(m)"), orgY - 2);
        for(int i = 100; i <= 600; i += 100)
        {
            int x2 = orgX + (int)((double)i * escalaX);
            g.drawLine(x2, orgY - 4, x2, orgY + 4);
            x2 = (orgX + (int)((double)i * escalaX)) - g.getFontMetrics().stringWidth(String.valueOf(i)) / 2;
            g.drawString(String.valueOf(i), x2, orgY + cAlto);
            x2 = orgX + (int)((double)(i - 50) * escalaX);
            g.drawLine(x2, orgY - 2, x2, orgY + 2);
        }

    }

    public void CargaAvion() {
        a=pa.recibirAvion();
        AltAvion=a.getAlt();
        VelAvion=a.getVel();
    }

    public void fracaso() {
        r.putMsg(0);
        xAvion = 0.0D;
        t = 0.0D;   
        formula();       
    }
        
public void formula(){
        double auxAlt;
    auxAlt = -1 * AltAvion;
        double AuxG = -1 * (9.80 / 2);
        double auxt =  Math.sqrt(auxAlt / AuxG);
    tiempo=Math.rint(auxt*100)/100;
   
    double xaux =VelAvion*tiempo;
    xFinal=Math.rint(xaux*100)/100;
    ValVel.setText(Integer.toString((int) VelAvion));
    ValAlt.setText(Integer.toString((int) AltAvion));
    ValItem.setText(""+tiempo);
    ValDis.setText(""+xFinal);

}

    public void Nuevo() {
      r=null;
        pa.setVisible(true);
        this.CargaAvion();
        this.CargarImagenes(); 
    t = 0.0D;
   //repaint();
    r=new Rescate(this);
    r.start();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ValVel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ValAlt = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ValDis = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ValItem = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Excursionistas extraviados");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 351, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 12));
        jLabel1.setText("Avion");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel2.setText("Velocidad");

        ValVel.setText("ValVel");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel3.setText("Altura");

        ValAlt.setText("ValAlt");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 12));
        jLabel4.setText("Lanzamiento");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel5.setText("Distancia Final");

        ValDis.setText("ValDis");

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 12));
        jLabel7.setText("Tiempo Caida.");

        ValItem.setText("ValTiem");

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ValDis))
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ValVel)
                                    .addComponent(jLabel3)
                                    .addComponent(ValAlt)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(ValItem))
                            .addComponent(jLabel7)
                            .addComponent(jButton1))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValVel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValAlt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValDis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-558)/2, (screenSize.height-389)/2, 558, 389);
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
Nuevo();
}//GEN-LAST:event_jButton1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ValAlt;
    private javax.swing.JLabel ValDis;
    private javax.swing.JLabel ValItem;
    private javax.swing.JLabel ValVel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}
