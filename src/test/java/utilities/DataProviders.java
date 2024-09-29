package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProviders 1
	
	@DataProvider(name="LoginData") //this name should different from one data provider to another data provider
	public String[][] getData() throws IOException{
		
		String path=".\\testData\\Opencart_LoginData.xlsx"; //taking xl file from testData 
		
		ExcelUtility xlutil = new ExcelUtility(path); //creating an object for XLUtility
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcols]; //created for two dimensional array which can store 
		
		for (int i=1; i<=totalrows; i++) { //read the data from xl file storing in two dimensional array
			
			for (int j=0; j<totalcols; j++) {
				
				//array index start from zero(00 position, so taking i-1 instead of logindata row index of [i]
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); //1, 0
			}
			
		}
		return logindata; //returning two dimensional array
	}
	
	//if need more data providers you should write below
	
	//DataProvider2 
	
	//DataProvider2

}
