import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;
public class lightStrength {
    public static void main(String[] args) {
        System.loadLibrary("opencv_java455");
        Mat mat=imread("D:\\Van Gogh\\img.jpg");
        imshow("adwwd",mat);
        waitKey();
        //用灰度模型进行均衡化
        Mat mat1=imread("D:\\Van Gogh\\test.jpg",IMREAD_GRAYSCALE);
        Imgproc.equalizeHist(mat1,mat1);
        imshow("light",mat1);
        waitKey();
        //用YUV模型中的亮度Y进行均衡化
        Mat matYUV=mat;
        Imgproc.cvtColor(matYUV,matYUV,Imgproc.COLOR_BGR2YUV);
        List<Mat> list=new ArrayList();
        Core.split(matYUV,list);;
        Imgproc.equalizeHist(list.get(0),list.get(0));
        Core.merge(list,matYUV);
        Imgproc.cvtColor(matYUV,matYUV,Imgproc.COLOR_YUV2BGR);
        imshow("YUV",matYUV);
        waitKey();
        //
        Mat color=mat;
       // Imgproc.cvtColor(color,color,Imgproc.COLOR_RGB2YUV);
        List<Mat> list1=new ArrayList();
        Core.split(color,list1);
        imshow("color",color);
        Imgproc.equalizeHist(list1.get(0),list1.get(0));
        Imgproc.equalizeHist(list1.get(1),list1.get(1));
        Imgproc.equalizeHist(list1.get(2),list1.get(2));
        Core.merge(list1,color);
       // Imgproc.cvtColor(color,color,Imgproc.COLOR_YUV2BGR);
        imshow("color",color);
        waitKey();
    }
}
