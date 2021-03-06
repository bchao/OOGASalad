package authoring.gameObjects;

import java.util.List;

import util.Constants;

/**
 * Class that holds data relevant to a door object
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class DoorData extends GridObjectData {


	private int toX;
	private int toY;
	private String toMap;

	public DoorData(int x, int y, int width, int height, String image, int toX, int toY, String toMap) {
		super(x, y, width, height, image, toX, toY, toMap, Constants.DOOR);
		this.toX=toX;
		this.toY=toY;
		this.toMap=toMap;
		setHeight(height);
		setWidth(width);
	}
	
	public int getToX(){
		return toX;
	}
	public int getToY(){
		return toY;
	}
	public String toMap(){
		return toMap;
	}

}
