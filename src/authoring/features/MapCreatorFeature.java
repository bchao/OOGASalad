package authoring.features;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



/**
 * Class that handles the naming and addition of a new map
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class MapCreatorFeature extends Feature {

	private JButton gridMakerButton;
	private GridViewerFeature gridView;

	public MapCreatorFeature() {
		gridMakerButton = new JButton("Add a New Map");
		gridMakerButton.addActionListener(new GridMakerListener());
		myComponents.put(gridMakerButton, BorderLayout.SOUTH);
		gridView = (GridViewerFeature) FeatureManager.getFeature("GridViewerFeature");
	}

	public class GridMakerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String mapName = JOptionPane.showInputDialog("Name your map:");
			if(mapName.equals("")){
				JOptionPane.showMessageDialog(null, "Must name map. Please try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
				return;				
			}
			gridView.addMap(mapName);
		}
	}
}
