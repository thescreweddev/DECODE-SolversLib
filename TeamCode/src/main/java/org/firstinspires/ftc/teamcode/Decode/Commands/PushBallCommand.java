package org.firstinspires.ftc.teamcode.Decode.Commands;

import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class PushBallCommand extends InstantCommand {
    public PushBallCommand(SortSubsystem sorter){
        super(()->{
            sorter.pushBall();
        },sorter);
    }
}
