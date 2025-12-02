package org.firstinspires.ftc.teamcode.Decode.Commands;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.Slot;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class ShootSequence extends SequentialCommandGroup {

    public ShootSequence(SortSubsystem sorter, IntakeSubsystem intakeSubsystem, Slot.BallColor[] color) {

        for (int targetShootIndex = 1; targetShootIndex <= 3; targetShootIndex++) {

            //int slotIndex = findSlotWithIndex(sorter, targetShootIndex);

            addCommands(
                    //new RotateToSlotCommand(sorter, slotIndex),
                    //new PushBallCommand(sorter),
                    //new RetractPusherCommand(sorter)
            );
        }

        addCommands(new ResetSorterCommand(sorter,intakeSubsystem),
                    new AutoRotateCommand(intakeSubsystem,sorter, color));
    }
/*
    private int findSlotWithIndex(SortSubsystem sorter, int shootIndex) {
        for (int i = 0; i < 3; i++) {
            if (sorter.slots[i].shootingIndex == shootIndex) return i;               // && slot empty
        }
        return 0;
    }
    */

}
