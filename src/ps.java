import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


import java.net.URL;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imread;
public class ps {
    public static void main(String[] args) {
        System.loadLibrary("opencv_java455");
        Mat mat=imread("D:\\Van Gogh\\img.jpg");
        Mat filteredMat1=new Mat();
        Mat filteredMat2=new Mat();
        Imgproc.bilateralFilter(mat,filteredMat1,0,40,5);
        Imgproc.bilateralFilter(mat,filteredMat2,0,40,15);
        imshow("null",filteredMat1);
        waitKey();
        imshow("null",filteredMat2);
        waitKey();
    }
}
