import org.openimaj.feature.DoubleFVComparison;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.pixel.statistics.HistogramModel;
import org.openimaj.math.statistics.distribution.MultidimensionalHistogram;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naga on 10-08-2016.
 */
public class ProcessingImages {
    public static void main(String args[]) throws IOException {
//        MBFImage image = ImageUtilities.readMBF(new File("data/ntr.jpg"));
        MBFImage image1 = ImageUtilities.readMBF(new File("data/check/3.jpg"));
        MBFImage image2 = ImageUtilities.readMBF(new File("data/check/5.jpg"));
//        image.drawShapeFilled(new Ellipse(100f, 150f, 50f, 40f, 0f), RGBColour.WHITE);
//        image.drawText("NTR", 90, 150, HersheyFont.ASTROLOGY, 20, RGBColour.BLACK);
//        image.processInplace(new CannyEdgeDetector());
//        DisplayUtilities.display(image);


        List<MultidimensionalHistogram> histograms = new ArrayList<MultidimensionalHistogram>();
        HistogramModel model1 = new HistogramModel(4, 4, 4);
        HistogramModel model2 = new HistogramModel(4, 4, 4);
        model1.estimateModel(image1);
        model2.estimateModel(image2);
        histograms.add( model1.histogram.clone());
        histograms.add( model2.histogram.clone());
        double distance = histograms.get(0).compare( histograms.get(1), DoubleFVComparison.EUCLIDEAN );
        System.out.println(distance);


    }
}
