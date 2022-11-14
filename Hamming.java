import java.lang.Math;
import java.lang.reflect.Array;
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

    static int[] calcParidades(int[] input)
    {
        int i = 1;
        int x = 0;
        int p = 0;
        int pot = 0;
        int count = 0;
        
        while(i < input.length)
        {
            if ((int)Math.pow(2, pot) == i)
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
                pot++;
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
        out = calcParidades(out);
        while (i < out.length)
            if(out[i++] == 1)
                count++;
        if(count % 2 != 0)
            out[0] = 1;
        System.out.println(Arrays.toString(out));
        return (out);
    }

    static int[] Noise(int[] sender)
    {
        int numeroMod = (int)(Math.random()*3);
        int posicionAleatoria;

        for(int i = 0; i < numeroMod; i++)
        {
            posicionAleatoria = (int)(Math.random()*sender.length);
            if (sender[posicionAleatoria] == 1)
                sender[posicionAleatoria] = 0;
            else
                sender[posicionAleatoria] = 1;
        }
        System.out.println(numeroMod);
        //System.out.println(Arrays.toString(sender));
        return (sender);
    }

    static int Reciver(int[] Noise)
    {
        int changes = 0;
        int count = 0;
        int[] temp = Noise;

        System.out.println(Arrays.toString(Noise));
        calcParidades(temp);
        System.out.println(Arrays.toString(temp));
        for(int x = 1; x < Noise.length; x++)
            if (Noise[x] != temp[x])
                changes++;
        for (int i = 1; i < Noise.length; i++)
            if (Noise[i] == 1)
                count++;
        return (changes);
    }
    public static void main(String[] args) {
        System.out.println(Reciver(Noise(Sender(11))));
    }
}

