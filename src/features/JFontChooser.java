

package features;

/**
 *
 * @author amit
 */


import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class JFontChooser extends javax.swing.JDialog {

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    private Font font;
    
    public JFontChooser(javax.swing.JTextArea textArea)
    {
     textArea.setFont(new Font("Dialog",Font.PLAIN,12));
     initComponents();
     
     labelPreview.setFont(font);
    }
    
    public JFontChooser(java.awt.Frame parent, Font font) {
        super(parent);
        this.font = font;
        initComponents();
        labelPreview.setFont(font);
    }
    
    public JFontChooser(java.awt.Frame parent) {
        super(parent);
        this.font = new Font("Dialog",Font.PLAIN,12);
        initComponents();
        labelPreview.setFont(font);
    }
    public JFontChooser(Font font) {
        super((javax.swing.JFrame)null);
        this.font = font;
        initComponents();
        labelPreview.setFont(font);
    }
    
    public JFontChooser() {
        super((javax.swing.JFrame)null);
        this.font = new Font("Dialog",Font.PLAIN,12);
        initComponents();
        labelPreview.setFont(font);
    }
    

    /** @return the font chosen by the user */
    public Font getFont(){
        return font;
    }
    
    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        fontPanel = new javax.swing.JPanel();
        fontScrollPane = new javax.swing.JScrollPane();
        fontList = new javax.swing.JList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        styleScrollPane = new javax.swing.JScrollPane();
        styleList = new javax.swing.JList();
        sizeScrollPanel = new javax.swing.JScrollPane();
        sizeList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        previewPanel = new javax.swing.JPanel();
        labelPreview = new javax.swing.JLabel();

        setTitle("Select Font");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        fontList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        fontList.setToolTipText("Select Font");
        fontList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                fontListValueChanged(evt);
            }
        });
        fontScrollPane.setViewportView(fontList);

        styleList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Plain", "Bold", "Italic", "Bold", "Italic" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        styleList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        styleList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                styleListValueChanged(evt);
            }
        });
        styleList.setToolTipText("Select Size");
        styleScrollPane.setViewportView(styleList);

        sizeList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "8", "10", "11", "12", "14", "16", "20", "24", "28", "36", "48", "72", "96" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        sizeList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sizeList.setToolTipText("Select Size");
        sizeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                sizeListValueChanged(evt);
            }
        });
        sizeScrollPanel.setViewportView(sizeList);

        jLabel1.setText("Font");

        jLabel2.setText("Style");

        jLabel3.setText("Size");

        javax.swing.GroupLayout fontPanelLayout = new javax.swing.GroupLayout(fontPanel);
        fontPanel.setLayout(fontPanelLayout);
        fontPanelLayout.setHorizontalGroup(
            fontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fontPanelLayout.createSequentialGroup()
                .addGroup(fontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fontScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(styleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fontPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addComponent(sizeScrollPanel)))
        );
        fontPanelLayout.setVerticalGroup(
            fontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fontPanelLayout.createSequentialGroup()
                .addGroup(fontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(fontPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sizeScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(styleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(fontScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
        );

        previewPanel.setBorder(new javax.swing.border.TitledBorder(null, "Preview", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12)));

        labelPreview.setFont(new java.awt.Font("Dialog", 0, 12));
        
        labelPreview.setText("abcdefgh ABCDEFGH");

        javax.swing.GroupLayout previewPanelLayout = new javax.swing.GroupLayout(previewPanel);
        previewPanel.setLayout(previewPanelLayout);
        previewPanelLayout.setHorizontalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, previewPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        previewPanelLayout.setVerticalGroup(
            previewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, previewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fontPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(fontPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(previewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancelButton)
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getRootPane().setDefaultButton(okButton);

        pack();
    }// </editor-fold>                        

    private void styleListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstStyleValueChanged
        int style = -1;
        String selStyle = (String)styleList.getSelectedValue();
        if(selStyle.equals("Plain"))
            style = Font.PLAIN;
        if(selStyle.equals("Bold"))
            style = Font.BOLD;
        if(selStyle.equals("Italic"))
            style = Font.ITALIC;
        if(selStyle.equals("Bold Italic"))
            style = Font.BOLD + Font.ITALIC;
        
        font = new Font(font.getFamily(),style,font.getSize());
        labelPreview.setFont(font);
    }//GEN-LAST:event_lstStyleValueChanged

    private void sizeListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstSizeValueChanged
        int size = Integer.parseInt((String)sizeList.getSelectedValue());
        font = new Font(font.getFamily(),font.getStyle(),size);
        labelPreview.setFont(font);
    }//GEN-LAST:event_lstSizeValueChanged

    private void fontListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstFontValueChanged
        font = new Font((String)fontList.getSelectedValue(),font.getStyle(),font.getSize());
        labelPreview.setFont(font);
    }//GEN-LAST:event_lstFontValueChanged
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        doClose(RET_OK);
    }                                        

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        doClose(RET_CANCEL);
    }                                            

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {                             
        doClose(RET_CANCEL);
    }                            
    
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
    
    public void showDialog()
    {
        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new JFontChooser().setVisible(true);     
    }
    
    

    // Variables declaration - do not modify                     
    private javax.swing.JButton cancelButton;
    private javax.swing.JList fontList;
    private javax.swing.JPanel fontPanel;
    private javax.swing.JScrollPane fontScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelPreview;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JList sizeList;
    private javax.swing.JScrollPane sizeScrollPanel;
    private javax.swing.JList styleList;
    private javax.swing.JScrollPane styleScrollPane;
    // End of variables declaration                   

    private int returnStatus = RET_CANCEL;
}
