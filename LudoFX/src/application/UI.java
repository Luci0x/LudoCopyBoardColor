package application;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import com.sun.scenario.effect.Glow;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
//use to execute jar file : java -jar Chich.jar
public class UI extends Main {
	//random class
	Random Rd = new Random();
	// players array
	static Player[] P = new Player[4];
	//Dog Buttons array
	static Button[] D_Bs = new Button[4];
	//dice X
	static int X=0;
	//current player
	static int CURRENT_PLAYER=-1;
	//win variable
	static boolean WIN;
	//used for star win images
	static int WIN_COUNT;
	//used on matchFinished to show the winners
	static String WIN_STRING;
	//cords of the positions, there are 52 main positions
	static int [][] D_Pos = new int [][] {{74, 110, 145, 181, 218, 253, 252, 252, 253, 253, 253, 289, 326, 360, 396, 397, 397, 398, 396, 396, 434, 469, 504, 541, 576, 576, 577, 576, 578, 541, 504, 468, 432, 396, 397, 396, 396, 396, 395, 361, 326, 289, 255, 253, 253, 254, 254, 253, 216, 181, 145, 110, 74, 74, 74, 74},
			{239, 239, 239, 239, 239, 239, 205, 172, 140, 105, 73, 73, 73, 72, 72, 105, 139, 171, 205, 239, 239, 239, 238, 239, 238, 272, 305, 337, 370, 370, 370, 370, 370, 371, 404, 438, 469, 503, 536, 536, 536, 536, 536, 503, 469, 438, 404, 371, 370, 372, 372, 372, 372, 338, 307, 274}};
	//Starting positions for all 4 players
	static int [][] Starting_P = new int [][] {{90,170,430,510},{150,242,482,574}};

	//Sound effects
	AudioClip deathSound;
	AudioClip applause;
	AudioClip diceRoll;
	
	//Menu
	@FXML private AnchorPane Game_Start_Menu;
	@FXML private AnchorPane Game_APane;
	@FXML private Button Dice_Button;
	@FXML private Rectangle diceButtonRec;
	@FXML private Button Start_Button;
	@FXML private Label startGameLabel;
	
	//Dog Buttons
	@FXML private Button D_Button1;
	@FXML private Button D_Button2;
	@FXML private Button D_Button3;
	@FXML private Button D_Button4;
	
	//Player 1 =
	@FXML private CheckBox P1_C;
	@FXML private Label P1_Lb;
	@FXML private TextField P1_N;
	@FXML private ColorPicker P1_Cl;
	@FXML private Polygon P1_Dog1;
	@FXML private Polygon P1_Dog2;
	@FXML private Polygon P1_Dog3;
	@FXML private Polygon P1_Dog4;
	@FXML private Rectangle P1_Rec;
	@FXML private Polygon P1_Tri;
	
	//Player 2 =
	@FXML private CheckBox P2_C;
	@FXML private Label P2_Lb;
	@FXML private TextField P2_N;
	@FXML private ColorPicker P2_Cl;
	@FXML private Polygon P2_Dog1;
	@FXML private Polygon P2_Dog2;
	@FXML private Polygon P2_Dog3;
	@FXML private Polygon P2_Dog4;
	@FXML private Rectangle P2_Rec;
	@FXML private Polygon P2_Tri;
	
	//Player 3 =
	@FXML private CheckBox P3_C;
	@FXML private Label P3_Lb;
	@FXML private TextField P3_N;
	@FXML private ColorPicker P3_Cl;
	@FXML private Polygon P3_Dog1;
	@FXML private Polygon P3_Dog2;
	@FXML private Polygon P3_Dog3;
	@FXML private Polygon P3_Dog4;
	@FXML private Rectangle P3_Rec;
	@FXML private Polygon P3_Tri;
	
	//Player 4 =
	@FXML private CheckBox P4_C;
	@FXML private Label P4_Lb;
	@FXML private TextField P4_N;
	@FXML private ColorPicker P4_Cl;
	@FXML private Polygon P4_Dog1;
	@FXML private Polygon P4_Dog2;
	@FXML private Polygon P4_Dog3;
	@FXML private Polygon P4_Dog4;
	@FXML private Rectangle P4_Rec;
	@FXML private Polygon P4_Tri;
	
	//Initialize
	public void initialize() {
		//initializing Sound effects
		deathSound = new AudioClip(getClass().getResource("audio/deathSound.wav").toString());
		applause = new AudioClip(getClass().getResource("audio/applause.wav").toString());
		diceRoll = new AudioClip(getClass().getResource("audio/Card-flip-sound-effect.mp3").toString());
	}
	
	//Menu
	public void checkboxChanged() {
		//checks if there is more than 2 players selected or no
		//make start button disabled if less than 2
		int i=0;
		if (P1_C.isSelected()) i++;
		if (P2_C.isSelected()) i++;
		if (P3_C.isSelected()) i++;
		if (P4_C.isSelected()) i++;
		
		if(i<2) {
			TranslateTransition TT = new TranslateTransition();
			TT.setDuration(Duration.seconds(1));
			TT.setNode(startGameLabel);
			TT.setToY(-25);
			FadeTransition FT = new FadeTransition();
			FT.setNode(startGameLabel);
			FT.setDuration(Duration.seconds(1));
			FT.setFromValue(0.0);
			FT.setToValue(0.95);
			ParallelTransition PT = new ParallelTransition(TT,FT);
			PT.play();
			
			Start_Button.setDisable(true);
		}
		else {
			TranslateTransition TT = new TranslateTransition();
			TT.setDuration(Duration.seconds(1));
			TT.setNode(startGameLabel);
			TT.setToY(25);
			TT.play();
			
			Start_Button.setDisable(false);
		}
	}

	public void Start_Game() {
		//putting CP to -1 , important
		CURRENT_PLAYER=-1;
		//integer used for naming
		int nameInt=1;
		//Temporary variables
		String color;
		String name;

		//startup:
		if(P1_C.isSelected()) {
			//setting current player , has to be set
			if (CURRENT_PLAYER<0) CURRENT_PLAYER=0;

			//Creating new player
			color = "#FF0000";
			name = P1_N.getText();

			//creating the player with a name and a color
			P[0] = new Player(name,color);

			//Giving the player his dogs
			P[0].Dogs[0] = P1_Dog1;
			P[0].Dogs[1] = P1_Dog2;
			P[0].Dogs[2] = P1_Dog3;
			P[0].Dogs[3] = P1_Dog4;

			for(int i=0;i<4;i++) P[0].Dogs[i].setFill(Paint.valueOf(P[0].color));

			//setting Dogs initial cords
			for (int i = 0; i < 4; i++) {
				P[0].Dogs_Pos_Ininial_Cord[0][i] = (int) P[0].Dogs[i].getLayoutX() - 13;
				P[0].Dogs_Pos_Ininial_Cord[1][i] = (int) P[0].Dogs[i].getLayoutY() - 13;
			}

			//Making all dogs dead
			for (int i = 0; i < 4; i++) P[0].Dogs_Alive[i] = false;

			//Making all dogs Not entering
			for (int i = 0; i < 4; i++) P[0].Dogs_Entering[i] = false;

			//setting Starting Point for each player
			P[0].Player_Start_Pos = 0;

			//player entering vars used in move dog...
			P[0].P_E_X = 0;
			P[0].P_E_Y = 1;

			//Setting label name, and painting it and triangle and rectangle
			P1_Lb.setText(P[0].Name);
			P1_Lb.setTextFill(Paint.valueOf(P[0].color));
			P1_Rec.setFill(Paint.valueOf(P[0].color));
			P1_Tri.setFill(Paint.valueOf(P[0].color));
		};

		if(P2_C.isSelected()) {
			if (CURRENT_PLAYER<0) CURRENT_PLAYER=1;

			color = "#008000";
			name = P2_N.getText();
			P[1] = new Player(name,color);


			P[1].Dogs[0] = P2_Dog1;
			P[1].Dogs[1] = P2_Dog2;
			P[1].Dogs[2] = P2_Dog3;
			P[1].Dogs[3] = P2_Dog4;

			for(int i=0;i<4;i++) P[1].Dogs[i].setFill(Paint.valueOf(P[1].color));
			for(int i=0;i<4;i++) {P[1].Dogs_Pos_Ininial_Cord[0][i]=(int)P[1].Dogs[i].getLayoutX()-13;P[1].Dogs_Pos_Ininial_Cord[1][i]=(int)P[1].Dogs[i].getLayoutY()-13;};
			for(int i=0;i<4;i++) P[1].Dogs_Alive[i]=false;
			for(int i=0;i<4;i++) P[1].Dogs_Entering[i]=false;
			P[1].Player_Start_Pos=14;
			P[1].P_E_X=-1;
			P[1].P_E_Y=0;
			P2_Lb.setText(P[1].Name);
			P2_Lb.setTextFill(Paint.valueOf(P[1].color));
			P2_Rec.setFill(Paint.valueOf(P[1].color));
			P2_Tri.setFill(Paint.valueOf(P[1].color));
		};

		if(P3_C.isSelected()) {
			if (CURRENT_PLAYER<0) CURRENT_PLAYER=2;

			color = "#FFFF00";
			name = P3_N.getText();
			P[2] = new Player(name,color);

			P[2].Dogs[0] = P3_Dog1;
			P[2].Dogs[1] = P3_Dog2;
			P[2].Dogs[2] = P3_Dog3;
			P[2].Dogs[3] = P3_Dog4;


			for(int i=0;i<4;i++) P[2].Dogs[i].setFill(Paint.valueOf(P[2].color));
			for(int i=0;i<4;i++) {P[2].Dogs_Pos_Ininial_Cord[0][i]=(int)P[2].Dogs[i].getLayoutX()-13;P[2].Dogs_Pos_Ininial_Cord[1][i]=(int)P[2].Dogs[i].getLayoutY()-13;};
			for(int i=0;i<4;i++) P[2].Dogs_Alive[i]=false;
			for(int i=0;i<4;i++) P[2].Dogs_Entering[i]=false;
			P[2].Player_Start_Pos=42;
			P[2].P_E_X=1;
			P[2].P_E_Y=0;
			P3_Lb.setText(P[2].Name);
			P3_Lb.setTextFill(Paint.valueOf(P[2].color));
			P3_Rec.setFill(Paint.valueOf(P[2].color));
			P3_Tri.setFill(Paint.valueOf(P[2].color));
		};

		if(P4_C.isSelected()) {
			if (CURRENT_PLAYER<0) CURRENT_PLAYER=3;

			color = "#0000FF";
			name = P4_N.getText();
			P[3] = new Player(name,color);

			P[3].Dogs[0] = P4_Dog1;
			P[3].Dogs[1] = P4_Dog2;
			P[3].Dogs[2] = P4_Dog3;
			P[3].Dogs[3] = P4_Dog4;


			for(int i=0;i<4;i++) P[3].Dogs[i].setFill(Paint.valueOf(P[3].color));
			for(int i=0;i<4;i++) {P[3].Dogs_Pos_Ininial_Cord[0][i]=(int)P[3].Dogs[i].getLayoutX()-13;P[3].Dogs_Pos_Ininial_Cord[1][i]=(int)P[3].Dogs[i].getLayoutY()-13;};
			for(int i=0;i<4;i++) P[3].Dogs_Alive[i]=false;
			for(int i=0;i<4;i++) P[3].Dogs_Entering[i]=false;
			P[3].Player_Start_Pos=28;
			P[3].P_E_X=0;
			P[3].P_E_Y=-1;
			P4_Lb.setText(P[3].Name);
			P4_Lb.setTextFill(Paint.valueOf(P[3].color));
			P4_Rec.setFill(Paint.valueOf(P[3].color));
			P4_Tri.setFill(Paint.valueOf(P[3].color));
		};


		//Coloring the dice
		diceButtonRec.setFill(Paint.valueOf(P[CURRENT_PLAYER].color));

		//Dogs buttons
		D_Bs[0] = D_Button1;
		D_Bs[1] = D_Button2;
		D_Bs[2] = D_Button3;
		D_Bs[3] = D_Button4;

		//setting dice to 6
		changeDice(Dice_Button,6);
		//resetting win count and string
		WIN_COUNT=1;
		WIN_STRING="";
		//switching to game panel
		Game_Start_Menu.setVisible(false);
		Game_APane.setVisible(true);
	}

	public void Backto_Menu() {
		//Reseting dogs position, sorry for bad code :(
		 //setting Player 1 and 3's X to start
		for(int i=0;i<3;i+=2) {
			for(int k=0;k<3;k+=2) if(P[i]!=null)P[i].Dogs[k].setLayoutX(Starting_P[0][0]);
			for(int k=1;k<4;k+=2) if(P[i]!=null)P[i].Dogs[k].setLayoutX(Starting_P[0][1]);
		}
		 //setting Player 2 and 4's X to start
		for(int i=1;i<4;i+=2) {
			for(int k=0;k<3;k+=2) if(P[i]!=null)P[i].Dogs[k].setLayoutX(Starting_P[0][2]);
			for(int k=1;k<4;k+=2) if(P[i]!=null)P[i].Dogs[k].setLayoutX(Starting_P[0][3]);
		}
		 //setting Player 1 and 3's Y to start
		for(int i=0;i<2;i++) {
			for(int k=0;k<2;k++) if(P[i]!=null)P[i].Dogs[k].setLayoutY(Starting_P[1][0]);
			for(int k=2;k<4;k++) if(P[i]!=null)P[i].Dogs[k].setLayoutY(Starting_P[1][1]);
		}
		 //setting Player 2 and 4's Y to start
		for(int i=2;i<4;i++) {
			for(int k=0;k<2;k++) if(P[i]!=null)P[i].Dogs[k].setLayoutY(Starting_P[1][2]);
			for(int k=2;k<4;k++) if(P[i]!=null)P[i].Dogs[k].setLayoutY(Starting_P[1][3]);
		}
		
		//Reseting dogs colors that changed
		for(int i=0;i<4;i++) if(P[i]!=null) for(int k=0;k<4;k++) P[i].Dogs[k].setFill(Paint.valueOf("#ffffff"));
		
		//Reseting players array
		for(int i=0;i<4;i++) P[i]=null;	
		
		//Part of Reset, reseting name labels,rectangles and triangles colors to white
		P1_Lb.setTextFill(Paint.valueOf("#ffffff"));
		P2_Lb.setTextFill(Paint.valueOf("#ffffff"));
		P3_Lb.setTextFill(Paint.valueOf("#ffffff"));
		P4_Lb.setTextFill(Paint.valueOf("#ffffff"));
		P1_Rec.setFill(Paint.valueOf("#ffffff"));
		P2_Rec.setFill(Paint.valueOf("#ffffff"));
		P3_Rec.setFill(Paint.valueOf("#ffffff"));
		P4_Rec.setFill(Paint.valueOf("#ffffff"));
		P1_Tri.setFill(Paint.valueOf("#ffffff"));
		P2_Tri.setFill(Paint.valueOf("#ffffff"));
		P3_Tri.setFill(Paint.valueOf("#ffffff"));
		P4_Tri.setFill(Paint.valueOf("#ffffff"));
		//making all players null
		for(int i=0;i<4;i++) P[i]=null;
		//dice button not disabled
		Dice_Button.setDisable(false);
		//resetting dog buttons
		setD_ButtonsNotVisible(D_Bs);
		//showing the menu
		Game_Start_Menu.setVisible(true);
		Game_APane.setVisible(false);
	}
	public String getRandomColor() {
		//this function returns a random color example: #32c4ef
		String color = "#";
		String alphebet = "abcdef";
		for(int i=0;i<6;i++) {
			if(Rd.nextBoolean()) color += ""+Rd.nextInt(9);
			else color += alphebet.charAt(Rd.nextInt(alphebet.length()));
		}
		return color;
	}
	
	//Main
	public int getRandom() {
		return Rd.nextInt(6)+1;
	}

	public void rollDice() {
		//playing dice sound if not already playing
		if(!diceRoll.isPlaying()) diceRoll.play();
		else {diceRoll.stop();diceRoll.play();}
		//Putting win to false
		WIN = false;
		//Getting the random
		X = getRandom();

		//changing the dice button to that random
		changeDice(Dice_Button,X);
		//getting how many dogs are alive
		int D_As = getHowManyDogsAlive(P[CURRENT_PLAYER]);
		
		if(X==6){
			//dice is six
			int k=setD_ButtonsVisible(X,P[CURRENT_PLAYER],D_Bs);
			// k is a special variable, if its positive that means only one button is visible and we have to simulate a move
			// and it contains the number of the dog +1, thats why its k-1 , and its that way to avoid the 0
			if(k>0) {
				setD_ButtonsNotVisible(D_Bs);
				// dog is alive we move it with 6 pos
				if(P[CURRENT_PLAYER].Dogs_Alive[k-1]) {
					WIN=moveDog(P[CURRENT_PLAYER],k-1,X);
				}
				// dog is dead so we move it to its starting point and make it alive
				else {
					WIN=moveDog(P[CURRENT_PLAYER],k-1,P[CURRENT_PLAYER].Player_Start_Pos);
					P[CURRENT_PLAYER].Dogs_Alive[k-1]=true;
				}
			}
			else if(k!=0){
				Dice_Button.setDisable(true);
			}
			//to not changeCP
			return;
		}

		else if(D_As==1) {
			//dice not six and only 1 dog alive
			int y = getFirstAlive();
			WIN=moveDog(P[CURRENT_PLAYER],y,X);
		}
		else if(D_As>1){
			//dice not six and more than 1 dog alive
			int k=setD_ButtonsVisible(X,P[CURRENT_PLAYER],D_Bs);
			if(k>0) {
				setD_ButtonsNotVisible(D_Bs);
				WIN=moveDog(P[CURRENT_PLAYER],k-1,X);
			}
			else if(k!=0){
				Dice_Button.setDisable(true);
				//to not changeCP
				return;
			}
		}

		//if the player won
		if(WIN) {
			//the wining animation , making player won , incrementing the win count
			won();
			P[CURRENT_PLAYER].won=true;
			WIN_COUNT++;
			//return to stop from changing player and running to an infinite loop sometimes
			return;
		}
		changeCP();
	}
	public void changeCP() {
		//changing to next player with proper order
		while(true) {
			
			switch(CURRENT_PLAYER) {
				case 0: CURRENT_PLAYER=1;break;
				case 1: CURRENT_PLAYER=3;break;
				case 2: CURRENT_PLAYER=0;break;
				case 3: CURRENT_PLAYER=2;break;
			}
			
			if (P[CURRENT_PLAYER]!=null && !P[CURRENT_PLAYER].won) {
				//Coloring the dice
				diceButtonRec.setFill(Paint.valueOf(P[CURRENT_PLAYER].color));
				return;
			}
		}
	}
	public void changeDice(Button B,int x) {
		B.setStyle("-fx-background-image: url(imgs/D_"+x+".png)");
		B.setText(""+x);
	}

	//Dog methods
	public int getFirstAlive() {
		for (int i=0;i<4;i++) {
			if(P[CURRENT_PLAYER].Dogs_Alive[i]) {
				return i;
			}
		}
		return -1;
	}
	public int getHowManyDogsAlive(Player Pl) {
		int As=0;
		for(int i=0;i<4;i++) if(Pl.Dogs_Alive[i]==true) As++;
		return As;
	}
	public boolean moveDog(Player P,int Dog,int x) {
		boolean Win=false;

		//new position in Player class
		P.Dogs_Pos[Dog]=P.Dogs_Pos[Dog]+x;
		
		//for the loop
		if(P.Dogs_Pos[Dog]>51) P.Dogs_Pos[Dog]=P.Dogs_Pos[Dog]-52;
		
		if(!P.Dogs_Entering[Dog]) {
			//dog not in entering state
			 //Entering ?
			int T =P.Player_Start_Pos-7;
			 //for the loop
			if (T<0) T=T+51;
			
			if(P.Player_Start_Pos==1) {if(P.Dogs_Pos[Dog]>T) P.Dogs_Entering[Dog]=true;}
			else if(P.Dogs_Pos[Dog]>T && P.Dogs_Pos[Dog]<P.Player_Start_Pos) P.Dogs_Entering[Dog]=true;

			//setting dog to new position
			P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]]);
			P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]]);
			//to eat dogs under
			eat(Dog);
		}
		else {
			if(P.Dogs_Pos[Dog]>P.Player_Start_Pos) {
				if(P.Dogs_Pos[Dog]>P.Player_Start_Pos && P.Dogs_Pos[Dog]<P.Player_Start_Pos+6) {

					//after starting pos
					//General: (wut a pain to find)
					P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]-1]+30*P.P_E_X);
					P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]-1]+30*P.P_E_Y);
					//P1
					//P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]-1]);
					//P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]-1]+30);
					//P2
					//P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]-1]-30);
					//P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]-1]);
					//P3
					//P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]-1]+30);
					//P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]-1]);
					//P4
					//P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]-1]);
					//P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]-1]-30);
				}
				else if(P.Dogs_Pos[Dog]==P.Player_Start_Pos+6){
					//Dog Reached final pos
					//General: 
					P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]-1]+60*P.P_E_X);
					P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]-1]+60*P.P_E_Y);
					//P1
					//P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]-1]);
					//P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]-1]+60);
					//P2
					//P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]-1]-60);
					//P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]-1]);
					//P3
					//P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]-1]+60);
					//P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]-1]);
					//P4
					//P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]-1]);
					//P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]-1]-60);
					
					//so dog doesn't move again
					P.Dogs_Entered[Dog]=true;
					Win=isWin(P);
					P.Dogs_Alive[Dog]=false;
				}
				else if(P.Dogs_Pos[Dog]>P.Player_Start_Pos+6 && P.Dogs_Pos[Dog]<P.Player_Start_Pos+12){
					//New pos is exceeding Dog's last pos
					P.Dogs_Pos[Dog]=P.Dogs_Pos[Dog]-x;
				}
				else {
					//before starting pos
					P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]]);
					P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]]);
					eat(Dog);
				}
			}
			else {
				//before starting pos
				P.Dogs[Dog].setLayoutX(D_Pos[0][P.Dogs_Pos[Dog]]);
				P.Dogs[Dog].setLayoutY(D_Pos[1][P.Dogs_Pos[Dog]]);
				eat(Dog);
			}
		}
		return Win;
	}

	//Dog buttons
	public void Dog_Button_Clicked(Event Ev) {
		//dog button either a dead dog moved to start pos or alive and moved, changing player at end.
		
		//x is the number of the dog (getting it from the text in the button)
		int x = Integer.parseInt(((Button)Ev.getSource()).getText());
		//y is the dice number
		int y =	Integer.parseInt(Dice_Button.getText());
		if(P[CURRENT_PLAYER].Dogs_Alive[x]) {
			WIN=moveDog(P[CURRENT_PLAYER],x,y);
		}
		else {
			WIN=moveDog(P[CURRENT_PLAYER],x,P[CURRENT_PLAYER].Player_Start_Pos);
			P[CURRENT_PLAYER].Dogs_Alive[x]=true;
		}
		setD_ButtonsNotVisible(D_Bs);
		Dice_Button.setDisable(false);
		
		//if the player won
		if(WIN) {
			won();
			P[CURRENT_PLAYER]=null;
			WIN_COUNT++;
		}
		//dont change if dice is 6
		
		if(y==6) return;
		changeCP();
	}
	public int setD_ButtonsVisible(int d,Player P,Button[] D_Bs) {
		int k=0;
		if(d==6) {
			//for when dice is 6
			for(int i=0;i<4;i++) {
				if(!P.Dogs_Alive[i]) {
					if(!P.Dogs_Entered[i]) {
						//not alive and not entered
						D_Bs[i].setLayoutX(P.Dogs_Pos_Ininial_Cord[0][i]);
						D_Bs[i].setLayoutY(P.Dogs_Pos_Ininial_Cord[1][i]);
						D_Bs[i].setVisible(true);
						if(k==0)k=i+1;
						else k=-1;
					}
				}
				else if(!(P.Dogs_Entering[i] && (P.Dogs_Pos[i]>P.Player_Start_Pos && P.Dogs_Pos[i]<P.Player_Start_Pos+6))){
					//alive but not (entering and above start point)
					//put a button only if its behind start pos, because 6 is exceeding if after 
					D_Bs[i].setLayoutX(D_Pos[0][P.Dogs_Pos[i]]-13);
					D_Bs[i].setLayoutY(D_Pos[1][P.Dogs_Pos[i]]-13);
					D_Bs[i].setVisible(true);
					if(k==0)k=i+1;
					else k=-1;
				}
			}
		}
		else {
			//for when dice is 1 to 5
			for(int i=0;i<4;i++) {
				if(P.Dogs_Alive[i]) {
					if(!P.Dogs_Entering[i]) {
						//alive and not entering
						D_Bs[i].setLayoutX(D_Pos[0][P.Dogs_Pos[i]]-13);
						D_Bs[i].setLayoutY(D_Pos[1][P.Dogs_Pos[i]]-13);
						D_Bs[i].setVisible(true);
						//+1 to avoid 0
						if(k==0)k=i+1;
						else k=-1;
					}
					else if(P.Dogs_Pos[i]>P.Player_Start_Pos && P.Dogs_Pos[i]<P.Player_Start_Pos+6) {
							//alive and entering after start pos
							if (P.Dogs_Pos[i]+d<=P.Player_Start_Pos+6) {
								//if possible move and not exceeding final pos
								D_Bs[i].setLayoutX((D_Pos[0][P.Dogs_Pos[i]-1]+30*P.P_E_X)-13);
								D_Bs[i].setLayoutY((D_Pos[1][P.Dogs_Pos[i]-1]+30*P.P_E_Y)-13);
								D_Bs[i].setVisible(true);
								if(k==0)k=i+1;
								else k=-1;
							}
					}
					else {
						//alive and entering before start pos
						D_Bs[i].setLayoutX(D_Pos[0][P.Dogs_Pos[i]]-13);
						D_Bs[i].setLayoutY(D_Pos[1][P.Dogs_Pos[i]]-13);
						D_Bs[i].setVisible(true);
						if(k==0)k=i+1;
						else k=-1;
					}
				}
			}
		}
		//if only 1 button move
		return k;
	}
	public void setD_ButtonsNotVisible(Button[] D_Bs) {
		for(int i=0;i<4;i++) D_Bs[i].setVisible(false);
	}
	
	//Eating Mechanics
	public void eat(int dog) {
		int CP = CURRENT_PLAYER;
		//if the moved dog is in a forbidden pos we do nothing
		if(!isInForbiddenPos(P[CP].Dogs_Pos[dog])) {
			ArrayList<Integer> Z = getSamePos(CP,dog);
		
			if(!Z.isEmpty()) for(int i=0;i<Z.size();i=i+2) {
				int player=Z.get(i);
				int hisDog=Z.get(i+1);
				moveDogToStart(player,hisDog);
				//Playing death sound
				if(!deathSound.isPlaying()) deathSound.play();
				else {deathSound.stop();deathSound.play();};
			}
		}
	}
	public ArrayList<Integer> getSamePos(int CP,int dog) {
		//this function gets an array of dogs who share the same pos as our moved dog
		ArrayList<Integer> Z = new ArrayList<Integer>();
		
		for (int i=0;i<4;i++) if(i!=CP && P[i]!=null) {
				for(int k=0;k<4;k++)
					if(P[CP].Dogs_Pos[dog]==P[i].Dogs_Pos[k] && P[i].Dogs_Alive[k] && isEatable(i,k)) {
						Z.add(i);
						Z.add(k);
				};
			}
		return Z;
	}
	public boolean isEatable(int i,int k) {
		//if the dog entered its false
		//if the dog is entering and above start pos its false
		if(!P[i].Dogs_Entered[k] && P[i].Dogs_Pos[k]>P[i].Player_Start_Pos && P[i].Dogs_Pos[k]<P[i].Player_Start_Pos+6)
				return false;
		return true;
	}
	public boolean isInForbiddenPos(int pos) {
		if (pos == 1 || pos == 14 || pos == 27 || pos == 40)return true;
		return false;
	}

	public void moveDogToStart(int p,int Dog) {
		//putting dog to its start point
		P[p].Dogs[Dog].setLayoutX(P[p].Dogs_Pos_Ininial_Cord[0][Dog]+2);
		P[p].Dogs[Dog].setLayoutY(P[p].Dogs_Pos_Ininial_Cord[1][Dog]+2);
		//dog dead and not entering
		P[p].Dogs_Entering[Dog] = false;
		P[p].Dogs_Alive[Dog] = false;
		//pos back to 0
		P[p].Dogs_Pos[Dog]=0;
	}
	
	//Winning Process
	public boolean isWin(Player P) {
		for(int i=0;i<4;i++) if(!P.Dogs_Entered[i]) return false;
		return true;
	}

	public void winAnimation(int C_P,ImageView Explostion_Sprite,ImageView Star,Label L) {
		Dice_Button.setDisable(true);
		
		 //Sprite Animation
		int x, y;
		if (C_P % 2 == 0) {
			x=0;
			if(C_P<1) y=0;
			else y=2;
		}
		else {
			x=2;
			if(C_P<2) y=0;
			else y=2;
		}
		//setting layout
		Explostion_Sprite.setLayoutX(Starting_P[0][x]-45);
		Explostion_Sprite.setLayoutY(Starting_P[1][y]-43);
		//Animating Explostion_Sprite
		animateSprite(Explostion_Sprite,1,1.3);
		
		//putting the star under the screen
		Star.setLayoutX(50);
		Star.setLayoutY(800);
		//Transition 1:
		//going from under the screen to the middle
		TranslateTransition TT1 = new TranslateTransition();
		TT1.setDuration(Duration.seconds(1));
		TT1.setNode(Star);
		TT1.setToY(-700);
		//going from the middle of the screen to the player Polygon
		TranslateTransition TT2 = new TranslateTransition();
		TT2.setDuration(Duration.seconds(.5));
		TT2.setNode(Star);
		TT2.setToX(Starting_P[0][x]-260);
		TT2.setToY(Starting_P[1][y]-1018);
		//the scaling 
		ScaleTransition ST1 = new ScaleTransition();
		ST1.setDuration(Duration.seconds(.5));
		ST1.setNode(Star);
		ST1.setToX(0.35);
		ST1.setToY(0.35);
		//the rotation
		RotateTransition RT1= new RotateTransition();
		RT1.setDuration(Duration.seconds(.5));
		RT1.setNode(Star);
		 //so if the player is in the left side its -360 and if in the right side its 360
		RT1.setByAngle((C_P % 2==0)?-360:360);

		 //Label
		//setting where to start
		L.setLayoutX(Starting_P[0][x]+13);
		L.setLayoutY(Starting_P[1][y]-15);
		//the translate transition
		TranslateTransition TT3 = new TranslateTransition();
		TT3.setDuration(Duration.seconds(1));
		TT3.setNode(L);
		TT3.setToY(-10);
		//the fade transition
		FadeTransition FT1 = new FadeTransition();
		FT1.setNode(L);
		FT1.setDuration(Duration.seconds(.7));
		FT1.setFromValue(0.0);
		FT1.setToValue(0.95);

		//making the rotation and scaling and transition in parallel
		ParallelTransition PL1 = new ParallelTransition(TT2,ST1,RT1);
		//making the Fade and transition 3 in parallel
		ParallelTransition PL2 = new ParallelTransition(TT3,FT1);
		//to play the first tran then the parallel 
		SequentialTransition SqT1 = new SequentialTransition(TT1,PL1,PL2);
		//Playing the animation
		SqT1.play();
		SqT1.setOnFinished((e)->{
			Dice_Button.setDisable(false);
			//match ended ?
			int k=0;
			//if all players null, means match has ended
			for(int i=0;i<4;i++) {
				if (P[i]==null || P[i].won) k++;
			}
			//ending the match if only 1 player isn't null and won
			if(k==3) matchEnded();
			else changeCP();
		});
	}
	public void animateSprite(ImageView IV,int CycleCount,double Delay) {
		//creating animator
		Animation animator = new SpriteAnimation(
			Duration.millis(1000),
			12, 10,
			0, 0,
			170, 170
			);
		//making sure the image is disabled and visible
		IV.setVisible(true);
		IV.setDisable(true);
		//setting the image to the animator
		((SpriteAnimation)animator).setImageView(IV);
		//setting cycles and delay then playing the animation
		animator.setCycleCount(CycleCount);
		animator.setDelay(Duration.seconds(Delay));
		animator.play();
	}
	public void won() {
		//adding to win string
		WIN_STRING+="\n"+WIN_COUNT+") "+P[CURRENT_PLAYER].Name;
		//adding the star
		ImageView Star = new ImageView(new Image("imgs/star_"+WIN_COUNT+".png"));
		Star.setId("toDelet");
		//adding the sprite explosion
		ImageView Explostion_Sprite = new ImageView(new Image("imgs/Explostion_Sprite.png"));
		Explostion_Sprite.setId("toDelet");
		//making viewpoint for the sprite
		Explostion_Sprite.setViewport(new Rectangle2D(0, 0, 170, 170));
		//creating label with the win count , painting it and giving it an id
		Label L = new Label(""+WIN_COUNT);
		L.setId("toDelet");
		L.getStyleClass().add("Win_count_label");
		L.setTextFill(Paint.valueOf(P[CURRENT_PLAYER].color));
		
		//adding to the game pane, order is important
		Game_APane.getChildren().add(Star);
		Game_APane.getChildren().add(Explostion_Sprite);
		Game_APane.getChildren().add(L);
		
		Star.setLayoutX(50);
		Star.setLayoutY(800);
		winAnimation(CURRENT_PLAYER,Explostion_Sprite,Star,L);
	}
	
	//Match ending
	public void matchEnded() {
		//Alert when the match is finished
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Match Finished !"+WIN_STRING);
		alert.show();
		//playing the applause sound
		applause.play();
		//when the alert is closed we go back to the menu and stop the sound
		alert.setOnCloseRequest((e)->{
			applause.stop();
			Backto_Menu();
			//this loop deletes win count labels, Star images, and explosion spites after the match is finished for optimization
			for(int i=0;i<9;i++) {
				Node N = Game_APane.lookup("#toDelet");
				if (N!=null) {
					N.setId("dead");
					N.setVisible(false);
					N=null;
				}
				else return;
			}
		});
	}

	public ImageView imageView1;
	public ImageView imageView2;
	public ImageView imageView3;
	public ImageView imageView4;
	public ImageView imageView5;



	/**
	 * ----------------  EXPLANATION BELOW ---------------------------------------
	 * <p>
	 * Each Card is assigned the location of the image 'imageDir'.
	 * This is located in the application folder, which has another folder called Cards.
	 * <p>
	 */

	Card aceOfHearts = new Card("application\\Cards\\ace_of_hearts.png",1);
	Card twoOfHearts = new Card("application\\Cards\\2_of_hearts.png",2);
	Card threeOfHearts = new Card("application\\Cards\\3_of_hearts.png",3);
	Card fourOfHearts = new Card("application\\Cards\\4_of_hearts.png",-4);
	Card fiveOfHearts = new Card("application\\Cards\\5_of_hearts.png",5);
	Card sixOfHearts = new Card("application\\Cards\\6_of_hearts.png",6);
	Card sevenOfHearts = new Card("application\\Cards\\7_of_hearts.png",7);
	Card eightOfHearts = new Card("application\\Cards\\8_of_hearts.png",8);
	Card nineOfHearts = new Card("application\\Cards\\9_of_hearts.png",9);
	Card tenOfHearts = new Card("application\\Cards\\10_of_hearts.png",10);
	Card jackOfHearts = new Card("application\\Cards\\jack_of_hearts.png",11);
	Card queenOfHearts = new Card("application\\Cards\\queen_of_hearts.png",12);
	Card kingOfHearts = new Card("application\\Cards\\king_of_hearts.png",13);

	// All Clubs Cards
	Card aceOfClubs = new Card("application\\Cards\\ace_of_clubs.png",1);
	Card twoOfClubs = new Card("application\\Cards\\2_of_clubs.png",2);
	Card threeOfClubs = new Card("application\\Cards\\3_of_clubs.png",3);
	Card fourOfClubs = new Card("application\\Cards\\4_of_clubs.png",-4);
	Card fiveOfClubs = new Card("application\\Cards\\5_of_clubs.png",5);
	Card sixOfClubs = new Card("application\\Cards\\6_of_clubs.png",6);
	Card sevenOfClubs = new Card("application\\Cards\\7_of_clubs.png",7);
	Card eightOfClubs = new Card("application\\Cards\\8_of_clubs.png",8);
	Card nineOfClubs = new Card("application\\Cards\\9_of_clubs.png",9);
	Card tenOfClubs = new Card("application\\Cards\\10_of_clubs.png",10);
	Card jackOfClubs = new Card("application\\Cards\\jack_of_clubs.png",11);
	Card queenOfClubs = new Card("application\\Cards\\queen_of_clubs.png",12);
	Card kingOfClubs = new Card("application\\Cards\\king_of_clubs.png",13);


	// All Spades Cards
	Card aceOfSpades = new Card("application\\Cards\\ace_of_spades.png",1);
	Card twoOfSpades = new Card("application\\Cards\\2_of_spades.png",2);
	Card threeOfSpades = new Card("application\\Cards\\3_of_spades.png",3);
	Card fourOfSpades = new Card("application\\Cards\\4_of_spades.png",-4);
	Card fiveOfSpades = new Card("application\\Cards\\5_of_spades.png",5);
	Card sixOfSpades = new Card("application\\Cards\\6_of_spades.png",6);
	Card sevenOfSpades = new Card("application\\Cards\\7_of_spades.png",7);
	Card eightOfSpades = new Card("application\\Cards\\8_of_spades.png",8);
	Card nineOfSpades = new Card("application\\Cards\\9_of_spades.png",9);
	Card tenOfSpades = new Card("application\\Cards\\10_of_spades.png",10);
	Card jackOfSpades = new Card("application\\Cards\\jack_of_spades.png",11);
	Card queenOfSpades = new Card("application\\Cards\\queen_of_spades.png",12);
	Card kingOfSpades = new Card("application\\Cards\\king_of_spades.png",13);

	// All Diamonds Cards
	Card aceOfDiamonds = new Card("application\\Cards\\ace_of_diamonds.png",1);
	Card twoOfDiamonds = new Card("application\\Cards\\2_of_diamonds.png",2);
	Card threeOfDiamonds = new Card("application\\Cards\\3_of_diamonds.png",3);
	Card fourOfDiamonds = new Card("application\\Cards\\4_of_diamonds.png",-4);
	Card fiveOfDiamonds = new Card("application\\Cards\\5_of_diamonds.png",5);
	Card sixOfDiamonds = new Card("application\\Cards\\6_of_diamonds.png",6);
	Card sevenOfDiamonds = new Card("application\\Cards\\7_of_diamonds.png",7);
	Card eightOfDiamonds = new Card("application\\Cards\\8_of_diamonds.png",8);
	Card nineOfDiamonds = new Card("application\\Cards\\9_of_diamonds.png",9);
	Card tenOfDiamonds = new Card("application\\Cards\\10_of_diamonds.png",10);
	Card jackOfDiamonds = new Card("application\\Cards\\jack_of_diamonds.png",11);
	Card queenOfDiamonds = new Card("application\\Cards\\queen_of_diamonds.png",12);
	Card kingOfDiamonds = new Card("application\\Cards\\king_of_diamonds.png",13);

	/**
	 * This is an array called 'deck'.
	 * This array contains all 52 cards that were created above.
	 * Each line is separated into the different suits.
	 * 1) Hearts
	 * 2) Clubs
	 * 3) Spades
	 * 4) Diamonds
	 */

	Card [] deck =
			{
					aceOfHearts, twoOfHearts, threeOfHearts, fourOfHearts, fiveOfHearts, sixOfHearts, sevenOfHearts, eightOfHearts, nineOfHearts, tenOfHearts, jackOfHearts, kingOfHearts, queenOfHearts,
					aceOfClubs, twoOfClubs, threeOfClubs, fourOfClubs, fiveOfClubs, sixOfClubs, sevenOfClubs, eightOfClubs, nineOfClubs, tenOfClubs, jackOfClubs, kingOfClubs, queenOfClubs,
					aceOfSpades, twoOfSpades, threeOfSpades, fourOfSpades, fiveOfSpades, sixOfSpades, sevenOfSpades, eightOfSpades, nineOfSpades, tenOfSpades, jackOfSpades, kingOfSpades, queenOfSpades,
					aceOfDiamonds, twoOfDiamonds, threeOfDiamonds, fourOfDiamonds, fiveOfDiamonds, sixOfDiamonds, sevenOfDiamonds, eightOfDiamonds, nineOfDiamonds, tenOfDiamonds, jackOfDiamonds, kingOfDiamonds, queenOfDiamonds
			};


	/**
	 *
	 * This method is used to generate a new card upon each click.
	 * It generated a random number between 0 and 52 and makes it
	 * so that you can't have duplicates
	 *
	 */
	public void newCard() {

		Random rand = new Random(); // Generates a new random number using the Random class

		int randomNumber1 = rand.nextInt(51) + 1;
		int randomNumber2 = rand.nextInt(51) + 1;
		int randomNumber3 = rand.nextInt(51) + 1;
		int randomNumber4 = rand.nextInt(51) + 1;
		int randomNumber5 = rand.nextInt(51) + 1;

		if (randomNumber1 == randomNumber2 || randomNumber1 == randomNumber3 || randomNumber1 == randomNumber4 || randomNumber1 == randomNumber5) {
			randomNumber1 = rand.nextInt(51) + 1;
		}

		if (randomNumber2 == randomNumber3 || randomNumber2 == randomNumber4 || randomNumber2 == randomNumber5) {
			randomNumber2 = rand.nextInt(51) + 1;
		}

		if (randomNumber3 == randomNumber4 || randomNumber3 == randomNumber5){
			randomNumber3 = rand.nextInt(51) + 1;
		}
		if (randomNumber4 == randomNumber5){
			randomNumber4 = rand.nextInt(51) + 1;
		}

		Image cardImage1 = new Image(deck[randomNumber1].imageDir);
		Image cardImage2 = new Image(deck[randomNumber2].imageDir);
		Image cardImage3 = new Image(deck[randomNumber3].imageDir);
		Image cardImage4 = new Image(deck[randomNumber4].imageDir);
		Image cardImage5 = new Image(deck[randomNumber5].imageDir);

		imageView1.setImage(cardImage1);
		imageView2.setImage(cardImage2);
		imageView3.setImage(cardImage3);
		imageView4.setImage(cardImage4);
		imageView5.setImage(cardImage5);

		imageView1.setVisible(true);
		imageView2.setVisible(true);
		imageView3.setVisible(true);
		imageView4.setVisible(true);
		imageView5.setVisible(true);
	}

		public void CardUsed1(){
		imageView1.setVisible(false);
	}

	public void CardUsed2(){
		imageView2.setVisible(false);
	}

	public void CardUsed3(){
		imageView3.setVisible(false);
	}

	public void CardUsed4(){
		imageView4.setVisible(false);
	}

	public void CardUsed5(){
		imageView5.setVisible(false);
	}



}