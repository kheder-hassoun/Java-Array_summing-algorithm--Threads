public class Tick implements Runnable
{
    public Tick()
    {
    }

    @Override
    public void run()
    {
        for(int i=1 ; i<=10 ; i++)
        {
            try
            {
                Thread.currentThread().sleep(i%2 + 1);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            System.out.println("Tick");
        }

    }
}
