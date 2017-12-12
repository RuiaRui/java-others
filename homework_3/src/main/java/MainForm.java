import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Pair;
import recommend.Recommend;
import recommend.Recommend;
import tf_idf.TF_IDF;
import util.StockSorter;
import vo.StockInfo;
import util.FileHandler;
import segmenter.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.apache.log4j.spi.Configurator.NULL;


public class MainForm {
    private FileHandler fileHandler;
    private StockInfo[] stockInfos;
    private ChineseSegmenter segmenter;
    private Recommend recommender;

    private StockSorter stockSorter;
    private TF_IDF tf_idf;

    private JPanel panel1;
    private JButton button_import;
    private JButton button_search;
    private JTextField textField4search;
    private JTextArea textArea1;
    private JTextField textField4addr;


    public MainForm() {


        button_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField4search.getText() == NULL) {
                    return;
                }
                //得到分词结果并切分
                String keyword = textField4search.getText();
                List<String> keywords = segmenter.cutword(new String[]{keyword});

                // 分词
                List<String> words = segmenter.getWordsFromData(stockInfos);
                // 词频统计
                Pair<String, Double>[] maps = tf_idf.getResult(words, stockInfos);

                //筛选关键词结果
                ArrayList<Pair<String, Double>> keywordSet = new ArrayList<>();
                for (String Keyword : keywords) {
                    for (Pair<String, Double> map : maps) {
                        if (map.getKey().equals(Keyword)) {
                            keywordSet.add(new Pair<>(Keyword, map.getValue()));
                        }
                    }
                }

                ArrayList<Pair<StockInfo,Double>> result = new ArrayList<>();
                for (StockInfo stockInfo : stockInfos) {
                    for (Pair<String, Double> kw : keywordSet) {
                        if (!(stockInfo.getTitle() + stockInfo.getContent() + stockInfo.getAnswer()).contains(kw.getKey())) {
                            continue;
                        }
                        result.add(new Pair<>(stockInfo, kw.getValue()));
                    }
                }
                textArea1.setText(" ");
                for (Pair<StockInfo,Double> stock : result) {
                    textArea1.append(stock.getKey().getTitle() + '\n' + stock.getKey().getContent() + '\t' + stock.getKey().getAnswer());
                }
                textArea1.addMouseListener(new MouseAdapter() {
                    /**
                     * {@inheritDoc}
                     *
                     * @param e
                     */
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        if (e.getClickCount() == 2){


                        }

                    }
                });

            }
        });


        button_import.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.showDialog(new JLabel(), "选择");
                File file = fileChooser.getSelectedFile();
                if (file.isFile()) {
                    try {
                        JOptionPane.showMessageDialog(null, "导入成功", "tips",
                                JOptionPane.INFORMATION_MESSAGE);
                        //显示内容
                        char ch[] = new char[1024];
                        String str = "", s = "";
                        FileReader fr = new FileReader(fileChooser.getSelectedFile());
                        while (fr.read(ch) != -1) {
                            s = new String(ch);
                            str = str + s;
                        }
                        textArea1.setText(str);
                        //显示地址
                        textField4addr.setText(file.getAbsolutePath());

                        //导入文件
                        stockInfos = fileHandler.getStockInfoFromFile(file.getAbsolutePath());

                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }

                }
            }
        });



    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setSize(800, 600);
        frame.setContentPane(new MainForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}
