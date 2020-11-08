package myPackage;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class Resister extends JFrame{

	JLabel lbl,namelbl,birthlbl,emaillbl, idlbl,pwlbl,nicklbl;
    JTextField nameField, birthField,emailField, idField, nickField;
    JPasswordField passwd;
    JPanel namePanel,birthPanel, emailPanel, idPanel,paPanel,nickPanel;
    JButton resister_btn;
    JTextArea content;
 
	public Resister() {
			super("Resister Form");
	        setLayout( new FlowLayout() );
	        EtchedBorder eborder =  new EtchedBorder();
	        
	        lbl = new JLabel( "Enter user information" );
	        // 레이블에 영역 만들기
	        lbl.setBorder(eborder);
	        add( lbl );
	        
	        
	        namePanel = new JPanel();
	        birthPanel = new JPanel();
	        emailPanel = new JPanel();
	        idPanel = new JPanel();
	        paPanel = new JPanel();
	        nickPanel = new JPanel();
	        
	        namelbl = new JLabel("User Name");
	        nameField = new JTextField(10);
	        namePanel.add(namelbl);
	        namePanel.add(nameField);
	        add(namePanel);
	        
	        birthlbl = new JLabel("birthday");
	        birthField = new JTextField(10);
	        birthPanel.add(birthlbl);
	        birthPanel.add(birthField);
	        add(birthPanel);
	        
	        emaillbl = new JLabel("email");
	        emailField = new JTextField(10);
	        emailPanel.add(emaillbl);
	        emailPanel.add(emailField);
	        add(emailPanel);


	        
	        idlbl = new JLabel("user Id");
	        idField = new JTextField(10);
	        idPanel.add(idlbl);
	        idPanel.add(idField);
	        add(idPanel);
	        
	        
	        pwlbl = new JLabel("password");
	        passwd = new JPasswordField(10);
	        paPanel.add(pwlbl);
	        paPanel.add(passwd);
	        add(paPanel);
	        
	        nicklbl = new JLabel("nick name");
	        nickField = new JTextField(10);
	        nickPanel.add(nicklbl);
	        nickPanel.add(nickField);
	        
	        resister_btn = new JButton("Resister");
	        add(resister_btn);
	        
	    
	        setSize( 250, 350 );
	        setVisible(true);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);

	}


}
