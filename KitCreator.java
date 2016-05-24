package plugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitCreator{
public KitCreator(String name, ChatColor color, Material weapon1, Material h, Material c, Material l, Material b, Player p, Enchantment ew, int lvl1){
ItemStack hat=new ItemStack(h);
ItemStack chest=new ItemStack(c);
ItemStack legs= new ItemStack(l);
ItemStack boots=new ItemStack(b);
ItemStack weapon=new ItemStack(weapon1);
ItemStack stew= new ItemStack(Material.MUSHROOM_SOUP);

ItemMeta hatmeta=hat.getItemMeta();
ItemMeta chestmeta=chest.getItemMeta();
ItemMeta legsmeta=legs.getItemMeta();
ItemMeta bootsmeta=boots.getItemMeta();
ItemMeta weaponmeta=weapon.getItemMeta();
ItemMeta stewmeta = stew.getItemMeta();

hatmeta.setDisplayName(color+""+ChatColor.BOLD + name + " Helmet");
chestmeta.setDisplayName(color+""+ChatColor.BOLD +name + " Chestplate");
legsmeta.setDisplayName(color+""+ChatColor.BOLD +name + " Leggings");
bootsmeta.setDisplayName(color+""+ChatColor.BOLD +name + " Boots");
weaponmeta.setDisplayName(color+""+ChatColor.BOLD + name + " Sword");
stewmeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Insta-soup");

hat.setItemMeta(hatmeta);
chest.setItemMeta(chestmeta);
legs.setItemMeta(legsmeta);
boots.setItemMeta(bootsmeta);
weapon.setItemMeta(weaponmeta);
stew.setItemMeta(stewmeta);

p.sendMessage(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"You have been given the " + name+ " kit!");
p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2.0F, 2.0F);
p.getInventory().clear();

p.getInventory().setHelmet(hat);
p.getInventory().setChestplate(chest);
p.getInventory().setLeggings(legs);
p.getInventory().setBoots(boots);
if(ew==null)
{
}else{
	weapon.addUnsafeEnchantment(ew, lvl1);
}
p.getInventory().addItem(weapon);

p.setSaturation(100);
p.closeInventory();

}


}

