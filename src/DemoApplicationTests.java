
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.COLOR_RGB2GRAY;
import static org.opencv.imgproc.Imgproc.cvtColor;


class DemoApplicationTests {
    public static void main(String[] args) throws Exception {
        System.loadLibrary("opencv_java455");
        // 读取图像
        Mat image = imread("D:\\Van Gogh\\Van Gogh1.jpg");
        Mat grayScale=imread("D:\\Van Gogh\\Van Gogh1.jpg", Imgcodecs.IMREAD_GRAYSCALE);
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Van Gogh", image);
        //等待退出，delay为等待时间，如果不填则一直等待
        waitKey(0);
        imshow("Van Gogh", grayScale);
        //等待退出，delay为等待时间，如果不填则一直等待
        waitKey(0);
        // 创建输出单通道图像
        //Mat grayImage = new Mat(image.rows(), image.cols(), CvType.CV_8SC1);
        // 进行图像色彩空间转换
        //cvtColor(image, grayImage, COLOR_RGB2GRAY);

    }
}
