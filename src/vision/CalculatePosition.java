package vision;


public class CalculatePosition {


    public static int[] SearchButton(int windowWidth, int windowHeight)  {
        int x = windowWidth / 5 + 500;
        int y = windowHeight / 5;
        int width = 100;
        int height = 30;
        int[] result = new int[4];
        result[0] = x;
        result[1] = y;
        result[2] = width;
        result[3] = height;
        return result;
    }
    public static int[] SearchBar(int windowWidth, int windowHeight)  {
        int x = windowWidth / 5;
        int y = windowHeight / 5;
        int width = 500;
        int height = 30;
        int[] result = new int[4];
        result[0] = x;
        result[1] = y;
        result[2] = width;
        result[3] = height;
        return result;
    }
    public static int[] SearchHint(int windowWidth, int windowHeight) {
        int x = windowWidth / 5;
        int y = windowHeight / 5;
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
