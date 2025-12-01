package org.firstinspires.ftc.teamcode.Decode.Commands;

import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class ResetSorterCommand extends InstantCommand {

    public ResetSorterCommand(SortSubsystem sorter) {
        super(() -> {
            sorter.clearAllSlots();
            sorter.rotateToSlot(0);
            sorter.canShoot = false;
            sorter.canIntake = true;
        }, sorter);
    }
}
