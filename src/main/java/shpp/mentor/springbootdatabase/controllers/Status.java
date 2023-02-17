package shpp.mentor.springbootdatabase.controllers;

public enum Status {
    PLANNED,
    COORDINATE,
    WORK_IN_PROGRESS,
    DONE,
    CANCEL;

    public static Status nextStatus(String sts) {
        int index = Status.valueOf(sts).ordinal();
        if (index < (Status.values().length-2)) {
            index++;
        }
        return Status.values()[index];
    }
    public static Status startStatus() {
        return Status.PLANNED;
    }
}