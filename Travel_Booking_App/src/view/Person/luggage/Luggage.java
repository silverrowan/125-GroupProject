
package view.Person.luggage;

/**
 *
 * @author rowan
 */
public class Luggage {
    int id;
    String description;
    int owner_id;
    
    public Luggage(){}
    public Luggage( String desc, int ownerId ){
        this.description = desc;
        this.owner_id = ownerId;
    }
    
    //getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }
    
    
}
