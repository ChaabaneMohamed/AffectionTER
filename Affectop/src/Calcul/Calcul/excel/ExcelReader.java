package Calcul.Calcul.excel;

import java.util.List;
import java.util.Map.Entry;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.ArrayList;

import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import Calcul.Calcul.base.BaseWriter;
import Calcul.Calcul.bean.Student;
import Calcul.Calcul.exceptions.TokenLengthException;
import Calcul.Calcul.exceptions.UnexpectedFileException;
import Calcul.Calcul.exceptions.cellNameException;
import Calcul.Calcul.token.Token;

public class ExcelReader implements ExcelAdapter {
	public class Position {
		private int line;
		private int column;

		public Position(int l, int c) {
			this.line = l;
			this.column = c;
		}

		public int getLine() {
			return line;
		}

		public int getColumn() {
			return column;
		}
	}

	@Override
	public void request(String path) {
		BaseWriter bw = new BaseWriter();

		Workbook wb = fileReader(path);
		Position p = getPosition("A2");
		ArrayList<String> tokenlist;
		TreeMap<String, ArrayList<Cell>> res = tabularReader(wb, wb.getSheetAt(0).getRow(p.line).getCell(p.column), 8);
		try {
			tokenlist = Token.generateTokenList(20, res.size());
		} catch (TokenLengthException e1) {
			e1.printStackTrace();
			return;
		}
		int cpt = 0;
		for (Entry<String, ArrayList<Cell>> e : res.entrySet()) {
			

			String token = tokenlist.get(cpt);
			String lastname = e.getValue().get(0).getStringCellValue();
			String firstname = e.getValue().get(1).getStringCellValue();
			String numetudiant = e.getKey();

			String mail = e.getValue().get(6).getStringCellValue();
			String step = e.getValue().get(2).getStringCellValue();
			Cell c = e.getValue().get(5);
			
			int year = c.getCellType() == CellType.NUMERIC ? (int) c.getNumericCellValue() : Integer.parseInt(c.getStringCellValue());
			System.out.println(token + " " + lastname + " " + firstname + " " + numetudiant + " " + mail + " " + step
					+ " " + year);
			cpt++;
			bw.writeStudent(year, lastname, firstname, numetudiant, mail,token, step);

		}
	}

	/**
	 * Open a file with .xlsx and return a workbook
	 *
	 */
	// TODO replace the filestream by a file, increase speed recommended by poi doc
	private Workbook fileReader(String path) {
		Workbook wb = null;

		try (InputStream inp = new FileInputStream(path)) {

			if (path.endsWith("xlsx")) {
				wb = XSSFWorkbookFactory.create(inp);
			} else if (path.endsWith("xls")) {
				wb = HSSFWorkbookFactory.create(inp);
			} else {
				throw new UnexpectedFileException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * Read a tabular which start at the specified cell and read until it reach a
	 * null row, width specifies the width of the tabular The format is key value
	 * value ... key value value ...
	 * 
	 * @version 2.0
	 */
	public static TreeMap<String, ArrayList<Cell>> tabularReader(Workbook wb, Cell cell, int width) {

		TreeMap<String, ArrayList<Cell>> m = new TreeMap<>();
		Row row = cell.getRow();
		Sheet sheet = cell.getSheet();
		int tabular_row_start = cell.getRowIndex(), tabular_column_start = cell.getColumnIndex();

		int r = 0;

		while (row.getCell(tabular_column_start) != null
				|| row.getCell(tabular_column_start).getStringCellValue() != null
				|| !row.getCell(tabular_column_start).getStringCellValue().equals("")) {

			row = sheet.getRow(tabular_row_start + r);

			if (row == null) {
				System.out.println(tabular_row_start + r);
				System.out.println("row null " + r);
				break;
			}
			if (row.getZeroHeight()) {
				r++;
				continue;
			}
			ArrayList<Cell> line = new ArrayList<>();
			int key = 2;
			// lecture de la ligne
			for (int i = 0; i < width; i++) {
				if (i == key)
					continue;// on utilise le num étudiant en tant que clé pour le dictionnaire
				line.add(row.getCell(tabular_column_start + i));
			}
			Cell c = row.getCell(key);
			String value = c.getCellType() == CellType.NUMERIC ? "" + (int) c.getNumericCellValue()
					: c.getStringCellValue();
			m.putIfAbsent(value, line);
			r++;
		}
		return m;
	}

	/**
	 * Read a tabular which start at the specified cell and read until it reach a
	 * null row, width specifies the width of the tabular The format is key value
	 * value ... key value value ...
	 */
	public static TreeMap<String, ArrayList<Cell>> tabularReader2(Workbook wb, Cell cell, int width) {

		TreeMap<String, ArrayList<Cell>> m = new TreeMap<>();
		Row row = cell.getRow();
		Sheet sheet = cell.getSheet();
		int tabular_row_start = cell.getRowIndex(), tabular_column_start = cell.getColumnIndex();
		int r = 0;

		while (row.getCell(tabular_column_start) != null
				|| row.getCell(tabular_column_start).getStringCellValue() != null
				|| !row.getCell(tabular_column_start).getStringCellValue().equals("")) {

			row = sheet.getRow(tabular_row_start + r);

			if (row == null) {
				System.out.println("row null " + r);
				break;
			}
			if (row.getZeroHeight()) {
				r++;
				continue;
			}
			ArrayList<Cell> line = new ArrayList<>();
			// lecture de la ligne
			for (int i = 1; i < width; i++) {
				line.add(row.getCell(tabular_column_start + i));
			}
			m.putIfAbsent(row.getCell(tabular_column_start).getStringCellValue(), line);
			r++;
		}
		return m;
	}

	private Position getPosition(String s) {
		Position pos;
		int l, col = 0;
		String[] splited = s.split("[^A-Z0-9]+|(?<=[A-Z])(?=[0-9])|(?<=[0-9])(?=[A-Z])");

		try {
			col = charToInt(splited[0]);
		} catch (cellNameException e) {
			System.out.println("Nom de la cellule invalide");
			e.printStackTrace();
		}
		l = Integer.parseInt(splited[1]) - 1;
		pos = new Position(l, col);
		return pos;
	}

	/**
	 * Convert an excel row name into an integer AB=27
	 * 
	 * @param string
	 *            the string you want to convert
	 * @return the converted string
	 * @throws cellNameException
	 *             if the given name is not of the type [A-Z]+
	 * @version 1.1
	 * @author Valentin JABRE
	 */
	private int charToInt(String string) throws cellNameException {
		int res = 0;
		for (int i = string.length() - 1; i >= 0; i--) {
			char c = string.charAt(i);
			int v = ((int) c) - ((int) 'A');
			System.out.println("v=" + v);
			if (v >= 0 || v < 26) {
				res += Math.pow(26, string.length() - i - 1) * (v + 1);
			} else {
				throw new cellNameException();
			}

		}
		return res - 1;
	}

}