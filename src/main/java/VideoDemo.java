import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.typography.hershey.HersheyFont;
import org.openimaj.video.Video;
import org.openimaj.video.xuggle.XuggleVideo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naga on 8/5/2016.
 */

public class VideoDemo {
    private static Video<MBFImage> video;
    private static List<MBFImage> imageList = new ArrayList<MBFImage>();
    public static void main(String args[]) throws IOException {
        video = new XuggleVideo(new File("data/ntr.mkv"));
//        https://www.youtube.com/watch?v=q6voJ13asIo
//        video = new XuggleVideo(new URL("http://r7---sn-p5qlsnes.googlevideo.com/videoplayback?mime=video%2Fmp4&nh=IgpwcjAzLmlhZDA3KgkxMjcuMC4wLjE&signature=421D5F64671B606F849B521579C1FB5657F1AA8C.24AC8B3A8C486FBC95B1131FAF9919B99A5CDDEA&source=youtube&mn=sn-p5qlsnes&upn=APXEqW2mFh0&itag=18&pl=24&mt=1471279651&ms=au&ei=xfSxV9zuN86fcNHvlHA&expire=1471301926&mm=31&id=o-AFE_Wg9PWGq3bf3UePTM4_lMvfAfWzbsM7ho9Dc-tX-p&sver=3&ratebypass=yes&ip=159.253.144.86&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Csource%2Cupn%2Cexpire&key=yt6&lmt=1471232920237077&dur=140.643&fexp=9407191%2C9419451%2C9422596%2C9426731%2C9427833%2C9428398%2C9428914%2C9431012%2C9431718%2C9433096%2C9433221%2C9433946%2C9435526%2C9435739%2C9438227%2C9438327%2C9438662%2C9438731%2C9438805%2C9439470%2C9439580%2C9439882%2C9440431%2C9440799%2C9440927%2C9441191%2C9441768%2C9442424%2C9442426%2C9443259%2C9444229&mv=m&ipbits=0&initcwndbps=117500&title=Janatha+Garage+Telugu+Theatrical+Trailer+%7C+Jr+NTR+%7C+Mohanlal+%7C+Samantha+%7C+Nithya+%7C+Koratala+Siva"));
//        video = new XuggleVideo(new URL("http://static.openimaj.org/media/tutorial/keyboardcat.flv"));
//        final Video<MBFImage> video = new VideoCapture(320, 240);


//        VideoDisplay<MBFImage> display = VideoDisplay.createVideoDisplay(video);



        int i = 0;
        for (MBFImage mbfImage : video) {
//            i++;
//            imageList.add(mbfImage);
//            BufferedImage bufferedFrame = ImageUtilities.createBufferedImageForDisplay(mbfImage);
//            String name = "data/new" + i + ".jpg";
//            File outputFile = new File(name);
//            ImageIO.write(bufferedFrame, "jpg", outputFile);
//            mbfImage.drawShapeFilled(new Ellipse(100f, 150f, 50f, 40f, 0f), RGBColour.WHITE);
            mbfImage.drawText("NTR", 90, 150, HersheyFont.ASTROLOGY, 20, RGBColour.BLACK);
            DisplayUtilities.displayName(mbfImage, "videoFrames");
        }
//        BufferedImage bufferedFrame1 = ImageUtilities.createBufferedImageForDisplay(imageList.get(0));
//        BufferedImage bufferedFrame2 = ImageUtilities.createBufferedImageForDisplay(imageList.get(200));
//        File outputFile1 = new File("data/new1.jpg");
//        File outputFile2 = new File("data/new.jpg");
//        ImageIO.write(bufferedFrame1, "jpg", outputFile1);
//        ImageIO.write(bufferedFrame2, "jpg", outputFile2);
        System.out.print(imageList.size());
    }
}

