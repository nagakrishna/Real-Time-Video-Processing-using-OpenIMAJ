import org.openimaj.feature.local.list.LocalFeatureList;
import org.openimaj.feature.local.matcher.FastBasicKeypointMatcher;
import org.openimaj.feature.local.matcher.LocalFeatureMatcher;
import org.openimaj.feature.local.matcher.consistent.ConsistentLocalFeatureMatcher2d;
import org.openimaj.image.MBFImage;
import org.openimaj.image.feature.local.engine.DoGSIFTEngine;
import org.openimaj.image.feature.local.keypoints.Keypoint;
import org.openimaj.math.geometry.transforms.estimation.RobustAffineTransformEstimator;
import org.openimaj.math.model.fit.RANSAC;
import org.openimaj.video.Video;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.xuggle.XuggleVideo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naga on 19-08-2016.
 */
public class Temp {
    public static void main(String args[]){
        Video<MBFImage> video, decodedVideo;
        video = new XuggleVideo(new File("data/ntr.mkv"));

        /*
        Encoding and Decoding Video
         */

//        File tempFile = new File("data/ntr.mkv");
//        String encodedString = null;
//
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(tempFile);
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        byte[] bytes;
//        byte[] buffer = new byte[8192];
//        int bytesRead;
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        try {
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                output.write(buffer, 0, bytesRead);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        bytes = output.toByteArray();
//        encodedString = Base64.encodeToString(bytes, true);
//
//
//
//        //Decode String To Video With mig Base64.
//        byte[] decodedBytes = Base64.decodeFast(encodedString.getBytes());
//
//        try {
//
//            FileOutputStream out = new FileOutputStream("data/decode.mkv");
//            out.write(decodedBytes);
//            out.close();
//        } catch (Exception e) {
//            // TODO: handle exception
////            Log.e("Error", e.toString());
//
//        }
//        decodedVideo = new XuggleVideo(new File("data/decode.mkv"));
        VideoDisplay<MBFImage> display = VideoDisplay.createVideoDisplay(video);
        List<MBFImage> imageList = new ArrayList<MBFImage>();
        List<MBFImage> imageList1 = new ArrayList<MBFImage>();
        List<Long> timeStamp = new ArrayList<Long>();
        for (MBFImage mbfImage : video) {
            MBFImage b = mbfImage.clone();
            imageList.add(b);
            timeStamp.add(video.getTimeStamp());
//            System.out.println(video.getTimeStamp());
        }
//        for (MBFImage mbfImage : decodedVideo) {
//            MBFImage b = mbfImage.clone();
//            imageList1.add(b);
//        }
        /*
        Main Frame Detection
         */
//
        List<Integer> mainPoints = new ArrayList<Integer>();
        for (int i=0; i<imageList.size() - 1; i++)
//        for (int i=0; i<30; i++)
        {
            MBFImage image1 = imageList.get(i);
            MBFImage image2 = imageList.get(i+1);
            DoGSIFTEngine engine = new DoGSIFTEngine();
            LocalFeatureList<Keypoint> queryKeypoints = engine.findFeatures(image1.flatten());
            LocalFeatureList<Keypoint> targetKeypoints = engine.findFeatures(image2.flatten());
            RobustAffineTransformEstimator modelFitter = new RobustAffineTransformEstimator(5.0, 1500,
                    new RANSAC.PercentageInliersStoppingCondition(0.5));
            LocalFeatureMatcher<Keypoint> matcher1 = new ConsistentLocalFeatureMatcher2d<Keypoint>(
                    new FastBasicKeypointMatcher<Keypoint>(8), modelFitter);
            matcher1.setModelFeatures(queryKeypoints);
            matcher1.findMatches(targetKeypoints);
            mainPoints.add(matcher1.getMatches().size());
            System.out.println(matcher1.getMatches().size());
        }

        List<Vector> vector = new ArrayList<Vector>();
        for(int i=0; i<imageList.size(); i++){
            if(mainPoints.get(i) < 50){
                Vector v = new Vector();
                v.setImage(imageList.get(i+1));
                v.setTimestamp(timeStamp.get(i+1));
            }
        }
        System.out.print("Finish");
    }
}

class Vector{
    public MBFImage getImage() {
        return image;
    }

    public void setImage(MBFImage image) {
        this.image = image;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    private MBFImage image;
    private Long timestamp;
}