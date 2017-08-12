import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.net.*;
import java.awt.Desktop;
import java.io.*;
 

public class earthScraper {
	
	private static final String USER_AGENT = "";
	private static String folderPath = "";
	private static Scanner reader;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		//set the path for storing files
		
		reader = new Scanner(System.in);
		System.out.println("Enter path of directory for file storage: ");
		String folderPath = reader.nextLine();
		
		// scrape from this page
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
		    
		    // target image elements
		    Elements imageLinks = doc.select("a[href]");
		    
		    // get the source url for each image found
		    for(Element e: imageLinks){
		    	String link = e.attr("href");
			    if (isImage(link)){ 
	                System.out.println("Link of Image: " + e.attr("href"));
	                imageToFolder(link, folderPath);
			    }
		    }
		} catch (IOException e){
			e.printStackTrace();
		}
	    Desktop.getDesktop().open(new File(folderPath));
	}
	
	public static String linkReader(){
		System.out.println("Enter the website URL: ");
		Scanner scan = new Scanner(System.in);
		String link = scan.nextLine();
		scan.close();
		
		return link;
	}
	
	public static String pathReader(){
		System.out.println("Enter the path for storing files: ");
		Scanner reader = new Scanner(System.in);
		String path = reader.nextLine();
		reader.close();
		
		return path;
	}
	
	// input/outputstreams to create new file in directory
	public static void imageToFolder(String src, String folderPath) throws IOException, InterruptedException{
		String imageName = src.substring(src.lastIndexOf("/") + 1);
		System.out.println("Saving: " + imageName + ", from: " + src);
		
		URLConnection connection = (new URL(src)).openConnection();
		
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
	
	// check if file is image through extensions
	public static boolean isImage(String http){
	    if (http.contains(".png") || http.contains("gfycat") || http.contains(".jpg") || http.contains(".jpeg") || http.contains(".gif")){
	        return true;
	    }
	    else{
	        return false;
	    }
	}
}