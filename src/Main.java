public class Main {
    public static void main(String[] args)
    {
        Tick s1 = new Tick();
        Thread t1 = new Thread(s1);
        Thread t2 = anonymousInnerClass();
        t1.start();
        t2.start();
        try
        {
            t1.join();
            t2.join();
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
        int[] first = new int[100000];
        int[] second = new int[100000];
        int[] third = new int[100000];
        System.out.println("****** sum without threads *******");
        for (int x = 1; x < 3; x++) {
            System.out.println("number of iteration :" + x);
            double start = System.currentTimeMillis();
            for (int i = 0; i < x * 3000; i++)
            {
                first[i] = i;
            }
            System.out.println(summer(first));
            double end = System.currentTimeMillis();
            double Result = end - start;
            System.out.println(Result);
        }
        System.out.println("****** sum with two thread *******");
        for (int x = 1; x < 3; x++)
        {
            System.out.println("number of iteration :" + x);
            double start = System.currentTimeMillis();
            for (int i = 0; i < x * 1000; i++)
            {
                second[i] = i;
            }
            System.out.println(sum(second));
            double end = System.currentTimeMillis();
            double Result = end - start;
            System.out.println(Result);
        }

        System.out.println("********** sum with four threads **********");
        for (int x = 1; x < 8; x++)
        {
            System.out.println("number of iteration :" + x);
            double start = System.currentTimeMillis();
            for (int i = 0; i < x * 1000; i++) {
                third[i] = i;
            }
            System.out.println(sumWithFourThread(third));
            double end = System.currentTimeMillis();
            double Result = end - start;
            System.out.println(Result);
        }



    }

    public static Thread anonymousInnerClass( )
    {
        return new Thread(new Runnable()
        {
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
                    System.out.println("Tock");
                }
            }
        });

    }
    public static int summer(int [] array)
    {
        int total=0;
        for(int i=0 ; i< array.length ; i++)
        {
            total = total + array[i];
        }
        return total;
    }
    public static int sum(int[] a)
    {
        Summer firstHalf = new Summer(a, 0, a.length/2);
        Summer secondHalf = new Summer(a, a.length/2, a.length);
        Thread t1 = new Thread(firstHalf);
        t1.start();
        Thread t2 = new Thread(secondHalf);
        t2.start();
        try
        {
            t1.join();
            t2.join();
        }
        catch (InterruptedException ie) {}
        return firstHalf.getSum() + secondHalf.getSum();
    }
    public static int sumWithFourThread(int[] a)
    {
        int threadCount = 4;
        int len = (int) Math.ceil(1.0 * a.length / threadCount);
        Summer[] summers = new Summer[threadCount];
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++)
        {
            summers[i] = new Summer(a, i*len, (i+1)*len);
            threads[i] = new Thread(summers[i]);
            threads[i].start();
        }
        try
        {
            for (Thread t : threads)
            {
                t.join();
            }
        }
        catch (InterruptedException ie)
        {
            System.out.println(ie.getMessage());
        }
        int total = 0;
        for (Summer summer : summers)
        {
            total += summer.getSum();
        }
        return total;
    }
}