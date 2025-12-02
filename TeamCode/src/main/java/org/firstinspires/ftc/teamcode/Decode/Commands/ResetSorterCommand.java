package org.firstinspires.ftc.teamcode.Decode.Commands;

import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class ResetSorterCommand extends InstantCommand {

    public ResetSorterCommand(SortSubsystem sorter, IntakeSubsystem intakeSubsystem) {
        super(() -> {
            sorter.clearAllSlots();
            sorter.currentSlotIndex = 0;
            sorter.rotateToSlot(0);
            sorter.canShoot = false;
            intakeSubsystem.canIntake = true;
        }, sorter,intakeSubsystem);
    }
}
