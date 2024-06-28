public class Sorting
{
    public static int sumRange(int[] a, int min, int max)
    {
        int total = 0;
        for (int i = min; i < max; i++)
        {
            total += a[i];
        }
        return total;
    }

}
