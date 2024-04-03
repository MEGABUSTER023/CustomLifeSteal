package me.plugin.lifesnatcher.events;

import me.plugin.lifesnatcher.Keys;
import me.plugin.lifesnatcher.items.itemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OpPickaxeEvents implements Listener {
    List<Block> blocks = new ArrayList<>();

    BlockFace blockface = null;

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            blockface = event.getBlockFace();
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        Player player = (Player) event.getPlayer();
        PersistentDataContainer PlayerContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();
        PersistentDataContainer OpPick = Objects.requireNonNull(itemManager.Op_Pickaxe.getItemMeta()).getPersistentDataContainer();
        if (OpPick.has(Keys.Tnt_Ammo) && PlayerContainer.has(Keys.Tnt_Ammo)) {
            if (blockface.equals(BlockFace.UP) || blockface.equals(BlockFace.DOWN)) {
                blocks.add(block.getRelative(BlockFace.EAST));
                blocks.add(block.getRelative(BlockFace.WEST));
                blocks.add(block.getRelative(BlockFace.NORTH));
                blocks.add(block.getRelative(BlockFace.SOUTH));
                blocks.add(block.getRelative(BlockFace.SOUTH_WEST));
                blocks.add(block.getRelative(BlockFace.SOUTH_EAST));
                blocks.add(block.getRelative(BlockFace.NORTH_WEST));
                blocks.add(block.getRelative(BlockFace.NORTH_EAST));
            }
            if (blockface.equals(BlockFace.EAST) || blockface.equals(BlockFace.WEST)) {
                blocks.add(block.getRelative(BlockFace.UP));
                blocks.add(block.getRelative(BlockFace.DOWN));
                blocks.add(block.getRelative(BlockFace.NORTH));
                blocks.add(block.getRelative(BlockFace.SOUTH));
                blocks.add(block.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH));
                blocks.add(block.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH));
                blocks.add(block.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH));
                blocks.add(block.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH));
            }
            if (blockface.equals(BlockFace.NORTH) || blockface.equals(BlockFace.SOUTH)) {
                blocks.add(block.getRelative(BlockFace.UP));
                blocks.add(block.getRelative(BlockFace.DOWN));
                blocks.add(block.getRelative(BlockFace.EAST));
                blocks.add(block.getRelative(BlockFace.WEST));
                blocks.add(block.getRelative(BlockFace.UP).getRelative(BlockFace.EAST));
                blocks.add(block.getRelative(BlockFace.UP).getRelative(BlockFace.WEST));
                blocks.add(block.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST));
                blocks.add(block.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST));
            }
            for(Block b : blocks){
                if(b.getType() != Material.IRON_ORE || b.getType() != Material.DIAMOND_ORE || b.getType() != Material.COAL_ORE || b.getType() != Material.COPPER_ORE || b.getType() != Material.REDSTONE_ORE || b.getType() != Material.GOLD_ORE || b.getType() != Material.COAL_ORE || b.getType() != Material.DEEPSLATE_IRON_ORE || b.getType() != Material.DEEPSLATE_DIAMOND_ORE || b.getType() != Material.DEEPSLATE_COAL_ORE || b.getType() != Material.DEEPSLATE_COPPER_ORE || b.getType() != Material.DEEPSLATE_REDSTONE_ORE || b.getType() != Material.DEEPSLATE_GOLD_ORE){
                    b.breakNaturally(event.getPlayer().getInventory().getItemInMainHand());
                }
                b.breakNaturally(event.getPlayer().getInventory().getItemInMainHand());
            }
            blocks.clear();
        }
    }
}
