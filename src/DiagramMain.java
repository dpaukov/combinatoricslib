


    import javax.swing.JFrame;
    import javax.swing.JLabel;

    //import statements
    //Check if window closes automatically. Otherwise add suitable code
    public class DiagramMain extends JFrame {

    	public static void main(String args[]) {
    		new DiagramMain();
    	}
    	
    	DiagramMain() 	{
    		JLabel jlbHelloWorld = new JLabel("Hello World");
    		add(jlbHelloWorld);
    		this.setSize(100, 100);
    		// pack();
    		setVisible(true);
    	}
    }
    
