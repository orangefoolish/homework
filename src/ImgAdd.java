import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


import java.net.URL;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imread;
public class ImgAdd {
    public static void main(String[] args) {
        System.loadLibrary("opencv_java455");
        Mat img1=imread("D:\\Van Gogh\\Van Gogh5.jpg");
        Mat img2=imread("D:\\Van Gogh\\Van Gogh6.jpg");
        Mat mixed=new Mat();
        //Core.addWeighted(img1,0.3,img2,0.9,0,mixed);
        //Core.add(img1,img2,mixed);//rgb三通道的值相加，如果超过255(白色)就取255
        //Core.bitwise_not(img1,mixed);//按位非，即反相，底片效果
        imshow("img1",img1);
        Point center=new Point(img1.width()/2,img1.height()/2);
        Mat affineTrans=Imgproc.getRotationMatrix2D(center,180.0,1.0);//获取旋转变换矩阵，旋转33度，缩放1.0倍
        Imgproc.warpAffine(img1,mixed,affineTrans,img1.size(),Imgproc.INTER_NEAREST);
        imshow("mixed",mixed);
        waitKey();
    }
}
