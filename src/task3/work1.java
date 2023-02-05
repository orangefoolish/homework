package task3;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class work1 {
    public static void main(String[] args) {
        System.loadLibrary("opencv_java455");
        Mat src = imread("D:\\Van Gogh\\Van Gogh2.jpg",IMREAD_GRAYSCALE);
        imshow("src",src);
        waitKey();
        Mat result=DFT(src);
        imshow("",result);
        waitKey();
        /*Mat low=LowFrequency(result,200);
        imshow("",low);
        waitKey();*/
        Mat high=HighFrequency(result,200);
        imshow("",high);
        waitKey();


    }
    static Mat DFT(Mat src){
        Mat padded = new Mat();                     // 将输入图像扩展到最佳大小

        // 获取图像的傅里叶最优尺寸大小，主要是为了提高运算速度。
        int m = Core.getOptimalDFTSize( src.rows() );
        int n = Core.getOptimalDFTSize( src.cols() );

        // 在边框上添加零值，将图像扩充为最优尺寸；
        Core.copyMakeBorder(src, padded, 0, m - src.rows(), 0, n - src.cols(), Core.BORDER_CONSTANT, Scalar.all(0));

        /*
         * 傅里叶变换的结果复数。这意味着对于每个像素值，结果是两个值。此外，频域范围比空间范围大得多。
         * 因此，通常至少以浮点格式存储这些数据，并用另一个通道将其展开，以保存复数值。
         * */
        List<Mat> planes = new ArrayList<>();
        padded.convertTo(padded, CvType.CV_32F);
        planes.add(padded);
        planes.add(Mat.zeros(padded.size(), CvType.CV_32F));
        Mat complexI = new Mat();
        Core.merge(planes, complexI);

        // 执行傅里叶变换
        Core.dft(complexI, complexI); // 输入与输出在相同的 Mat 对象


        // => log(1 + sqrt(Re(DFT(I))^2 + Im(DFT(I))^2))
        Core.split(complexI, planes);//将变换后的通道分离
        // planes.get(1) = Im(DFT(I))

        // 计算二维矢量的幅值：幅值 = sqrt(Re(DFT(I))^2 + Im(DFT(I))^2)
        Core.magnitude(planes.get(0), planes.get(1), planes.get(0));
        Mat result = planes.get(0);
        Mat matOfOnes = Mat.ones(result.size(), result.type());
        Core.add(matOfOnes, result, result);
        Core.log(result, result); // 通过对数转换实现尺度缩放

        // 在扩充边界时，在图像中添加了额外的像素，需要将这部分像素剔除。
        result = result.submat(new Rect(0, 0, result.cols() & -2, result.rows() & -2));// 如果频谱的行数或列数为奇数，则裁剪频谱
        // 重新排列傅里叶图像的象限，使原点位于图像中心
        int cx = result.cols()/2;
        int cy = result.rows()/2;
        Mat q0 = new Mat(result, new Rect(0, 0, cx, cy));    // Top-Left - Create a ROI per quadrant
        Mat q1 = new Mat(result, new Rect(cx, 0, cx, cy));      // Top-Right
        Mat q2 = new Mat(result, new Rect(0, cy, cx, cy));      // Bottom-Left
        Mat q3 = new Mat(result, new Rect(cx, cy, cx, cy));         // Bottom-Right
        Mat tmp = new Mat();               // 交换象限：左上与右下互换
        q0.copyTo(tmp);
        q3.copyTo(q0);
        tmp.copyTo(q3);
        q1.copyTo(tmp);                    // 交换象限：右上与左下互换
        q2.copyTo(q1);
        tmp.copyTo(q2);
        result.convertTo(result, CvType.CV_8UC1);
        Core.normalize(result, result, 0, 255, Core.NORM_MINMAX, CvType.CV_8UC1); // 归一化
        return result;
    }
    static Mat LowFrequency(Mat src,int size){
        Mat fliter=new Mat(src.height(),src.width(), CvType.CV_8UC1,new Scalar(1));
        for(int i=0;i<src.height();i++){
            for(int j=0;j<src.width();j++) {
               if (!((i >= size && i <= src.height() - size) && (j >= size && j <= src.width() - size))) {
                    fliter.put(i, j, 0);
                }
            }
        }
        Mat result=new Mat();
        Core.multiply(src,fliter,result);
        return result;
    }
    static Mat HighFrequency(Mat src,int size){
        Mat fliter=new Mat(src.height(),src.width(), CvType.CV_8UC1,new Scalar(1));
        for(int i=0;i<src.height();i++){
            for(int j=0;j<src.width();j++) {
                if ((i >= size && i <= src.height() - size) && (j >= size && j <= src.width() - size)) {
                    fliter.put(i, j, 0);
                }
            }
        }
        Mat result=new Mat();
        Core.multiply(src,fliter,result);
        result.convertTo(result,CvType.CV_32F);
        Core.idft(result,result,0);
        result.convertTo(result, CvType.CV_8UC1);
        return result;
    }
    private static Mat antitransformImage(Mat complexImage, List<Mat> allPlanes) {
        Mat invDFT = new Mat();
        Core.idft(complexImage, invDFT, Core.DFT_SCALE | Core.DFT_REAL_OUTPUT, 0);
        Mat restoredImage = new Mat();
        invDFT.convertTo(restoredImage, CvType.CV_8U);
        if (allPlanes.size() == 0) {
            allPlanes.add(restoredImage);
        } else {
            allPlanes.set(0, restoredImage);
        }
        Mat lastImage = new Mat();
        Core.merge(allPlanes, lastImage);
        return lastImage;
    }

}
