package engine.state;

import java.awt.event.KeyEvent;

import GameView.GameFrame;
import engine.Control;
import engine.gridobject.person.Player;
import engine.menu.managers.MenuManager;

public class LoadState extends AbstractState {

	private Player myPlayer;
	private MenuManager myMenuManager;
	private StringBuffer buffer = new StringBuffer();
	private String displayString;
	private String loadFile;

	public LoadState(Player p, MenuManager mm) {
		super();
		myPlayer = p;
		myMenuManager = mm;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (loadFile == null) {
			if (e.getKeyCode() == Control.ESC) {
				myPlayer.setState(new MenuState(myPlayer, myMenuManager));
				myPlayer.setInteractionBox(myMenuManager);
				
			} else if (e.getKeyCode() != Control.ENTER
					&& e.getKeyCode() != Control.SHIFT) {
				buffer.append(e.getKeyChar());
				displayString = new String(buffer.toString());
				
			} else if (e.getKeyCode() == Control.ENTER) {
				loadFile = buffer.toString();
				load(loadFile);
				
			}
		} else {
			myPlayer.setState(new MenuState(myPlayer, myMenuManager));
			myPlayer.setInteractionBox(myMenuManager);
		}

	}

	public String getDisplayString() {
		if (loadFile != null) {
			displayString = "Load Complete!";
		}
		return displayString;
	}

	public void load(String loadFile) {
//		DataManager dm = new DataManager();
//		WorldData loadedData = dm.loadWorldDataFromFile(loadFile + ".json");
		GameFrame gm = new GameFrame();
		gm.initialize(loadFile);
	}

}
