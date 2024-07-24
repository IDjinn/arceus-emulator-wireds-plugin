package org.emulator.wireds.boxes.util.selection;

import utils.pathfinder.Position;

import java.util.List;

public final class AreaHelper {
    public static boolean isItInside(List<? extends Area> areas, Position position) {
        for (Area area : areas) {
            if (area.isItInside(position)) 
                return true;
        }
        return false;
    }
}
