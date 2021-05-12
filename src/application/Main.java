package src.application;

import javafx.stage.Stage;

public class Main {
	
	public static void main(String []args) {	

		UserSession.userName = "";
        UserSession.userType = "";
        
		FirstSession firstsession = new FirstSession();	
		firstsession.launchFirstSession();
		
	}
}
