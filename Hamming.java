import java.lang.Math;
import java.util.Arrays;

public class Hamming {
    static int[] mensaje(int len)
    {
        int numeroRandom;
        int array[] = new int[len];
        for(int i = 0; i < len; i++)
        {
            numeroRandom = (int)(Math.random()*2);
            array[i] = numeroRandom;
        }
        return (array);
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(mensaje(10)));
    }
}

