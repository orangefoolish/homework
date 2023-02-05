package task1;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class Work1 {
    static double coefficient=0.5;//伽马变换s=cr^y中的常数y
    public static void main(String[] args) {
        System.loadLibrary("opencv_java455");
        Mat mat=imread("D:\\Van Gogh\\Van Gogh3.jpg");
        Mat hsvMat=new Mat();
        Mat grayMat=imread("D:\\Van Gogh\\22.png",IMREAD_GRAYSCALE);
        Imgproc.cvtColor(mat,hsvMat,Imgproc.COLOR_RGB2HSV);
        imshow("beforHsvImg",hsvMat);
        waitKey();
        List<Mat> hsvColor=new ArrayList();
        Core.split(hsvMat,hsvColor);
        gamma_change(hsvColor.get(2));
        Core.merge(hsvColor,hsvMat);
        imshow("hsvImg",hsvMat);
        waitKey();
        imshow("grayImgBefore",grayMat);
        waitKey();
        gamma_change(grayMat);
        imshow("grayImg",grayMat);
        waitKey();
    }
    public static void gamma_change(Mat mat){
        for(int i=0;i<mat.height();i++){
            for(int j=0;j<mat.width();j++){
                if(mat.get(i,j)!=null){
                    mat.put(i,j,Math.pow(mat.get(i,j)[0]/255.0,coefficient)*255);
                }
            }
        }
    }
}
