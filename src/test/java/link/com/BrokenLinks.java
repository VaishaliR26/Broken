package link.com;


import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {

	public static void main(String[] args) throws IOException   {
		
		WebDriver driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://www.amazon.in/");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		 System.out.println( "Total links: "+links.size());
		 
		 int validlinkcount=0;
		 
		 int brokenlinkcount=0;
		 
		for(WebElement link:links) {
			
		
		String url=	link.getAttribute("href");
		 
		URL u= new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) u.openConnection();
		
		int code = con.getResponseCode();
		
		String msg = con.getResponseMessage();
		
		if(code==200) {
			
			System.out.println("Valid Link--->"+code+"----->"+msg);
			
			validlinkcount++;
		}
		
		else {
			
			System.out.println("Broken Link---->"+code+"---->"+msg);
			
			brokenlinkcount++;
		}
		 
		 System.out.println("Validlinkcount: "+validlinkcount);
		 
		 System.out.println("Brokenlinkcount: "+brokenlinkcount);
		}

	}

}
