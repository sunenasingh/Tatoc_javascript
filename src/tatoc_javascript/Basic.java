package tatoc_javascript;

import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;

public class Basic {
	JavascriptExecutor js;
	WebDriver driver;

	@BeforeClass
	public void setUp(){
		GeneralActions generalActions= new GeneralActions();
		driver = generalActions.getDriver();
		generalActions.getURL(driver);
		if(driver instanceof JavascriptExecutor){
			js = (JavascriptExecutor)driver;		
		}
	}
	
	@AfterClass
	public void closeUp(){
		driver.close();
	}

	@Test(priority=1)
	public void tatoc_basic1(){
		String title=null;
		//1 Grid Gate
		if(js!=null){
			js.executeScript("document.getElementsByClassName(\"greenbox\")[0].click();");;
			title = (String) js.executeScript("return document.title");
		}
		Assert.assertEquals(title, "Frame Dungeon - Basic Course - T.A.T.O.C");
	}

	@Test(dependsOnMethods= {"tatoc_basic1"})
	public void tatoc_basic2(){
		//2 Frame Dungeon
		String title=null;
		if(js!=null){
			try {
				String color1, color2;
				do{
					color1 = (String) js.executeScript("return document.getElementById(\"main\").contentWindow.document.getElementById(\"answer\").className;");
					color2 = (String) js.executeScript("return document.getElementById(\"main\").contentWindow.document.getElementById(\"child\").contentWindow.document.getElementById(\"answer\").className;");
					if(color1.equals(color2))
						break;
					js.executeScript("$(\"#main\").contents().find(\"a:contains(Repaint Box 2)\").click();");
					Thread.sleep(200);
				}while(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			js.executeScript("$(\"#main\").contents().find(\"a:contains(Proceed)\").click();");
			title = (String) js.executeScript("return document.title");
		}
		Assert.assertEquals(title, "Drag - Basic Course - T.A.T.O.C");
	}

	@Test(dependsOnMethods= {"tatoc_basic2"})
	public void tatoc_basic3(){
		//3 Drag Around
		String title=null;
		if(js!=null){
			js.executeScript("document.getElementById(\"dropbox\").appendChild(document.getElementById(\"dragbox\"));"+
					"var d=document.getElementById(\"dragbox\");"+
					"d.style.position = \"absolute\";"+
					"d.style.left = document.getElementById(\"dropbox\").getBoundingClientRect().left;"+
					"$(\"a:contains(Proceed)\")[0].click();");
			title = (String) js.executeScript("return document.title");
		}
		Assert.assertEquals(title, "Windows - Basic Course - T.A.T.O.C");
	}

	@Test(dependsOnMethods= {"tatoc_basic3"})
	public void tatoc_basic4(){
		//4 Popup Windows 
		String title=null;
		if(js!=null){
			String parent_window = driver.getWindowHandle();
			//driver.findElement(By.partialLinkText("Launch Popup Window")).click();
			js.executeScript("$(\"a:contains(Launch Popup Window)\").click();");
			for(String handle:driver.getWindowHandles()){
				driver.switchTo().window(handle);
			}
			js.executeScript("document.getElementById(\"name\").value=\"sunena\";"+
					"document.getElementById(\"submit\").click();");
			driver.switchTo().window(parent_window);
			js.executeScript("$(\"a:contains(Proceed)\").click();");
			title = (String) js.executeScript("return document.title");
		}
		Assert.assertEquals(title, "Cookie Handling - Basic Course - T.A.T.O.C");
	}

	@Test(dependsOnMethods= {"tatoc_basic4"})
	public void tatoc_basic5(){
		//5 Cookie Handling
		String title=null;
		if(js!=null){
			js.executeScript("$(\"a:contains(Generate Token)\").click();"+
					"var token = document.getElementById(\"token\").innerText.split(\": \")[1];"+
					"\"Token=\"+token;"+
					"document.cookie=\"Token=\"+token;"+
					"$(\"a:contains(Proceed)\").click();");
			title = (String) js.executeScript("return document.title");
		}
		Assert.assertEquals(title, "End - T.A.T.O.C");
	}
}
