import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private  int score = 0;
    private int totalBricks = 21;
    private  Timer time;
    private int delay = 8;
    private int playerX = 310;
    private int ballX = 120;
    private int ballY = 350;
    private int dirXBall = -1;
    private int dirYball = -2;
    private  MapGenerator map;
    public GamePlay(){
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay,this);
        time.start();
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.PINK);
        g.fillRect(1,1,692,592);
        //drawing map
        map.draw((Graphics2D) g);
        //score
        g.setColor(Color.BLUE);
        g.setFont(new Font("serif", Font.BOLD,25));
        g.drawString(""+score,590,30);
        //borders
        g.setColor(Color.GREEN);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        //paddle
        g.setColor(Color.cyan);
        g.fillRect(playerX,550,100,8);
        //the ball
        g.setColor(Color.black);
        g.fillOval(ballX,ballY,20,20);
        if(totalBricks <= 0){
            play = false;
            ballX =0; ballY = 0;
            g.setColor(Color.BLUE);
            g.setFont(new Font("serif", Font.BOLD,30));
            g.drawString("You Won!!!",260,300);

            g.setFont(new Font("serif", Font.BOLD,20));
            g.drawString("Press Enter to start"+score,230,350);
        }
        if(ballY > 570){
            play = false;
            ballX =0; ballY = 0;
            g.setColor(Color.BLUE);
            g.setFont(new Font("serif", Font.BOLD,30));
            g.drawString("Game Over"+score,190,300);

            g.setFont(new Font("serif", Font.BOLD,20));
            g.drawString("Press Enter to start"+score,230,350);
        }
        g.dispose();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if (play){
            if( new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                dirYball = -dirYball;
            }
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if(map.map[i][j] >= 0){
                        int brickX = j * map.bWidth + 80;
                        int brickY = i* map.bHeight+50;
                        int brickWidth = map.bWidth;
                        int brickHeight = map.bHeight;
                        Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle balRec = new Rectangle(ballX,ballY,20,20);
                        Rectangle brickRect = rect;
                        if(balRec.intersects(brickRect)){
                            map.setBrick(0,i,j);
                            totalBricks --;
                            score += 5;
                            if(ballX + 19 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width){
                                dirXBall = -dirXBall;
                            }else {
                                dirYball = -dirYball;
                            }
                            break ;
                        }
                    }
                }
                
            }
            ballX += dirXBall;
            ballY += dirYball;
            if(ballX < 0){
                dirXBall = -dirXBall;
            }
            if(ballY < 0){
                dirYball = -dirYball;
            }
            if(ballX > 670){
                dirXBall = -dirXBall;
            }

        }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (playerX >= 600){
                playerX = 600;
            }else {
                moverRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if (playerX <= 10){
                playerX = 10;
            }else {
                moverLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play =true;
                ballX = 120;
                ballY = 350;
                dirXBall= -1;
                dirYball = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3,7);
                repaint();
            }
        }

    }
    //move method
    public void moverRight(){
        play = true;
        playerX += 20;
    }
    public void moverLeft(){
        play = true;
        playerX -= 20;
    }


}
