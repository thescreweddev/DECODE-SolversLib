package org.firstinspires.ftc.teamcode.Decode.Commands;

import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class RotateToSlotCommand extends InstantCommand {
    public RotateToSlotCommand(SortSubsystem sorter, int index){
        super(()->{
            sorter.rotateToSlot(index);
        },sorter);
    }


}
