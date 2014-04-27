package authoring;
import java.util.*;

public class Main {
	public static void main(String[] args){
		List<Feature> myFeatures = new ArrayList<Feature>();
		myFeatures.add(new GridViewerFeature());
        myFeatures.add(new SaveGameFeature());
		myFeatures.add(new MapCreatorFeature());
		myFeatures.add(new PrimaryMapChooserFeature());
		myFeatures.add(new AddMusicFeature());
        myFeatures.add(new ImageChooser());
        myFeatures.add(new AddGameComponents());
		
		AuthoringView view = new AuthoringView(myFeatures, true);
	}
}