import javax.swing.UIManager;

import org.kalecser.skype.ui.SkypeFollowMeScreen;


public class MainSwing {

	public static void main(String[] args) {
		setLookAndFeel();
		new SkypeFollowMeScreen(new SkypeFollowMeScreenNeedsImpl()).show();
	}

	private static void setLookAndFeel() {
		try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			//ignore
		}
	}
	
}
