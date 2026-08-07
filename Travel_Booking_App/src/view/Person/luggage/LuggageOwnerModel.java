package view.Person.luggage;

/**
 *
 * @author rowan
 */
public class LuggageOwnerModel {
    private int luggageId;
    private String description;
    private int ownerId;
    private String name;
    private int accountNumber;
    
    public LuggageOwnerModel(){}
    public LuggageOwnerModel( String desc, int ownerId ){
        this.description = desc;
        this.ownerId = ownerId;
    }

    public int getLuggageId() {
        return luggageId;
    }

    public void setLuggageId(int luggageId) {
        this.luggageId = luggageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    
}
