package me.nurio.bungeekeeper.packets;

import java.util.Random;

public class IdentityUtil {

    private static Random random = new Random();

    public static long timeBasedId() {
        long time = System.currentTimeMillis() * 10_000;
        int randomInt = random.nextInt(9000) + 1000;

        return time + randomInt;
    }

}