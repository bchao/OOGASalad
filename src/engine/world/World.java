package engine.world;

import java.util.ArrayList;
import java.util.List;

import engine.GameObject;
import engine.gridobject.GridObject;

public abstract class World {
	
	private int myNumTileWidth;
	private int myNumTileHeight;
	private int myTileSize;
	private Tile[][] myTileMatrix;
	private List<GridObject> myGridObjectList;
	private String myBackground;

	/**
	 * Instantiates a new World.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public World(int tileSize, String background) {
		myTileSize=tileSize;
		myGridObjectList = new ArrayList<GridObject>();
		myBackground = background;
//		Image myBackground = new ScaledImage(width, height,myBackground).scaleImage();
	}
	
	public void setDimensions(int width, int height){
		myNumTileWidth = width/myTileSize;
		myNumTileHeight = height/myTileSize;
		makeTileMatrix();
	}
	
	public int getTileSize(){
		return myTileSize;
	}
	
	public int getTileGridHeight() {
		return myNumTileHeight;
	}
	
	public int getTileGridWidth() {
		return myNumTileWidth;
	}
	
	public String getBackgroundString() {
		return myBackground;

	}

	/**
	 * Make empty matrix of tiles.
	 * 
	 * @return the tile matrix
	 */
	public Tile[][] makeTileMatrix() {
		System.out.println(myNumTileWidth + " " + myNumTileHeight);
		Tile[][] tileMatrix = new Tile[myNumTileWidth][myNumTileHeight];
		for (int i = 0; i < myNumTileWidth; i++) {
			for (int j = 0; j < myNumTileHeight; j++) {
				tileMatrix[i][j] = new Tile(myTileSize,i*myTileSize,j*myTileSize);
			}
		}
		
		myTileMatrix = tileMatrix;
		
		return tileMatrix;
	}

	public List<GridObject> getGridObjectList(){
		return myGridObjectList;
	}
	
	public void setTileObject(GridObject obj, int xTile, int yTile) {
		myTileMatrix[xTile][yTile].setTileObject(obj);
		myGridObjectList.add(obj);
	}

//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2d = (Graphics2D) g;
//		setOpaque(false);
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				RenderingHints.VALUE_ANTIALIAS_ON);
//		
//		int height = myNumTileHeight * myTileSize;
//		int width = myNumTileWidth * myTileSize;
//		Image background = new ScaledImage(width, height,myBackground).scaleImage();
//		g2d.drawImage(background, 0, 0,null);
//		
//		for(int i=0; i<myGridObjectList.size(); i++){
//			myGridObjectList.get(i).paint(g2d);
//		}
//	}

	
	
	public void setViewSize(){
		
	}

}
