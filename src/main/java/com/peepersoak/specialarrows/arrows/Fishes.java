package com.peepersoak.specialarrows.arrows;

import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fishes {

    public Fishes() {
        fish.add(EntityType.COD);
        fish.add(EntityType.SALMON);
        fish.add(EntityType.DOLPHIN);
        fish.add(EntityType.TROPICAL_FISH);
        fish.add(EntityType.GLOW_SQUID);
        fish.add(EntityType.GLOW_SQUID);
        fish.add(EntityType.PUFFERFISH);
        fish.add(EntityType.SQUID);
    }

    private final List<EntityType> fish = new ArrayList<>();

    public EntityType getRandomFish() {
        Collections.shuffle(fish);
        return fish.get(0);
    }
}
