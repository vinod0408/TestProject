package commonFunctions;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import driver.Driver;


public class FrameworkFunctions extends Driver{
	
	// Get current Date and append to file name
	public static Calendar calDate = Calendar.getInstance();
	public static Date currentDate = calDate.getTime();
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MMM_dd_HH_mm_ss");
	public static String dateNow = formatter.format(currentDate.getTime());
	//System.out.println("Date Is :" + dateNow);

	public static void reportGeneration(String testSuiteName, String testCaseName, String result) {
		try {

			String fileName = TestReportsPath+"\\TestResult_" + dateNow + ".xlsx";

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Automation Result");

			// First Row
			XSSFRow FirstRow = sheet.createRow(0);
			XSSFCell FirstRowcell = FirstRow.createCell(0);
			FirstRowcell.setCellValue("Automation Result Summary");

			// Second Row
			XSSFRow secondRow = sheet.createRow(1);
			XSSFCell secondRowcell = secondRow.createCell(0);
			secondRowcell.setCellValue("Browser Name :"+browserName);

			// ThirdRow
			XSSFRow thirdRow = sheet.createRow(2);
			XSSFCell thirdRowcell = thirdRow.createCell(0);
			thirdRowcell.setCellValue("Date and Time:" + dateNow);

			// Fifth Row (Result Header Row)
			XSSFRow headerRow = sheet.createRow(4);

			XSSFCell headerRowcellOne = headerRow.createCell(0);
			headerRowcellOne.setCellValue("TestSuitName");

			XSSFCell headerRowcellTwo = headerRow.createCell(1);
			headerRowcellTwo.setCellValue("TestCaseName");

			XSSFCell headerRowcellThree = headerRow.createCell(2);
			headerRowcellThree.setCellValue("Result(Pass/Fail)");

			// Result Writing to Excel file

			XSSFRow rowForData = sheet.createRow(sheet.getLastRowNum() + 1);
			XSSFCell rowCellOne = rowForData.createCell(0);
			rowCellOne.setCellValue(testSuiteName);

			XSSFCell rowCellTwo = rowForData.createCell(1);
			rowCellTwo.setCellValue(testCaseName);

			XSSFCell rowCellThree = rowForData.createCell(2);
			rowCellThree.setCellValue(result);

			File newFile = new File(fileName);
			if (!newFile.exists()) {
				FileOutputStream fileOut = new FileOutputStream(fileName);
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
			} else {
				FileInputStream Newinput = new FileInputStream(fileName);
				XSSFWorkbook NewWb = new XSSFWorkbook(Newinput);
				XSSFSheet NewSheet = NewWb.getSheet("Automation Result");
				System.out.println("NewSheet" + NewSheet);

				int numberOfRows = NewSheet.getLastRowNum();
				System.out.println("numberOfRows " + numberOfRows);

				XSSFRow NewRow = NewSheet.createRow(numberOfRows + 1);

				XSSFCell cellNew1 = NewRow.createCell(0);
				cellNew1.setCellValue(testSuiteName);

				XSSFCell cellNew2 = NewRow.createCell(1);
				cellNew2.setCellValue(testCaseName);

				XSSFCell cellNew3 = NewRow.createCell(2);
				cellNew3.setCellValue(result);

				FileOutputStream fileOut = new FileOutputStream(fileName);
				NewWb.write(fileOut);
				fileOut.flush();
				fileOut.close();
				

			}

		} catch (Exception e) {

		}
		
		
	}


	
	
	public static void screenShot(String fileName) throws IOException, HeadlessException, AWTException
	{
		
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", new File(AppScrinShotsPath+"\\"+fileName+"_"+dateNow+".png"));
		
	}

}
