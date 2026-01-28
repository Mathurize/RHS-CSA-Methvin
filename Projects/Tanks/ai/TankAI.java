package ai;

import game.PowerUp;
import game.Target;
import game.Tank;
import game.TankAIBase;
import game.Vec2;

public class TankAI extends TankAIBase {

    public String getPlayerName() {
        return "YOUR-NAME-HERE";  // <---- Put your first name here
    }
    public int getPlayerPeriod() {
        return -1;                // <---- Put your period here
    }
        
    // You are free to add member variables & methods to this class (and delete this comment).
    //  You should use the methods in its base class (TankAIBase) to query the world. 
    //  Note that you are not allowed to reach into game code directly or make any
    //  modifications to code in the game package. Use your judgement and ask your 
    //  teacher if you are not sure. If it feels like cheating, it probably is.

    public boolean updateAI() {
        package ai;

import game.PowerUp;
import game.TankAIBase;
import game.Target;
import game.Vec2;

public class TankAI extends TankAIBase {

   
    public String getPlayerName() {
        return "RUHAAN";
    }

    public int getPlayerPeriod() {
        return 6;
    }

    
    public boolean updateAI() {

        
        if (getTank().hasCommand()) {
            return true;
        }

        Vec2 myPos = getTankPos();
        Vec2 myDir = getTankDir().unit();
        double myRange = getTankShotRange();

       
        Target[] targets = getTargets();
        Target bestTarget = null;
        double bestScore = -Double.MAX_VALUE;

        for (Target t : targets) {
            if (t == null) continue;

            Vec2 tPos = t.getPos();
            double dist = Vec2.distance(tPos, myPos);

            
            double baseValue = 25.0;

          
            double rangeBonus = (dist <= myRange) ? 15.0 : 0.0;

         
            double score = (baseValue + rangeBonus) / (dist + 0.1);

            if (score > bestScore) {
                bestScore = score;
                bestTarget = t;
            }
        }

        
        if (bestTarget != null) {
            Vec2 tgtPos = bestTarget.getPos();
            double distT = Vec2.distance(tgtPos, myPos);

            Vec2 toTarget = Vec2.subtract(tgtPos, myPos);

            if (toTarget.lengthSqr() > 0.0001) {
                Vec2 desiredDir = toTarget.unit();
                double alignment = Vec2.dot(myDir, desiredDir);

                
                if (distT <= myRange) {
                    if (alignment < 0.90) {
                        queueCmd("turn", desiredDir);
                    } else {
                        queueCmd("shoot", desiredDir);
                    }
                    return true;
                }

              
                Vec2 moveVec;
                if (Math.abs(toTarget.x) > Math.abs(toTarget.y)) {
                    moveVec = new Vec2(Math.signum(toTarget.x), 0);
                } else {
                    moveVec = new Vec2(0, Math.signum(toTarget.y));
                }

                double moveAlign = Vec2.dot(myDir, moveVec.unit());
                if (moveAlign < 0.90) {
                    queueCmd("turn", moveVec);
                } else {
                    queueCmd("move", moveVec);
                }
                return true;
            }
        }

        
        PowerUp[] powerUps = getPowerUps();
        for (PowerUp pu : powerUps) {
            if (pu == null) continue;
            Vec2 puPos = pu.getPos();
            Vec2 toPU = Vec2.subtract(puPos, myPos);

            if (toPU.lengthSqr() > 0.0001) {
                Vec2 moveVec;
                if (Math.abs(toPU.x) > Math.abs(toPU.y)) {
                    moveVec = new Vec2(Math.signum(toPU.x), 0);
                } else {
                    moveVec = new Vec2(0, Math.signum(toPU.y));
                }

                double puAlign = Vec2.dot(myDir, moveVec.unit());
                if (puAlign < 0.90) {
                    queueCmd("turn", moveVec);
                } else {
                    queueCmd("move", moveVec);
                }
                return true;
            }
        }

        
        Vec2 center = new Vec2(5, 5);
        Vec2 toCenter = Vec2.subtract(center, myPos);
        if (toCenter.lengthSqr() > 0.0001) {
            Vec2 moveVec;
            if (Math.abs(toCenter.x) > Math.abs(toCenter.y)) {
                moveVec = new Vec2(Math.signum(toCenter.x), 0);
            } else {
                moveVec = new Vec2(0, Math.signum(toCenter.y));
            }

            double centerAlign = Vec2.dot(myDir, moveVec.unit());
            if (centerAlign < 0.90) {
                queueCmd("turn", moveVec);
            } else {
                queueCmd("move", moveVec);
            }
        }

        return true;
    }
}


        // TODO: Your code goes here

        return true;
    }
}
