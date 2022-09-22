package lesson1.homework.src.com.hsai.sort;

public class MergeSort {
    public static void mergeSort(int[] array)
    {
        int mergeParts = 1;
        int[] mergeArray = new int[array.length];
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] < array[i-1])
            {
                mergeArray[mergeParts] = i;
                mergeParts++;
            }
        }
        while (mergeParts > 1)
        {
            for (int j = 0; j < mergeParts/2; j++)
            {
                int part1 = mergeArray[j];
                int part2 = mergeArray[j+1];
                int end = 0;
                if (j + 2 < mergeParts) end = mergeArray[j+2];
                else end = array.length;
                int k = 0;
                int[] merge = new int[end - part1];
                int idx1 = part1;
                int idx2 = part2;
                while (idx1 != part2 || idx2 != end)
                {
                    if (idx1 == part2)
                    {
                        merge[k] = array[idx2];
                        idx2++;
                    }
                    else if (idx2 == end)
                    {
                        merge[k] = array[idx1];
                        idx1++;
                    }
                    else if (array[idx1] < array[idx2])
                    {
                        merge[k] = array[idx1];
                        idx1++;
                    }
                    else
                    {
                        merge[k] = array[idx2];
                        idx2++;
                    }
                    k++;
                }
                for (int z = 0; z < k; z++) array[mergeArray[j]+z] = merge[z];
                for (int y = j+1; y < mergeParts - 1; y++)
                {
                    mergeArray[y] = mergeArray[y+1];
                }
                mergeParts--;
            }
        }
    }
}