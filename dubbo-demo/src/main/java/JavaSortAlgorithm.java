import java.util.Arrays;

/**
 * @Auther: LiRuiChuan
 * @Date: 2020/3/20 11:02
 * @Description:
 */
public class JavaSortAlgorithm {

    public static void main(String[] args) {

        int[] arr = {5,3,9,1,2,10,20,13,4,17};

        //maoPao(arr);

        //xuanze(arr);

        //charu(arr);

        //xier(arr);

        guibing(arr);

    }

    /**
     * 冒泡算法
     * a、冒泡排序，是通过每一次遍历获取最大/最小值
     * b、将最大值/最小值放在尾部/头部
     * c、然后除开最大值/最小值，剩下的数据在进行遍历获取最大/最小值
     */
    public static void maoPao(int[] arr){

        for(int i = 0; i < arr.length;i++){
            for(int j = 0; j < arr.length - i -1 ;j++){
                //比较相邻两个数 如果 前者小于后者则交换位置
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择算法
     * a、将第一个值看成最小值
     *b、然后和后续的比较找出最小值和下标
     *c、交换本次遍历的起始值和最小值
     *d、说明：每次遍历的时候，将前面找出的最小值，看成一个有序的列表，后面的看成无序的列表，然后每次遍历无序列表找出最小值
     */
    public static void xuanze(int[] arr){

        for(int i = 0 ; i <arr.length ; i++){
            //假设第一个数为最小值
            int min = arr[i];
            //记录最小值索引
            int index = i;
            for(int j = i+1;j<arr.length;j++){
                //发现最小值则交换
                if(min > arr[j]){
                    min = arr[j];
                    index = j;
                }
            }
            //然后将最小值与本次循环的，开始值交换
            int temp = arr[i];
            arr[i] = min;
            arr[index] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入算法
     * a、默认从第二个数据开始比较。
     *b、如果第二个数据比第一个小，则交换。然后在用第三个数据比较，如果比前面小，则插入（交换）。否则，退出循环
     *、说明：默认将第一数据看成有序列表，后面无序的列表循环每一个数据，如果比前面的数据小则插入（交换）。否则退出
     */
    public static void charu(int[] arr){

        for(int i = 1 ;i < arr.length; i++){

            for(int j = i; j > 0; j--){
                //如果当前数比前一个数小 则交换 否则退出此次循环
                if(arr[j] <arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }else{
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔算法
     *a、基本上和插入排序一样的道理
     *b、不一样的地方在于，每次循环的步长，通过减半的方式来实现
     *c、说明：基本原理和插入排序类似，不一样的地方在于。通过间隔多个数据来进行插入排序。
     */
    public static void xier(int[] arr){

        int gap = arr.length/2;

        while(gap > 0){
            //内部进行插入排序
            for(int i = gap; i < arr.length;i++){
                for(int j = i; j > 0 && j - gap >= 0; j-=gap){
                    //比较两个相隔gap 的两个数据的大小
                    if(arr[j] < arr[j -gap]){
                        int temp = arr[j];
                        arr[j] = arr[j-gap];
                        arr[j-gap] = temp;
                    }else{
                        break;
                    }
                }
            }
            gap/=2;
        }
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 归并算法
     * a、将列表按照对等的方式进行拆分
     *b、拆分小最小快的时候，在将最小块按照原来的拆分，进行合并
     *c、合并的时候，通过左右两块的左边开始比较大小。小的数据放入新的块中
     *d、说明：简单一点就是先对半拆成最小单位，然后将两半数据合并成一个有序的列表。
     */
    public static void guibing(int[] arr){
        mergeSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    //两路归并算法，两个排好序的子序列合并为一个子序列
    public static void merge(int []a,int left,int mid,int right){
        int []tmp=new int[a.length];//辅助数组
        int p1=left,p2=mid+1,k=left;//p1、p2是检测指针，k是存放指针


        while(p1<=mid && p2<=right){
            if(a[p1]<=a[p2])
                tmp[k++]=a[p1++];
            else
                tmp[k++]=a[p2++];
        }

        while(p1<=mid) tmp[k++]=a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while(p2<=right) tmp[k++]=a[p2++];//同上

        //复制回原素组
        for (int i = left; i <=right; i++)
            a[i]=tmp[i];
    }

    public static void mergeSort(int [] a,int start,int end){
        if(start<end){//当子序列中只有一个元素时结束递归
            int mid=(start+end)/2;//划分子序列
            mergeSort(a, start, mid);//对左侧子序列进行递归排序
            mergeSort(a, mid+1, end);//对右侧子序列进行递归排序
            merge(a, start, mid, end);//合并
        }
    }


    /**
     * 快速算法
     * a、确认列表第一个数据为中间值，第一个值看成空缺（低指针空缺）。
     *b、然后在剩下的队列中，看成有左右两个指针（高低）。
     *c、开始高指针向左移动，如果遇到小于中间值的数据，则将这个数据赋值到低指针空缺，并且将高指针的数据看成空缺值（高指针空缺）。然后先向右移动一下低指针，并且切换低指针移动。
     *d、当低指针移动到大于中间值的时候，赋值到高指针空缺的地方。然后先高指针向左移动，并且切换高指针移动。重复c、d操作。
     *e、直到高指针和低指针相等时退出，并且将中间值赋值给对应指针位置。
     *f、然后将中间值的左右两边看成行的列表，进行快速排序操作。
     */
    public static void kusisu(int[] arr){

        //快速排序
        int low = 0;
        int high = arr.length - 1;
        quickSort(arr, low, high);
    }

    /**
     * 快速排序算法
     * @param arr
     * @param low
     * @param high
     */
    private static void quickSort(int[] arr, int low, int high){
        //相同位置时退出
        if(high - low < 1){
            return;
        }
        //默认第一个为中间值
        int midValue = arr[low];
        //标记，从高指针开始，还是低指针（默认高指针）
        boolean flag = true;
        //记录指针的其实位置
        int start = low;
        int end = high;

        while(true){

            //先从右向左移动
            if(flag){
                //当值比中间值大时 继续寻找
                if(arr[high] > midValue){
                    high-- ;
                }else if(arr[high] < midValue){
                    //如果小于，则覆盖最开始的低指针值，并且移动低指针，标志位改成从低指针开始移动
                    arr[low] = arr[high];
                    low++;
                    flag = false;
                }
            }else{
                //当值比中间值小时 继续寻找
                if(arr[low] < midValue){
                    low++;
                }else if(arr[low] > midValue){
                    //如果低指针的值大于中间值，则覆盖高指针停留时的数据，并向左移动高指针。切换为高指针移动
                    arr[high] = arr[low];
                    high--;
                    flag = true;
                }
            }
            //当两个指针的位置相同时，则找到了中间值的位置，并退出循环
            if (low == high) {
                arr[low] = midValue;
                break;
            }
        }
        //然后出现有，中间值左边的小于中间值。右边的大于中间值。
        //然后在对左右两边的列表在进行快速排序
        quickSort(arr, start, low -1);
        quickSort(arr, low + 1, end);
    }
}
