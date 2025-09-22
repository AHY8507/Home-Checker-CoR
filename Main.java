class Status {
    public boolean alarm = true;
    public boolean locked = true;
    public boolean light = true;
    public Status(boolean alarm, boolean locked, boolean light) {
        this.alarm = alarm;
        this.locked = locked;
        this.light = light;
    }    
}

abstract class HomeChecker {
    protected HomeChecker successor;
    
    public void setSeccessor(HomeChecker successor) {
        this.successor = successor;
    }

    public abstract void check(Status s);

    public void next(Status home) {
        if(this.successor != null) {
            this.successor.check(home);
        }
    }

}

class AlarmCheck extends HomeChecker {
    @Override
    public void check(Status s) {
        if(!s.alarm) {
            throw new Error("Alarm");
        }
        this.next(s);
    }
}

class LockedCheck extends HomeChecker {
    @Override
    public void check(Status s) {
        if(!s.locked) {
            throw new Error("Locked");
        }
        this.next(s);
    }
}

class LightCheck extends HomeChecker {
    @Override
    public void check(Status s) {
        if(!s.light) {
            throw new Error("Light");
        }
        this.next(s);
    }
}


public class Main {
    public static void main(String[] args) {
        Status st = new Status(true, true, true);
        AlarmCheck alarm = new AlarmCheck();
        LockedCheck locked = new LockedCheck();
        LightCheck light = new LightCheck();
        alarm.setSeccessor(locked);
        locked.setSeccessor(light);
        locked.check(st);
    }
}