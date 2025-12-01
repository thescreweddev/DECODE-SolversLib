package org.firstinspires.ftc.teamcode.Decode.Commands;


import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.Slot;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class AutoRotateCommand extends CommandBase {

    private final SortSubsystem sorter;
    private final int[] matchOrder; // example: {2,1,3} based on pattern VVG

    public AutoRotateCommand(SortSubsystem sorter, int[] matchOrder) {
        this.sorter = sorter;
        this.matchOrder = matchOrder;
        addRequirements(sorter);
    }

    @Override
    public void execute() {
        if (!sorter.canIntake) return;

        if (sorter.ballPresent()) {

            Slot.BallColor color = sorter.detectColor();

            // Determine shootingIndex for this color
            int idx = computeShootingIndex(color);

            sorter.markSlot(color, idx);

            int next = sorter.nextEmptySlotIndex();

            if (next == -1) {
                sorter.canIntake = false;
                sorter.canShoot = true;
                return;
            }

            sorter.rotateToSlot(next);
        }
    }

    private int computeShootingIndex(Slot.BallColor color) {
        // If matchOrder is e.g. {GREEN, VIOLET, GREEN}
        // Returns correct shoot index
        for (int i = 0; i < matchOrder.length; i++) {
            if (matchOrder[i] == color.ordinal()) return i + 1;
        }
        return 1;
    }

    @Override
    public boolean isFinished() {
        return sorter.canShoot;
    }
}
