package authoring;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SaveGridObjectFeature extends Feature{
	private JButton createButton;
	private GridObjectCreation mySuperFeature;
	private int x;
	private int y;
	public SaveGridObjectFeature(GridObjectCreation gridObjectCreation){
		mySuperFeature = gridObjectCreation;
		createButton = new JButton("Create GridObject!");
		createButton.addActionListener(new CreateGridObjectListener());
		myComponents.put(createButton, BorderLayout.SOUTH);
	}
	private class CreateGridObjectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			x=((GridObjectCoordinateFeature)(mySuperFeature.getFeature("GridObjectCoordinateFeature"))).getX();
			y=((GridObjectCoordinateFeature)(mySuperFeature.getFeature("GridObjectCoordinateFeature"))).getY();
			mySuperFeature.getData().setX(x);
			mySuperFeature.getData().setY(y);
			mySuperFeature.getData().setSteppable(((SteppableFeature)(mySuperFeature.getFeature("SteppableFeature"))).isSteppable());
			mySuperFeature.getData().setImageName(((GridObjectImageFeature)(mySuperFeature.getFeature("GridObjectImageFeature"))).getImageName());
			mySuperFeature.getData().setTalkable(((TalkableFeature)(mySuperFeature.getFeature("TalkableFeature"))).isTalkable());
			mySuperFeature.getData().setWidth(((WidthHeightFeature)(mySuperFeature.getFeature("WidthHeightFeature"))).getWidth());
			mySuperFeature.getData().setHeight(((WidthHeightFeature)(mySuperFeature.getFeature("WidthHeightFeature"))).getHeight());
			mySuperFeature.getData().setDialogue(((DialogueFeature)(mySuperFeature.getFeature("DialogueFeature"))).getDialogue());
			
			if(mySuperFeature.getData().isDefined()){
				mySuperFeature.getData().init();
				FeatureManager.getWorldData().getMap(WorldData.DEFAULT_MAP).getTileData(x,y).addGridObjectData(mySuperFeature.getData());
				((GridViewerFeature)FeatureManager.getFeature("authoring.GridViewerFeature")).tileRepaint();
				//mySuperFeature.getView().close();
			}
		}
	}
}
