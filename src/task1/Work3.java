package task1;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;
public class Work3 {

    public static void main(String[] args) {
        System.loadLibrary("opencv_java455");
        Mat Gx=initGx();
        Mat Gy=initGy();
        Mat Gxx=initGxx();
        Mat Gyy=initGyy();
        Mat src=imread("D:\\Van Gogh\\22.png");
        imshow("img1",src);
        waitKey();
        Mat K1x=new Mat();
        Mat K1y=new Mat();
        Imgproc.filter2D(src,K1x,-1,Gx);
        Imgproc.filter2D(src,K1y,-1,Gy);
        Mat K1=new Mat();
        Core.add(K1x,K1y,K1);
        imshow("img2",K1);
        waitKey();

        Mat K2x=new Mat();
        Mat K2y=new Mat();
        Imgproc.filter2D(src,K2x,-1,Gxx);
        Imgproc.filter2D(src,K2y,-1,Gyy);
        Mat K2=new Mat();
        Core.add(K2x,K2y,K2);
        imshow("img3",K2);
        waitKey();


    }
    public static Mat initGx(){
        Mat Gx=new Mat(3,3, CvType.CV_16SC1, new Scalar(0));//8位unsigned和1个channel
        Gx.put(0,0,-1);
        Gx.put(0,2,1);
        Gx.put(1,0,-2);
        Gx.put(1,2,2);
        Gx.put(2,0,-1);
        Gx.put(2,2,1);
        return Gx;
    }
    public static Mat initGy(){
        Mat Gy=new Mat(3,3, CvType.CV_16SC1,new Scalar(0));
        Gy.put(0,0,1);
        Gy.put(0,1,2);
        Gy.put(0,2,1);
        Gy.put(2,0,-1);
        Gy.put(2,1,-2);
        Gy.put(2,2,-1);
        return Gy;
    }
    public static Mat initGxx(){
        Mat Gxx=new Mat(3,3,CvType.CV_16SC1,new Scalar(0));
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(j!=1){
                    Gxx.put(i,j,-1);
                }
            }
        }
        return Gxx;
    }

    public static Mat initGyy(){
        Mat Gyy=new Mat(3,3,CvType.CV_16SC1,new Scalar(0));
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(i==1){
                    Gyy.put(i,j,1);
                }else if(i==2){
                    Gyy.put(i,j,-1);
                }
            }
        }
        return Gyy;
    }
}
