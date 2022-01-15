
import java.awt.event.KeyEvent;

public class LPiece extends Tetromino{
	int nextRotatePhase=1;
	
	public LPiece(int x, int y) {
		super(x,y);
		for(int i=0;i<4;i++) {
			switch(i) {
				case(0):Tile one=new Tile(x,y,2);
						tetromino.add(one);
				case(1):Tile two=new Tile(x-30,y,2);
						tetromino.add(two);
				case(2):Tile three=new Tile(x+30,y,2);
						tetromino.add(three);
				case(3):Tile four=new Tile(x-30,30+y,2);
						tetromino.add(four);
			}
			
		}
	}
	
	public void moveDown() {
		if(lowestY()<=600&&isLocked==false) {
			for(int i=0;i<4;i++) {
				Tile temp=tetromino.get(i);
				temp.moveDown();
				//tetromino.get(i).draw(g2d);
			}
			y+=30;
		}
	}
	
	public int lowestY() {
		int lowestY=0;
		for(int i=0;i<4;i++) {
			if(tetromino.get(i).y>lowestY) {
				lowestY=tetromino.get(i).y;
			}
		}
		return lowestY;
	}
	
	public int leftX() {
		int leftestX=700;
		for(int i=0;i<4;i++) {
			if(tetromino.get(i).x<leftestX) {
				leftestX=tetromino.get(i).x;
			}
		}
		return leftestX;
	}
	
	public int rightX() {
		int RightestX=0;
		for(int i=0;i<4;i++) {
			if(tetromino.get(i).x>RightestX) {
				RightestX=tetromino.get(i).x;
			}
		}
		return RightestX;
	}
	public boolean checkCollisionLeft() {
        boolean collided=false;
        int hit=0;
        int hitX,hitY;
        
        if (leftX()<=59) {
            collided=true;
        } else {
            for(int i=0;i<4;i++) {
                for(int k=0;k<GameFrame.tiles.size();k++) {
                    if(tetromino.get(i).x-30==GameFrame.tiles.get(k).x&&tetromino.get(i).y==GameFrame.tiles.get(k).y) {
                        hit++;
                        hitX=tetromino.get(i).x-30;
                        hitY=tetromino.get(i).y;
                        for(int j=0;j<4;j++) {
                        	if(hitX==tetromino.get(j).x&&hitY==tetromino.get(j).y) {
                        		hit--;
                        	}
                        }
                    }
                }
            }
            if(hit>0) {
            	collided=true;
            }
        }
        return collided;
    }
	
	public boolean checkCollisionRight() {
        boolean collided=false;
        int hit=0;
        int hitX,hitY;
        
        if(rightX()>=279) {
            collided=true;
        } else {
            for(int i=0;i<4;i++) {
                for(int k=0;k<GameFrame.tiles.size();k++) {
                    if(tetromino.get(i).x+30==GameFrame.tiles.get(k).x&&tetromino.get(i).y==GameFrame.tiles.get(k).y) {
                        hit++;
                        hitX=tetromino.get(i).x+30;
                        hitY=tetromino.get(i).y;
                        for(int j=0;j<4;j++) {
                        	if(hitX==tetromino.get(j).x&&hitY==tetromino.get(j).y) {
                        		hit--;
                        	}
                        }
                    }
                }
            }
            if(hit>0) {
            	collided=true;
            }
        }
        return collided;
    }
	
	
	public void lock() {
        int hit=0,hitX,hitY;
        if(lowestY()>=600) {
            isLocked=true;
        } else {
        for(int i=0;i<4;i++) {
            Tile temp=tetromino.get(i)	;
                for(int k=0;k<GameFrame.tiles.size();k++) {
                    if(temp.y+30==(GameFrame.tiles.get(k).y)&&temp.x==GameFrame.tiles.get(k).x) {
                        hit++;
                        hitX=temp.x;
                        hitY=temp.y+30;
                        for(int j=0;j<4;j++) {
                        	if(hitY==tetromino.get(j).y&&hitX==tetromino.get(j).x) {
                        		hit--;
                        	}
                        }
                    }
                }
                if(hit>0) {
                	isLocked=true;
                }
            }
        }
    }
	/*public Image getTileImg(){
        ImageIcon ic=new ImageIcon("C:\\Users\\rober_000\\Pictures\\block.png");
        return ic.getImage();
	}*/
	
	/*public void draw(Graphics2D g2d){
		for(int i=0;i<4;i++) {
			Tile temp=tetromino.get(i);
			g2d.drawImage(temp.getTileImg(),temp.x,temp.y,null);
		}

	}*/
	
	public boolean checkCollisionRotate(int x, int y) {
		boolean collide=false;
		int hit=0,hitX,hitY;
		if(x<0||x>271) {
			collide=true;
		} else {
			for(int k=0;k<GameFrame.tiles.size();k++) {
				if(x==GameFrame.tiles.get(k).x&&y==GameFrame.tiles.get(k).y) {
					hit++;
					hitX=GameFrame.tiles.get(k).x;
					hitY=GameFrame.tiles.get(k).y;
					 for(int j=0;j<4;j++) {
	                 	if(hitY==tetromino.get(j).y&&hitX==tetromino.get(j).x) {
	                 		hit--;
	                 	}
	                 }
				}
			}
			 if(hit>0) {
				 collide=true;
			 }
		}
		return collide;
	}
	
	public void rotate() {
		int newX1,newY1,newX2,newY2,newX3,newY3;
		if(nextRotatePhase>=4) {
			nextRotatePhase-=4;
		}
		switch(nextRotatePhase) {
		case(0):newX1=tetromino.get(1).x-30;
				newY1=tetromino.get(1).y+30;
				newX2=tetromino.get(2).x+30;
				newY2=tetromino.get(2).y-30;
				newX3=tetromino.get(3).x;
				newY3=tetromino.get(3).y+60;
				if(checkCollisionRotate(newX1,newY1)==false&&checkCollisionRotate(newX2,newY2)==false&&checkCollisionRotate(newX3,newY3)==false) {
					tetromino.get(1).x-=30;
					tetromino.get(1).y+=30;
					tetromino.get(2).x+=30;
					tetromino.get(2).y-=30;
					tetromino.get(3).y+=60;
					nextRotatePhase++;
				}
				break;
		case(1):newX1=tetromino.get(1).x+30;
				newY1=tetromino.get(1).y+30;
				newX2=tetromino.get(2).x-30;
				newY2=tetromino.get(2).y-30;
				newX3=tetromino.get(3).x+60;
				newY3=tetromino.get(3).y;
				if(checkCollisionRotate(newX1,newY1)==false&&checkCollisionRotate(newX2,newY2)==false&&checkCollisionRotate(newX3,newY3)==false) {
					tetromino.get(1).x+=30;
					tetromino.get(1).y+=30;
					tetromino.get(2).x-=30;
					tetromino.get(2).y-=30;
					tetromino.get(3).x+=60;
					nextRotatePhase++;
				}
				break;
		case(2):newX1=tetromino.get(1).x+30;
				newY1=tetromino.get(1).y-30;
				newX2=tetromino.get(2).x-30;
				newY2=tetromino.get(2).y+30;
				newX3=tetromino.get(3).x;
				newY3=tetromino.get(3).y-60;
				if(checkCollisionRotate(newX1,newY1)==false&&checkCollisionRotate(newX2,newY2)==false&&checkCollisionRotate(newX3,newY3)==false) {
					tetromino.get(1).x+=30;
					tetromino.get(1).y-=30;
					tetromino.get(2).x-=30;
					tetromino.get(2).y+=30;
					tetromino.get(3).y-=60;
					nextRotatePhase++;
				}
				break;
		case(3):newX1=tetromino.get(1).x-30;
				newY1=tetromino.get(1).y-30;
				newX2=tetromino.get(2).x+30;
				newY2=tetromino.get(2).y+30;
				newX3=tetromino.get(3).x-30;
				newY3=tetromino.get(3).y;
				if(checkCollisionRotate(newX1,newY1)==false&&checkCollisionRotate(newX2,newY2)==false&&checkCollisionRotate(newX3,newY3)==false) {
					tetromino.get(1).x-=30;
					tetromino.get(1).y-=30;
					tetromino.get(2).x+=30;
					tetromino.get(2).y+=30;
					tetromino.get(3).x-=60;
					nextRotatePhase++;
				}
				break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();
		 if (key == KeyEvent.VK_A&&isLocked==false){
			if ((checkCollisionLeft())==false) {
				 for(int i=0;i<4;i++) {
					Tile temp=tetromino.get(i);
					temp.moveLeft();
				}
				x-=30;
			} 
		 }
		
		 if (key == KeyEvent.VK_D&&isLocked==false){ 
			 if((checkCollisionRight())==false) {
			 	for(int i=0;i<4;i++) {
			 		Tile temp=tetromino.get(i);
			 		temp.moveRight();
			 	}
			 	x+=30;
			 }
		 }
		 
		 if (key == KeyEvent.VK_S&&isLocked==false){
			 lock();
				if (isLocked==false) {
					 for(int i=0;i<4;i++) {
						Tile temp=tetromino.get(i);
						temp.moveDown();
					}
					y+=30;
				} 
				
		}
		 
		if (key == KeyEvent.VK_R&&isLocked==false){
			rotate();
		}
	
	
	}
}
