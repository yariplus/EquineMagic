package com.yaricraft.equinemagic.tileentity;

import com.yaricraft.equinemagic.enums.ESpectralManipulator;
import net.minecraft.init.Blocks;

import java.util.ArrayList;

/**
 * Created by Yari on 10/17/2014.
 */
public class TileSpectralCannon extends TileSpectralManipulator
{
    public TileSpectralCannon()
    {
        this.type = ESpectralManipulator.CANNON;

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

        hOffset = 0;
        wOffset = -1;
        dOffset = 1;
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
                if (workLayer + 1 >= this.depth)
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

        //LogHelper.info(String.format("Harvesting Pattern %s Row %s Column %s",workPattern,workRow,workColumn));

        switch (worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
        {
            case 2: // +Z
                mineBlock(xCoord + wOffset + workColumn, yCoord + hOffset + workRow, zCoord + dOffset + workLayer, fillers.get(patterns.get(workPattern).get(workRow).get(workColumn)));
                break;
            case 3: // -Z
                mineBlock(xCoord + wOffset + workColumn, yCoord + hOffset + workRow, zCoord - dOffset + workLayer * -1, fillers.get(patterns.get(workPattern).get(workRow).get(workColumn)));
                break;
            case 4: // +X
                mineBlock(xCoord + dOffset + workLayer, yCoord + hOffset + workRow, zCoord + wOffset + workColumn, fillers.get(patterns.get(workPattern).get(workRow).get(workColumn)));
                break;
            case 5: // -X
                mineBlock(xCoord - dOffset - workLayer, yCoord + hOffset + workRow, zCoord + wOffset + workColumn, fillers.get(patterns.get(workPattern).get(workRow).get(workColumn)));
                break;
        }
    }
}
