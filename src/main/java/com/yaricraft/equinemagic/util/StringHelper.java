package com.yaricraft.equinemagic.util;

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
            String output = "";

            for (int i = 0; i < oldArray.length; i++)
            {
                output = output + oldArray[i].substring(0, 1).toUpperCase() + oldArray[i].substring(1).toLowerCase();
            }

            return output;
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String dictPrefix(String input)
    {
        String[] oldArray = input.split("_");
        return oldArray[oldArray.length-1].toLowerCase();
    }
}
