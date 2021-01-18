package com.redhat.service.api.model.expenses;

import java.io.Serializable;
import com.redhat.hr.expenses.model.ExpenseItemModel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Payload for Reponse ExpensesApprovals process
 * 
 *         
 * {
 *  "limit" : "Met",
 * }
 *
 * {
 *  "limit" : "Exceeded",
 * }
 */

//@XmlRootElement(name = "expense-validation-response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExpenseValidationResponse implements java.io.Serializable {

  @XmlElement(name = "limit")
  private String limit = null;


  public ExpenseValidationResponse(String limit) {
    this.limit = limit;
  }

      /**
    * Gets the Limit
    */
    public String getLimit() {
      return this.limit;
   }

   @Override
   public String toString() {
       return "ExpenseValidationResponse{" +
               "limit='" + limit + '\'' +    
               '}';
   }

}
