import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//import jdk.internal.platform.Container;

import javax.swing.JMenu;

public class ImagemViewer extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ImagemViewer() {
		setTitle("ImagemViewer");
		setSize(300, 400);
		
		JMenuBar mbar = new JMenuBar();
		JMenu m = new JMenu();
		
		JMenuItem m1 = new JMenuItem("Abrir");
		m1.addActionListener((ActionListener) this);
		m.add(m1);
		
		JMenuItem m2 = new JMenuItem("Sair");
		m2.addActionListener((ActionListener) this);
		m.add(m2);
		
		mbar.add(m);
		setJMenuBar(mbar);
		
		label = new JLabel();
		java.awt.Container contentPane = getContentPane();
		contentPane.add(label, "Center");
		contentPane.add(mbar);
	}
	
	public void actinPerformed(ActionEvent evt) {
		String arg;
		arg = evt.getActionCommand();
		if (arg.equals("Abrir")){
			JFileChooser chooser = new JFileChooser();
			
			chooser.setCurrentDirectory(new File("."));
			/*
			chooser.setFileFilter(new  
				javax.swing.filechooser.FileFilter(){
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith("*,gif");
				}
			});
 r = chooser.showOpenDialog(this);
			if( r == JFileChooser.APPROVE_OPTION) {
				String name = chooser.getSelectedFile().getPath();
				label.setIcon(new ImageIcon(name));
			}*/
		}
		else if(arg.equals("Sair")) {
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new ImagemViewer();
		frame.setVisible(true);
		//frame.show();
	}
	private JLabel label;
}
