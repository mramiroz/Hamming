import java.lang.Math;
import java.util.Arrays;

import javax.lang.model.util.ElementScanner6;

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

    static int numParidades(int lenMsg)
    {
        double p = 1;

        while (Math.pow(2, p) < p + lenMsg + 1)
            p++;
        return ((int)p);
    }

    static int[] calcParidades(int[] input, int lenMsg)
    {
        int i = 1;
        int x = 0;
        int p = 0;
        int count = 0;
        
        while(i < input.length)
        {
            if (input[i] == 2)
            {
                x = i;
                while (x < input.length)
                {
                    while (p < i && x < input.length)
                    {
                        if (input[x++] == 1)
                            count++;
                        p++;
                    }
                    x += i;
                    p = 0;
                }
                if (count % 2 != 0)
                    input[i] = 1;
                else
                    input[i] = 0;
                count = 0;
            }
            i++;
        }
        return (input);
    }
    static int[] Sender(int lenMsg)
    {
        int[] mensaje = mensaje(lenMsg);
        int numPar = numParidades(lenMsg);
        int[] out = new int[lenMsg + numPar + 1];
        int i = 1;
        int m = 0;
        int p = 0;
        int count = 0;

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
        i = 0;
        System.out.println(Arrays.toString(out));
        out = calcParidades(out, lenMsg);
        while (i < out.length)
            if(out[i++] == 1)
                count++;
        if(count % 2 != 0)
            out[0] = 1;

        return (out);
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Sender(11)));
    }
}

