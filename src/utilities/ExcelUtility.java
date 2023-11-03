package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public FileInputStream file;
	public FileOutputStream fileoutput;
	private XSSFWorkbook workbook;
	public XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	public int noOfRows;
	public int noOfCells;
	
	//String filePath="./src/com/Files/Database.xlsx";

	/**
	 * Constructor to intialize the Excel file and to load workbook
	 */
	public ExcelUtility() {
		try {
			file = new FileInputStream("./src/com/Files/Database.xlsx");
			workbook = new XSSFWorkbook(file);
//			Workbook book= WorkbookFactory.create(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * Retriving the last row number of sheet
	 * 
	 * @param sheetName
	 * @return noOfRows
	 */
	public int getLastRowNumber(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		int noOfRows = sheet.getLastRowNum();
		return noOfRows;
	}
	/**
	 * Getting the last cell number of a row
	 * 
	 * @param sheetName
	 * @param Rowindex
	 * @return noOfCells
	 */
	public int getLastCellNumber(String sheetName, int Rowindex) {
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(Rowindex);
		int noOfCells = row.getLastCellNum();
		return noOfCells;
	}

	/**
	 * Reading the data from Excel sheet
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @return data
	 */
	public String getData(String sheetName, int rowIndex, int cellIndex) {
		try {
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		row = sheet.getRow(rowIndex);
		cell = row.getCell(cellIndex);
		String data = cell.getStringCellValue();

		return data;
	}

	/**
	 * Writing the data to excel sheet
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @param data
	 * @throws IOException
	 */
	public void setData(String sheetName, int rowIndex, int cellIndex, String data) throws IOException {
		sheet = workbook.getSheet(sheetName);
		row = sheet.createRow(rowIndex);
		cell = row.createCell(cellIndex);
		cell.setCellValue(data);
	}
	/**
	 * Output stream used to write the data to excel
	 * 
	 * @param destExcelFile
	 * @throws IOException
	 */
	
	public void setDatas(String sheetName, int rowIndex, int cellIndex, String data) throws IOException {
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowIndex);
		cell = row.createCell(cellIndex);
		cell.setCellValue(data);
	}
	
	
	
	
	public void Excelwrite() throws IOException {
		fileoutput = new FileOutputStream("./src/com/Files/Database.xlsx");
		workbook.write(fileoutput);
        fileoutput.close();
	}
	
}
