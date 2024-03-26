package phone;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created on:  Jan 27, 2024
 * Ref:
 */

public class PrimeVideoCompletion {

    public static void main(String[] args) {

    }

    public static int minimumTimeSpent(List<Integer> comedyReleaseTime, List<Integer> comedyDuration, List<Integer> dramaReleaseTime, List<Integer> dramaDuration) {


        Map<Integer, Integer> comedy = new TreeMap<>();
        Map<Integer, Integer> drama = new TreeMap<>();

        for(int i =0; i< comedyDuration.size() ; i++){
            comedy.put(comedyReleaseTime.get(i), comedyDuration.get(i));
        }

        for(int i=0; i< dramaDuration.size(); i++){
            drama.put(dramaReleaseTime.get(i), dramaDuration.get(i));
        }


        int minFinishTime = Integer.MAX_VALUE;

        minFinishTime = getMinFinishTime(comedy, drama, minFinishTime);
        minFinishTime = getMinFinishTime(drama, comedy, minFinishTime);

        return minFinishTime;

    }

    private static int getMinFinishTime(Map<Integer, Integer> comedy, Map<Integer, Integer> drama, int preMinValue){
        int minValue = preMinValue;

        for(int i: comedy.keySet()){
            if(i > minValue) break;

            for(int j: drama.keySet()){
                // We already found the best Minimum time to complete.
                if(j > minValue) break;
                if(j+ drama.get(j) >= minValue) continue;
                // Check if I can watch the entry 2 movie after watching entry 1.
                int comedyFinishTime = i + comedy.get(i);
                int currentFinishTime = 0;
                if(j < comedyFinishTime) {
                    currentFinishTime = drama.get(j) + comedyFinishTime;
                }else{
                    currentFinishTime = j + drama.get(j);
                }
                minValue = Math.min(minValue, currentFinishTime);
            }
        }

        return minValue;
    }

}
