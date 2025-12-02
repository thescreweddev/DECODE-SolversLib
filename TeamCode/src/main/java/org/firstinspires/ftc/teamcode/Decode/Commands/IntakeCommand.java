package org.firstinspires.ftc.teamcode.Decode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Decode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Decode.Subsystems.SortSubsystem;

public class IntakeCommand extends CommandBase {
    public IntakeSubsystem intakeSubsystem;
    public SortSubsystem sortSubsystem;
    public IntakeCommand(SortSubsystem sortSubsystem, IntakeSubsystem intakeSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        this.sortSubsystem = sortSubsystem;
        addRequirements(intakeSubsystem,sortSubsystem);
    }

//    @Override
//    public void execute() {
//        super.execute();
//        if(sortSubsystem.canIntake){
//            intakeSubsystem.active();
//        }else{
//            intakeSubsystem.idle();
//        }
//    }


}
