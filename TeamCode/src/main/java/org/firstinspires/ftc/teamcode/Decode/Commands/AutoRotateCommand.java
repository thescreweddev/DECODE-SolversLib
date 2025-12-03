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
            int idx = computeShootingIndex(color,matchOrder);

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

    private int computeShootingIndex(Slot.BallColor color, Slot.BallColor[] colorCase) {
        boolean isFound = false;
        int special = -1;
                      //loop through each color case using a for loop
                      //check if the color detected is equal to the color on the case
                      // check if the index is already taken by another slot > if yes, go to the next index, if no index was found , index = -1;
        for(int i=1; i<=3;i++){
            if(colorCase[i-1] == color){
                for(int j=0; j<3;j++){
                    if(sorter.slots[j].color == color && sorter.slots[j].shootingIndex != i){
                        isFound = true;
                        return i;

                    }
                }
            }
            if(!isFound){
                special = -1;
            }
        }
            return special ;
    }

    @Override
    public boolean isFinished() {             //conditia de oprire este canshoot = true;
        return sorter.canShoot;
    }
}
