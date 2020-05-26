package me.nurio.bungeekeeper.packets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IdentityUtilTest {

    @Test
    public void timeBasedId() {
        long result = IdentityUtil.timeBasedId();
        long result2 = IdentityUtil.timeBasedId();
        long time = System.currentTimeMillis();

        String timeStr = ("" + time);
        int timeLength = timeStr.length();

        String resultStr = ("" + result);
        int resultLength = resultStr.length();

        assertEquals(timeLength + 4, resultLength);
        assertEquals((time / 100), result / 1000_000);
        assertNotEquals(result, result2);
    }

}