package findinghospitals.utilities;

public class DataProv {

public static Object[][] testData(String excelPath, String sheetName) {
		
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);
		
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		
		Object data[][] = new Object[rowCount-2][colCount];
		
		for(int i=2; i<rowCount; i++) {
			for(int j=0; j<colCount; j++) {
				
				String cellData = excel.getCellData(i, j);
				
				data[i-2][j] = cellData;
				
			}
			
		}
		return data;
		
	}
}
