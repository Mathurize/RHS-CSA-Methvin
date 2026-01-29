package ai;

import game.PowerUp;
import game.Target;
import game.Tank;
import game.TankAIBase;
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

        
        Tank otherTank = getOther();
        Target bestTarget = null;
        double bestScore = -Double.MAX_VALUE;

        if (otherTank != null) {
            Vec2 otherPos = otherTank.getPos();
            double distOther = Vec2.distance(otherPos, myPos);

            
            double baseValueO = 30.0;
            double rangeBonusO = (distOther <= myRange) ? 15.0 : 0.0;
            double scoreO = (baseValueO + rangeBonusO) / (distOther + 0.1);

            bestTarget = new Target() {
                public Vec2 getPos() {
                    return otherPos;
                }
                public double getRadius() {
                    return otherTank.getRadius();
                }
                public int getId() {
                    return -1;
                }
            };
            bestScore = scoreO;
        }

        
        Target[] targets = getTargets();

        for (Target t : targets) {
            if (t == null) continue;

            Vec2 tPos = t.getPos();
            double dist = Vec2.distance(tPos, myPos);

            double baseValue = 25.0;
            double rangeBonus;
            if (dist <= myRange) {
                rangeBonus = 15.0;
            } else {
                rangeBonus = 0.0;
            }
            double score = (baseValue + rangeBonus) / (dist + 0.1);

            if (score > bestScore) {
                bestScore = score;
                bestTarget = t;
            }
        }

        if (bestTarget != null) {
            Vec2 tgtPos = bestTarget.getPos();
            double distT = Vec2.distance(tgtPos, myPos);

            if (distT <= myRange) {
                Vec2 aimDir = Vec2.subtract(tgtPos, myPos).unit();
                double alignment = Vec2.dot(myDir, aimDir);
                if (alignment < 0.90) {
                    queueCmd("turn", aimDir);
                } else {
                    queueCmd("shoot", aimDir);
                }
                return true;
            }

            Vec2 toTarget = Vec2.subtract(tgtPos, myPos);

            if (toTarget.lengthSqr() > 0.0001) {

                Vec2 horizStep = new Vec2(toTarget.x, 0);
                Vec2 vertStep = new Vec2(0, (toTarget.y));

                double distAfterHoriz = Vec2.distance(Vec2.add(myPos, horizStep), tgtPos);
                double distAfterVert = Vec2.distance(Vec2.add(myPos, vertStep), tgtPos);

                Vec2 bestStep;
                if (distAfterHoriz < distAfterVert) {
                    bestStep = horizStep;
                } else {
                    bestStep = vertStep;
                }

                double stepAlign = Vec2.dot(myDir, bestStep.unit());
                if (stepAlign < 0.90) {
                    return queueCmd("turn", bestStep);
                } else {
                    return queueCmd("move", bestStep);
                }
            }
        }

        PowerUp[] powerUps = getPowerUps();
        for (PowerUp pu : powerUps) {
            if (pu == null) continue;

            Vec2 puPos = pu.getPos();
            Vec2 toPU = Vec2.subtract(puPos, myPos);

            if (toPU.lengthSqr() > 0.0001) {

                Vec2 horizStep = new Vec2((toPU.x), 0);
                Vec2 vertStep = new Vec2(0, (toPU.y));

                double distAfterHoriz = Vec2.distance(Vec2.add(myPos, horizStep), puPos);
                double distAfterVert = Vec2.distance(Vec2.add(myPos, vertStep), puPos);

                Vec2 bestStep;
                if (distAfterHoriz < distAfterVert) {
                    bestStep = horizStep;
                } else {
                    bestStep = vertStep;
                }

                double puAlign = Vec2.dot(myDir, bestStep.unit());
                if (puAlign < 0.90) {
                    queueCmd("turn", bestStep);
                } else {
                    queueCmd("move", bestStep);
                }
                return true;
            }
        }

        Vec2 center = new Vec2(5, 5);
        Vec2 toCenter = Vec2.subtract(center, myPos);

        if (toCenter.lengthSqr() > 0.0001) {

            Vec2 horizStep = new Vec2((toCenter.x), 0);
            Vec2 vertStep = new Vec2(0, (toCenter.y));

            double distAfterHoriz = Vec2.distance(Vec2.add(myPos, horizStep), center);
            double distAfterVert = Vec2.distance(Vec2.add(myPos, vertStep), center);

            Vec2 bestStep;
            if (distAfterHoriz < distAfterVert) {
                bestStep = horizStep;
            } else {
                bestStep = vertStep;
            }

            double centerAlign = Vec2.dot(myDir, bestStep.unit());
            if (centerAlign < 0.90) {
                queueCmd("turn", bestStep);
            } else {
                queueCmd("move", bestStep);
            }
        }

        return true;
    }
}
