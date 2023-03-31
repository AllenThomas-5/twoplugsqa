package com.testplugs.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ScreenshotClass {

	public static void takeScreenshot(WebDriver driver, String screenshotName, ExtentTest test) throws IOException {
		 File source_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 File destination_file = new File(System.getProperty("user.dir") + "/Screenshots/" + screenshotName + System.currentTimeMillis() + ".png");
		// FileHandler.copy(file,new File(System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png"));
		 FileUtils.copyFile(source_file, destination_file);
		 String absolutePath = destination_file.getAbsolutePath();
		 test.addScreenCaptureFromPath(absolutePath);
	}
}
