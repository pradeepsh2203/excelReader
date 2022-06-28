package test.streaming.reader;

import com.ibm.icu.text.ArabicShaping;
import com.monitorjbl.xlsx.StreamingReader;
import com.monitorjbl.xlsx.impl.StreamingWorkbookReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class PerformanceTest {
    static ArrayList<Integer> numOfRows = new ArrayList<Integer>(Arrays.asList(10, 20, 30, 40));
    static ArrayList<Integer> bufferSize = new ArrayList<>(Arrays.asList(512, 1024, 2048, 4096));

    static long performTest(String fileName, String FilePath, int numOfRow, int bufferSize, String sheetName) throws FileNotFoundException {
        InputStream stream = new FileInputStream(new File( FilePath+fileName));
        Sheet reader = StreamingReader.builder()
                .rowCacheSize(numOfRow)
                .bufferSize(bufferSize)
                .open(stream)
                .getSheet(sheetName);
        long startTime = System.currentTimeMillis();
        for (Row r : reader) {
            for (Cell c : r) {
//                System.err.println(c.getStringCellValue()+",");
            }
//            System.err.println("\n");
        }

        long endTime = System.currentTimeMillis();

        return endTime-startTime;
    }

    public static void main(String args[]) {
        try{
            String filePath = System.getProperty("user.dir") + "/src/main/resources/";
            String fileName="bigFile.xlsx";
            String sheetName="Sales Order_data";
            System.out.println(filePath);

            for (int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    long temp= performTest(fileName,filePath,numOfRows.get(j),
                            bufferSize.get(i),sheetName);
                    System.out.printf("time taken for rows(%d) and buffer size(%d) is %d in millis\n",
                            numOfRows.get(j),bufferSize.get(i),temp);
                }
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
