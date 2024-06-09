package luis122448.platformtraining.util.component;

import lombok.Data;
import luis122448.platformtraining.util.object.archive.ColumnInfo;
import luis122448.platformtraining.util.object.archive.ImportErrorModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Utils {

	public static final String IMPORT_SHEET_PRINCIPAL = "Format";
	public static void copyNonNullProperties(Object source, Object destination) {
	    BeanUtils.copyProperties(source, destination, getNullPropertyNames(source));
	}

	public static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper srcWrapper = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = srcWrapper.getPropertyDescriptors();

	    List <String> nullProperties = new ArrayList<>();
	    for (java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = srcWrapper.getPropertyValue(pd.getName());
	        if (srcValue == null) {
	            nullProperties.add(pd.getName());
	        }
	    }
	    return nullProperties.toArray(new String[0]);
	}

	public static String getStringCellValue(Cell cell) {
		if (cell != null) {
			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			}
		}
		return "";
	}

	public static Double getDoubleCellValue(Cell cell) {
		try {
			if (cell != null) {
				if (cell.getCellType() == CellType.STRING) {
					return Double.parseDouble(cell.getStringCellValue());
				} else if (cell.getCellType() == CellType.NUMERIC) {
					return cell.getNumericCellValue();
				}
			}
			return (double) 0;
		} catch ( NumberFormatException e) {
			return (double) 0;
		}
	}

	public static Integer getIntegerCellValue(Cell cell) {
		try {
			if (cell != null) {
				if (cell.getCellType() == CellType.STRING) {
					return Integer.getInteger(cell.getStringCellValue());
				} else if (cell.getCellType() == CellType.NUMERIC) {
					return (int) cell.getNumericCellValue();
				}
			}
			return (Integer) 0;
		} catch ( NumberFormatException e) {
			return (Integer) 0;
		}
	}

	public static Long getLongCellValue(Cell cell) {
		try {
			if (cell != null) {
				if (cell.getCellType() == CellType.STRING) {
					return Long.getLong(cell.getStringCellValue());
				} else if (cell.getCellType() == CellType.NUMERIC) {
					return (long) cell.getNumericCellValue();
				}
			}
			return 0L;
		} catch ( NumberFormatException e) {
			return 0L;
		}
	}

	public static BigDecimal getBigDecimalCellValue(Cell cell) {
		try {
			if (cell != null) {
				if (cell.getCellType() == CellType.STRING) {
					return BigDecimal.valueOf(Double.parseDouble(cell.getStringCellValue()));
				} else if (cell.getCellType() == CellType.NUMERIC) {
					return BigDecimal.valueOf(cell.getNumericCellValue());
				}
			}
			return BigDecimal.valueOf((double) 0);
		} catch ( NumberFormatException e) {
			return BigDecimal.valueOf((double) 0);
		}
	}

	public static List<ColumnInfo> readHeaderXSSFWorkbook(XSSFWorkbook xssfWorkbook) {
		List<ColumnInfo> columnInfoList = new ArrayList<>();
		XSSFSheet xssfSheet = xssfWorkbook.getSheet(IMPORT_SHEET_PRINCIPAL);

		if (xssfSheet == null) {
			throw new IllegalArgumentException("SHEET *FORMAT* IS NOT EXISTS");
		}

		int headerRow = 4;
		int indexCol = 0;

		Row row = xssfSheet.getRow(headerRow);

		Iterator<Cell> cellIterator = row.cellIterator();

		while( cellIterator.hasNext() ) {
			Cell cell = cellIterator.next();
			String name = cell.getStringCellValue();
			Boolean required = name.endsWith("*");
			if (required) {
				name = name.substring(0, name.length() - 1).trim();
			}
			ColumnInfo columnInfo = new ColumnInfo(indexCol,name,required);
			columnInfoList.add(columnInfo);
			indexCol++;
		}
		return columnInfoList;
	}

	public static List<ImportErrorModel> validateFieldRequiredXSSFWorkbook(List<ColumnInfo> columnInfoList, Row row) {

		List<ImportErrorModel> importErrorModelList = new ArrayList<>();

		for (ColumnInfo columnInfo: columnInfoList) {
			int columnIndex = columnInfo.getIndex();

			if(columnInfo.getRequired()) {
				Cell cell = row.getCell(columnIndex);

				if(cell == null || cell.getCellType() == CellType.BLANK) {
					String message = "THIS " + columnInfo.getName() + " IS FIELD IS REQUIRED";
					ImportErrorModel importErrorModel = new ImportErrorModel(row.getRowNum(),"BLANK",message);
					importErrorModelList.add(importErrorModel);
				}
			}
		}
		return importErrorModelList;
	}

	public static CellStyle importStyle(XSSFWorkbook xssfWorkbook){
		CellStyle cellStyle = xssfWorkbook.createCellStyle();
		DataFormat dataFormat = xssfWorkbook.createDataFormat();
		cellStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
		Font font = xssfWorkbook.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		cellStyle.setFont(font);
		return cellStyle;
	}

	public static CellStyle discountStyle(XSSFWorkbook xssfWorkbook){
		CellStyle cellStyle = xssfWorkbook.createCellStyle();
		DataFormat dataFormat = xssfWorkbook.createDataFormat();
		cellStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
		Font font = xssfWorkbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		cellStyle.setFont(font);
		return cellStyle;
	}

//	public static CellStyle yesOrNotStyle(XSSFWorkbook xssfWorkbook, XSSFSheet xssfSheet, Integer startRow, Integer ynColumnIndex){
//		CellStyle ynCellStyle = xssfWorkbook.createCellStyle();
//		String[] validValues = { "Y", "N" };
//		DataValidationHelper validationHelper = xssfSheet.getDataValidationHelper();
//		DataValidationConstraint dvConstraint = validationHelper.createExplicitListConstraint(validValues);
//		CellRangeAddressList addressList = new CellRangeAddressList(startRow, 1000, ynColumnIndex, ynColumnIndex);
//		DataValidation dataValidation = validationHelper.createValidation(dvConstraint, addressList);
//
//	}

}
