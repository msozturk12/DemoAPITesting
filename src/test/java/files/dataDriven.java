package files;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class dataDriven {
    public static void main(String[] args) throws IOException {

        //ıdentify TestCases column by scanning the entire 1st row
        //once column is identified then scan entire testcase column to identify purchase testcase row
        //after you grab purchase testcase row = pull all the data of that row and feed into test

    }
    public  ArrayList<String> getData(String sheetName,String testCaseName) throws IOException {

        ArrayList<String> a = new ArrayList<>();

        FileInputStream fis = new FileInputStream("C:\\Users\\mesut\\Desktop\\SDET\\SeleniumExcelExercises\\excelDrivenExercise2.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                //ıdentify TestCases column by scanning the entire 1st row
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();

                Iterator<Cell> ce = firstRow.cellIterator();//rows collection of cell

                int k = 0;
                int column = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
                        //desired column
                        column = k;
                    }
                    k++;
                }
                System.out.println(column);

                //once column is identified then scan entire testcase column to identify purchase testcase row
                while(rows.hasNext()){
                    Row r = rows.next();
                    if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)){
                        //after you grab purchase testcase row = pull all the data of that row and feed into test
                        Iterator<Cell> cv =  r.cellIterator();
                        while(cv.hasNext()){
                            Cell c = cv.next();
                            if(c.getCellType()== CellType.STRING){
                                a.add(c.getStringCellValue());
                            }else{
                               a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }

                }
            }



        }
        return a;
    }
}
