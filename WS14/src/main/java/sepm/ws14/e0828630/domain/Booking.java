package sepm.ws14.e0828630.domain;

import java.time.LocalDateTime;

public class Booking extends DomainObject {

    private LocalDateTime from;
    private LocalDateTime to;
    private LocalDateTime created;
    private LocalDateTime lastChanged;
    private int horseId;
    private int customerId;
    private boolean isCanceled;


    public Booking(LocalDateTime from, LocalDateTime to, int horseId, int customerId) {
        this.from = from;
        this.to = to;
        this.horseId = horseId;
        this.customerId = customerId;
    }

    public Booking(int id, LocalDateTime from, LocalDateTime to, int horseId, int customerId, LocalDateTime created, LocalDateTime lastChanged, boolean isCanceled) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.horseId = horseId;
        this.customerId = customerId;
        this.created = created;
        this.lastChanged = lastChanged;
        this.isCanceled = isCanceled;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(LocalDateTime lastChanged) {
        this.lastChanged = lastChanged;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

//    public boolean isEditable() {
//        return LocalTime..daysBetween(LocalTime.now(), from ).getDays() > 14 && !isCanceled;
//    }
}
