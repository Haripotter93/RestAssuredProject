package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;

	String path;

	public XLUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String xlSheet) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);

		int rowCount = ws.getLastRowNum();

		wb.close();
		fi.close();

		return rowCount;
	}

	public int getCellCount(String xlSheet, int rowNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);

		int cellCount = row.getLastCellNum();

		wb.close();
		fi.close();

		return cellCount;
	}

	public String getCellData(String xlSheet, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);

		String data;

		try {
			switch (cell.getCellType()) {

			case STRING:
				data = cell.getStringCellValue();
				break;

			case NUMERIC:
				data = String.valueOf((long) cell.getNumericCellValue());
				break;

			case BOOLEAN:
				data = String.valueOf(cell.getBooleanCellValue());
				break;

			default:
				data = "";
				break;
			}
		} catch (Exception e) {
			data = "";
		}

		wb.close();
		fi.close();

		return data;
	}

	public void setCellData(String xlSheet, int rowNum, int colNum, String data) throws IOException {

		File xlFile = new File(path);

		fi = new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);

		if (wb.getSheetIndex(xlSheet) == -1)
			wb.createSheet(xlSheet);

		ws = wb.getSheet(xlSheet);

		if (ws.getRow(rowNum) == null)
			ws.createRow(rowNum);

		row = ws.getRow(rowNum);

		cell = row.getCell(colNum);

		if (cell == null)
			cell = row.createCell(colNum);

		cell.setCellValue(data);

		fo = new FileOutputStream(xlFile);
		wb.write(fo);

		wb.close();
		fi.close();
		fo.close();
	}

	public void fillGreenColor(String xlSheet, int rowNum, int colNum) {
		// Optional: Implement if result coloring is needed
	}

	public void fillRedColor(String xlSheet, int rowNum, int colNum) {
		// Optional: Implement if result coloring is needed
	}
}