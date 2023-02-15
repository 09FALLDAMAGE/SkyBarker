package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.CANCoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotCommander;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

import static frc.robot.Constants.*;

public class Arm {
    public static enum ArmPos {
        topNode,
        middleNode,
        lowerNode,
        packagePos,
        manual,
        stay
    }

    TalonFX shoulder = new TalonFX(SHOULDER);
    TalonFX extension = new TalonFX(EXTENSION);
    TalonSRX elbow = new TalonSRX(ELBOW);
    CANCoder shoulderEncoder = new CANCoder(SHOULDER_ENCODER);

    public Arm(){
        //Configure sensor source for primary PID
        shoulder.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, SHOULDER_K_PID_LOOP_IDX,
        ARM_TIMEOUT);   

        shoulder.setSensorPhase(false);
        shoulder.setInverted(false);

        /* Set relevant frame periods to be at least as fast as periodic rate */
        shoulder.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, ARM_TIMEOUT);
        shoulder.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic,10);

        /* Set the peak and nominal outputs */
        shoulder.configNominalOutputForward(0, ARM_TIMEOUT);
        shoulder.configNominalOutputReverse(0, ARM_TIMEOUT);
        shoulder.configPeakOutputForward(1, ARM_TIMEOUT);
        shoulder.configPeakOutputReverse(-1, ARM_TIMEOUT);

        /* Set Motion Magic gains in slot0 - see documentation */
        shoulder.selectProfileSlot(SHOULDER_PID_SLOT, SHOULDER_K_PID_LOOP_IDX);
        shoulder.config_kF(SHOULDER_PID_SLOT, SHOULDER_MOTOR_kF, ARM_TIMEOUT);
        shoulder.config_kP(SHOULDER_PID_SLOT, SHOULDER_MOTOR_kP, ARM_TIMEOUT);
        shoulder.config_kI(SHOULDER_PID_SLOT, SHOULDER_MOTOR_kI, ARM_TIMEOUT);
        shoulder.config_kD(SHOULDER_PID_SLOT, SHOULDER_MOTOR_kD, ARM_TIMEOUT);

        /* Set acceleration and vcruise velocity - see documentation */
        shoulder.configMotionCruiseVelocity(SHOULDER_CRUISEVELOCITY, ARM_TIMEOUT);
        shoulder.configMotionAcceleration(SHOULDER_ACCEL, ARM_TIMEOUT);

        /* Zero the sensor once on robot boot up */
        shoulder.setSelectedSensorPosition(0, SHOULDER_K_PID_LOOP_IDX, ARM_TIMEOUT);

        //Configure sensor source for primary PID
        extension.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, EXTENSION_K_PID_LOOP_IDX,
        200);

        extension.setSensorPhase(false);
        extension.setInverted(false);

        /* Set relevant frame periods to be at least as fast as periodic rate */
        extension.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, ARM_TIMEOUT);
        extension.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic,10);

        /* Set the peak and nominal outputs */
        extension.configNominalOutputForward(0, ARM_TIMEOUT);
        extension.configNominalOutputReverse(0, ARM_TIMEOUT);
        extension.configPeakOutputForward(1, ARM_TIMEOUT);
        extension.configPeakOutputReverse(-1, ARM_TIMEOUT);

        /* Set Motion Magic gains in slot0 - see documentation */
        extension.selectProfileSlot(EXTENSION_PID_SLOT, EXTENSION_K_PID_LOOP_IDX);
        extension.config_kF(EXTENSION_PID_SLOT, EXTENSION_MOTOR_kF, ARM_TIMEOUT);
        extension.config_kP(EXTENSION_PID_SLOT, EXTENSION_MOTOR_kP, ARM_TIMEOUT);
        extension.config_kI(EXTENSION_PID_SLOT, EXTENSION_MOTOR_kI, ARM_TIMEOUT);
        extension.config_kD(EXTENSION_PID_SLOT, EXTENSION_MOTOR_kD, ARM_TIMEOUT);

        /* Set acceleration and vcruise velocity - see documentation */
        extension.configMotionCruiseVelocity(EXTENSION_CRUISEVELOCITY, ARM_TIMEOUT);
        extension.configMotionAcceleration(EXTENSION_ACCEL, ARM_TIMEOUT);

        /* Zero the sensor once on robot boot up */
        extension.setSelectedSensorPosition(0, EXTENSION_K_PID_LOOP_IDX, ARM_TIMEOUT);

        //Configure sensor source for primary PID
        elbow.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, ELBOW_K_PID_LOOP_IDX,
        200);   

        elbow.setSensorPhase(true);
        elbow.setInverted(false);

        /* Set relevant frame periods to be at least as fast as periodic rate */
        elbow.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, ARM_TIMEOUT);
        elbow.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic,10);

        /* Set the peak and nominal outputs */
        elbow.configNominalOutputForward(0, ARM_TIMEOUT);
        elbow.configNominalOutputReverse(0, ARM_TIMEOUT);
        elbow.configPeakOutputForward(1, ARM_TIMEOUT);
        elbow.configPeakOutputReverse(-1, ARM_TIMEOUT);

        /* Set Motion Magic gains in slot0 - see documentation */
        elbow.selectProfileSlot(ELBOW_PID_SLOT, ELBOW_K_PID_LOOP_IDX);
        elbow.config_kF(ELBOW_PID_SLOT, ELBOW_MOTOR_kF, ARM_TIMEOUT);
        elbow.config_kP(ELBOW_PID_SLOT, ELBOW_MOTOR_kP, ARM_TIMEOUT);
        elbow.config_kI(ELBOW_PID_SLOT, ELBOW_MOTOR_kI, ARM_TIMEOUT);
        elbow.config_kD(ELBOW_PID_SLOT, ELBOW_MOTOR_kD, ARM_TIMEOUT);

        /* Set acceleration and vcruise velocity - see documentation */
        elbow.configMotionCruiseVelocity(ELBOW_CRUIESVELOCITY, ARM_TIMEOUT);
        elbow.configMotionAcceleration(ELBOW_ACCEL, ARM_TIMEOUT);

        /* Zero the sensor once on robot boot up */
        elbow.setSelectedSensorPosition(0, ELBOW_K_PID_LOOP_IDX, ARM_TIMEOUT);
    }
    
    public void armPercentOutZero(){
        shoulder.set(ControlMode.PercentOutput, 0);
        extension.set(ControlMode.PercentOutput, 0);
        elbow.set(ControlMode.PercentOutput, 0);
    }

    public void armZeroSensorPos(){
        shoulder.setSelectedSensorPosition(shoulderEncoder.getAbsolutePosition() * SHOULDER_DEGREES_TO_TICKS);
        extension.setSelectedSensorPosition(0);
        elbow.setSelectedSensorPosition(0);
    }

    public void setPosition(double shoudlerPos, double extensionPos, double elbowPos){
        shoulder.set(ControlMode.MotionMagic, shoudlerPos);
        extension.set(ControlMode.MotionMagic, extensionPos);
        elbow.set(ControlMode.MotionMagic, elbowPos);
    }

    public void action(RobotCommander commander){
        if(commander.getArmPosition() == ArmPos.packagePos){
            setPosition(SHOULDER_TARGET_POSITION_PACKAGE, EXTENSION_TARGET_POSITION_PACKAGE, ELBOW_TARGET_POSITION_PACKAGE);
        } else if (commander.getArmPosition() == ArmPos.lowerNode){
            setPosition(SHOULDER_TARGET_POSITION_LOW, EXTENSION_TARGET_POSITION_LOW, ELBOW_TARGET_POSITION_LOW);
        } else if (commander.getArmPosition() == ArmPos.middleNode){
            setPosition(SHOULDER_TARGET_POSITION_MIDDLE, EXTENSION_TARGET_POSITION_MIDDLE, ELBOW_TARGET_POSITION_MIDDLE);
        } else if (commander.getArmPosition() == ArmPos.topNode){
            setPosition(SHOULDER_TARGET_POSITION_HIGH, EXTENSION_TARGET_POSITION_HIGH, ELBOW_TARGET_POSITION_HIGH);
        } else if (commander.getArmPosition() == ArmPos.stay){
            armPercentOutZero();
        } else if(commander.getArmPosition() == ArmPos.manual){
            if ((shoulder.getSelectedSensorPosition() / FALCON500_TICKS_PER_REV) > 170 && commander.armShoulder() > 0){
                shoulder.set(ControlMode.PercentOutput, 0);
            } else if((shoulder.getSelectedSensorPosition() / FALCON500_TICKS_PER_REV) < -170  && commander.armShoulder() < 0){
                shoulder.set(ControlMode.PercentOutput, 0);
            } else{
                shoulder.set(ControlMode.PercentOutput, commander.armShoulder());
            }

            if ((extension.getSelectedSensorPosition() / FALCON500_TICKS_PER_REV)> 70  && commander.armExtension() > 0){
                extension.set(ControlMode.PercentOutput, 0);
            } else if ((extension.getSelectedSensorPosition() / FALCON500_TICKS_PER_REV) < 5 && commander.armExtension() < 0){
                extension.set(ControlMode.PercentOutput, 0);
            } else{
                extension.set(ControlMode.PercentOutput, commander.armExtension());
            }
        }
    }

    public void logData(){

        double shoulderPosition = shoulder.getSelectedSensorPosition();
        double extensionPosition = extension.getSelectedSensorPosition();
        double elbowPosition = elbow.getSelectedSensorPosition();

        SmartDashboard.putNumber("shoulderVelocity", shoulderPosition / FALCON500_TICKS_PER_REV);
        SmartDashboard.putNumber("extensionVelocity", extensionPosition / FALCON500_TICKS_PER_REV);     
        SmartDashboard.putNumber("elbowVelocity", elbowPosition / FALCON500_TICKS_PER_REV);
        SmartDashboard.putNumber("Absolute Encoder Ticks", shoulderEncoder.getAbsolutePosition() * 1.3786);
        SmartDashboard.putNumber("Absolute Encoder", shoulderEncoder.getAbsolutePosition());
    }

    public void brakeMode(){
        shoulder.setNeutralMode(NeutralMode.Brake);
        extension.setNeutralMode(NeutralMode.Brake);
    }

    public void coastMode(){
        shoulder.setNeutralMode(NeutralMode.Coast);
        extension.setNeutralMode(NeutralMode.Coast);
    }

}

