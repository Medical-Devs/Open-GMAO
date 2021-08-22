package OptionXLogin;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class animefx extends Application implements EventHandler<KeyEvent> 
{
	@Override
	public void start(Stage primeStage) throws Exception 
	{
		final int width = 686;
		final int height = 406;
		Pane root = new Pane();
		TranslateTransition trans = new TranslateTransition();
		TranslateTransition trans2 = new TranslateTransition();
		TranslateTransition trans3 = new TranslateTransition();
		TranslateTransition trans4 = new TranslateTransition();
		TranslateTransition trans5 = new TranslateTransition();
		
		trans.setDuration(Duration.seconds(1.6));
		trans.setToY(-226);
		final Rectangle rect1 = new Rectangle(0, 226, 171, 1080);
		rect1.setFill(Color.rgb(37, 61, 97));
		rect1.setArcHeight(50);
		rect1.setArcWidth(50);
		trans.setAutoReverse(true);
		trans.setCycleCount(2);
		trans.setNode(rect1);
		
		trans2.setDuration(Duration.seconds(2));
		trans2.setToY(-170);
		final Rectangle rect2 = new Rectangle(171, 170, 171, 1080);
		rect2.setFill(Color.rgb(38, 71, 120));
		rect2.setArcHeight(50);
		rect2.setArcWidth(50);
		trans2.setAutoReverse(true);
		trans2.setCycleCount(2);
		trans2.setNode(rect2);
		
		trans3.setDuration(Duration.seconds(2.2));
		trans3.setToY(-99);
		final Rectangle rect3 = new Rectangle(342, 99, 171, 1080);
		rect3.setFill(Color.rgb(53, 98, 164));
		rect3.setArcHeight(50);
		rect3.setArcWidth(50);
		trans3.setAutoReverse(true);
		trans3.setCycleCount(2);
		trans3.setNode(rect3);
		
		trans4.setDuration(Duration.seconds(2.4));
		trans4.setToY(-35);
		final Rectangle rect4 = new Rectangle(513, 35, 171, 1080);
		rect4.setFill(Color.rgb(58, 112, 190));
		rect4.setArcHeight(50);
		rect4.setArcWidth(50);
		trans4.setAutoReverse(true);
		trans4.setCycleCount(2);
		trans4.setNode(rect4);
		
		Label bienLbl = new Label("Bienvenue");
		bienLbl.setLayoutX(283);
		bienLbl.setLayoutY(-67);
		bienLbl.setStyle("<font-weight>: BOLD");
		bienLbl.setFont(new Font(22));
		bienLbl.setTextFill(Color.rgb(255, 255, 255));
		bienLbl.setTextAlignment(TextAlignment.CENTER);
		trans5.setDuration(Duration.seconds(5));
		trans5.setToY(100);
		trans5.setNode(bienLbl);
		
		trans.play();
		trans2.play();
		trans3.play();
		trans4.play();
		trans5.play();
		
		root.getChildren().add(rect1);
		root.getChildren().add(rect2);
		root.getChildren().add(rect3);
		root.getChildren().add(rect4);
		root.getChildren().add(bienLbl);
		root.setBackground(new Background(new BackgroundFill(Color.rgb(85, 122, 177), null, null)));
		
		primeStage.setX(1366/2 - width/2 + 10);
		primeStage.setY(766/2 - height/2 + 10);

		Scene sc = new Scene(root, width, height);
		sc.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			@Override
			public void handle(KeyEvent arg0) 
			{
				if(arg0.getCode() == KeyCode.ENTER) 
				{
					try 
					{
						selectionLogin sl = new selectionLogin();
						sl.setVisible(true);
						primeStage.close();
					} 
					catch(Exception e) 
					{
						e.printStackTrace();
					}	
				}
			}
		});
		primeStage.setTitle("SoftGmao");
		primeStage.setScene(sc);
		primeStage.setResizable(false);
		primeStage.getIcons().add(new Image("/Icons/frame.png"));
		primeStage.show();
	}
	
	public static void main(String[] args) throws Exception 
	{
		launch(args);
	}

	@Override
	public void handle(KeyEvent arg0) {
		
	}
}