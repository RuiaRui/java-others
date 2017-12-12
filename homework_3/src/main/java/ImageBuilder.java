
import javafx.util.Pair;

import vo.StockInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageBuilder {

    private List<String> getKeyWord(Pair<String, Double>[] pairs, String content) {
        List<String> keyword = new ArrayList<>();
        for (int a = 0; a < pairs.length; a++) {
            if (content.contains(pairs[a].getKey())) {
                keyword.add(pairs[a].getKey());
            }
        }
        return keyword;
    }
}






