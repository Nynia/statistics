import org.apache.taglibs.standard.lang.jstl.StringLiteral;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ridiculous on 2016/10/28.
 */
public class commonTest {
    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {

        List<String> stringList1 = new ArrayList<String>();
        stringList1.add("1");
        stringList1.add("2");

        List<String> stringList2 = new ArrayList<String>();

        stringList2.add("3");
        stringList2.add("4");
        stringList2.addAll(0,stringList1);

        for (String str: stringList2) {
            System.out.println(str);
        }
    }
}