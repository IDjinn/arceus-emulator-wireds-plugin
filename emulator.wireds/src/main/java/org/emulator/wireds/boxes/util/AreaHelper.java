package org.emulator.wireds.boxes.util;

import utils.pathfinder.Position;

import java.util.List;

public class AreaHelper {
    public static boolean isItInside(List<Area> areas, Position position) {
        for (Area area : areas) {
            if (area.isItInside(position)) 
                return true;
        }
        return false;
    }
}
