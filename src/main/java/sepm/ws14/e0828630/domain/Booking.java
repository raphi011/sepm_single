package sepm.ws14.e0828630.domain;

import java.util.Date;

public class Booking extends DomainObject {

    private Date from;
    private Date to;
    private Date created;
    private Date lastChanged;
    private int horseId;
    private int customerId;

    public Booking(Date from, Date to, int horseId, int customerId) {
        this.from = from;
        this.to = to;
        this.horseId = horseId;
        this.customerId = customerId;
    }

    public Booking(int id, Date from, Date to, int horseId, int customerId, Date created, Date lastChanged) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.horseId = horseId;
        this.customerId = customerId;
        this.created = created;
        this.lastChanged = lastChanged;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }
}
