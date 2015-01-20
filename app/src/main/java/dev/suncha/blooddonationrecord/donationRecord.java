package dev.suncha.blooddonationrecord;

/**
 * Created by Sunny on 1/19/2015.
 */
public class donationRecord {
    int _id;
    String _date;
    String _location;
    String _organisers;
    String _reminder;

    public donationRecord(){

    }
    public donationRecord(int _id, String _date, String _location, String _organisers, String _reminder) {
        this._id = _id;
        this._date = _date;
        this._location = _location;
        this._organisers = _organisers;
        this._reminder = _reminder;
    }

    public donationRecord(String _date, String _location, String _organisers, String _reminder){
        this._date=_date;
        this._location=_location;
        this._organisers=_organisers;
        this._reminder=_reminder;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_location() {
        return _location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public String get_organisers() {
        return _organisers;
    }

    public void set_organisers(String _organisers) {
        this._organisers = _organisers;
    }

    public String get_reminder() {
        return _reminder;
    }

    public void set_reminder(String _reminder) {
        this._reminder = _reminder;
    }
}
