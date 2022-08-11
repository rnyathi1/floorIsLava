package entity;

import com.company.GamePanel;
import com.company.Keyhandler;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    Keyhandler handler;

    public Player(GamePanel gp, Keyhandler handler){
        this.gp = gp;
        this.handler = handler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(handler.upPressed == true || handler.downPressed == true || handler.rightPressed == true || handler.leftPressed == true){
            if (handler.upPressed == true && y > 1){
                direction = "up";
                y -= speed;
            }
            if (handler.downPressed == true && y < 430){
                direction = "down";
                y += speed;
            }
            if (handler.rightPressed == true && x < 430){
                direction = "right";
                x += speed;
            }
            if (handler.leftPressed == true && x > 0){
                direction = "left";
                x -= speed;
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2){


        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }

                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;


        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }


}
