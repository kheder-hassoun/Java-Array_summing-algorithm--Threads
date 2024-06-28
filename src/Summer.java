public class Summer implements Runnable
{
    private int[] a;
    private int min, max, sum;
    public Summer(int[] a, int min, int max)
    {
        this.a = a;
        this.min = min;
        this.max = Math.min(max, a.length);
    }
    public int getSum()
    {
        return sum;
    }
    public void run()
    {
        sum = Sorting.sumRange(a, min, max);
    }
}