
package view.Person;

/**
 *
 * @author rowan
 */
public class PersonModel {
    private int id;
    private int accountNumber;
    private String name;
    private Double weight;
    
    
    //simplified, diff v types
    public PersonModel(){}
    public PersonModel(String firstName, int accountNumber, Double weight ){
        this.name = firstName;
        this.accountNumber = accountNumber;
        this.weight = weight;
    }
    
    //getters, setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    
}
