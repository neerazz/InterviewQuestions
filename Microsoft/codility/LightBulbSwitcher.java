package codility;/*
Time - O(n)
Space - O(1)
*/

public class LightBulbSwitcher {

    public static int lightBulbSwitcher3(int[] bulbs) {
        int moments = 0, maxLightBulb = 0, lightBulbs = 0;
        for (int bulb : bulbs) {
            maxLightBulb = Math.max(maxLightBulb, bulb);
            lightBulbs++;
            if (lightBulbs == maxLightBulb) moments += 1;
        }
        return moments;
    }


    public static void main(String[] args) {
        System.out.println(lightBulbSwitcher3(new int[]{2, 1, 3, 5, 4}));
    }
}
  

                                 
