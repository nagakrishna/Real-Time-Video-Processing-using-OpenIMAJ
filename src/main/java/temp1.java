/**
 * Created by Naga on 23-08-2016.
 */
public class temp1 {
    public static void main(String args[]){
        System.out.print(countPalin("hellolle"));
    }
    public static int countPalin(String str)
    {
        int numPalin = 0;
        for (int i = 2; i <= str.length(); i++)
        {
            for (int j = 0; j+i <= str.length(); j++)
            {
                System.out.println("j "+j);
                System.out.println("i "+i);
                String test = str.substring(j, i);

                if (checkPalin(test) && !test.equals(""))
                {
                    System.out.println("test "+test);
                    numPalin++;
                }
            }
        }

        return numPalin;
    }

    public static boolean checkPalin(String str)
    {
        StringBuffer sb = new StringBuffer(str);
        String rev = sb.reverse().toString();

        //System.out.println("str "+str);
        //System.out.println("rev "+rev);
        if (!str.equalsIgnoreCase(rev))
        {
            return false;
        }

        return true;
    }
}
