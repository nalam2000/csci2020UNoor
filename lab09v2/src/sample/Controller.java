package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Controller {

    public Canvas canvas;
    private GraphicsContext gc;
    private double max = 0;


    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        ArrayList<Double> apple_prices = downloadStockPrices("AAPL");
        ArrayList<Double> google_prices = downloadStockPrices("GOOG");
        drawLinePlot(apple_prices, google_prices);
    }

    private ArrayList<Double> downloadStockPrices(String stockTicker) {
        ArrayList<Double> stock = new ArrayList<>();
        String wURL = "https://query1.finance.yahoo.com/v7/finance/download/ticker?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
        try {
            InputStream file = new URL(wURL.replace("ticker", stockTicker)).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            String line;

            int count = 0;

            while ((line = reader.readLine()) != null) {
                if (count > 1) {
                    double price = Double.parseDouble(line.split(",")[4]);
                    stock.add(price);
                    if (price > max) {
                        max = price;
                    }
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return all the prices
        return stock;
    }

    private void drawLinePlot(ArrayList<Double> Apple, ArrayList<Double> Google) {
        gc.setStroke(Color.BLACK);
        gc.strokeLine(50, 50, 50, canvas.getHeight() - 50);
        gc.strokeLine(50, canvas.getHeight() - 50, canvas.getWidth() - 50, canvas.getHeight() - 50);


        gc.setStroke(Color.BLUE);
        plotLine(Apple);

        gc.setStroke(Color.RED);
        plotLine(Google);
    }

    private void plotLine(ArrayList<Double> Prices) {
        double space = (canvas.getWidth() - 100) / Prices.size();
        for (int i =1; i<Prices.size(); i++){
            double y1 = 500 - (Prices.get(i-1)*500/max);
            double y2 = 500 - (Prices.get(i)*500/max);
            gc.strokeLine(50 + space*(i-1),y1, 50 + space*i,y2);
        }
    }
}