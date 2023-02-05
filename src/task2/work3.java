package task2;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class work3 {
    public static void main(String[] args) {
        System.loadLibrary("opencv_java455");
        Mat sourceMat=imread("D:\\Van Gogh\\Van Gogh2.jpg",IMREAD_GRAYSCALE);
        imshow("",sourceMat);
        waitKey();
        Mat result = new Mat();
        Imgproc.threshold(sourceMat,result,127,255,Imgproc.THRESH_BINARY_INV);
        /*
        Imgproc.threshold(src,dst,thresh,maxval,type)
        第一个参数src：需要进行二值分割的输入图像；
        第二个参数：输出的二值图像；
        第三个参数，thresh为阙值
        第四个参数maxValue：最大灰度值，一般为255；
        第五个参数thresholdType：使用的阈值分割方式，只能输入THRESH_BINARY或THRESH_BINARY_INV，当使用其他阈值分割方式时会报错；二值分割后，需要是黑色背景、白色前景，根据目标是处于背景或前景来选择该参数；
        THRESH_BINARY：二值图像 = 原图 – 均值图像 - C > 0? 255 : 0
        THRESH_BINARY_INV: 二值图像 = 原图 – 均值图像 - C > 0? 0 : 255
         */
        imshow("",result);
        waitKey();

    }
}
