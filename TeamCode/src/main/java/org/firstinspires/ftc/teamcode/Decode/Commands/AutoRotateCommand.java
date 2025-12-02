package org.firstinspires.ftc.teamcode.Decode.Commands;


import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.Slot;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class AutoRotateCommand extends CommandBase {

    private final SortSubsystem sorter;
    public IntakeSubsystem intakeSubsystem;
    private Slot.BallColor[] matchOrder ;


    public AutoRotateCommand(IntakeSubsystem intakeSubsystem, SortSubsystem sorter, Slot.BallColor[] matchOrder) {
        this.sorter = sorter;
        this.matchOrder = matchOrder;
        addRequirements(sorter);
    }

    @Override
    public void execute() {
        if (!intakeSubsystem.canIntake) return;

        if (sorter.ballPresent()) {

            Slot.BallColor color = sorter.detectColor();

            // Determine shootingIndex for this color
            int idx = computeShootingIndex(color);

            sorter.markSlot(color, idx);
            // increment index??


            int next = sorter.nextEmptySlotIndex();

            if (next == -1) {
                intakeSubsystem.canIntake = false;
                sorter.canShoot = true;
                return;
            }

            sorter.rotateToSlot(next);
            //new WaitCommand(200);5
        }
    }

    private int computeShootingIndex(Slot.BallColor color) {
                      return 1;
    }

    @Override
    public boolean isFinished() {             //conditia de oprire este canshoot = true;
        return sorter.canShoot;
    }
}
