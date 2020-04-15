import org.openjdk.jol.info.ClassLayout;

/**
 * @Auther: LiRuiChuan
 * @Date: 2020/4/1 23:13
 * @Description:
 */
public class PrintClass {

    public static void main(String[] args) {
        Object o = new Object();

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Object[] o_arry = new Object[]{};

        System.out.println(ClassLayout.parseInstance(o_arry).toPrintable());
    }
}
