package recommend;


import javafx.util.Pair;
import segmenter.ChineseSegmenter;
import segmenter.ChineseSegmenterImpl;
import tf_idf.TF_IDF;
import tf_idf.TF_IDFImpl;
import vo.StockInfo;
import vo.UserInterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class RecommendImpl implements Recommend {

    private static ChineseSegmenter segment = new ChineseSegmenterImpl();
    private static TF_IDF tf_idf = new TF_IDFImpl();

    /**
     * this function need to calculate stocks' content similarity,and return the similarity matrix
     *
     * @param stocks stock info
     * @return similarity matrix
     */
    @Override
    public double[][] calculateMatrix(StockInfo[] stocks) {
        //TODO: write your code here


        List<String> keywords = GetKeyWords((stocks));
        int[][] frequent = FrequencyCalculator(keywords, stocks);

        return CosSimilarityCalculator(frequent);
    }

    /**
     * this function need to recommend the most possibility stock number
     *
     * @param matrix similarity matrix
     * @param userInterest user interest
     * @return commend stock number
     */
    @Override
    public double[][] recommend(double[][] matrix, UserInterest[] userInterest) {
        //TODO: write your code here
        double[][] interestScoreMatrix = InterestScoreMatrixCalculator(matrix, userInterest);
        double[][] recommend = Choose(interestScoreMatrix);
        return recommend;

    }


    /**
     * this function need to get content of each stock
     *
     * @param stocks stocks
     * @return the list of key word
     */
    @Override
    public List<String> GetKeyWords(StockInfo[] stocks) {
        //每个stock的content分词
        ArrayList<List<String>> contentWords = new ArrayList<>();
        for (StockInfo stock : stocks) {
            contentWords.add(segment.getWordsFromData(new StockInfo[]{stock}));
        }

        //取10个关键词
        ArrayList<String> keywordsList = new ArrayList<>();
        for (List<String> list : contentWords) {
            Pair<String, Double>[] pairs = tf_idf.getResult(list, stocks);
            //System.out.println("length" + pairs.length);
            for (int i = 0; i < Math.min(10, pairs.length); i++) {
                keywordsList.add(pairs[i].getKey().toString());
                //删除重复关键字
                HashSet hs = new HashSet(keywordsList);
                keywordsList.clear();
                keywordsList.addAll(hs);

            }
        }
        return keywordsList;

    }

    /**
     * this function need to calculate the frequency
     *
     * @param keywords
     * @param stocks
     * @return frequency
     */
    @Override
    public int[][] FrequencyCalculator(List<String> keywords, StockInfo[] stocks) {
        int[][] frequency = new int[60][keywords.size()];
        int flag = 0;
        for (StockInfo s : stocks) {
            HashMap<String, Integer> count = new HashMap<>();
            for (String keyword : keywords) {
                count.put(keyword, 0);
            }

            List<String> words = segment.getWordsFromData(new StockInfo[]{s});
            for (String word : words) {
                if (count.get(word) != null) {
                    count.put(word, count.get(word) + 1);
                }
            }

            for (int i = 0; i < keywords.size(); i++) {
                frequency[flag][i] = count.get(keywords.get(i));
            }
            flag++;
        }
        return frequency;
    }

    /**
     * this function need to use cosine function to calculate the similarity
     *
     * @param array1
     * @param array2
     * @return similarity represented by cos
     */

    @Override
    public Double CosCalculator(int[] array1, int[] array2) {
        int numerator = 0;
        for (int i = 0; i < array1.length; i++) {
            numerator = numerator + array1[i] * array2[i];
        }

        Double denominator1 = 0.0;
        for (int i = 0; i < array1.length; i++) {
            denominator1 = denominator1 + array1[i] * array1[i];
        }
        denominator1 = Math.sqrt(denominator1);

        Double denominator2 = 0.0;
        for (int i = 0; i < array2.length; i++) {
            denominator2 = denominator2 + array2[i] * array2[i];
        }
        denominator2 = Math.sqrt(denominator2);
        Double denominator = denominator1 * denominator2;

        return numerator / denominator;
    }

    /**
     * this function need to generate the similarity matrix based on cos function
     *
     * @param frequentMatrix the frequency matrix
     * @return similarity represented by cos
     */
    @Override
    public double[][] CosSimilarityCalculator(int[][] frequentMatrix) {
        double[][] similarityMatrix = new double[frequentMatrix.length][frequentMatrix.length];
        for (int i = 0; i < frequentMatrix.length; i++) {
            for (int j = 0; j < frequentMatrix.length; j++) {
                similarityMatrix[i][j] = CosCalculator(frequentMatrix[i], frequentMatrix[j]);
            }
        }
        return similarityMatrix;
    }


    /**
     * 计算用户对各个新闻的喜爱得分矩阵
     *
     * @param matrix
     * @param userInterest
     * @return 得分矩阵
     */
    private double[][] InterestScoreMatrixCalculator(double[][] matrix, UserInterest[] userInterest) {
        double[][] interestScoreMatrix = new double[userInterest.length][60];
        for (UserInterest interest : userInterest) {
            int userId = interest.getUserId();
            List<Integer> readId = interest.getReadId();
            for (int i = 0; i < 60; i++) {
                if (readId.contains(i + 1)) {
                    interestScoreMatrix[userId - 1][i] = 0;
                } else {
                    interestScoreMatrix[userId - 1][i] = InterestScoreCalculator(matrix[i], readId);
                }
            }
        }
        return interestScoreMatrix;
    }


    /**
     * 计算用户对某条信息的喜爱得分
     *
     * @param similar
     * @param readId
     * @return
     */
    private double InterestScoreCalculator(double[] similar, List<Integer> readId) {
        double interestScore = 0;
        for (Integer id : readId) {
            interestScore += similar[id - 1];
        }
        return interestScore;
    }

    /**
     * 选择10个推荐消息Id
     *
     * @return 选择之后的矩阵
     */
    private double[][] Choose(double[][] scoreMatrix) {
        double[][] sort = scoreMatrix;
        double[][] recommend = new double[scoreMatrix.length][10];
        for (int i = 0; i < scoreMatrix.length; i++) {
            for (int j = 0; j < 10; j++) {
                int index = ChooseMax(sort[i]);
                recommend[i][j] = index + 1;
                sort[i][index] = 0;
            }
        }
        return recommend;
    }


    /**
     * 选择一个数组中最大元素的下标
     *
     * @param scores
     * @return index，最大元素的下标
     */
    private int ChooseMax(double[] scores) {
        int flag = 0;
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[flag]) {
                flag = i;
            }
        }
        return flag;
    }


}
