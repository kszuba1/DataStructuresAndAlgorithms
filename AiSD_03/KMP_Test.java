import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class KMP_Test {
    @Test
    public void tests(){
       ArrayList<Integer> indexes= KMP.KMP_Matcher("acbababaaababba","aba");
        Assert.assertEquals(3,indexes.size());
        Assert.assertEquals((Integer) 3,indexes.get(0));
        Assert.assertEquals(java.util.Optional.ofNullable(5), java.util.Optional.ofNullable(indexes.get(1)));
        Assert.assertEquals(java.util.Optional.ofNullable(9), java.util.Optional.ofNullable(indexes.get(2)));


    }
}
