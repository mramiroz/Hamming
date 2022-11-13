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

    static int[] Sender(int lenMsg)
    {
        int[] mensaje = mensaje(lenMsg);
        int numPar = calcParidades(lenMsg);
        int[] out = new int[lenMsg + numPar + 1];
        int i = 1;
        int m = 0;
        int p = 0;

        while(i < out.length)
        {
            if (Math.pow(2, p) == i)
            {
                out[i] = 2;
                p++;
            }
            else
                out[i] = mensaje[m++];
            i++;       
        }
        return (out);
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Sender(12)));
    }
}

