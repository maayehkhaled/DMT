package com.qpros.common.web;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Util {

    public static void typeString(String string) throws InterruptedException, AWTException
    {
        //Instantiating robot
        Robot robot = new Robot();

        //Looping through every char
        for (int i = 0; i < string.length(); i++)
        {
            //Getting current char
            char c = string.charAt(i);

            //Pressing shift if it's uppercase
            if (Character.isUpperCase(c))
            {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }

            //Actually pressing the key
            robot.keyPress(Character.toUpperCase(c));
            robot.keyRelease(Character.toUpperCase(c));

            //Releasing shift if it's uppercase
            if (Character.isUpperCase(c))
            {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }

            //Optional delay to make it look like it's a human typing
            Thread.sleep(20 );
        }
    }
}
