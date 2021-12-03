import com.delsystem.instamart.workapp.model.Outlets;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * deliverysystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: TradePointTest.java
 * Date/time: 22 ноябрь 2021 in 19:19
 */

public class TradePointTest {
    private static final Outlets outlets = Outlets.getInstance();

    @Test
    public void getTradePointShouldByStringType() {
        Assert.assertTrue(outlets.getTradePoint("1234").getTradePointNumber() instanceof String);
    }

    @Test
    public void getTradePointShouldByNotNull() {
        Assert.assertNotNull(outlets.getTradePoint("1234").getTradePointNumber());
    }

    @Test
    public void getOrdersShouldByNotNull() {
        Assert.assertNotNull(outlets.getTradePoint("1234").getOrders());
    }

    @Test
    public void getOrdersShouldByFaceTradePointGetEmptyMap() {
        Assert.assertTrue(outlets.getTradePoint("1234").getOrders().isEmpty());
    }

    @Test
    public void getOrdersShouldByInstanceofMap() {
        Assert.assertTrue(outlets.getTradePoint("1234").getOrders() instanceof ConcurrentHashMap);
    }
}
