import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.net.*;
import java.io.*;
 

public class earthScraper {
	
	public static final String USER_AGENT = "<Your User Agent Here>";
	private static final String folderPath = "<Your Folder Path Here>";
	
	public static void main(String[] args) throws InterruptedException {
		String url = linkReader();
		
		System.out.println("");
		File dir = new File(folderPath + "/");
		System.out.println("Path: " + dir.getAbsolutePath());
		System.out.println("");

		try {
			Document doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(0).get();

		    // get the page title 
		    String title = doc.title();
		    System.out.println("Scraping from " + title);
		    System.out.println("");
		    
		    // target image elements -- /r/earthPorn images are stored as href
		    Elements imageLinks = doc.select("img");
		    
		    for(Element e: imageLinks){
		    	String src = e.absUrl("src");
			    imageToFolder(src, folderPath);
		    }
		} catch (IOException e) {
			System.out.println("Error occurred!");
		}
		    // get the source url for each image found
	}
	
	public static String linkReader(){
		System.out.println("Enter the website URL: ");
		Scanner reader = new Scanner(System.in);
		String link = reader.nextLine();
		reader.close();
		
		return link;
	}
	
	// input/outputstreams to create new file in directory
	public static void imageToFolder(String src, String folderPath) throws IOException, InterruptedException{
		String imageName = src.substring(src.lastIndexOf("/") + 1);
		System.out.println("Saving: " + imageName + ", from: " + src);
		
		URLConnection connection = (new URL(src)).openConnection();

		// decrease requests 
		Thread.sleep(2000);
		connection.setRequestProperty("User-Agent", USER_AGENT);

		InputStream input = connection.getInputStream();
		OutputStream output = new BufferedOutputStream(new FileOutputStream(folderPath + "/" + imageName));
		
		byte[] b = new byte[2048];
		int length;
		
		while ((length = input.read(b)) != -1){
			output.write(b, 0, length);
		}
		output.close();
		input.close();
	}
}