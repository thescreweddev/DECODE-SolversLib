package org.firstinspires.ftc.teamcode.Decode.Subsystems;
public class Slot {

    public enum BallColor {
        NONE,
        GREEN,
        VIOLET
    }

    public int slotIndex;
    public double angleDeg;

    public BallColor color;

    public int shootingIndex;
    public double shootSlotAngle;

    public Slot(int slotIndex, double angleDeg, double shootSlotAngle) {
        this.slotIndex = slotIndex;
        this.angleDeg = angleDeg;
        this.color = BallColor.NONE;
        this.shootSlotAngle = shootSlotAngle;
        this.shootingIndex = -1; // unused
    }

    public boolean isEmpty() {
        return color == BallColor.NONE;
    }

    public void clear() {
        color = BallColor.NONE;
        shootingIndex = -1;
    }
}
