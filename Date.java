/* Ryan Rose EECS 132 Programming Project 1 
 * 
 * This class creates a date from day, month, and year, and allows for incrementation and comparison of dates.
 * 
 */

public class Date{
  // FIELDS 
  
  private int day = 0;
  private int month = 0;
  private int year = 0;
  
  // CONSTRUCTOR 
  
  /** Creates a date with given day, month, and year */
  public Date(int day, int month, int year){
    this.day = day;
    this.month = month;
    this.year = year;
  }
  
  // METHODS
  
  /** Returns the day of the current date. */
  public int getDay(){
    if(this.day >= 1 && this.day <= 31)
      return this.day;
    else 
      return 0;
  }
  
  /** Returns the month of the current date. */
  public int getMonth(){
    if(this.month >= 1 && this.month <= 12)
      return this.month;
    else 
      return 0;
  }
  
  /** Returns the year of the current date. */
  public int getYear(){
    return this.year;
  }
  
  /** Returns true if the current year is a leap year. */
  public boolean isLeapYear(){
    if(this.year % 4 == 0 && this.year % 100 != 0)
      return true;
    if(this.year % 100 == 0 && this.year % 400 == 0)
      return true;
    else
      return false;
  }
  
  /** Adds one day to date. */
  public void incrementDay(){
    //Adds one to any day that doesn't exceed 28.
    if(this.getDay() <= 27){
      this.day++;}
    else{//Adds one to any day over 27 in months that have 31 days, up until adding the 31st day.
    if((this.getDay() > 27) && (this.getDay() <= 30) && (this.getMonth() == 1 || this.getMonth() == 3 || 
                                                         this.getMonth() == 5 || this.getMonth() == 7 || 
                                                         this.getMonth() == 8 || this.getMonth() == 10 ||
                                                         this.getMonth() == 12)){
      this.day++;}
    else{//Adds one to any day over 27 in months that have 30 days, up until the adding 30th day.
    if((this.getDay() > 27) && (this.getDay() <= 29) && (this.getMonth() == 4 || this.getMonth() == 6 || this.getMonth() == 9 ||
                                               this.getMonth() == 11)){
      this.day++;}
    //Adds one day to start a new month in months with 31 days, excluding December.
    else{if((this.getMonth() == 1 || this.getMonth() == 3 || this.getMonth() == 5 || this.getMonth() == 7 ||
        this.getMonth() == 8 || this.getMonth() == 10) && (this.getDay() == 31)){
      this.month++;
      this.day = 1;}
    //Adds one day to December 31st, starting a new year.
    else{if((this.getMonth() == 12) && (this.getDay() == 31)){
      this.day = 1;
      this.month = 1;
      this.year++;}
    //Adds one day to start a new month in months with 30 days.
    else{if((this.getMonth() == 4 || this.getMonth() == 6 || this.getMonth() == 9 || this.getMonth() == 11)
        && (this.getDay() == 30)){
      this.month++;
      this.day = 1;}
    //Adds one day to February 28th in a NON-leap year.
    else{if((this.getMonth() == 2) && (this.getDay() == 28) && (this.isLeapYear() == false)){
      this.month++;
      this.day = 1;}
    //Adds one day to February 28th in a leap year.
    else{if((this.getMonth() == 2) && (this.getDay() == 28) && (this.isLeapYear() == true)){
      this.day++;}
    //Adds one day to February 29th in a leap year.
    else{if((this.getMonth() == 2) && (this.getDay() == 29) && (this.isLeapYear() == true)){
      this.month++;
      this.day = 1;}}}}}}}}}
  }
    
  /** Compares two dates and returns -0.1 if this date comes before the input date, or 0.1 if the input date is earlier.
    * If equal it returns 0.0.*/
  public double compareTo(Date date){
    if(this.getYear() > date.getYear())
      return 0.1;
    else if((this.getYear() == date.getYear()) && (this.getMonth() > date.getMonth()))
      return 0.1;
    else if((this.getYear() == date.getYear()) && (this.getMonth() == date.getMonth()) && (this.getDay() > date.getDay()))
      return 0.1;
    else if((this.getYear() == date.getYear()) && (this.getMonth() == date.getMonth()) && (this.getDay() == date.getDay()))
      return 0.0;
    else
      return -0.1;
  }
}