package tconstruct.plugins.waila;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import tconstruct.blocks.logic.CastingBasinLogic;

import java.util.List;

public class BasinDataProvider implements IWailaDataProvider {

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof CastingBasinLogic)
        {
            return ((CastingBasinLogic)accessor.getTileEntity()).getStackInSlot(0);
        }
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof CastingBasinLogic)
        {
            CastingBasinLogic te = (CastingBasinLogic)accessor.getTileEntity();
            if (te.getFluidAmount() != 0)
            {
                FluidStack fs = te.getFluid();
                currenttip.add("Liquid: " + WailaRegistrar.fluidNameHelper(fs));
                currenttip.add("Amount: " + fs.amount + "/" + te.getCapacity());
            }
            else
            {
                currenttip.add("§oEmpty"); // "§o" == Italics
            }
        }
        return currenttip;
    }

}
