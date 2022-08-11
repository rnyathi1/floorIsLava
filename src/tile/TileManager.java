package tile;

import com.company.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TileManager {
    Random rand;
    boolean flag = true;
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];
    int time = 0;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[2];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();

        loadMap("/maps/map01.txt");

    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
    } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap(String filepath) {
        try{
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col< gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col=0;
                    row++;
                }

            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col< gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y +=gp.tileSize;
            }
        }



            Timer timer = new Timer();
            TimerTask timeT = new TimerTask() {
                int secondsPassed = 0;
                @Override
                public void run() {

                    secondsPassed++;
                    time = secondsPassed;
                    if(time % 3 == 0){
                        rand = new Random();
                        int tempCol = rand.nextInt(10);
                        int tempRow = rand.nextInt(10);
                        mapTileNum[tempCol][tempRow] = 1;
                    }
                    if(secondsPassed >= 21){
                        timer.cancel();
                        timer.purge();
                        //time = 0;
                        //secondsPassed = 0;
                        flag = true;

                    }
                }
            };

            if(time == 0 && flag){
                flag = false;
                timer.scheduleAtFixedRate(timeT,1000,1000);
                System.out.println("test1");

            }

        g2.drawString(String.valueOf(time), 0, 12);
    }
}
