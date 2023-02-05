package task1;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class Work2 {
    public static void main(String[] args) {
        System.loadLibrary("opencv_java455");
        Mat mat=imread("D:\\Van Gogh\\Van Gogh2.jpg");
        imshow("preImg",mat);
        waitKey();
        Mat blurMat=new Mat();
        Imgproc.blur(mat,blurMat,new Size(100,100));//卷积核越大越模糊
        imshow("blurImg",blurMat);
        waitKey();
    }
}
