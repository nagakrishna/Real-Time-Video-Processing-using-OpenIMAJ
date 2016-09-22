import org.openimaj.feature.local.list.LocalFeatureList;
import org.openimaj.feature.local.matcher.BasicMatcher;
import org.openimaj.feature.local.matcher.FastBasicKeypointMatcher;
import org.openimaj.feature.local.matcher.LocalFeatureMatcher;
import org.openimaj.feature.local.matcher.MatchingUtilities;
import org.openimaj.feature.local.matcher.consistent.ConsistentLocalFeatureMatcher2d;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.feature.local.engine.DoGSIFTEngine;
import org.openimaj.image.feature.local.keypoints.Keypoint;
import org.openimaj.math.geometry.transforms.estimation.RobustAffineTransformEstimator;
import org.openimaj.math.model.fit.RANSAC;
import org.openimaj.video.Video;
import org.openimaj.video.xuggle.XuggleVideo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Naga on 11-08-2016.
 */
public class SIFTFeatureMatching {

    public static void main(String args[]) throws IOException{
//        MBFImage image1 = ImageUtilities.readMBF(new File("data/check/logo1.png"));
//        MBFImage image2 = ImageUtilities.readMBF(new File("data/check/logo1.png"));
        Video<MBFImage> video;
        video = new XuggleVideo(new File("data/ntr.mkv"));


        List<MBFImage> imageList = new ArrayList<MBFImage>();
        int i = 0;
        Integer a;
        List<BufferedImage> list = new ArrayList<BufferedImage>();
        Iterator<MBFImage> ll=  video.iterator();
//        while(ll.hasNext())
//        {
//            i++;
//            imageList.add(ll.next());
////            BufferedImage bufferedFrame = ImageUtilities.createBufferedImageForDisplay(ll.next());
////            String name = "data/new" + i + ".jpg";
////            File outputFile = new File(name);
////            ImageIO.write(bufferedFrame, "jpg", outputFile);
//        }
        MBFImage image;
        for (MBFImage mbfImage : video) {
            MBFImage b = mbfImage.clone();
//            MBFImage image1 = mbfImage;
//            a = video.getCurrentFrameIndex();
//            System.out.println(a);
            BufferedImage bufferedFrame = ImageUtilities.createBufferedImageForDisplay(mbfImage);
            i++;
            String name = "data/new" + i + ".jpg";
            File outputFile = new File(name);
            ImageIO.write(bufferedFrame, "jpg", outputFile);
            imageList.add(b);
            list.add(bufferedFrame);
            System.out.println(mbfImage.toByteImage());
//            System.out.println("");
            DisplayUtilities.displayName(mbfImage, "video");
        }

//        for(BufferedImage b: list){
//            i++;
//            String name = "data/new" + i + ".jpg";
//            File outputFile = new File(name);
//            ImageIO.write(b, "jpg", outputFile);
//        }

        for(MBFImage b: imageList){
            i++;
            BufferedImage bufferedFrame = ImageUtilities.createBufferedImageForDisplay(b);
            String name = "data/new" + i + ".jpg";
            File outputFile = new File(name);
            ImageIO.write(bufferedFrame, "jpg", outputFile);
        }
//        for(MBFImage mbfImage: list){
//            DisplayUtilities.displayName(mbfImage, "videoFrames");
//        }

        DisplayUtilities.displayName(imageList.get(10), "1");
        DisplayUtilities.displayName(imageList.get(77), "2");
        DisplayUtilities.displayName(imageList.get(189), "3");
        DisplayUtilities.displayName(imageList.get(259), "4");


        MBFImage image1 = ImageUtilities.readMBF(new File("data/check/4.jpg"));
        MBFImage image2 = ImageUtilities.readMBF(new File("data/check/4_1.jpg"));
        DoGSIFTEngine engine = new DoGSIFTEngine();
        LocalFeatureList<Keypoint> queryKeypoints = engine.findFeatures(image1.flatten());
        LocalFeatureList<Keypoint> targetKeypoints = engine.findFeatures(image2.flatten());
        LocalFeatureMatcher<Keypoint> matcher = new BasicMatcher<Keypoint>(80);
//        matcher.setModelFeatures(queryKeypoints);
//        matcher.findMatches(targetKeypoints);
//        MBFImage basicMatches = MatchingUtilities.drawMatches(image1, image2, matcher.getMatches(), RGBColour.RED);
//        DisplayUtilities.display(basicMatches);


        RobustAffineTransformEstimator modelFitter = new RobustAffineTransformEstimator(5.0, 1500,
                new RANSAC.PercentageInliersStoppingCondition(0.5));
        LocalFeatureMatcher<Keypoint> matcher1 = new ConsistentLocalFeatureMatcher2d<Keypoint>(
                new FastBasicKeypointMatcher<Keypoint>(8), modelFitter);
        matcher1.setModelFeatures(queryKeypoints);
        matcher1.findMatches(targetKeypoints);
        MBFImage consistentMatches = MatchingUtilities.drawMatches(image1, image2, matcher1.getMatches(),
                RGBColour.RED);
        DisplayUtilities.display(consistentMatches);
    }
}
