import java.util.HashMap;
import javax.imageio.*;
import java.awt.*;
import java.io.*;

public class AssetLoader {

    public static HashMap<String, Image> loadedImages = new HashMap<String, Image>();


    public static void LoadImage(String name, String path)
    {
        try
		{
			Image image = ImageIO.read(new File(path));
            loadedImages.put(name, image);
		}
		catch(Exception e)	{
            System.out.println("Image loading failed on "+path);
        }
    }

    public static void MassLoad(String[] names, String[] paths)
    {
        for (int i = 0; i < names.length; i++)
        {
            LoadImage(names[i], paths[i]);
        }
    }
}
