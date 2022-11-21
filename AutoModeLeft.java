package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//#########################################
//The use of encoders allow for a more accurate movement. This is because if we were move using sleep
/*This code uses mecanum wheels, or omni-directional wheels. Encoders are also used to help measure the distance robot has drove*/
@Autonomous (name = "AutoModeLeft", group="Robot")
//@Disabled
public class AutoModeLeft extends LinearOpMode{
    //private means setting value to nothing, only when setting to null
    //Declare Motors
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront   = null;
    private DcMotor leftBack    = null;
    private DcMotor rightFront  = null;
    private DcMotor rightBack   = null;
    private DcMotor elevatorMotor  = null;
    //Declare Servos
    public Servo leftClaw       = null;
    public Servo rightClaw      = null;
    static final double DRIVE_POWER = .6;
    static final double COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);


    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        elevatorMotor = hardwareMap.get(DcMotor.class, "eleMotor");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");

        leftClaw.setPosition(0);
        rightClaw.setPosition(0);
        //use encoders
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Starting at",  "%7d :%7d",
                leftFront.getCurrentPosition(),
                leftBack.getCurrentPosition(),
                rightFront.getCurrentPosition(),
                rightBack.getCurrentPosition());
        telemetry.update();

        //wait for game to start
        waitForStart();

        //ROBOT MOVE
        driveMove(DRIVE_POWER,12);
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

    }


    private void driveForward(double power)    {
        leftBack.setPower(power);
        leftFront.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(power);
    }

    private void driveLeft(double power)
    {
        leftBack.setPower(power);
        leftFront.setPower(-power);
        rightFront.setPower(power);
        rightBack.setPower(-power);
    }

    private void driveStop()
    {
        leftBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }

    private void driveMove(double power, int distance)
    {

        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Set target position
        leftBack.setTargetPosition(distance);
        leftFront.setTargetPosition(distance);
        rightFront.setTargetPosition(distance);
        rightBack.setTargetPosition(distance);

        //Set RUN_TO_POSITION mode
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);;
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        driveForward(power);

        while(leftBack.isBusy() && leftFront.isBusy() && rightFront.isBusy() && rightBack.isBusy()){
        //wait till target reach position
        }

        driveStop();
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}