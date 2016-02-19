package com.gridworld.book_code;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tom on 1/7/2016.
 */
public class ProbabCalc {
    public List<Integer> dataList;
//    public static int numLoops;

    public ProbabCalc() {
    }

    public static Integer userOperation() {
//        override this method by own method that produces single result
        return 0;
    }

    public List<Integer> loopOperation(int loops) {
        List<Integer> dataList = new LinkedList<>();
        for (int i = 0; i < loops; i++) {
            dataList.add(userOperation());
        }
        return dataList;
    }

    public void createDataList(int numLoops) {
        dataList.addAll(loopOperation(numLoops));
    }

    public Integer getMean() {
        Integer sum = 0;
        for (Integer num : dataList) {
            sum += num;
        }
        return sum / dataList.size();
    }
}
