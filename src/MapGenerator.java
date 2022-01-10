import java.awt.*;
import java.awt.font.GraphicAttribute;

public class MapGenerator {
    public  int[][] map;
    public  int bHeight;
    public int bWidth;
    public MapGenerator(int row, int col){
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        bWidth = 540/col;
        bHeight = 150/row;
    }
    public void draw(Graphics2D g){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] > 0){
                    g.setColor(Color.RED);
                    g.fillRect(j*bWidth+80,i*bHeight+50,bWidth,bHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j*bWidth+80,i*bHeight+50,bWidth,bHeight);
                }
            }
        }
    }

    public  void  setBrick(int val, int row, int col){
        map[row][col] = val;
    }
}
