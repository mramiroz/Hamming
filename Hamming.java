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

    static int calcParidades(int lenMsg)
    {
        double p = 1;

        while (Math.pow(2, p) < p + lenMsg + 1)
            p++;
        return ((int)p);
    }
    public static void main(String[] args) {
        System.out.println(calcParidades(11));
    }
}

