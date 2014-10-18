package sepm.ws14.e0828630.domain;

import org.joda.time.DateTime;

public class Booking extends DomainObject {

    private DateTime from;
    private DateTime to;
    private DateTime created;
    private DateTime lastChanged;
    private int horseId;
    private int customerId;

    public Booking(DateTime from, DateTime to, int horseId, int customerId) {
        this.from = from;
        this.to = to;
        this.horseId = horseId;
        this.customerId = customerId;
    }

    public Booking(int id, DateTime from, DateTime to, int horseId, int customerId, DateTime created, DateTime lastChanged) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.horseId = horseId;
        this.customerId = customerId;
        this.created = created;
        this.lastChanged = lastChanged;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public DateTime getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(DateTime lastChanged) {
        this.lastChanged = lastChanged;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public DateTime getFrom() {
        return from;
    }

    public void setFrom(DateTime from) {
        this.from = from;
    }

    public DateTime getTo() {
        return to;
    }

    public void setTo(DateTime to) {
        this.to = to;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }
}
