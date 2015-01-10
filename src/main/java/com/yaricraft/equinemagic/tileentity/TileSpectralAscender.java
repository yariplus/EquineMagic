package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.enums.ESpectralManipulator;
import com.yaricraft.equinemagic.util.LogHelper;
import net.minecraft.init.Blocks;

import java.util.ArrayList;

/**
 * Created by Yari on 9/17/2014.
 */
public class TileSpectralAscender extends TileSpectralManipulator
{
    public TileSpectralAscender()
    {
        this.type = ESpectralManipulator.ASCENDER;

        fillers.add(Blocks.air);

        for (int p = 0; p < 1; p++)
        {
            ArrayList<ArrayList<Integer>> rows = new ArrayList<ArrayList<Integer>>();
            patterns.add(rows);
            for (int r = 0; r < 3; r++)
            {
                ArrayList<Integer> columns = new ArrayList<Integer>();
                rows.add(columns);
                for (int i = 0; i < 3; i++) columns.add(0);
            }
        }

        hOffset = -1;
        wOffset = -1;
        dOffset = -2;
    }

    @Override
    public void shoot()
    {
        if (workColumn + 1 == patterns.get(workPattern).get(workRow).size())
        {
            workColumn = 0;
            if (workRow + 1 == patterns.get(workPattern).size())
            {
                workRow = 0;
                if (workPattern + 1 == patterns.size()) workPattern = 0; else workPattern++;
                if (workLayer + 1 >= 65)
                {
                    workPattern = 0;
                    workRow = 0;
                    workColumn = -1;
                    chargeAmount = 0;
                    workLayer = 0;
                    return;
                }else{ workLayer++; }
            }else{ workRow++; }
        }else{ workColumn++; }

        LogHelper.info(String.format("Harvesting Pattern %s Row %s Column %s", workPattern, workRow, workColumn));

        mineBlock(xCoord + wOffset + workColumn, yCoord + dOffset + workLayer, zCoord + hOffset + workRow, fillers.get(patterns.get(workPattern).get(workRow).get(workColumn)));
    }
}
