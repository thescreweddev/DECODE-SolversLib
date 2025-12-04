package org.firstinspires.ftc.teamcode.Decode.Commands;

import com.seattlesolvers.solverslib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class RotateToShoot extends InstantCommand {
    public RotateToShoot(SortSubsystem sortSubsystem, int index){
        super(()->{
            sortSubsystem.rotateToShoot(index);
        },sortSubsystem);
    }
}
