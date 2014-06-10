/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import features.JFontChooser;
import features.KeyCheck;
import features.search.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import static javafx.scene.text.Font.getFontNames;

import javax.swing.*;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Element;

/**
 *
 * @author amit
 */
public class MainWindow extends JFrame{
    private JTextArea line_number, textArea;
    private JScrollPane textAreaScrollPane;
    private JMenuBar JMB;
    private JMenu File;
    private JMenu Edit;
    private JMenu View;
    private JMenu RunMenu;
    private JMenu DebugMenu;
    
    
    private boolean changed = false;
    private String currentFile;
    private String fileName = "Untitled";
    private String currentFont = Font.MONOSPACED;
    private boolean usedSearch = false;
    KeyCheck keychecker = new KeyCheck();
     
    
    private File file;       
    private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	/*File choosers provide a GUI for navigating the file system, and then either choosing 
	 	a file or directory from a list, or entering the name of a file or directory.
	 	To display a file chooser, you usually use the JFileChooser API to show a modal dialog*/
    MainWindow()
    {
     createAndShowGUI();   
    }    
    
    private void createAndShowGUI()
    {
        
        setTitle("Misal IDE 1.0 - "+fileName);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        JMB = new JMenuBar();
        setJMenuBar(JMB);
        File = new JMenu("File");
        File.setMnemonic(KeyEvent.VK_F);
        
        Edit = new JMenu("Edit");
        Edit.setMnemonic(KeyEvent.VK_E);
        
        View = new JMenu("View");
        View.setMnemonic(KeyEvent.VK_V);
        
        RunMenu = new JMenu("Run");
        RunMenu.setMnemonic(KeyEvent.VK_R);
        
        DebugMenu = new JMenu("Debug");
        DebugMenu.setMnemonic(KeyEvent.VK_D);
        
        JMB.add(File);
        JMB.add(Edit);
        JMB.add(View);
        JMB.add(RunMenu);
        JMB.add(DebugMenu);
        
        File.add(New).setText("New");
        File.add(Open).setText("Open");
        File.add(Save).setText("Save");
        File.add(SaveAs).setText("SaveAs");
        File.add(Exit).setText("Exit");
        
        Edit.add(Cut).setText("Cut");
        Edit.add(Copy).setText("Copy");
        Edit.add(Paste).setText("Paste");
        Edit.add(Find).setText("Find");
        Edit.add(ChangeFont).setText("ChangeFont");
        
        RunMenu.add(CompileProject).setText("Compile");
        RunMenu.add(ExecProject).setText("Execute");
        
        DebugMenu.add(StartDebug).setText("Start");
        DebugMenu.add(SetBP).setText("Set Breakpoint");
        DebugMenu.add(ContinueBP).setText("Continue");
        DebugMenu.add(RemBP).setText("Remove BreakPoint");
        DebugMenu.add(RemAllBP).setText("Remove All BreakPoints");
        
        textAreaScrollPane = new JScrollPane();
        
        line_number = new JTextArea("1");
        line_number.setBackground(Color.GRAY);
        line_number.setForeground(Color.red);
        line_number.setFont(new Font(currentFont,Font.CENTER_BASELINE,15));
        
        this.add(line_number);
        line_number.setEditable(false);
        
        textArea = new JTextArea();
        textArea.setBackground(Color.darkGray);
        textArea.setCaretColor(Color.GREEN);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
        textArea.getDocument().addDocumentListener(new DocumentListener(){
        
            public String getText(){
            
                int caretPosition = textArea.getDocument().getLength();
                Element root = textArea.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
            
            for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++)
            {
		text += i + System.getProperty("line.separator");
            }
             return text;
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                line_number.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                line_number.setText(getText());   
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                line_number.setText(getText());
            }
            
        });
        
        textAreaScrollPane.getViewport().add(textArea,BorderLayout.CENTER);
        textAreaScrollPane.setRowHeaderView(line_number);
        textAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textAreaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(textAreaScrollPane);
        pack();
        setSize(700,500);
        
        Save.setEnabled(false);
        SaveAs.setEnabled(false);
        textArea.addKeyListener(k1);
        
    }
    

//--------------_File_-------------
    
    Action New = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                   createNewFile(); 
                }
    };
    
    Action Open = new AbstractAction(){
		public void actionPerformed(ActionEvent e)
                {
                    saveOld();
                    if(dialog.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
                    {   
                         fileName = dialog.getSelectedFile().getName();
                        currentFile = dialog.getSelectedFile().getAbsolutePath();
                        file=new File(currentFile);
                        readInFile(currentFile);  
		//passing the address(path) of the chosen file to readInFile Method.
                    }
                    SaveAs.setEnabled(true);
            }
    };
    
    Action Save = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    if(!fileName.equals("Untitled"))
                        saveFile(currentFile);
                    else
                        saveFileAs();
                    //If current File is not equal to untitled then use SaveAs
                }
    };
    
    Action SaveAs = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    saveFileAs();
                    
                }
    };
    
    Action Exit = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    saveOld();  
                    System.exit(0);
                    
                }
    };
    
    //-----------_END-File_-----------
    
    
    //--------_Edit_---------------
    
    
    Action Cut = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    DefaultEditorKit.CutAction cutAction = new DefaultEditorKit.CutAction();  
                    cutAction.actionPerformed(e);
                }
    };
    
    Action Copy = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    DefaultEditorKit.CopyAction copyAction = new DefaultEditorKit.CopyAction();
                    copyAction.actionPerformed(e);
                }
    };
    
    Action Paste = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    DefaultEditorKit.PasteAction pasteAction = new DefaultEditorKit.PasteAction();
                    pasteAction.actionPerformed(e);
                    
                }
    };
    
    Action Find = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    
                            String word = JOptionPane.showInputDialog(new MainWindow(),"","Find",PLAIN_MESSAGE);
                                new WordSearcher(textArea,word);
                                usedSearch = true;
                  
                    //after searching if any key is pressed then remove the highlights
                    //Therefore Using THe KeyChek Class
                }                   
    };
        
        Action ChangeFont = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                  
                    JFontChooser fd = new JFontChooser(textArea.getFont());
                    fd.showDialog();
                    
                    if(fd.getReturnStatus() == fd.RET_OK){
                           textArea.setFont(fd.getFont());
                           line_number.setFont(fd.getFont());
                         }
                    fd.dispose();
                    
                }
    };

    //-----------------_END Edit_-----------
    
    //----------------_Run_----------------
    Action CompileProject = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    
                    compileFile(file);
                }
    };
    
    Action ExecProject = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    run(file);
                }
    };

    //----------------_End_Run_--------------
    
    //----------------_Debug_----------------
    Action StartDebug = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    
                    
                }
    };
    
    Action SetBP = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    
                }
    };
    
    Action ContinueBP = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    
                }
    };
    
    Action RemBP = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    
                }
    };
    
    Action RemAllBP = new AbstractAction(){
		public void actionPerformed(ActionEvent e){
                    
                }
    };
    
    
    //----------------_End_Debug_--------------
   
    private KeyListener k1 = new KeyAdapter()
	{       
		public void keyPressed(KeyEvent e)
		{                           
                        int i  =keychecker.InvalidKeyCheck(e) ;
                        if(i==0)
                        {
			changed = true;
			Save.setEnabled(true);    //----MADE----
			SaveAs.setEnabled(true);
                        
                        setTitle("Misal IDE 1.0 - *"+fileName);
                        
                        //For Setting the Highlights over the font to normal
                        
                        
                        if( usedSearch )
                        {
                                new WordSearcher(textArea,"");
                                usedSearch = false;
                            }
                        
                    //Shortcut Key Pressed Check
                        
                        if(e.isControlDown())
                        {
                         
                        int validShortcut = keychecker.ValidateShortcut(e,new MainWindow());
                             if(validShortcut != -1)
                                {
                                    if(validShortcut == KeyEvent.VK_F)
                                    {
                                        Find.actionPerformed(null);
                                    }
                                    else if(validShortcut == KeyEvent.VK_S)
                                    {
                                        Save.actionPerformed(null);
                                    }
                                    else if(validShortcut == KeyEvent.VK_O)
                                    {
                                        Open.actionPerformed(null);
                                    }
                                }
                        }
                        
                        }
                        
                }
                
                
	};
	
    void createNewFile()
    {
        if(changed == true)
        {   
            saveOld();
            //saveFileAs();
            changed = false;
            fileName = "Untitled";
            setTitle("Misal IDE 1.0 - "+fileName);
            textArea.setText(null);
	}
	else
	{
            changed=false;    
            fileName = "Untitled";
            setTitle("Misal IDE 1.0 - "+fileName);
            textArea.setText(null);
	}

    }
    
    private void saveFileAs()
	{
		if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			saveFile(dialog.getSelectedFile().getAbsolutePath(),
                                            dialog.getSelectedFile().getName());
		
	}
    
    private void saveFile(String filePath)
	{
		try{
                        file = new File(filePath);
			FileWriter w = new FileWriter(filePath);
			textArea.write(w);
			w.close();
                        currentFile = filePath;
                        
			setTitle("Misal IDE 1.0 - "+fileName);
			
			changed = false;
			Save.setEnabled(false);
		}
		catch(IOException e)
		{
			//no exception handling routine
		}
		
	}
    //Overloading Save file Method
    private void saveFile(String filePath,String fName)
	{
		try{    
                        file = new File(filePath);
			FileWriter w = new FileWriter(filePath);
			textArea.write(w);
			w.close();
                        currentFile = filePath;
                        
			setTitle("Misal IDE 1.0 - "+fName);
                        fileName = fName;
			
			changed = false;
			Save.setEnabled(false);
		}
		catch(IOException e)
		{
			//no exception handling routine
		}
		
	}
    
    
    private void saveOld(){
		if(changed){
               int i = JOptionPane.showConfirmDialog(this, "Would Like To Save " +currentFile+
                                " ?","Save",JOptionPane.YES_NO_CANCEL_OPTION);
               
			if(i==JOptionPane.YES_OPTION)
				saveFileAs();
                        //elseif(i==JOptionPane.NO_OPTION)
                                //Do Nothing
                        //elseif(i==JOptionPane.CANCEL_OPTION)
                                //
                         
					}
	}
    
    private void readInFile(String filePath){
		try{
			FileReader r = new FileReader(filePath);
			textArea.read(r,null);
			//area is object of JTextArea. It is a multiline plain text area.
			r.close();
			currentFile = filePath;
			//currentFile variable is of String type and stores the name of the text File
			//it is used for Setting the name on Title Bar.
			setTitle("Misal IDE 1.0 - "+fileName);
			changed = false;	
		}
		catch(IOException e)
		{
			Toolkit.getDefaultToolkit().beep();
			//Beep signal emitted on reciept of and error
			JOptionPane.showMessageDialog(this,"Editor Cant Find The File Called "+filePath);
			
		}
	}
    
    public void compileFile(File file)
    {
        try {
            String fullName=file.getName();
            String name;
            int index=file.getName().lastIndexOf('.');
            
            if(index>0 && index<=file.getName().length()-2)
                name=file.getName().substring(0, index);
            else
                name=file.getName();
   
            String dir1=file.getParent();
            
            String[] command = {"xterm","-e","nasm -f elf64 " + fullName};
          
           ProcessBuilder pb= new ProcessBuilder(command);
           pb.directory(new File(dir1));
           Process p=pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String Error;
            while ((Error = stdError.readLine()) != null) {
                System.out.println(Error);
            }
            while ((Error = stdInput.readLine()) != null) {
                System.out.println(Error);
            }
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
   
    }
    
    public void run(File file)
    {
        try {
            String fullName=file.getName();
            String name;
            int index=file.getName().lastIndexOf('.');
            
            if(index>0 && index<=file.getName().length()-2)
                name=file.getName().substring(0, index);
            else
                name=file.getName();
   
            String dir1=file.getParent();
          String obj= name+".o"; 
          String[] command = {"xterm","-e","ld -o out " + obj};
          System.out.print(command);
           ProcessBuilder pb= new ProcessBuilder(command);
           pb.directory(new File(dir1));
           Process p=pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String Error;
            while ((Error = stdError.readLine()) != null) {
                System.out.println(Error);
            }
            while ((Error = stdInput.readLine()) != null) {
                System.out.println(Error);
            }
            if(Error==null)
                execution(dir1);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void execution(String dir1)
    {
        try{
                   String[] command = {"xterm","-e","./out"};
           ProcessBuilder pb= new ProcessBuilder(command);
           pb.directory(new File(dir1));
           Process p=pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String Error;
            while ((Error = stdError.readLine()) != null) {
                System.out.println(Error);
            }
            while ((Error = stdInput.readLine()) != null) {
                System.out.println(Error);
            }
          
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void main(String args[])
    {
        try {
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn); 
        } catch (Exception cnf) {
        }
        MainWindow o = new MainWindow();
        o.setVisible(true);
    }
    
}
