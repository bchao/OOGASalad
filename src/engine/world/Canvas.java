package engine.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import engine.AbstractGameState;
import engine.WalkAroundState;
import engine.gridobject.GridObject;
import engine.images.ScaledImage;

public class Canvas extends JPanel{

	private JFrame myFrame;
	private int myHeight;
	private int myWidth;
	private World myWorld;
	private int myWorldHeight;
	private int myWorldWidth;
	private int offsetMinX = 0;
	private int offsetMinY=0;



	/**
	 * Instantiates a new canvas.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public Canvas(int width, int height){
		JFrame frame = new JFrame("Pokemon");
		myFrame = frame;
		myHeight=height;
		myWidth=width;
//		frame.setJMenuBar(makeMenuBar());
		frame.setSize((int) width, (int) height);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.requestFocus();
	}
	
	private JMenuBar makeMenuBar() {
		JMenuBar menu = new JMenuBar();
		menu.add(makeFileMenu());
		return menu;
	}
	
	@SuppressWarnings("serial")
	private JMenu makeFileMenu() {
		JMenu file = new JMenu("File");
		file.add(new AbstractAction("Load") {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		file.add(new AbstractAction("Save") {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		file.add(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		return file;
	}


	public void setWorld(World world){
		myFrame.add(this);
		myFrame.addKeyListener(new WalkAroundState(this, world));
		myWorld = world;
		myWorldHeight = myWorld.getTileGridHeight() * myWorld.getTileSize();
		myWorldWidth = myWorld.getTileGridWidth() * myWorld.getTileSize();
	}

	public void setState(AbstractGameState state){
		myFrame.addKeyListener(state);
	}

	public int getHeight(){
		return myHeight;
	}

	public int getWidth(){
		return myWidth;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		setOpaque(false);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int height = myWorld.getTileGridHeight() * myWorld.getTileSize();
		int width = myWorld.getTileGridWidth() * myWorld.getTileSize();
		
//		Image background = new ScaledImage(width, height, myWorld.getBackgroundString()).scaleImage();
//  		g2d.drawImage(background, 0, 0,null);
		

		
		// paints objects on tiles

		//Image background = new ScaledImage(width, height, myWorld.getBackgroundString()).scaleImage();
//		getCameraOffset();
		//g2d.drawImage(background, -getCameraOffset()[0], -getCameraOffset()[1],null);
		
		// paints background of each tile
	
		for (int i = 0; i < myWorld.getTileGridWidth(); i++) {
			for (int j = 0; j < myWorld.getTileGridHeight(); j++) {
				if (myWorld.getPlayer()!=null && tileIsInView(myWorld.getTileMatrix()[i][j], getCameraOffset()[0], getCameraOffset()[1]))
				myWorld.getTileMatrix()[i][j].paint(g2d, getCameraOffset()[0], getCameraOffset()[1]);
			}
		}
		
		for(int i=0; i<myWorld.getGridObjectList().size(); i++) {
			if(isInView(myWorld.getGridObjectList().get(i),getCameraOffset()[0],getCameraOffset()[1])){
				myWorld.getGridObjectList().get(i).paint(g2d,getCameraOffset()[0], getCameraOffset()[1]);
				myWorld.getGridObjectList().get(i).paintDialoge(g2d, myWidth, myHeight, getCameraOffset()[0], getCameraOffset()[1]);
			}
		}

		
		
	}

	public int[] getCameraOffset(){
		int offsetMaxX = myWorldWidth-myWidth;
		int offsetMaxY = myWorldHeight-myHeight;
		System.out.println(myWorld.getPlayer().getX());
		int cameraX = myWorld.getPlayer().getX() - myWidth /2;
		int cameraY = myWorld.getPlayer().getY() - myHeight /2;
		if (cameraX > offsetMaxX)
			cameraX = offsetMaxX;
		else if (cameraX < offsetMinX)
			cameraX = offsetMinX;
		if (cameraY>offsetMaxY)
			cameraY = offsetMaxY;
		else if(cameraY<offsetMinY)
			cameraY=offsetMinY;
		return new int[] {cameraX, cameraY};
	}
	
	public boolean isInView(GridObject go, int cameraX, int cameraY){
		return (go.getBounds().getMaxX()>cameraX && go.getBounds().getMaxY()>cameraY
				&& go.getBounds().getMinX()<(cameraX+myWidth) && go.getBounds().getMinY()<(cameraY + myHeight));
	}
	
	public boolean tileIsInView(Tile go, int cameraX, int cameraY){
		return (go.getBounds().getMaxX()>cameraX && go.getBounds().getMaxY()>cameraY
				&& go.getBounds().getMinX()<(cameraX+myWidth) && go.getBounds().getMinY()<(cameraY + myHeight));
	}
}
