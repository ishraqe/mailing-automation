package TestWithNg;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Yahoo {
	 WebDriver driver=new ChromeDriver();
	 String Baseurl="https://www.yahoo.com/";
	 String email="your mail";
	 String password="your password";
	 String mailTo="exmple@gmail.com",subject="Something as you like",message="Few lines about your plan for next week";
	 String fileUrl="";
	 
	 
	 
	 
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		Yahoo check=new Yahoo();
		check.openConnection();
		check.action();
		check.close();
	
		
	
		
		
	}
	public void  openConnection() {
		driver.get(Baseurl);
		
	}
	
	public  boolean signIn() throws InterruptedException {
		waitFor("//input[contains(@placeholder,'Enter your email')]");
		driver.findElement(By.xpath("//input[contains(@placeholder,'Enter your email')]")).clear();
		driver.findElement(By.xpath("//input[contains(@placeholder,'Enter your email')]")).sendKeys(email);
		
		waitFor("//button[contains(@name,'signin')]");
		
		driver.findElement(By.xpath("//button[contains(@name,'signin')]")).click();
		
		Thread.sleep(5000);
		
		
		waitFor("//input[contains(@placeholder,'Password')]");
		driver.findElement(By.xpath("//input[contains(@placeholder,'Password')]")).clear();
		driver.findElement(By.xpath("//input[contains(@placeholder,'Password')]")).sendKeys(password);
		
		driver.findElement(By.xpath(".//*[@id='login-signin']")).click();
		
		
		String error=	driver.findElement(By.xpath(".//*[@id='mbr-login-error']")).toString();
		String errorMessage="Sorry, we";
		boolean  Errorexists = error.startsWith(errorMessage);
		
		return Errorexists;
	}
	
	public void action() throws InterruptedException, AWTException {
		driver.findElement(By.id("uh-mail-link")).click();
		
		if (signIn()){
			System.out.println("Not logged in");
		}
		
		System.out.println("Logged in");
		waitFor(" //button[contains(@class,'btn-compose')]");
		Thread.sleep(300);
		mail();
		
		Thread.sleep(3000);
	
		//Verify Email sent successful message
		Thread.sleep(3000); 
		
		if(!driver.switchTo().activeElement().isDisplayed()){
			System.out.println(driver.switchTo().alert().getText());
			driver.switchTo().alert().dismiss();
		}
		System.out.println("Mail sent");
		
	
		
	}
	
	private void waitFor(String xpath){
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	private void mail() throws InterruptedException, AWTException{
		driver.findElement(By.xpath("//button[contains(@class,'btn-compose')]")).click();
		
		waitFor("//input[contains(@id,'to-field')]");
		driver.findElement(By.xpath("//input[contains(@id,'to-field')]")).clear();
		driver.findElement(By.xpath("//input[contains(@id,'to-field')]")).sendKeys(mailTo);
		
		waitFor("//input[contains(@placeholder,'Subject')]");
		driver.findElement(By.xpath("//input[contains(@placeholder,'Subject')]")).clear();
		driver.findElement(By.xpath("//input[contains(@placeholder,'Subject')]")).sendKeys(subject);
		
		waitFor("//div[contains(@class,'cm-rtetext')]");
		driver.findElement(By.xpath("//div[contains(@class,'cm-rtetext')]")).clear();
		driver.findElement(By.xpath("//div[contains(@class,'cm-rtetext')]")).sendKeys(message);
		StringSelection filelocation = new StringSelection("C:\\Users\\ish\\Downloads\\shazam_by_danieldahl-d4s0r21.jpg");
		attachFile(filelocation); //attach file
		
	}
	private void attachFile(Transferable file) throws InterruptedException, AWTException{
		

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file,null);
		
		waitFor("//span[contains(@id,'attach-btn-menu')]");
		driver.findElement(By.xpath("//span[contains(@id,'attach-btn-menu')]")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("scroll(0,350)");
		
		
		
		Thread.sleep(5000);
		System.out.println(driver.findElement(By.linkText("Attach files from computer")).getText());
		driver.findElement(By.linkText("Attach files from computer")).click();
		
		Robot robot = new Robot();
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		//Click Send button
		driver.findElement(By.linkText("Send")).click();
	}
	

	public void close() {
		driver.close();
	}

}

