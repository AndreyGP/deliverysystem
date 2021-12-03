import com.delsystem.instamart.util.JSONFileParser;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * deliverysystem Created by Home Work Studio AndrHey [andreigp]
 * FileName: JSONFileParserTest.java
 * Date/time: 23 ноябрь 2021 in 20:09
 */

public class JSONFileParserTest {
    @Test(expected = NoSuchFileException.class)
    public void getCurrentOrdersMapShouldThrowsNoSuchFileException() {
        JSONFileParser parser = new JSONFileParser();
        parser.setFilePath("1234");
        parser.getCurrentOrdersMap();
    }
}
