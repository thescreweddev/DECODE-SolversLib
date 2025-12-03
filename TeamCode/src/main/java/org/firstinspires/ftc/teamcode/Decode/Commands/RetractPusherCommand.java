package org.firstinspires.ftc.teamcode.Decode.Commands;

import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class RetractPusherCommand extends InstantCommand {
    public  RetractPusherCommand(SortSubsystem sorter){
        super(()->{
            sorter.retractPusher();
        },sorter);
    }
}
