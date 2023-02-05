import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


import java.util.ArrayList;
import java.util.List;

import static org.opencv.highgui.HighGui.*;
import static org.opencv.imgcodecs.Imgcodecs.*;

public class ImgRead {
    public static void main(String[] args) {
        System.loadLibrary("opencv_java455");
        Mat mat=imread("D:\\Van Gogh\\Van Gogh6.jpg");
        Mat mat1=new Mat();
        List<Mat>list=new ArrayList<Mat>();
        Core.split(mat,list);
        list.forEach(temp->{
            imwrite("D:\\Van Gogh\\"+System.currentTimeMillis()+".jpg",temp);
        });
        Core.merge(list,mat1);
        imshow(null,mat);
        waitKey();
        imshow(null,mat1);
        waitKey();
    }
}
