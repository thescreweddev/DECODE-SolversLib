package org.firstinspires.ftc.teamcode.Decode.Subsystems;


import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class SortSubsystem extends SubsystemBase {

    public final ServoEx spindexer;
    private final ServoEx pusher;
    public final RevColorSensorV3 colorSensor;

    public final Slot[] slots = new Slot[3];

    //public static boolean canIntake = true;
    public boolean canShoot = false;

    public int currentSlotIndex = 0;

    // pusher positions
    private final double PUSH_POS = 0;
    private final double RETRACT_POS = 1;

    public SortSubsystem(HardwareMap hardwareMap) {
        super();
        spindexer = new ServoEx(hardwareMap,"spin");
        pusher = new ServoEx(hardwareMap,"arm");
        colorSensor = hardwareMap.get(RevColorSensorV3.class, "colorsensor");

        slots[0] = new Slot(0, /*1*/ 0.3, 0.13);
        slots[1] = new Slot(1, /*2*/ 0.545, 0.37);
        slots[2] = new Slot(2, /*3*/ 0.79, 0.625);
    }

    // logica slot

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

    // servo

    public void rotateToSlot(int index) {
        currentSlotIndex = index;
        double deg = slots[index].angleDeg;
        spindexer.set(deg);
    }
    public void rotateToShoot(int index){
        currentSlotIndex = index;
        double deg = slots[index].shootSlotAngle;
        spindexer.set(deg);
    }

    public void pushBall() {
        pusher.set(PUSH_POS);
    }

    public void retractPusher() {
        pusher.set(RETRACT_POS);
    }

    // comenzi culoare

    public Slot.BallColor detectColor() {
        int r = colorSensor.red();
        int g = colorSensor.green();
        int b = colorSensor.blue();

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
