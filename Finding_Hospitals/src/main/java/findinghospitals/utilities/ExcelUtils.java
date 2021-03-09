package findinghospitals.utilities;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFWorkbook workbook2;
	static XSSFSheet sheet2;
	static FileOutputStream outFile;
	
	public static void writeToExcel(ArrayList<String> ...list) {
		
		
		try {
			outFile = new FileOutputStream(System.getProperty("user.dir") + "\\ExcelOutput\\"
					+ "ExcelReport_" + System.currentTimeMillis() + ".xlsx");
			workbook2 = new XSSFWorkbook();

			// Create first/desired sheet from the workbook
			sheet2 = workbook2.createSheet("Test Report");
			
			
			
				
			  for (ArrayList<String> i: list) {
				  int lastRowNumber=sheet2.getLastRowNum();
				  if(lastRowNumber!=0)
					  lastRowNumber+=2;		
				  for(int j=lastRowNumber;j<i.size()+lastRowNumber;j++) {
						Row r = sheet2.createRow(j);
						r.createCell(0).setCellValue(i.get(j-lastRowNumber));
				  
		    } 

		
		}
			  workbook2.write(outFile);
				workbook2.close(); // closing the workbook
				outFile.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public ExcelUtils(String excelPath, String sheetName) {
		try {
			String path=System.getProperty("user.dir")+"\\TestData\\"+excelPath+".xlsx";
		workbook = new XSSFWorkbook(path);
		sheet = workbook.getSheet(sheetName);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static int getRowCount() {
		int rowCount=0;
		try {
			
			rowCount = sheet.getPhysicalNumberOfRows();
			

		}catch(Exception exp) {
			System.out.println(exp.getMessage());;
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return rowCount;

	}
	
	public static int getColCount() {
		int colCount=0;
		try {
			
			colCount = sheet.getRow(1).getPhysicalNumberOfCells();
		

		}catch(Exception exp) {
			System.out.println(exp.getMessage());;
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return colCount;

	}
	

	public static String getCellData(int rowNum, int colNum) {
		try {
			
			Cell cell = sheet.getRow(rowNum).getCell(colNum);
			CellType type = cell.getCellType();
			if (type ==CellType.STRING) {
				String values =cell.getStringCellValue();
				if(values.equalsIgnoreCase("null"))
			              return "";
				else
					return values;
			}
			else if (type==CellType.NUMERIC || type==CellType.FORMULA) {

				String cellText = String.valueOf((long)cell.getNumericCellValue());
				return cellText;
			} else if (type ==CellType.BLANK)
				return "";
			else if(type ==CellType.BOOLEAN)
				return String.valueOf(cell.getBooleanCellValue());
			else
				return String.valueOf(cell.getStringCellValue());
				
		

		}catch(Exception exp) {
			System.out.println(exp.getMessage());;
			System.out.println(exp.getCause());
			exp.printStackTrace();
			return null;
		}

	}
	
	
	public static void getCellDataNumber(int rowNum, int colNum) {
		try {
			
			double cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			System.out.println(cellData);

		}catch(Exception exp) {
			System.out.println(exp.getMessage());;
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
	
	

}
