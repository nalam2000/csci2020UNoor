package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {
    public Canvas mainCanvas;
    public GraphicsContext gc;

    private static double[] avgHousingPricesByYear = {247381.0, 264171.4, 287715.3, 294736.1, 308431.4, 322635.9, 340253.0, 363153.7};
    private static double[] avgCommercialPricesByYear = {1121585.3, 1219479.5, 1246354.2, 1295364.8, 1335932.6, 1472362.0, 1583521.9, 1613246.3};
    private static String[] ageGroups = {"18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = {648, 1021, 2453, 3173, 1868, 2247};
    private static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        drawGraph(120, 300, avgHousingPricesByYear, avgCommercialPricesByYear, Color.RED, Color.BLUE, 150);
        drawPie(purchasesByAgeGroup, pieColours);
    }

    private void drawPie(int[] purchasesByAgeGroup, Color[] pieColours) {
        double sum = 0;
        double startAng = 0;
        for(int val : purchasesByAgeGroup){
            sum += val;
        }
        for(int i = 0; i < purchasesByAgeGroup.length; i++){
            gc.setFill(pieColours[i]);
            double angle = ((double) purchasesByAgeGroup[i] / (sum)) * 360;
            gc.fillArc(500,175,300,300,startAng,angle, ArcType.ROUND);
            startAng += angle;

        }
    }

    private void drawGraph(int x, int y, double[] avgHousingPricesByYear, double[] avgCommercialPricesByYear, Color red, Color blue, int fillX ) {
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.MAX_VALUE;
        double space_inb = (x/avgHousingPricesByYear.length);
        for (double current : avgHousingPricesByYear) {
            if (current > max) {
                max = current;
            }
            if (current < min) {
                min = current;
            }
        }


        for (double current : avgCommercialPricesByYear) {
            if (current > max) {
                max = current;
            }
            if (current < min) {
                min = current;
            }
        }


        double fill_bar = fillX;
        gc.setFill(red);
        for (double val : avgHousingPricesByYear) {
            double height = ((val-min) / (max-min)) * y ;
            gc.fillRect(fill_bar-60,(y-height)+150, space_inb, height+40);
            fill_bar += space_inb*2.5;
        }
        gc.setFill(blue);
        fill_bar = 150 + space_inb;
        for (double val : avgCommercialPricesByYear) {
            double height = ((val-min)/(max - min)) * y;
            gc.fillRect(fill_bar-60, (y-height) + 150, space_inb,height+40);
            fill_bar += space_inb*2.5;
        }

    }


}