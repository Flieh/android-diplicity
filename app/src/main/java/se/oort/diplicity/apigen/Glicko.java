package se.oort.diplicity.apigen;

import retrofit2.http.*;
	
public class Glicko implements java.io.Serializable {
  public String GameID;
  public String UserId;
  public java.util.Date CreatedAt;
  public String Member;
  public Double Rating;
  public Double PracticalRating;
  public Double Deviation;
  public Double Volatility;
}