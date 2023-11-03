package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelWork {

	
	FileInputStream fis;
	FileOutputStream fos;
	Workbook book;
	Sheet sh;
	Row row;
	Cell cell;
	public ExcelWork(String sheet) {
		
		try {
			fis=new FileInputStream("C:\\Users\\PraneethReddyKatamre\\Desktop\\Java Workspace\\newProject\\src\\com\\Files\\hello.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
		 book=WorkbookFactory.create(fis);
			 sh=book.getSheet(sheet);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public int getLastRowNumber() {
		
		int noOfRows = sh.getLastRowNum();
		return noOfRows;
	}
	/**
	 * Getting the last cell number of a row
	 * 
	 * @param sheetName
	 * @param Rowindex
	 * @return noOfCells
	 */
	public int getLastCellNumber(int Rowindex) {
		
		row = sh.getRow(Rowindex);
		int noOfCells = row.getLastCellNum();
		return noOfCells;
	}	
	public String getData( int rowIndex, int cellIndex) {
	
		row = sh.getRow(rowIndex);
		cell = row.getCell(cellIndex);
		//String data = cell.getStringCellValue();

		DataFormatter formatData=new DataFormatter();
		String data=formatData.formatCellValue(cell);
		
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
	public void setData( int rowIndex, int cellIndex, String data)  {
		
		row = sh.createRow(rowIndex);
		cell = row.createCell(cellIndex);
		cell.setCellValue(data);
	}	
public void setDatas( int rowIndex, int cellIndex, String data) {
		
		row = sh.getRow(rowIndex);
		cell = row.createCell(cellIndex);
		cell.setCellValue(data);
	}
	public void writeData() {
		try {
			fos=new FileOutputStream("C:\\Users\\PraneethReddyKatamre\\Desktop\\Java Workspace\\newProject\\src\\com\\Files\\hello.xlsx");
			try {
				book.write(fos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				book.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
