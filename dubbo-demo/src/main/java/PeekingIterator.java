/**
 * @Auther: LiRuiChuan
 * @Date: 2020/3/19 20:31
 * @Description:
 */
public class PeekingIterator implements SelfIterator {

    private int cursor;

    private Object[] data;

    PeekingIterator(Object[] data){

        this.data = data;
    }

    @Override
    public Object next() throws Exception{
        int i = cursor;
        if(cursor > data.length){
            throw new Exception("no such element");
        }
        cursor = i + 1;
        return data[i];
    }

    @Override
    public boolean hashNext() {
        return cursor != data.length;
    }

    public Object peek() throws Exception{
        if(hashNext()){
            return data[cursor +1];
        }
        throw new Exception("no such element");
    }
}
