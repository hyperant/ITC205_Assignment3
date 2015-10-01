package library.panels;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import library.interfaces.IMainListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private IMainListener listener; //Because we are access this var from within side a nested function we have two options in order to declare tha var, option 1 set it as final, option to make it global. Global seems like a better fit so I have changed the code to reflect this

	public MainPanel(IMainListener list) {
		this.listener =list;
		
		setBorder(new TitledBorder(null, "Main Menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBounds(500, 50, 470, 680);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Backwoods Regional Library");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(12, 27, 446, 32);
		add(lblNewLabel);
		
		JLabel lblSelfServiceSystem = new JLabel("Self Service System");
		lblSelfServiceSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelfServiceSystem.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSelfServiceSystem.setBounds(12, 61, 446, 32);
		add(lblSelfServiceSystem);
		
		JButton btnBorrowBooks = new JButton("Borrow Books");
		btnBorrowBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listener.borrowBooks();
			}
		});
		btnBorrowBooks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrowBooks.setBounds(141, 138, 155, 37);
		add(btnBorrowBooks);
        //setBounds(500, 100, 750, 615);
	}
}
