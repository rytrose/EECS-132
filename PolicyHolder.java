/* Ryan Rose EECS 132 Programming Project 1 
 * 
 * This class models the policy holder, including the holder's policy and its anniversary date, and can process the
 * date in order to reset the policy of the policy holder at the correct date.
 */

public class PolicyHolder{
  // FIELDS
  private String firstName = "";
  private String lastName = "";
  private String address = "";
  private InsurancePolicy policy = null;
  private Date date = null;
  
  // CONSTRUCTORS 
  
  /** Constuctor with only name and address. */
  public PolicyHolder(String firstName, String lastName, String address){
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
  }
  
  /** Constructor with policy and date of creation attached to the name and address of the holder. */
  public PolicyHolder(String firstName, String lastName, String address, InsurancePolicy policy, Date date){
    this(firstName, lastName, address);
    this.policy = policy;
    this.date = date;
  }
   
  // METHODS
  
  /** Returns the first name of the policy holder. */
  public String getFirstName(){
    return this.firstName;
  }
  
  /** Sets the first name of the policy holder. */
  public void setFirstName(String firstName){
    this.firstName = firstName;
  }
  
  /** Returns the last name of the policy holder. */
  public String getLastName(){
    return this.lastName;
  }
  
  /** Sets the last name of the policy holder. */
  public void setLastName(String lastName){
    this.lastName = lastName;
  }
  
  
  /** Returns the address of the policy holder. */
  public String getAddress(){
    return this.address;
  }
  
  /** Sets the address of the policy holder. */
  public void setAddress(String address){
    this.address = address;
  }
 
  /** Returns the insurance policy of the policy holder. */
  public InsurancePolicy getPolicy(){
    return this.policy;
  }
  
  /** Sets the insurance policy of the policy holder. */
  public void setInsurancePolicy(InsurancePolicy policy){
    this.policy = policy;
  }

  /** Returns the creation date of the policy holder entry. */
  public Date getDate(){
    return this.date;
  }
  
  /** Sets the creation date of the policy holder entry. */
  public void setDate(Date date){
    this.date = date;
  }
  
  /** Increments the date of the policy holder, as well as processing the date through InsurancePolicy. */
  public void incrementDate(){
    this.getDate().incrementDay();
    Date newDate = this.getDate();
    this.setDate(newDate);
    this.getPolicy().processDate(this.getDate());
  }
}