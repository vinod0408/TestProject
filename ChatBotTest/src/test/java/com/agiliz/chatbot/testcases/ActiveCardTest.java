package com.agiliz.chatbot.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.agiliz.chatbot.pages.ActiveCardPage;

public class ActiveCardTest extends ActiveCardPage {

	Logger log = Logger.getLogger(ActiveCardTest.class);
	static String sheetName = prop.getProperty("activateCard");

	@BeforeClass
	public void classPreRequisite(){
		excelRead.loadSheet(sheetName);
		excelRead.loadData();
	}

	@Test(priority=1)
	public void clickActiveCard() throws Exception{
		clkActiveBtn();
		Thread.sleep(2000);
	}

	@Test(priority=2)
	public void verifyActiveCardTitle(){
		titleActiveDisplay();
	}

	@Test(priority=3)
	public void actCreditCardMsg() throws Exception{
		vSend(excelRead.getMessage("actcard_input_cc"),excelRead.getExpTxt("actcard_input_cc"));
	}

	@Test(priority=4)
	public void actCreditSuc1Msg() throws Exception{
		vSend(excelRead.getMessage("actcard_input_suc1"),excelRead.getExpTxt("actcard_input_suc1"));
	}


	@Test(priority=5)
	public void actCreditSuc2Msg() throws Exception{
		vSend(excelRead.getMessage("actcard_input_suc2"),excelRead.getExpTxt("actcard_input_suc2"));
	}
	@Test(priority=6)
	public void actCreditSuc3Msg() throws Exception {	
		vSend(excelRead.getMessage("actcard_input_suc3"),excelRead.getExpTxt("actcard_input_suc3"));
	}

	@Test(priority=7)
	public void actCreditYsMsg() throws Exception {	
		vSend(excelRead.getMessage("actcard_input_yes"),excelRead.getExpTxt("actcard_input_yes"));
	}

	@Test(priority=8)
	public void actCreditMailMsg() throws Exception {	
		vSend(excelRead.getMessage("actcard_input_mail"),excelRead.getExpTxt("actcard_input_mail"));
	}

}
