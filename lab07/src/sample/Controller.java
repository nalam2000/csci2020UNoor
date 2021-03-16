package sample;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;

public class Controller {

    private HashMap<String, Integer> warningsType = new HashMap<>();
    private static Color[] colors = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON
    };


    public Canvas mainCanvas;
    public GraphicsContext gc;


    public void initialize() {
        gc = mainCanvas.getGraphicsContext2D();
        numWarnings("weatherwarnings-2015.csv");
        drawPieChart();
    }

    private void numWarnings(String fileName) {
        String line = "";
        try {
            BufferedReader rf = new BufferedReader(new FileReader(fileName));
            while ((line = rf.readLine()) != null) {
                String[] columns = line.split(",");
                String warning = columns[5];
                //
                if (warningsType.containsKey(warning)) {
                    int oldValue = warningsType.get(warning);
                    warningsType.put(warning, oldValue + 1);
                } else {

                    warningsType.put(warning, 1);
                }
            }
            //
            rf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawPieChart() {
        //  Draw Legend
        int x = 50;
        int y = 100;
        int i = 0;
        int total = 0;
        for (String warning : warningsType.keySet()) {
            total += warningsType.get(warning);
        }
        for (String warning : warningsType.keySet()) {
            gc.setFill(colors[i]);
            gc.fillRect(x, y, 50, 35);

            gc.setFill(Color.BLACK);
            gc.fillText(warning, x + 60, y + 20);
            y += 50;
            i++;
        }

        // Draw Pie Chart
        double start_ang = 0;
        double angle = 0;
        int k = 0;
        for (String warning : warningsType.keySet()) {
            angle = 360 * ((double) warningsType.get(warning) / (double) total);
            gc.setFill(colors[k]);
            gc.fillArc(360, 20, 280, 270, start_ang,angle, ArcType.ROUND);

            start_ang += angle;
            k++;
        }

    }
}