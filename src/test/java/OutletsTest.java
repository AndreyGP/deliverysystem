import com.delsystem.instamart.workapp.model.Outlets;
import org.junit.Assert;
import org.junit.Test;

/**
 * deliverysystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: OutletsTest.java
 * Date/time: 22 ноябрь 2021 in 18:24
 */

public class OutletsTest {

    @Test
    public void getInstanceShouldBeEquals() {
        Assert.assertEquals(Outlets.getInstance(), Outlets.getInstance());
    }

    @Test
    public void getInstanceShouldBeNotNull() {
        Assert.assertNotNull(Outlets.getInstance());
    }

    @Test
    public void getInstanceShouldBeSingleton() {
        Assert.assertSame(Outlets.getInstance(), Outlets.getInstance());
    }

    @Test
    public void getTradePointShouldBeNotNull() {
        Assert.assertNotNull(Outlets.getInstance().getTradePoint("111"));
    }

    @Test
    public void getTradePointShouldBeNotNewInstance() {
        Assert.assertSame(Outlets.getInstance().getTradePoint("1223"), Outlets.getInstance().getTradePoint("1223"));
    }
}
