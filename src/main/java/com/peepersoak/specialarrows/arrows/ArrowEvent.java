package com.peepersoak.specialarrows.arrows;

import com.peepersoak.specialarrows.SpecialArrows;
import com.peepersoak.specialarrows.utils.StringPath;
import com.peepersoak.specialarrows.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ArrowEvent implements Listener {

    private final HashMap<UUID, Types> arrowTypes = new HashMap<>();

    private final Random rand = new Random();

    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player shooter)) return;
        if (!(e.getProjectile() instanceof Arrow projectile)) return;
        arrowTypes.remove(shooter.getUniqueId());

        Arrows arrows = new Arrows(shooter);

        if (e.getConsumable() != null) {
            setProjectileType(projectile, arrows.getTypes(e.getConsumable()));
        }

        if (arrows.getArrow() == null) return;
        if (arrows.getTypes() == null) return;

        Types type = arrows.getTypes();

        switch (type) {
            case RIDE -> {
                Bukkit.getScheduler().runTask(SpecialArrows.getInstance(), () -> e.getProjectile().remove());
                Arrow arrow = shooter.launchProjectile(Arrow.class);
                arrow.setShooter(shooter);
                arrow.addPassenger(shooter);
                arrow.setVelocity(e.getProjectile().getVelocity());
                removePlayer(shooter);
            }
            case BUNDLE_OF -> {
                Bukkit.getScheduler().runTask(SpecialArrows.getInstance(), () -> e.getProjectile().remove());
                for (int i = 0; i < 5; i++) {
                    Arrow arrow = shooter.getWorld().spawnArrow(shooter.getEyeLocation(), shooter.getLocation().getDirection(), e.getForce(), 20F);
                    arrow.setShooter(shooter);
                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                }
                removePlayer(shooter);
            }
            case END_CRYSTAL -> {
                Bukkit.getScheduler().runTask(SpecialArrows.getInstance(), () -> e.getProjectile().remove());
                Arrow arrow = shooter.launchProjectile(Arrow.class);
                arrow.setVelocity(arrow.getVelocity().multiply(2));
                arrow.setGravity(false);
                arrow.setShooter(shooter);
                removePlayer(shooter);
            }
            default -> arrowTypes.put(shooter.getUniqueId(), type);
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof LivingEntity entity)) return;
        if (!(e.getDamager() instanceof Arrow arrow)) return;
        if (!(arrow.getShooter() instanceof Player shooter)) return;
        if (!arrowTypes.containsKey(shooter.getUniqueId())) return;

        Types type = arrowTypes.get(shooter.getUniqueId());

        switch (type) {
            case EXPLOSIVE -> {
                entity.getWorld().createExplosion(entity.getLocation(), 4.0F);
                removePlayer(shooter);
            }
            case LIGHTNING -> {
                entity.getWorld().strikeLightning(entity.getLocation());
                removePlayer(shooter);
            }
            case TELEPORT -> {
                shooter.teleport(entity);
                removePlayer(shooter);
            }
            case GRAVITY -> {
                List<Entity> entityList = entity.getNearbyEntities(50, 50, 50);
                for (Entity ent : entityList) {
                    if (!(ent instanceof LivingEntity entities)) continue;
                    if (ent instanceof Player) continue;
                    entities.teleport(entity);
                    entities.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 1));
                    entities.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 250));
                }
                removePlayer(shooter);
            }
            case FISH -> {
                if (entity instanceof Player ||
                entity instanceof EnderDragon ||
                entity instanceof Wither) {
                    shooter.sendMessage(Utils.color("&cThis arrow has no effect on this mobs!!"));
                    return;
                }
                Fishes fishes = new Fishes();
                entity.getWorld().spawnEntity(entity.getLocation(), fishes.getRandomFish());
                Bukkit.getScheduler().runTask(SpecialArrows.getInstance(), entity::remove);
                removePlayer(shooter);
            }
            case INFINITY -> {
                double damage = e.getDamage() + rand.nextInt(40);
                e.setDamage(damage);
                Bukkit.getScheduler().runTaskLater(SpecialArrows.getInstance(), () -> {
                    ArrowFactory arrowFactory = new ArrowFactory();
                    ItemStack newArrow = arrowFactory.createArrow(type);
                    if (newArrow == null) return;
                    HashMap<Integer, ItemStack> returnedItems = shooter.getInventory().addItem(newArrow);
                    if (returnedItems.isEmpty()) return;
                    for (ItemStack item : returnedItems.values()) {
                        shooter.getWorld().dropItemNaturally(shooter.getLocation(), item);
                    }
                }, 40);
                removePlayer(shooter);
            }
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();

        if (!(e.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent event)) return;
        if (!(event.getDamager() instanceof Arrow arrow)) return;
        if (!(arrow.getShooter() instanceof Player shooter)) return;
        if (!arrowTypes.containsKey(shooter.getUniqueId())) return;

        Types types = arrowTypes.get(shooter.getUniqueId());

        if (types == Types.DIAMOND) {
            ArrowLoots loot = new ArrowLoots();
            entity.getWorld().dropItemNaturally(entity.getLocation(), loot.getRandomLoot());
            removePlayer(shooter);
        }
    }

    @EventHandler
    public void onPickUp(PlayerPickupArrowEvent e) {
        if (!(e.getArrow() instanceof Arrow arrow)) return;
        PersistentDataContainer pdc = arrow.getPersistentDataContainer();
        if (!pdc.has(StringPath.EFFECT_KEY, PersistentDataType.INTEGER)) return;
        Integer key = pdc.get(StringPath.EFFECT_KEY, PersistentDataType.INTEGER);
        if (key == null) return;
        Types types;
        switch (key) {
            case 0 -> types = Types.EXPLOSIVE;
            case 1 -> types = Types.LIGHTNING;
            case 2 -> types = Types.TELEPORT;
            case 3 -> types = Types.RIDE;
            case 4 -> types = Types.GRAVITY;
            case 5 -> types = Types.BUNDLE_OF;
            case 6 -> types = Types.DIAMOND;
            case 7 -> types = Types.END_CRYSTAL;
            case 8 -> types = Types.FISH;
            case 9 -> types = Types.INFINITY;
            default -> types = null;
        }
        if (types == null) return;
        ArrowFactory factory = new ArrowFactory();
        e.getItem().setItemStack(factory.createArrow(types));
    }

    private void removePlayer(Player shooter) {
        arrowTypes.remove(shooter.getUniqueId());
    }

    private void setProjectileType(Projectile arrow, Types types) {
        if (types == null) return;

        PersistentDataContainer pdc = arrow.getPersistentDataContainer();
        int key;
        switch (types) {
            case EXPLOSIVE -> key = 0;
            case LIGHTNING -> key = 1;
            case TELEPORT -> key = 2;
            case RIDE -> key = 3;
            case GRAVITY -> key = 4;
            case BUNDLE_OF -> key = 5;
            case DIAMOND -> key = 6;
            case END_CRYSTAL -> key = 7;
            case FISH -> key = 8;
            case INFINITY -> key = 9;
            default -> key = -1;
        }
        pdc.set(StringPath.EFFECT_KEY, PersistentDataType.INTEGER, key);
    }
}
