package com.yaricraft.equinemagic.utility;

/**
 * Created by Yari on 10/28/2014.
 */
public class StringHelper
{
    public static enum Color
    {
        BLACK        ("0"),
        DARK_BLUE    ("1"),
        DARK_GREEN   ("2"),
        DARK_AQUA    ("3"),
        DARK_RED     ("4"),
        DARK_PURPLE  ("5"),
        GOLD         ("6"),
        GRAY         ("7"),
        DARK_GRAY    ("8"),
        BLUE         ("9"),
        GREEN        ("a"),
        AQUA         ("b"),
        RED          ("c"),
        LIGHT_PURPLE ("d"),
        YELLOW       ("e"),
        WHITE        ("f");

        private final String text;

        Color(String text)
        {
            this.text = text;
        }

        @Override
        public String toString() {

            return "\u00a7" + text;
        }
    }

    public static String vanillaCaseToCamelCase(String input)
    {
        String[] oldArray = input.split("_");

        if (oldArray.length > 1)
        {
            String output = oldArray[0];

            for (int i = 1; i < oldArray.length; i++)
            {
                output = output + oldArray[i].substring(0, 1).toUpperCase() + oldArray[i].substring(1);
            }

            return output;
        }

        return input;
    }
}
