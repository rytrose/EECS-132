/* Ryan Rose EECS 132 Programming Project 1 
 * 
 * This class models an insurance policy; processing claims given inputted restrictions and policy guidelines,
 * resetting after an expiration date, and computing useful data for both the insurance company and the policy holder.
 */

public class InsurancePolicy {
  /* FIELDS */
  
  private String policyNumber = "";
  private Date expirationDate = null;
  private double copay = 0.0;
  private double deductible = 0.0;
  // Stores amount currently applied to deductible. 
  private double appliedToDeductible = 0.0;
  private double actuarialValue = 0.0;
  // Stores accumulated annual benefit.
  private double annualBenefit = 0.0;
  private boolean hasAnnualLimit = false;
  private double annualLimit = 0.0;
  private boolean hasOutOfPocketLimit = false;
  private double outOfPocketLimit = 0.0;
  // Stores accumulated lifetime benefit. 
  private double lifetimeBenefit = 0.0;
  private boolean hasLifetimeLimit = false;
  private double lifetimeLimit = 0.0;
  private double expectedTenYearBenefit = 0.0;
  private double profitMargin = 0.0;
  private InsurancePolicy supplementalInsurance = null;
  // Stores accumulated out of pocket costs. 
  private double outOfPocketCost = 0.0;
  // Stores the premium of this policy.
  private double premium = 0.0;
  
  // CONSTRUCTORS 
  
  /** Creates a less-restricted, simple insurance policy. */
  public InsurancePolicy(String policyNumber, Date expirationDate, double copay, 
                         double deductible, double actuarialValue){
    this.policyNumber = policyNumber;
    this.expirationDate = expirationDate;
    this.copay = copay;
    this.deductible = deductible;
    this.actuarialValue = actuarialValue;
  }
  
  /** Creates an insurance policy with limits, expected outcomes, and supplemental policy. */
  public InsurancePolicy(String policyNumber, Date expirationDate, double copay, 
                         double deductible, double actuarialValue, boolean hasAnnualLimit,
                         double annualLimit, boolean hasOutOfPocketLimit, double outOfPocketLimit,
                         boolean hasLifetimeLimit, double lifetimeLimit,
                         double expectedTenYearBenefit, double profitMargin,
                         InsurancePolicy supplementalInsurance){
    this(policyNumber, expirationDate, copay, deductible, actuarialValue);
    this.hasAnnualLimit = hasAnnualLimit;
    this.annualLimit = annualLimit;
    this.hasOutOfPocketLimit = hasOutOfPocketLimit;
    this.outOfPocketLimit = outOfPocketLimit;
    this.hasLifetimeLimit = hasLifetimeLimit;
    this.lifetimeLimit = lifetimeLimit;
    this.expectedTenYearBenefit = expectedTenYearBenefit;
    this.profitMargin = profitMargin;
    this.supplementalInsurance = supplementalInsurance;
  }
  
  // METHODS 
  
  /** Returns the policy number of the policy in question. */
  public String getPolicyNumber(){
    return this.policyNumber;
  }
  
  /** Sets the copay of the policy in question. */
  public void setCopay(double copay){
    this.copay = copay;
  }
  
  /** Returns the copay of the policy in question. */
  public double getCopay(){
    return this.copay;
  }
  
  /** Sets the yearly deductible for the policy in question. */
  public void setDeductible(double deductible){
    this.deductible = deductible;
  }
  
  /** Returns the yearly deductible for the policy in question. */
  public double getDeductible(){
    return this.deductible;
  }
  
  /** Returns the amount currently applied to the deductible this year. */
  public double getAmountAppliedToDeductible(){
    return this.appliedToDeductible;
  }
  
  /** Sets the amount currently applied to the deductible this year. */
  private void setAmountAppliedToDeductible(double amount){
    this.appliedToDeductible = this.getAmountAppliedToDeductible() + amount;
  }
    
  /** Sets the actuarial value, the percentage of claims the policy in question will cover. */
  public void setActuarialValue(double actuarialValue){
    this.actuarialValue = actuarialValue;
  }
  
  /** Returns the actuarial value of the policy in question. */
  public double getActuarialValue(){
    return this.actuarialValue;
  }
  
  /** Defines if the policy has an annual limit, and sets the value if it does. */
  public void setAnnualLimit(boolean hasAnnualLimit, double annualLimit){
    this.hasAnnualLimit = hasAnnualLimit;
    if(this.hasAnnualLimit == true)
      this.annualLimit = annualLimit;
  }
  
  /** Returns whether or not the policy in question has an annual limit. */
  public boolean hasAnnualLimit(){
    return(this.hasAnnualLimit == true);
  }
   
  /** Returns the annual limit for the policy, or -0.1 if it has none. */
  public double getAnnualLimit(){
    if(this.hasAnnualLimit == true)
      return this.annualLimit;    
    else
      return -0.1;
  }
    
  /** Defines if the policy has an out of pocket limit, and sets the value if it does. */
  public void setOutOfPocketLimit(boolean hasOutOfPocketLimit, double outOfPocketLimit){
    this.hasOutOfPocketLimit = hasOutOfPocketLimit;
    if(this.hasOutOfPocketLimit == true)
      this.outOfPocketLimit = outOfPocketLimit;
  }
  
  /** Returns whether or not the policy has an out of pocket limit. */
  public boolean hasOutOfPocketLimit(){
    return(this.hasOutOfPocketLimit == true);
  }

  /** Returns the out of pocket limit for the policy, or -0.1 if it has none. */
  public double getOutOfPocketLimit(){
    if(this.hasOutOfPocketLimit == true)
      return this.outOfPocketLimit;
    else
      return -0.1;
  }
  
  /** Gets accumulated out of pocket cost. */
  private double getOutOfPocketCost(){
    return this.outOfPocketCost;
  }
  
  /** Sets accumulated out of pocket cost. */
  private void setOutOfPocketCost(double amount){
    if(this.hasOutOfPocketLimit() == true &&((this.getOutOfPocketCost() + amount) > this.getOutOfPocketLimit()))
      this.outOfPocketCost = this.getOutOfPocketLimit();
    else
      this.outOfPocketCost = this.getOutOfPocketCost() + amount;
  }
  
  /** Defines if the policy has a lifetime limit, and sets the value if it does. */
  public void setLifetimeLimit(boolean hasLifetimeLimit, double lifetimeLimit){
    this.hasLifetimeLimit = hasLifetimeLimit;
    if(this.hasLifetimeLimit == true)
      this.lifetimeLimit = lifetimeLimit;
  }
  
  /** Returns whether or not the policy has a lifetime limit. */
  public boolean hasLifetimeLimit(){
    return(this.hasLifetimeLimit == true);
  }

  /** Returns the lifetime limit for the policy, or -0.1 if it has none. */
  public double getLifetimeLimit(){
    if(this.hasLifetimeLimit == true)
      return this.lifetimeLimit;
    else
      return -0.1;
  }
  
  /** Sets the profit margin the company desires from this policy. */
  public void setProfitMargin(double profitMargin){
    this.profitMargin = profitMargin;
  }
  
  /** Returns the desired profit margin from this policy. */
  public double getProfitMargin(){
    return this.profitMargin;
  }
  
  /** Sets the expected benefit, in dollars, of the policy after ten years. */
  public void setExpectedTenYearBenefit(double expectedBenefit){
    this.expectedTenYearBenefit = expectedBenefit;
  }
  
  /** Returns the expected benefit, in dollars, of the policy after ten years. */
  public double getExpectedTenYearBenefit(){
    return this.expectedTenYearBenefit;
  }
  
  /** Returns the amount of money this policy has paid this year. */
  public double getAnnualBenefit(){
    return this.annualBenefit;
  }
  
  /** Sets the amount of money this policy has paid this year. */
  private void setAnnualBenefit(double amount){
    if(this.hasAnnualLimit() == true &&((this.getAnnualBenefit() + amount) > this.getAnnualLimit()))
      this.annualBenefit = this.getAnnualLimit();
    else
      this.annualBenefit = this.getAnnualBenefit() + amount;
  }
  
  /** Returns the amount of money this policy has paid since its creation. */
  public double getLifetimeBenefit(){
    return this.lifetimeBenefit;
  }
  
  /** Sets the amount of money this poliy has paid in its existence. */
  private void setLifetimeBenefit(double amount){
    if(this.hasLifetimeLimit() == true &&((this.getLifetimeBenefit() + amount) > this.getLifetimeLimit()))
      this.lifetimeBenefit = this.getLifetimeLimit();
    else
    this.lifetimeBenefit = this.getLifetimeBenefit() + amount;
  }
  
  /** Creates a supplemental insurance policy within this policy. */
  public void setSupplementalInsurance(InsurancePolicy policy){
    this.supplementalInsurance = policy;
  }
  
  /** Returns the supplemental insurance policy for this policy, or null if there is none. */
  public InsurancePolicy getSupplementalInsurance(){
    return this.supplementalInsurance;
  }
  
  /** Returns the expiration date of the policy. */
  public Date getExpirationDate(){
    return this.expirationDate;
  }
 
  /** Returns the premium for the policy. */
  public double getPremium(){
    return this.premium;
  }
  
  /** Returns the insurance claim minus the copay amount. */
  public double applyCopay(double claim){
    return claim - this.getCopay();
  }
 
  /** Applies the deductible and returns a new claim. */
  public double applyDeductible(double claim){
    if(this.getAmountAppliedToDeductible() <= this.getDeductible()){
      if((this.getAmountAppliedToDeductible() + claim) < this.getDeductible()){
        this.setAmountAppliedToDeductible(claim);
        return 0.0;
      }
      if((this.getAmountAppliedToDeductible() + claim) >= this.getDeductible()){
        double toBeInsured = (claim - (this.getDeductible() - this.getAmountAppliedToDeductible()));
        this.setAmountAppliedToDeductible(this.getDeductible() - this.getAmountAppliedToDeductible());
        return toBeInsured;   
      }
      else
        return 0.0;
    }
    else 
      return 0.0;
  }
  
  /** Applies the actuarial value of the policy to a claim input. */
  public double applyActuarialValue(double claim){
    return claim * this.getActuarialValue();
  }
  
  /** Applies supplemental insurance policy, and returns adjusted claim. 
    * Returns original claim if no supplemental insurance. */
  public double applySupplementalInsurance(double claim){
    if(this.supplementalInsurance != null)
      return this.getSupplementalInsurance().processClaim(claim);
    else
      return claim;
  }
  
  /** Applies the annual limit of the policy and returns the adjusted claim. */
  public double applyAnnualLimit(double claim){
    if(this.hasAnnualLimit() == true && (this.getAnnualLimit() < (claim + this.getAnnualBenefit()))){
      return claim - ((claim + this.getAnnualBenefit()) - this.getAnnualLimit());
    }
    else{
      return claim;
    }
  }
  
  /** Applies the lifetime limit of the policy and returns the adjusted claim. */
  public double applyLifetimeLimit(double claim){
    if(this.hasLifetimeLimit() == true && (this.getLifetimeLimit() < (claim + this.getLifetimeBenefit()))){
      return claim - ((claim + this.getLifetimeBenefit()) - this.getLifetimeLimit());
    }
    else{
      return claim;
    }
  }
  
  /** Takes an input claim and returns what the policy holder must pay out of pocket. */
  public double processClaim(double claim){
    double originalClaim = claim;
    // Reduces by copay.
    claim = this.applyCopay(claim);
    // Reduces by deductible.
    claim = this.applyDeductible(claim);
    // Reduces by the actuarial value.
    claim = this.applyActuarialValue(claim);
    //Reduces claim if it causes the policy to surpass its annual limit.
    claim = this.applyAnnualLimit(claim);
    // Reduces claim if it causes the policy to exceed its lifetime limit.
    claim = this.applyLifetimeLimit(claim);
    // Sets a local variable benefit to the reduced claim.
    double benefit = claim;
    // Sets a local variable to current out of pocket cost. 
    double outOfPocketCost = originalClaim - claim;
    // Reduces out of pocket cost by applying supplemental insurance.
      outOfPocketCost = this.applySupplementalInsurance(outOfPocketCost);
    // If policy has an out of pocket limit, and the current out of pocket cost exceeds that limit,
    // it reduces the cost by the amount it exceeds the limit, and adds that sum to the benefit.
    if(this.hasOutOfPocketLimit == true && ((this.getOutOfPocketCost() + outOfPocketCost) > 
                                            this.getOutOfPocketLimit())){
      outOfPocketCost = this.getOutOfPocketLimit() - this.getOutOfPocketCost();
      benefit += (this.getOutOfPocketCost() + outOfPocketCost) - this.getOutOfPocketLimit();
    }
    // Benefit is added to annual benefit.
    this.setAnnualBenefit(benefit);
    // Benefit is added to lifetime benefit.
    this.setLifetimeBenefit(benefit);
    // Out of pocket cost is added to yearly out of pocket cost.
    this.setOutOfPocketCost(outOfPocketCost);
    return outOfPocketCost;
  }
  
  /** Resets all annual accumulations, adjusts expected ten year benefit, and sets new expiration date. */
  public void renewPolicy(){
    // Resets amount applied to deductible.
    this.setAmountAppliedToDeductible(-this.getAmountAppliedToDeductible());
    // Resets annual benefit.
    this.setAnnualBenefit(-this.getAnnualBenefit());
    // Resets accumulated out of pocket cost.
    this.setOutOfPocketCost(-this.getOutOfPocketCost());
    // Adjusts the expected ten year benefit.
    this.setExpectedTenYearBenefit(this.getAnnualBenefit() + (0.9 * this.getExpectedTenYearBenefit()));
    // Resets expiration date to one year from current expiration date.
    this.expirationDate = new Date(expirationDate.getDay(), expirationDate.getMonth(), expirationDate.getYear() + 1);
  }
  
  /** Sets the premium to a percentage profit margin more than the ten year benefit.*/
  public void calculatePremium(){
    this.premium = (this.getProfitMargin() + 1) * (0.1 * this.getExpectedTenYearBenefit());
  }
  
  /** Returns the sum of all premiums associated with this policy or any of its supplemental policies. */
  public double totalPremium(){
    return this.getPremium() + this.getSupplementalInsurance().getPremium();
  }
  
  /** Compares given date to the expiration date of the policy, and renews the policy. Also processes the date of any
    * supplemental insurance policy if it exists. */
  public void processDate(Date date){
    if(this.getExpirationDate().compareTo(date) <= 0){
      this.renewPolicy();
      this.calculatePremium();
    }
    if(this.getSupplementalInsurance() != null)
      this.getSupplementalInsurance().processDate(date);  
  } 
}











