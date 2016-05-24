package plugin;

import java.util.ArrayList;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class DrKitPvP
  extends JavaPlugin
  implements Listener
{
	
  public final Logger logger = Logger.getLogger("Minecraft");
  public static DrKitPvP plugin;
  public ArrayList<Player> cooldown = new ArrayList<Player>();
  public ArrayList<Player> weakness = new ArrayList<Player>();
  
  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
    getConfig().options().copyDefaults(true);
	saveConfig();
  }
 
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		 Player p = (Player) sender;
		if(commandLabel.equalsIgnoreCase("pos1")){
			getConfig().set("ArenaX1", p.getLocation().getX());
			getConfig().set("ArenaY1", p.getLocation().getY());
			getConfig().set("ArenaZ1", p.getLocation().getZ());
			p.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Position set to("+getConfig().getInt("ArenaX1")+","+getConfig().getInt("ArenaY1")+","+getConfig().getInt("ArenaZ1")+")");
			
			if(getConfig().getInt("ArenaX1")<getConfig().getInt("ArenaX2")){
				int tempint=getConfig().getInt("ArenaX1");
				getConfig().set("ArenaX1", getConfig().getInt("ArenaX2"));
				getConfig().set("ArenaX2",tempint);
				saveConfig();
				
				
			}
			if(getConfig().getInt("ArenaY1")<getConfig().getInt("ArenaY2")){
				int tempint=getConfig().getInt("ArenaY1");
				getConfig().set("ArenaY1", getConfig().getInt("ArenaY2"));
				getConfig().set("ArenaY2",tempint);
				saveConfig();
				
			}
			if(getConfig().getInt("ArenaZ1")<getConfig().getInt("ArenaZ2")){
				int tempint=getConfig().getInt("ArenaZ1");
				getConfig().set("ArenaZ1", getConfig().getInt("ArenaZ2"));
				getConfig().set("ArenaZ2",tempint);
				saveConfig();
				
			}
		}
		else if(commandLabel.equalsIgnoreCase("pos2")){
			getConfig().set("ArenaX2", p.getLocation().getX());
			getConfig().set("ArenaY2", p.getLocation().getY());
			getConfig().set("ArenaZ2", p.getLocation().getZ());
			p.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Position set to("+getConfig().getInt("ArenaX2")+","+getConfig().getInt("ArenaY2")+","+getConfig().getInt("ArenaZ2")+")");
			if(getConfig().getInt("ArenaX1")<getConfig().getInt("ArenaX2")){
				int tempint=getConfig().getInt("ArenaX1");
				getConfig().set("ArenaX1", getConfig().getInt("ArenaX2"));
				getConfig().set("ArenaX2",tempint);
				saveConfig();
			}
			if(getConfig().getInt("ArenaY1")<getConfig().getInt("ArenaY2")){
				int tempint=getConfig().getInt("ArenaY1");
				getConfig().set("ArenaY1", getConfig().getInt("ArenaY2"));
				getConfig().set("ArenaY2",tempint);
				saveConfig();
			}
			if(getConfig().getInt("ArenaZ1")<getConfig().getInt("ArenaZ2")){
				int tempint=getConfig().getInt("ArenaZ1");
				getConfig().set("ArenaZ1", getConfig().getInt("ArenaZ2"));
				getConfig().set("ArenaZ2",tempint);
				saveConfig();
				
			}
		}
		
			 return false;
	 }
  private void openGUI(Player player)
  {
    ItemStack assasingui = new ItemStack(Material.NETHER_STAR, 1);
    ItemMeta assasinguimeta = assasingui.getItemMeta();
    assasinguimeta.setDisplayName(ChatColor.GOLD + "Assassin");
    assasingui.setItemMeta(assasinguimeta);
    ItemStack bow = new ItemStack(Material.BOW, 1);
    ItemMeta bowm = bow.getItemMeta();
    bowm.setDisplayName(ChatColor.GOLD + "Archer");
    bow.setItemMeta(bowm);
    ItemStack heavy = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemMeta heavymeta = heavy.getItemMeta();
    heavymeta.setDisplayName(ChatColor.GOLD + "Heavy");
    heavy.setItemMeta(heavymeta);
    ItemStack pyro = new ItemStack(Material.FLINT_AND_STEEL);
    ItemMeta pyrometa = pyro.getItemMeta();
    pyrometa.setDisplayName(ChatColor.GOLD + "Pyro");
    pyro.setItemMeta(pyrometa);
    ItemStack pvp = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta pvpmeta = pvp.getItemMeta();
    pvpmeta.setDisplayName(ChatColor.GOLD + "Pvp");
    pvp.setItemMeta(pvpmeta);
    Inventory kits = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + "Kit selector");
    kits.setItem(0, pvp);
    kits.setItem(1, assasingui);
    kits.setItem(2, bow);
    kits.setItem(3, heavy);
    kits.setItem(4, pyro);
    player.openInventory(kits);
  }
  
  @EventHandler
  public void OnInventoryClick(InventoryClickEvent event)
  {
    if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Kit selector")) {
      return;
    }
    Player player = (Player)event.getWhoClicked();
    event.setCancelled(true);
    player.closeInventory();
    ItemStack stew = new ItemStack(Material.MUSHROOM_SOUP);
    ItemMeta stewmeta = stew.getItemMeta();
    stewmeta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD + "Insta-soup");
    stew.setItemMeta(stewmeta);
    switch (event.getCurrentItem().getType())
    {
    case NETHER_STAR: 

   new KitCreator("Assassin", ChatColor.DARK_BLUE,Material.STONE_SWORD,Material.CHAINMAIL_HELMET,Material.IRON_CHESTPLATE,Material.CHAINMAIL_LEGGINGS,Material.IRON_BOOTS,player,null,0);
      player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
      for(ItemStack item : player.getInventory().getContents())
      {
          if(item == null)
            player.getInventory().addItem(stew);
      }
      event.setCancelled(true);
      break;
    case BOW: 
      new KitCreator("Archer", ChatColor.GREEN,Material.STONE_SWORD,Material.IRON_HELMET,Material.IRON_CHESTPLATE,Material.IRON_LEGGINGS,Material.IRON_BOOTS,player,null,0);

      ItemStack arrow = new ItemStack(Material.ARROW);
      ItemStack bow = new ItemStack(Material.BOW);
      ItemMeta bowmeta = bow.getItemMeta();
      bowmeta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"Archer Bow");
      bow.setItemMeta(bowmeta);
      bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
      bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
      player.getInventory().addItem(bow);
    
      for(ItemStack item : player.getInventory().getContents())
      {
          if(item == null)
            player.getInventory().addItem(stew);
      }
      player.getInventory().addItem(arrow);
      event.setCancelled(true);
      break;
    case DIAMOND_CHESTPLATE: 
      player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));
      new KitCreator("Heavy", ChatColor.LIGHT_PURPLE,Material.IRON_SWORD,Material.DIAMOND_HELMET,Material.IRON_CHESTPLATE,Material.DIAMOND_LEGGINGS,Material.IRON_BOOTS,player,null,0);
      for(ItemStack item : player.getInventory().getContents())
      {
          if(item == null)
            player.getInventory().addItem(stew);
      }
      event.setCancelled(true);
      break;
    case FLINT_AND_STEEL: 
      new KitCreator("Pyro", ChatColor.AQUA,Material.STONE_SWORD,Material.CHAINMAIL_HELMET,Material.CHAINMAIL_CHESTPLATE,Material.CHAINMAIL_LEGGINGS,Material.CHAINMAIL_BOOTS,player,Enchantment.FIRE_ASPECT,2);
      for(ItemStack item : player.getInventory().getContents())
      {
          if(item == null)
            player.getInventory().addItem(stew);
      }
      event.setCancelled(true);
      break;
    case DIAMOND_SWORD: 
    	new KitCreator("PVP", ChatColor.GOLD,Material.IRON_SWORD,Material.IRON_HELMET,Material.IRON_CHESTPLATE,Material.IRON_LEGGINGS,Material.IRON_BOOTS,player, null, 0);
    	for(ItemStack item : player.getInventory().getContents())
    	{
    	    if(item == null)
    	      player.getInventory().addItem(stew);
    	}
    	event.setCancelled(true);
      break;
    default: 
      player.closeInventory();
      event.setCancelled(true);
    }
    
  }
  
  @SuppressWarnings("deprecation")
@EventHandler
  public void OnPlayerInteract2(PlayerInteractEvent event)
  {
    Action a = event.getAction();
    ItemStack is = event.getItem();
    Player player = event.getPlayer();
    if(player.getLocation().getX()<getConfig().getInt("ArenaX1")&&player.getLocation().getX()>getConfig().getInt("ArenaX2")&&player.getLocation().getY()<getConfig().getInt("ArenaY1")&&player.getLocation().getY()>getConfig().getInt("ArenaY2")&&player.getLocation().getZ()<getConfig().getInt("ArenaZ1")&&player.getLocation().getZ()>getConfig().getInt("ArenaZ2")){
    if ((a == Action.PHYSICAL) || (is == null) || (is.getType() == Material.AIR)) {
      return;
    }
    if ((player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) && (player.getHealth() <= 14.0D))
    {
      double health = 6.0D;
      player.setHealth(player.getHealth() + health);
      if (player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) {
        player.getItemInHand().setType(Material.BOWL);
      }
    }
    else if ((player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) && (player.getFoodLevel() <= 14))
    {
      int food = 6;
      player.setFoodLevel(player.getFoodLevel() + food);
      if (player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) {
        player.getItemInHand().setType(Material.BOWL);
      }
    }
    else if ((player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) && (player.getHealth() > 14.0D) && (player.getHealth() != 20.0D))
    {
      double health = 20.0D - player.getHealth();
      
      player.setHealth(player.getHealth() + health);
      if (player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) {
        player.getItemInHand().setType(Material.BOWL);
      }
    }
    else if ((player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) && (player.getFoodLevel() > 14) && (player.getFoodLevel() != 20))
    {
      int food = 20 - player.getFoodLevel();
      player.setFoodLevel(player.getFoodLevel() + food);
      if (player.getItemInHand().getType().equals(Material.MUSHROOM_SOUP)) {
        player.getItemInHand().setType(Material.BOWL);
      }
    }
    }
  }
  
  @EventHandler
  public void OnPlayerInteract(PlayerInteractEvent event)
  {
	  Player p =event.getPlayer();
	  if(p.getLocation().getX()<getConfig().getInt("ArenaX1")&&p.getLocation().getX()>getConfig().getInt("ArenaX2")&&p.getLocation().getY()<getConfig().getInt("ArenaY1")&&p.getLocation().getY()>getConfig().getInt("ArenaY2")&&p.getLocation().getZ()<getConfig().getInt("ArenaZ1")&&p.getLocation().getZ()>getConfig().getInt("ArenaZ2")){
    Action a = event.getAction();
    p.sendMessage("You are within the arena");
    ItemStack is = event.getItem();
    if ((a == Action.PHYSICAL) || (is == null) || (is.getType() == Material.AIR)) {
      return;
    }
    if (is.getType() == Material.COMPASS) {
      openGUI(event.getPlayer());
    }
  }
  }
  @EventHandler
  public void OnPlayerJoin(PlayerJoinEvent event)
  {
	 Player p = event.getPlayer();
	  if(p.getLocation().getX()<getConfig().getInt("ArenaX1")&&p.getLocation().getX()>getConfig().getInt("ArenaX2")&&p.getLocation().getY()<getConfig().getInt("ArenaY1")&&p.getLocation().getY()>getConfig().getInt("ArenaY2")&&p.getLocation().getZ()<getConfig().getInt("ArenaZ1")&&p.getLocation().getZ()>getConfig().getInt("ArenaZ2")){
    ItemStack compass = new ItemStack(Material.COMPASS, 1);
    ItemMeta metass = compass.getItemMeta();
    metass.setDisplayName(ChatColor.DARK_PURPLE + "Kit Selector");
    compass.setItemMeta(metass);
    compass.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
    event.getPlayer().getInventory().addItem(new ItemStack[] { compass });
	 }
  }
  
  @EventHandler
  public void OnPlayerQuit(PlayerQuitEvent event)
  {
    ItemStack is = new ItemStack(Material.AIR);
    PlayerInventory pa = event.getPlayer().getInventory();
    pa.clear();
    pa.setHelmet(is);
    pa.setChestplate(is);
    pa.setLeggings(is);
    pa.setBoots(is);
  }
  
  @EventHandler
  public void OnPlayerRespawn(PlayerRespawnEvent event)
  {
	  Player p = event.getPlayer();
	   if(p.getLocation().getX()<getConfig().getInt("ArenaX1")&&p.getLocation().getX()>getConfig().getInt("ArenaX2")&&p.getLocation().getY()<getConfig().getInt("ArenaY1")&&p.getLocation().getY()>getConfig().getInt("ArenaY2")&&p.getLocation().getZ()<getConfig().getInt("ArenaZ1")&&p.getLocation().getZ()>getConfig().getInt("ArenaZ2")){
    ItemStack is = new ItemStack(Material.AIR);
    PlayerInventory pa = event.getPlayer().getInventory();
    pa.clear();
    pa.setHelmet(is);
    pa.setChestplate(is);
    pa.setLeggings(is);
    pa.setBoots(is);
    ItemStack compass = new ItemStack(Material.COMPASS, 1);
    ItemMeta metass = compass.getItemMeta();
    metass.setDisplayName(ChatColor.DARK_PURPLE + "Kit Selector");
    compass.setItemMeta(metass);
    compass.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
    event.getPlayer().getInventory().addItem(new ItemStack[] { compass });
	  }
  }
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent e)
  {
	  
	  final Player player1 =  e.getEntity();
	  player1.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
	  player1.removePotionEffect(PotionEffectType.SPEED);
	  player1.removePotionEffect(PotionEffectType.WEAKNESS);
	  if(this.cooldown.contains(player1))
		  this.cooldown.remove(player1);
	  if(this.weakness.contains(player1))
		  this.weakness.remove(player1);
  }
  @EventHandler
  public void onEntityDeath(EntityDeathEvent e)
  {
    e.getDrops().clear();
  }
  
  @EventHandler
  public void onItemSpawn(ItemSpawnEvent event)
  {
    event.getEntity().remove();
  }
  
  @EventHandler
  public void onRightClick(PlayerInteractEvent e)
  {
    ItemStack stew5 = new ItemStack(Material.MUSHROOM_SOUP);
    ItemMeta stewmeta5 = stew5.getItemMeta();
    stewmeta5.setDisplayName(ChatColor.RED+""+ChatColor.BOLD + "Insta-soup");
    stew5.setItemMeta(stewmeta5);
    Player p = e.getPlayer();
    if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.CHEST))
    {
      Chest c = (Chest)e.getClickedBlock().getState();
      if(p.getLocation().getX()<getConfig().getInt("ArenaX1")&&p.getLocation().getX()>getConfig().getInt("ArenaX2")&&p.getLocation().getY()<getConfig().getInt("ArenaY1")&&p.getLocation().getY()>getConfig().getInt("ArenaY2")&&p.getLocation().getZ()<getConfig().getInt("ArenaZ1")&&p.getLocation().getZ()>getConfig().getInt("ArenaZ2")){
      e.setCancelled(true);
      int size = c.getInventory().getSize();
      if (size > 54) {
        size = 54;
      }
      Inventory i = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', ChatColor.LIGHT_PURPLE + "Soup Chest"));
      for (int x = 0; x < i.getSize(); x++)
      {
        i.setItem(x, new ItemStack(Material.MUSHROOM_SOUP));
        i.setItem(x, stew5);
      }
      p.openInventory(i);
    }
    }
  }
}
