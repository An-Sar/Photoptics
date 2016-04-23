package abr.mod.photoptics.item;

import abr.mod.photoptics.render.overlay.IOverlayRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stellarapi.api.StellarAPIReference;
import stellarapi.api.optics.IViewScope;

public abstract class ItemTelescopeBase extends Item {
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		this.onUse(stack, player);
		return stack;
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		this.onUse(stack, player);
    	return true;
    }
	
	public void onUse(ItemStack stack, EntityPlayer player) {
		player.setItemInUse(stack, Integer.MAX_VALUE);
		
		StellarAPIReference.updateScope(player);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int slot)
    {
		player.clearItemInUse();
		StellarAPIReference.updateScope(player);
    }
	
	public abstract IViewScope getScope(ItemStack stack);
	
	@SideOnly(Side.CLIENT)
	public abstract IOverlayRenderer getOverlayRenderer(ItemStack stack);

}