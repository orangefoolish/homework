package task2;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class work2 {
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
        Mat sourceMat=imread("D:\\Van Gogh\\Hough.jpg",IMREAD_GRAYSCALE);
        Mat result=calc(kernelX,kernelY,sourceMat);
        imshow("",sourceMat);
        waitKey();
        imshow("",result);
        waitKey();
        Mat houghLine=new Mat();
        Imgproc.HoughLines(result,houghLine ,1, Math.PI / 180, 250, 0, 0, 0, 10);
        /*
        rho：极坐标系中半径r的搜索步长（累加值），如果为1表示每次累计1个像素；
        theta：θ的精度。
        threshold：阈值，得票数高于该值的线才被认为是线，由于投票数取决于线上的点数，所以它代表了应该被检测到的线的最小点数
        srn/stn：对于多尺度霍夫变换，rho为极坐标系中累加值的粗粒度表示，srn/stn为细粒度的累加值；
        */
        for (int x = 0; x < houghLine.rows(); x++) {//这里遍历出的直线上的每个点就是直线
            double[] vec = houghLine.get(x, 0);
            double r = vec[0]; //就是圆的半径r
            double angle = vec[1]; //就是直线的角度
            Point pt1 = new Point();
            Point pt2 = new Point();
            double a = Math.cos(angle);
            double b = Math.sin(angle);
            double x0 = a * r;
            double y0 = b * r;
            int lineLength = 1000;
            pt1.x = Math.round(x0 + lineLength * (-b));
            pt1.y = Math.round(y0 + lineLength * (a));
            pt2.x = Math.round(x0 - lineLength * (-b));
            pt2.y = Math.round(y0 - lineLength * (a));

            if (angle >= 0) {
                Imgproc.line(sourceMat, pt1, pt2, new Scalar(255, 0, 0), 1, Imgproc.LINE_4, 0);
                //在图中绘制直线的函数
            }
        }
        imshow("",sourceMat);
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
                if(r>70){
                    result.put(i,j,r);
                }

            }
        }
        return result;
    }
}
