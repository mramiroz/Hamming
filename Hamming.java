import java.lang.Math;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.lang.model.util.ElementScanner6;
import javax.xml.transform.Source;

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
        System.out.println("Mensaje: " + Arrays.toString(array));
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

    static int[] validateGlobal(int[] arr)
    {
        int i = 1;
        int count = 0;
        while (i < arr.length)
        if(arr[i++] == 1)
            count++;
        if(count % 2 != 0)
            arr[0] = 1;
        else if(count % 2 == 0)
            arr[0] = 0;
        return (arr);
    }
    static int[] Sender(int lenMsg)
    {
        int[] mensaje = mensaje(lenMsg);
        int numPar = numParidades(lenMsg);
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
        out = calcParidades(out);
        out = validateGlobal(out);
        System.out.println("Sender: " + Arrays.toString(out));
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
        System.out.println("Numero de modificaciones: " + numeroMod);
        return (sender);
    }

    static int verError(int[] noise)
    {
        int error = 0;
        double p = 0;
        for (int i = 1; i < noise.length; i++)
        {
            if (Math.pow(2, p) == i)
            {
                if (noise[i] == 1)
                    error = error + i;
                p++;
            }
        }
        return(error);
    }

    static void Reciver(int[] noise)
    {
        int error = 0;
        int[] temp = new int[noise.length];
        int antesGlobal;

        System.out.println("Noise:  " + Arrays.toString(noise));
        antesGlobal = noise[0];
        for(int x = 0; x < noise.length; x++)
            temp[x] = noise[x];
        temp = calcParidades(temp);
        temp = validateGlobal(temp);
        error = verError(temp);
        if (error == 0)
        {
            noise = validateGlobal(noise);
            if(antesGlobal != noise[0])
                System.out.println("Error: " + error);
            else
                System.out.println("No hay ningun error");
        }
        else if(error > 0)
        {
            noise = validateGlobal(noise);
            if (noise[0] == antesGlobal)
                System.out.println("Hay dos errores");
            else if(noise[0] != antesGlobal)
                System.out.println("Error: " + error);
        }
    }
    public static void main(String[] args) {
        Reciver(Noise(Sender(4)));
    }
}

