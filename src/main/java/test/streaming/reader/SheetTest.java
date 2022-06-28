package test.streaming.reader;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SheetTest {

    public static void readFromXLSX(){
        try {
            String fileName = System.getProperty("user.dir") + "/src/main/resources/finance.xlsx";
            InputStream stream = new FileInputStream(new File(fileName));
            Sheet reader = StreamingReader.builder()
                    .bufferSize(1024)
                    .rowCacheSize(20)
                    .open(stream)
                    .getSheet("Sheet1");
            for (Row row:reader){
                System.out.print(row.getRowNum()+" ->");
                for (Cell cell:row){
                    System.out.print(cell.getStringCellValue()+",");
                }
                System.out.println("\b");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void readFromXLS(){
        try {
            String fileName = System.getProperty("user.dir") + "/src/main/resources/finance.xls";
            InputStream stream = new FileInputStream(new File(fileName));
            Sheet reader = StreamingReader.builder()
                    .bufferSize(1024)
                    .rowCacheSize(20)
                    .open(stream)
                    .getSheet("Sheet1");
            for (Row row:reader){
                row.getRowNum();
                for (Cell cell:row){
                    System.out.print(cell.getStringCellValue()+",");
                }
                System.out.println("\b");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String args[]){
//        readFromXLS();
        readFromXLSX();
    }
}
