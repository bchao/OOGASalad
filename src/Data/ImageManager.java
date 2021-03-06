package Data;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class handles the loading and usage of images, specifically for the authoring environment so that
 * users can load in their own images and use them for specific objects or tiles. 
 * @author Davis Treybig
 *
 */
public class ImageManager extends AbstractFileManager {
	public static final String[] VALID_IMAGE_EXTENSIONS={".jpg", ".gif", ".JPG", ".GIF", ".png", ".PNG"};
	public static final String DEFAULT_IMAGE_EXTENSION="png";
	public static final String[] IMAGE_FOLDER_OPTIONS={"gridobject", "TileImage"};
	public static final String PLAYER_IMAGE_FOLDER="src/PlayerImages/";
	
	public ImageManager() {	
		super(VALID_IMAGE_EXTENSIONS, DEFAULT_IMAGE_EXTENSION);
	}
	/**
	 * Returns a list of currently saved images
	 */
	public List<ImageFile> getSavedImageMap(){
		List<ImageFile> imageList=new ArrayList<ImageFile>();
		for(String s: IMAGE_FOLDER_OPTIONS){
			for(String image: getSavedImageList(SRC+s+"/")){
				ImageFile imageFile=new ImageFile(image, s);
				imageList.add(imageFile);
			}
		}
		return imageList;
	}
	/**
	 * Loads a specified image file into the project directory to be used in the authoring environment
	 * @param fileName Name of the image file that will be created in the project directory (without an extension)
	 * @param imageFile File that is to be copied into the project directory
	 * @param imageType indicates what the image will be used for (and thus what folder it will be saved in)
	 * @throws IOException
	 * @return Returns the new File stored in the project
	 */
	public ImageFile storeScaledImage(String filename, Image image, String imageType) throws IOException{
		String newParentPath=SRC+imageType+"/";
		String fullName=filename+"."+DEFAULT_FILE_EXTENSION;
		String fullPath=newParentPath+fullName;
		
		File newImageFile=new File(fullPath);
		if(checkFileExtension(getFileExtension(newImageFile))){
			File parentFolderFile=new File(newParentPath);
			parentFolderFile.mkdirs();

			RenderedImage rendered=imageToRenderedImage(image);
			ImageIO.write(rendered, DEFAULT_FILE_EXTENSION, newImageFile);
			return new ImageFile(fullName, imageType);
		}
		return null;
	}
	/**
	 * Converts an image to a RenderedImage. Used to save the image to file. 
	 * @param image Image to be saved
	 * @return RenderedImage of the input image. 
	 */
	private RenderedImage imageToRenderedImage(Image image){
		BufferedImage bImage= new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bImageGraphics = bImage.createGraphics();
		bImageGraphics.drawImage(image, 0, 0, null);
		return (RenderedImage)bImage;
	}

	/**
	 * Loads the image corresponding to the file name within the default Image resource package. 
	 * @param s String name of the image file
	 * @param imageType Type of the image which is to be loaded. Includes gridObject or tileImage
	 * @return File s with the path of the image
	 */
	private ImageFile loadImage(String name, String imageType){
		return new ImageFile(name, imageType);
	}
	/**
	 * Loads the image corresponding to the file name within the default Image resource package. 
	 * @param s String name of the image file
	 * @return File s with the path of the image
	 */
	public ImageFile loadGridObjectImage(String name){
		return loadImage(name, IMAGE_FOLDER_OPTIONS[0]);
	}
	/**
	 * Loads the tile image with the given string name, ie "TestImage.jpg" 
	 * @param s String name of the image file
	 * @return ImageFile ImageFile of the given image
	 */
	public ImageFile loadTileImage(String name){
		return loadImage(name, IMAGE_FOLDER_OPTIONS[1]);
	}
	/**
	 * Loads the player images with the given string name, ie "Ash" 
	 * @param s String name of the image file
	 * @return ImageFile ImageFile of the given image
	 */
	public ImageFile loadPlayerImage(String name){
		FileLister f=new FileLister();
		List<String> playerImageFolders=f.getFileList(PLAYER_IMAGE_FOLDER);
		for(String s: playerImageFolders){
			if(f.getFileList(PLAYER_IMAGE_FOLDER+s).contains(name)){
				return new ImageFile(name, PLAYER_IMAGE_FOLDER+s+"/"+name);
			}
		}
		return null;
	}
	/**
	 * Returns a list of all the names of currently saved images in the default image
	 * folder. 
	 * @return List of names of images
	 */
	private List<String> getSavedImageList(String path){
		return new FileLister().getFileList(path);
	}
}
