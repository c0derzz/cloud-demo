package com.self.cloud.demo.demo;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Created by liruichuan on 2018/8/31.
 */
public class Utils {

    public void doForkJoinTask(){

    }


}

/**
 * 带有返回值的ForkJoinTask 子类
 * 对数组求和
 */
class MyRecursiveTask extends RecursiveTask<Long> {

    //每个小任务执行的最大数量
    static final int THRESHOLD = 100;

    private int start;
    private int end;
    private long[] array;

    public MyRecursiveTask(int start, int end, long[] array) {
        this.start = start;
        this.end = end;
        this.array = array;
    }

    @Override
    protected Long compute() {
        long sum = 0 ;

        //当任务足够小则直接执行计算
        if(end - start <= THRESHOLD){
            for(int i = 0 ; i < array.length ; i++){
                sum += array[i];
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(String.format("compute %d %d = %d", start, end, sum));
        }else{

            int middle = (end + start) / 2 ;

            MyRecursiveTask subtask1 = new MyRecursiveTask(start, middle,this.array);
            MyRecursiveTask subtask2 = new MyRecursiveTask(middle, end,this.array);
            invokeAll(subtask1, subtask2);

            Long subresult1 = subtask1.join();
            Long subresult2 = subtask2.join();

            sum = subresult1 + subresult2;
            System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + sum);
        }

        return sum;
    }
}

/**
 * 不带有返回值的ForkJoinTask 子类
 */
class MyRecursiveAction extends RecursiveAction {

    @Override
    protected void compute() {

    }
}
