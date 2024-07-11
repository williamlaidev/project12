package vision;


public class CalculatePosition {

    public static int[] Search(int windowWidth, int windowHeight)  {
        int x = windowWidth / 2;
        int y = windowHeight / 2;
        int width = 100;
        int height = 30;
        int[] result = new int[4];
        result[0] = x;
        result[1] = y;
        result[2] = width;
        result[3] = height;
        return result;
    }
}
