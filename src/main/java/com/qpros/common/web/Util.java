package com.qpros.common.web;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Util {
    public static void robotTypeString(String string) throws InterruptedException, AWTException
    {
        Robot robot = new Robot();
        for (int i = 0; i < string.length(); i++)
        {
            char c = string.charAt(i);
            if (Character.isUpperCase(c))

            {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            robot.keyPress(Character.toUpperCase(c));
            robot.keyRelease(Character.toUpperCase(c));
            if (Character.isUpperCase(c))
            {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            Thread.sleep(20 + new Random().nextInt(150));
        }
    }
}
