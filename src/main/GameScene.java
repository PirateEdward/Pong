package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import e.Game;
import e.Resources;
import e.Scene;
import e.Window;
import e.graphics.Shader;
import e.graphics.gui.Color;
import e.graphics.gui.Font;
import e.graphics.gui.Text;
import e.math.Vector3f;
import g.entities.Background;
import g.entities.Bar;
import g.entities.BarLeft;
import g.entities.BarLeftAI;
import g.entities.BarRight;
import g.entities.MiddleLine;
import g.entities.RandomBall;

public class GameScene extends Scene {
	
	public int scoreLeft, scoreRight;
	public Background background;
	public Bar left, right;
	public RandomBall ball;
	public MiddleLine line;
	public Shader textShader;
	
	public Text textLeft, textRight, startText, winText, pauseText;
	
	private boolean started, paused;
	private boolean singleplayer;

	public GameScene(int id, boolean singleplayer) {
		super(id);
		
		this.singleplayer = singleplayer;
		
		started = false;
		paused = false;
		
		textShader = Resources.getShader("text");
		
		line = new MiddleLine(new Vector3f(0.0f, 0.0f, 0.05f));
		
		textLeft = new Text("0", new Font("default_font", 1.0f, new Color(1.0f, 0.8f, 0.1f, 1.0f)));
		textRight = new Text("0", new Font("default_font", 1.0f, new Color(1.0f, 0.8f, 0.1f, 1.0f)));
		startText = new Text("Press space to start!", new Font("default_font", 0.5f, new Color(1.0f, 0.8f, 0.1f, 1.0f)));
		winText = new Text("", new Font("default_font", 1.0f, new Color(1.0f, 0.2f, 0.1f, 1.0f)));
		pauseText = new Text("Paused", new Font("default_font", 2.0f, new Color(1.0f, 0.8f, 0.1f, 1.0f)));
		
		//reset();
	}

	public void reset(){
		if(started)
			winText.setText(((scoreLeft > scoreRight) ? "Left" : "Right") + " side wins!");
		else 
			winText.setText("");
		ball = new RandomBall(new Vector3f());
		if(!paused) started = false;
		right = new BarRight(new Vector3f(Window.right - 1.0f, 0.0f, 0.0f));
		if(!singleplayer)
			left = new BarLeft(new Vector3f(Window.left + 0.5f, 0.0f, 0.0f));
		else
			left = new BarLeftAI(new Vector3f(Window.left + 0.5f, 0.0f, 0.0f), ball);
		background = new Background(ball);
		ball.setEntityCollision(left, right);
		
		textRight.setText(String.valueOf(scoreRight));
		textLeft.setText(String.valueOf(scoreLeft));
	}

	@Override
	public void update(Game game) {
		if(!paused){
			ball.update();
			line.update();
			if(started){
				left.update();
				right.update();
				if(ball.getRight() < left.getLeft()){
					ball.reset();
					scoreRight++;
					textRight.setText(String.valueOf(scoreRight));
				}
				else if(ball.getLeft() > right.getRight()){
					ball.reset();
					scoreLeft++;
					textLeft.setText(String.valueOf(scoreLeft));
				}
		
				if(scoreRight > 9){
					System.out.println("Right side wins!");
					reset();
				}
				else if(scoreLeft > 9){
					System.out.println("Left side wins!");
					reset();
				}
			}
		}
	}

	@Override
	public void render() {
		//ball.render();
		left.render();
		right.render();
		background.render();
		line.render();
		
		renderText();
	}
	
	public void renderText(){
		textLeft.render(textShader, new Vector3f(-8.0f, Window.top - 0.5f, 0.1f));
		textRight.render(textShader, new Vector3f(8.0f, Window.top - 0.5f, 0.1f));
		if(!started && !paused){
			startText.render(textShader, new Vector3f(0.0f, 0.0f, 0.1f));
			winText.render(textShader, new Vector3f(0.0f, 4.5f, 0.1f));
		}
		if(paused){
			pauseText.render(textShader, new Vector3f(0.0f, 0.0f, 0.1f));
		}
		checkError();
	}
	
	public void checkError(){
		int i = glGetError();
		if(i != GL_NO_ERROR) System.out.println(i);
	}

	@Override
	public void onSelect() {
		if(!paused) started = false;
		if(!started && !paused) reset();
	}

	@Override
	public void isKeyReleased(Game game, int key, int action) {
		if(action == GLFW_RELEASE){
			if(!started){
				if(key == GLFW_KEY_SPACE || key == GLFW_KEY_ENTER){
					started = true;
					scoreLeft = scoreRight = 0;
					textRight.setText(String.valueOf(scoreRight));
					textLeft.setText(String.valueOf(scoreLeft));
				}
			}
			else{
				if(key == GLFW_KEY_P){
					paused = !paused;
				}
			}
			if(!started || paused){
				if(key == GLFW_KEY_ESCAPE)
					game.setCurrent(Pong.menu);
			}
		}
	}

}
