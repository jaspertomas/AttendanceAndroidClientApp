package models;


import org.json.JSONObject;

public abstract class Entity {
	protected JSONObject values= new JSONObject();
	
    public abstract Integer getId();
    public abstract void setId(Integer id);
//    public abstract Class getDaoClass();
    public abstract JSONObject getValues();
    public abstract void save();
    public abstract void delete();
    public abstract void insert();
    public abstract void update();

    public class NullValuesAssignmentException extends Exception {
        public NullValuesAssignmentException() {
            super("You have attempted to assign a null value to this.values");
        }
    }
}
