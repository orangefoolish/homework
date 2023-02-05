package task2;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class Work1 {
    static int max=80;
    public static void main(String[] args) {
        System.loadLibrary("opencv_java455");

        Mat kernelX = new Mat(new Size(3,3), CvType.CV_32F, new Scalar(255));
        kernelX.put(0, 0, -1); kernelX.put(0, 1, 0); kernelX.put(0, 2, 1);
        kernelX.put(1, 0, -2); kernelX.put(1, 1, 0); kernelX.put(1, 2, 2);
        kernelX.put(2, 0, -1); kernelX.put(2, 1, 0); kernelX.put(2, 2, 1);
        Mat kernelY = new Mat(new Size(3,3), CvType.CV_32F, new Scalar(255));
        kernelY.put(0, 0, -1); kernelY.put(0, 1, -2); kernelY.put(0, 2, -1);
        kernelY.put(1, 0, 0); kernelY.put(1, 1, 0); kernelY.put(1, 2, 0);
        kernelY.put(2, 0, 1); kernelY.put(2, 1, 2); kernelY.put(2, 2, 1);

        Mat sourceMat=imread("D:\\Van Gogh\\2.1.png",IMREAD_GRAYSCALE);
        Mat result=calc(kernelX,kernelY,sourceMat);
        imshow("",sourceMat);
        waitKey();
        imshow("",result);
        waitKey();


    }
    static Mat calc(Mat kernelX, Mat kernelY, Mat sourceMat){
        Mat result=new Mat(sourceMat.height(),sourceMat.width(),CvType.CV_8UC1,new Scalar(0));
        double x,y,r;
        for(int i=1;i<sourceMat.height()-1;i++){//行
            for(int j=1;j<sourceMat.width()-1;j++){//列
                x=sourceMat.get(i-1,j-1)[0]*kernelX.get(0,0)[0]+sourceMat.get(i-1,j+1)[0]*kernelX.get(0,2)[0]+
                        sourceMat.get(i,j-1)[0]*kernelX.get(1,0)[0]+sourceMat.get(i,j+1)[0]*kernelX.get(1,2)[0]+
                        sourceMat.get(i+1,j-1)[0]*kernelX.get(2,0)[0]+sourceMat.get(i+1,j+1)[0]*kernelX.get(2,2)[0];
                y=sourceMat.get(i-1,j-1)[0]*kernelY.get(0,0)[0]+sourceMat.get(i-1,j)[0]*kernelY.get(0,1)[0]+
                        sourceMat.get(i-1,j+1)[0]*kernelY.get(0,2)[0] +sourceMat.get(i+1,j-1)[0]*kernelY.get(2,0)[0]
                        +sourceMat.get(i+1,j)[0]*kernelY.get(2,1)[0] +sourceMat.get(i+1,j+1)[0]*kernelY.get(2,2)[0];
                r=Math.sqrt(x*x+y*y);
                result.put(i,j,r);
                if(r>max){
                    result.put(i,j,r);
                }
            }
        }
        return result;
    }
}
