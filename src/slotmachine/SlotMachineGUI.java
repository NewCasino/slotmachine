package calango;
/*
 * @author alisson;
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlotMachineGUI {
    
    private JButton btnCash, btnSpin;
    private JCheckBox cbModoCheater, cbEikeBatista, cbVSFMode;
    private JFrame frmFrame;
    private JLabel lblCredits, lblLost, lblMatchThree, lblMatchTwo, lblMoney, box1, box2, box3, lblStatus, lblWon;
    private JPanel pnlReels, pBox1, pBox2, pBox3;
    private JProgressBar pbModoCheat;
    private JSeparator sepCheats, separator1, separator2, separator3;
    private JToggleButton btSound;
    private int creditos = 100, creditosComprados = 100, aposta = 15, matchThree, matchTwo, win, lost;
    private double pagamento = 25.0, creditoFora = 10.0, funds;
    private int rodada1 = 7, rodada2 = 7, rodada3 = 7; 
    private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
    private DecimalFormat df = new DecimalFormat("0.00");
    
    public SlotMachineGUI(int creditos, int creditosComprados, int aposta, double pagamento, double creditoFora, int rodada1, int rodada2, int rodada3) {
        this.creditos=creditos;
        this.creditosComprados=creditosComprados;
        this.aposta=aposta;
        this.pagamento=pagamento;
        this.creditoFora=creditoFora;
        this.rodada1=rodada1;
        this.rodada2=rodada2;
        this.rodada3=rodada3;
        createForm();
        loadImages();
        addFields();
        addButtons();
        layoutFrame();
        layoutReels();
        layoutOther();
    }
    
    public SlotMachineGUI() {
        createForm();
        loadImages();
        addFields();
        addButtons();
        layoutFrame();
        layoutReels();
        layoutOther();
    }
    
    /** Criacao de JFrames e JPanels */
    private void createForm() {
        
        frmFrame = new JFrame();
        frmFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frmFrame.setTitle("Voce quer casar com Ana?");
        frmFrame.setResizable(false);
        frmFrame.setVisible(true);
        
        pnlReels = new JPanel();
        pnlReels.setBorder(BorderFactory.createEtchedBorder());
        
        pBox1 = new JPanel();
        pBox1.setBackground(new Color(255, 215, 0));
        pBox1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        pBox2 = new JPanel();
        pBox2.setBackground(new Color(255, 216, 0));
        pBox2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        pBox3 = new JPanel();
        pBox3.setBackground(new java.awt.Color(255, 215, 0));
        pBox3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        
    }
    
    /** Criacao de componentes. */
    private void addFields() {
        
        box1 = new JLabel();
        box2 = new JLabel();
        box3 = new JLabel();
        
        separator1 = new JSeparator();
        lblMatchTwo = new JLabel();
        lblMatchTwo.setText("Duas combinacoes: ");
        lblMatchThree = new JLabel();
        lblMatchThree.setText("Tres combinacoes: ");
        lblWon = new JLabel();
        lblWon.setText("Ganhou: ");
        
        separator2 = new JSeparator();
        separator2.setOrientation(SwingConstants.VERTICAL);
        lblCredits = new JLabel();
        lblCredits.setText("Creditos: "+creditos);
        lblMoney = new JLabel();
        lblMoney.setText("Dinheiro: R$"+df.format(funds));
        lblLost = new JLabel();
        lblLost.setText("Perdeu: ");
        
        separator3 = new JSeparator();
        lblStatus = new JLabel();
        lblStatus.setBackground(new Color(255, 255, 255));
        lblStatus.setFont(new Font("Segoe UI", 1, 14));
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setText("Bem-vindo! 2014");
        
        sepCheats = new JSeparator();
        pbModoCheat = new JProgressBar();
        pbModoCheat.setToolTipText("Encha a barra para desbloquear o menu de cheats.");
        
        box1.setIcon(images.get(rodada1));
        box2.setIcon(images.get(rodada2));
        box3.setIcon(images.get(rodada3));
        
    }
    
    /** Criacao de botoes. */
    private void addButtons() {
        
        btnSpin = new JButton();
        btnSpin.setBackground(new Color(255, 255, 255));
        btnSpin.setText("Apostar!");
        btnSpin.setToolTipText("Clique para rodar as apostas.");
        btnSpin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        btnSpin.setInheritsPopupMenu(true);
        btnSpin.setMaximumSize(new Dimension(200, 50));
        btnSpin.setMinimumSize(new Dimension(200, 50));
        btnSpin.addActionListener(new SpinHandler());
        
        btnCash = new JButton();
        btnCash.setBackground(new Color(255, 0, 0));
        btnCash.setText("Comprar Creditos");
        btnCash.setToolTipText("R$"+df.format(aposta)+" convertido para "+creditosComprados+" creditos.");
        btnCash.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCash.addActionListener(new CompraCreditos());
        
       btSound = new JToggleButton();
       btSound.setSelected(false);
       btSound.setText("Sound ON");
       btSound.setToolTipText("O som nao esta implementado.");
       btSound.addActionListener(new SoundHandler());
        
        cbModoCheater = new JCheckBox();
        cbModoCheater.setText("Modo Cheater");
        cbModoCheater.setEnabled(false);
        cbModoCheater.setToolTipText("Ativa o Modo Cheater");
        cbModoCheater.addActionListener(new AlwaysWinHandler());
        
        cbVSFMode = new JCheckBox();
        cbVSFMode.setText("Nao clique.");
        cbVSFMode.setEnabled(false);
        cbVSFMode.setToolTipText("Voce nao vai querer ativar isso.");
        cbVSFMode.addActionListener(new TrollfaceHandler());
        
        cbEikeBatista = new JCheckBox();
        cbEikeBatista.setText("Modo Eike Batista");
        cbEikeBatista.setEnabled(false);
        cbEikeBatista.setToolTipText("Ativa o modo Eike Batista");
        cbEikeBatista.addActionListener(new SuperPrizeHandler());
        
    }
    
    /** Layouts */
    private void layoutFrame() {
        
        GroupLayout frameLayout = new GroupLayout(frmFrame.getContentPane());
        frmFrame.getContentPane().setLayout(frameLayout);
        frameLayout.setHorizontalGroup(frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
        frameLayout.setVerticalGroup(frameLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
    }
    
    private void layoutReels() {
        
        GroupLayout pnlReelsLayout = new GroupLayout(pnlReels);
        pnlReels.setLayout(pnlReelsLayout);
        pnlReelsLayout.setHorizontalGroup(pnlReelsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(pnlReelsLayout.createSequentialGroup().addContainerGap()
        .addComponent(pBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
        .addComponent(pBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
        .addComponent(pBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlReelsLayout.setVerticalGroup(pnlReelsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(pnlReelsLayout.createSequentialGroup().addContainerGap()
        .addGroup(pnlReelsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
        .addComponent(pBox2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(pBox1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(pBox3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        
        GroupLayout pnlReel1Layout = new GroupLayout(pBox1);
        pBox1.setLayout(pnlReel1Layout);
        pnlReel1Layout.setHorizontalGroup(pnlReel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pnlReel1Layout.createSequentialGroup().addContainerGap()
        .addComponent(box1).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlReel1Layout.setVerticalGroup(pnlReel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pnlReel1Layout.createSequentialGroup().addContainerGap()
        .addComponent(box1).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        
        GroupLayout pnlReel2Layout = new GroupLayout(pBox2);
        pBox2.setLayout(pnlReel2Layout);
        pnlReel2Layout.setHorizontalGroup(pnlReel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pnlReel2Layout.createSequentialGroup().addContainerGap()
        .addComponent(box2).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlReel2Layout.setVerticalGroup(pnlReel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pnlReel2Layout.createSequentialGroup().addContainerGap()
        .addComponent(box2).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        
        GroupLayout pnlReel3Layout = new GroupLayout(pBox3);
        pBox3.setLayout(pnlReel3Layout);
        pnlReel3Layout.setHorizontalGroup(pnlReel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pnlReel3Layout.createSequentialGroup().addContainerGap()
        .addComponent(box3).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlReel3Layout.setVerticalGroup(pnlReel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(pnlReel3Layout.createSequentialGroup().addContainerGap()
        .addComponent(box3).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }
    
    private void layoutOther() {
        
        GroupLayout layout = new GroupLayout(frmFrame.getContentPane());
        frmFrame.getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
        .addComponent(sepCheats)
        .addComponent(pbModoCheat, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
        .addGap(0, 0, Short.MAX_VALUE))
        .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
        .addGroup(layout.createSequentialGroup()
        .addComponent(cbModoCheater)
        .addGap(18, 18, 18)
        .addComponent(cbVSFMode)
        .addGap(18, 18, 18)
        .addComponent(cbEikeBatista)
        .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btSound))
        .addComponent(btnSpin, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(pnlReels, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(separator1, GroupLayout.Alignment.TRAILING)
        .addComponent(lblStatus, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
        .addComponent(lblMatchTwo, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(lblWon, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(lblMatchThree, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
        .addComponent(lblLost, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(lblCredits, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(lblMoney, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
        .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
        .addComponent(btnCash)
        .addComponent(separator3, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())))
        );
        
        layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(pnlReels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(btnSpin, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addComponent(lblWon, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(lblMatchTwo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(lblMatchThree, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        .addComponent(separator2)
        .addGroup(layout.createSequentialGroup()
        .addComponent(lblLost, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(lblCredits, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(lblMoney, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(btnCash, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(separator3, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addComponent(sepCheats, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.RELATED)
        .addComponent(pbModoCheat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(cbModoCheater)
        .addComponent(cbVSFMode)
        .addComponent(cbEikeBatista)
        .addComponent(btSound))
        .addContainerGap())
        );
        
        frmFrame.pack();
        
    }
    
    /**Botao de creditos*/
    class CompraCreditos implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            buyCredits();
        }
    }
    
    /** Compra de creditos */
    public void buyCredits() {
        if (funds >= creditoFora) {
            funds -= creditoFora;
            lblMoney.setText("Dinheiro: R$"+df.format(funds));
            creditos += creditosComprados;
            lblCredits.setText("Creditos: "+creditos);
            lblStatus.setText("+"+creditosComprados+" Creditos comprados -R$"+df.format(creditoFora));
            } else {
            lblStatus.setText("Saldo insuficiente para a compra de creditos.");
        }
        buyCreditsCheck();
    }
    
    /** Se o usuario nao possui fundos suficientes para comprar creditos, a cor dos botoes devem ser alteradas para alerta-lo */
    public void buyCreditsCheck() {
        if (funds < aposta) {
            btnCash.setBackground(new java.awt.Color(255, 0, 0));
            } else {
            btnCash.setBackground(new java.awt.Color(50, 255, 50));
        }
    }
    
    /** Acao a ser executada quando o botao 'Apostar' e' clicado. */
    class SpinHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (funds < creditoFora && creditos < aposta) {
                lblStatus.setText("Sem credito, nem dinheiro. Nao e' seu dia de sorte.");
                } else if ((creditos - aposta) >= 0) {
                pBox1.setBackground(new java.awt.Color(255, 215, 0));
                pBox2.setBackground(new java.awt.Color(255, 215, 0));
                pBox3.setBackground(new java.awt.Color(255, 215, 0));
                genReelNumbers();
                matchCheck();
                } else {
                lblStatus.setText("A aposta custa "+aposta+" creditos, compre mais com seu dinheiro!");
            }
            buyCreditsCheck();
        }
    }
    
    /** Gera os 3 numeros da rodada */
    public void genReelNumbers() {
        Random rand = new Random();
        if (cbModoCheater.isSelected() == true) { 
            int winType = rand.nextInt(4); 
            rodada1 = rand.nextInt(images.size());
            if (winType == 0) { 
                rodada2 = rodada1;
                rodada3 = rodada1;
                } else if (winType == 1) { 
                rodada2 = rodada1;
                } else if (winType == 2) { 
                rodada3 = rodada1;
                } else {    
                if (rodada1 >= 0 ) {
                    rodada2 = rodada1 + 1;
                    rodada3 = rodada1 + 1;
                    } if (rodada1 == images.size()-1) {
                    rodada2 = rodada1 - 1;
                    rodada3 = rodada1 - 1;
                }
            }
            } else { 
            rodada1 = rand.nextInt(images.size());
            rodada2 = rand.nextInt(images.size());
            rodada3 = rand.nextInt(images.size());
        }
        setReelIcon(rodada1, rodada2, rodada3);
    }
    
    /** 'Seta' os icones das rodadas baseados nas imagens carregadas do ArrayList*/
    public void setReelIcon(int ico1, int ico2, int ico3) {
        box1.setIcon(images.get(ico1)); 
        box2.setIcon(images.get(ico2));
        box3.setIcon(images.get(ico3));
    }
    
    /** Checagem de resultados */
    public void matchCheck() {
        if (rodada1 == rodada2 && rodada2 == rodada3) {
            lblStatus.setText("Voce combinou tres simbolos ("+images.get(rodada1).getDescription()+")! +R$"+df.format(getPrize(pagamento))+"!");
            lblMatchThree.setText("Combinou tres: "+matchThree());
            pBox1.setBackground(new java.awt.Color(255, 0, 0)); // Destacar os simbolos combinados
            pBox2.setBackground(new java.awt.Color(255, 0, 0));
            pBox3.setBackground(new java.awt.Color(255, 0, 0));
            } else if (rodada1 == rodada2 || rodada1 == rodada3) {
            lblStatus.setText("Voce combinou dois simbolos ("+images.get(rodada1).getDescription()+")! +R$"+df.format(getPrize(pagamento))+"!");
            lblMatchTwo.setText("Combinou dois: "+matchTwo());
            if (rodada1 == rodada2) {
                pBox1.setBackground(new java.awt.Color(255, 0, 0)); // Destacar os simbolos combinados
                pBox2.setBackground(new java.awt.Color(255, 0, 0));
                } else if (rodada1 == rodada3){
                pBox1.setBackground(new java.awt.Color(255, 0, 0)); // Destacar os simbolos combinados
                pBox3.setBackground(new java.awt.Color(255, 0, 0));
            }
            } else if (rodada2 == rodada3) {
            lblStatus.setText("Voce combinou 2 simbolos ("+images.get(rodada2).getDescription()+")! +R$"+df.format(getPrize(pagamento))+"!");
            lblMatchTwo.setText("Combinou dois: "+matchTwo());
            pBox2.setBackground(new java.awt.Color(255, 0, 0)); // Destacar os simbolos combinados
            pBox3.setBackground(new java.awt.Color(255, 0, 0));
            } else {
            lblStatus.setText("Voce nao combinou nenhum.");
            lblLost.setText("Perdeu: "+lose());
        }
        lblCredits.setText("Creditos: "+(creditos -= aposta)); 
        lblMoney.setText("Dinheiro: R$"+df.format((funds += getPrize(pagamento)))); 
        lblWon.setText("Vitorias: "+win()); 
    }
    
    /** Quando a barra de progresso chegar a o fim, desbloqueia o "Modo Cheater" */
    public void prgBarCheck() {
        if (pbModoCheat.getValue() <= 99) {
            pbModoCheat.setValue(win);
            } else if (pbModoCheat.getValue() == 100) { 
            pbModoCheat.setValue(100);
            lblStatus.setText("Parabens! Voce chegou a 100 vitorias.");
            JOptionPane.showMessageDialog(null, "O menu de cheat foi desbloqueado.");
            cbVSFMode.setEnabled(true);
            cbEikeBatista.setEnabled(true);
            cbModoCheater.setEnabled(true);
        }
    }
    
    /** Calcula os premios */
    public double getPrize(double prize) {
        if (rodada1 == rodada2 && rodada2 == rodada3) {
            if (cbEikeBatista.isSelected() == true) {
                prize *= 100; 
                } else {
                prize = pagamento; 
            }
            } else if (rodada1 == rodada2 || rodada1 == rodada3 || rodada2 == rodada3) {
            if (cbEikeBatista.isSelected() == true) {
                prize *= 50; 
                } else {
                prize = pagamento / 5; 
            }
            } else {
            prize = 0; 
        }
        return prize;
    }
    
    /** Evento do botao "Eike Batista". */
    class SuperPrizeHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (cbEikeBatista.isSelected() == true) {
                lblStatus.setText("Modo Eike Batista ativado.");
            }
            if (cbEikeBatista.isSelected() == false) {
                lblStatus.setText("Modo Eike Batista desativado.");
            }
        }
    }
    
    /** Evento do botao "Modo Cheater". */
    class AlwaysWinHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (cbModoCheater.isSelected() == true) {
                lblStatus.setText("Modo cheater iniciado. (Seu fraco)!");
            }
            if (cbModoCheater.isSelected() == false) {
                lblStatus.setText("Modo cheater desativado.");
            }
        }
    }
    
    /** Evento do botao extra. */
    class TrollfaceHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (cbVSFMode.isSelected() == true && images.get(images.size()-1) != createImageIcon("rafael.jpg", "Rafael")) {
                images.add(createImageIcon("rafael.jpg", "Rafael")); 
                lblStatus.setText("VSF Rafael - Ativado");
            }
            if (cbVSFMode.isSelected() == false && images.get(images.size()-1) != createImageIcon("rafael.jpg", "Rafael")) {
                images.remove(images.size()-1); 
                lblStatus.setText("VSF Rafael - Desativado");
            }
        }
    }
    
    /** Acao de quando o botao "Som ON" for clicado.
    * Nao Implementado
    */
    class SoundHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (btSound.isSelected() == false) {
                btSound.setText("Som ON");
                lblStatus.setText("Sons ligados!");
                // permissao para reproduzir sons
                } else {
                btSound.setText("Som OFF");
                lblStatus.setText("Sons desligados!");
                //desabilita sons
            }
        }
    }
    
    
    public void loadImages() {
        images.add(createImageIcon("0.png", "Starbucks"));
        images.add(createImageIcon("1.png", "Giraffas"));
        images.add(createImageIcon("2.png", "BurgerKing"));
        images.add(createImageIcon("3.png", "Habib's"));
        images.add(createImageIcon("4.png", "McDonalds"));
        images.add(createImageIcon("5.png", "Bob's"));
        images.add(createImageIcon("6.png", "MiniKalzone"));
        images.add(createImageIcon("7.png", "Calango"));
        images.add(createImageIcon("8.png", "Subway"));
    }
    
    /** Cria um novo ImageIcon, a menos que a URL seja encontrada. */
    public ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
            } else {
            System.err.println("Nao foi possivel encontrar o arquivo: " + path);
            return null;
        }
    }
    
    public int matchThree() {
        matchThree++;
        return matchThree;
    }
    
    public int matchTwo() {
        matchTwo++;
        return matchTwo;
    }
    
    public int lose() {
        lost++;
        return lost;
    }
    
    /** Barra de progresso */
    public int win() {
        win = matchThree + matchTwo;
        prgBarCheck();
        return win;
    }
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Alisson".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new SlotMachineGUI();
            }
        });
        
    }
    
}
