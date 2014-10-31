package com.yaricraft.equinemagic.utility;

/**
 * Created by Yari on 10/28/2014.
 */
public class StringHelper
{
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
