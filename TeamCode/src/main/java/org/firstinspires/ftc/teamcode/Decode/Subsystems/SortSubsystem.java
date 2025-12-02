package org.firstinspires.ftc.teamcode.Decode.Subsystems;


import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class SortSubsystem extends SubsystemBase {

    private final ServoEx spindexer;
    private final ServoEx pusher;
    private final RevColorSensorV3 colorSensor;

    public final Slot[] slots = new Slot[3];

    //public static boolean canIntake = true;
    public boolean canShoot = false;

    public int currentSlotIndex = 0;

    // pusher positions
    private final double PUSH_POS = 0.70;
    private final double RETRACT_POS = 0.20;

    public SortSubsystem(HardwareMap hardwareMap) {
        spindexer = new ServoEx(hardwareMap,"spin");
        pusher = new ServoEx(hardwareMap,"arm");
        colorSensor = hardwareMap.get(RevColorSensorV3.class, "colorsensor");

        slots[0] = new Slot(0, /*1*/ 0);
        slots[1] = new Slot(1, /*2*/ 120);
        slots[2] = new Slot(2, /*3*/ 240);
    }

    // ---------------- SLOT LOGIC ----------------

    public Slot getCurrentSlot() {
        return slots[currentSlotIndex];
    }

    public boolean allSlotsFull() {
        return !slots[0].isEmpty() && !slots[1].isEmpty() && !slots[2].isEmpty();
    }

    public int nextEmptySlotIndex() {
        for (int i = 0; i < 3; i++) {
            if (slots[i].isEmpty()) return i;
        }
        return -1;
    }

    public void markSlot(Slot.BallColor color, int shootingIndex) {                                 //pointer??
        Slot s = getCurrentSlot();
        s.color = color;
        s.shootingIndex = shootingIndex;
    }

    public void clearAllSlots() {
        for (Slot s : slots) {
            s.clear();
        }
    }

    // ---------------- SERVO CONTROL ----------------

    public void rotateToSlot(int index) {
        currentSlotIndex = index;
        double deg = slots[index].angleDeg;
        spindexer.set(deg);
    }

    public void pushBall() {
        pusher.set(PUSH_POS);
    }

    public void retractPusher() {
        pusher.set(RETRACT_POS);
    }

    // ---------------- COLOR CLASSIFICATION ----------------

    public Slot.BallColor detectColor() {
        int r = colorSensor.red();
        int g = colorSensor.green();
        int b = colorSensor.blue();

        // Explanation:
        // Green ball = high G channel
        // Violet ball = high R+B (which forms purple)
        // Chamber is dark → LED makes contrast visible

        if (g > r && g > b) {
            return Slot.BallColor.GREEN;
        }
        if ((r + b) > (2 * g)) {
            return Slot.BallColor.VIOLET;
        }

        return Slot.BallColor.NONE;
    }

    public boolean ballPresent() {
        return detectColor() != Slot.BallColor.NONE;
    }
}
