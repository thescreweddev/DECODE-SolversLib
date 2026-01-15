package org.firstinspires.ftc.teamcode.Decode.Commands;

import com.qualcomm.robotcore.robocol.Command;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.BallDetectionSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class RotateWhenFull extends CommandBase {

    SortSubsystem sorter;
    BallDetectionSubsystem baller;
    public int  CurentIndex;

    public RotateWhenFull(SortSubsystem sorter, BallDetectionSubsystem baller){
        super();
        this.sorter = sorter;
        this.baller = baller;
        CurentIndex = 0;
        addRequirements(sorter);

    }

    public int index(int INX){
        return INX%3;
    }


    @Override
    public void execute() {
        super.execute();
        if(baller.canDetectIntake == true && (baller.co1.alpha()>150 || baller.co2.alpha()>150)){

            //sorter.rotateToSlot(index(CurentIndex));
            CurentIndex++;
            baller.canDetectIntake = false;

        }
    }
}
