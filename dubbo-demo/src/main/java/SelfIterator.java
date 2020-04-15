/**
 * @Auther: LiRuiChuan
 * @Date: 2020/3/19 20:30
 * @Description:
 */
public interface SelfIterator<E> {


    E next() throws Exception;

    boolean hashNext();
}
