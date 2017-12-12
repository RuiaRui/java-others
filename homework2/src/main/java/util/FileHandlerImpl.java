package util;

import vo.StockInfo;
import vo.UserInterest;
import java.util.*;
import java.io.*;

public class FileHandlerImpl implements FileHandler {

    /**
     * This func gets stock information from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return the Stock information array from the interfaces,or NULL
     */
    @Override
    public StockInfo[] getStockInfoFromFile(String filePath) throws FileNotFoundException {
        //TODO: write your code here
        try{
            Scanner getIn=new Scanner(new FileInputStream(filePath));
            StockInfo[] stocks=new StockInfo[60];
            getIn.nextLine();
            int i=0;
            while(getIn.hasNext()){
                stocks[i]=new StockInfo(getIn.nextLine());
                i++;
            }
            return stocks;

        }catch (FileNotFoundException fileNotFoundExcepetionPara){
            fileNotFoundExcepetionPara.printStackTrace();
            return null;
        }

    }

    /**
     * This func gets user interesting from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return
     */
    @Override
    public UserInterest[] getUserInterestFromFile(String filePath) {

        try {
            UserInterest[] userInterests = new UserInterest[500];
            Scanner sc = new Scanner(new File(filePath));
            int i = 0;
            while (sc.hasNext()) {
                userInterests[i] = new UserInterest(i + 1, sc.nextLine());
                i++;
            }
            return userInterests;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function need write matrix to files
     *
     * @param matrix the matrix you calculate
     */
    @Override
    public void setCloseMatrix2File(double[][] matrix) {
        //TODO: write your code here

        try {
            File file = new File(this.getClass().getClassLoader().getResource(".").getPath() + "closematrix.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            for (double[] line : matrix) {
                for (double num : line) {
                    output.write(String.format("%.6f", num) + "\t");
                }
                output.write("\n");
            }
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * This function need write recommend to files
     *
     * @param recommend the recommend you calculate
     */
    @Override
    public void setRecommend2File(double[][] recommend) {
        //TODO: write your code here
        try {
            File file = new File(this.getClass().getClassLoader().getResource(".").getPath() + "recommend.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write("ID\tRECOMMEND\n");
            int ID = 1;
            for (double[] line : recommend) {
                output.write(ID + "\t");
                for (double num : line) {
                    output.write((int) num + "\t");
                }
                output.write("\n");
                ID++;
            }
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
