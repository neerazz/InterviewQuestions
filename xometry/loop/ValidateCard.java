package loop;

import java.util.*;

/**
 *
 * @author :  bnira
 * Created :  10/8/2025
 * Problem :
 *
 */

public class ValidateCard {

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

// const testCases = [
//   ["378282246310005", "AMEX", "Valid"],
//   ["371449635398431", "AMEX", "Valid"],
//   ["371449635398432", "AMEX", "Invalid"],
//   ["5405 0412 3456 7893", "MasterCard", "Valid"],
//   ["6011111111111117", "Discover", "Valid"],
//   ["4417 1234 5678 9112", "Visa", "Invalid"],
//   ["4408 0412 3456 7893", "Visa", "Valid"],
//   ["9408 0412 3456 7833", "Unknown", "Invalid"]
// ]


  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("378282246310005");
    strings.add("371449635398431");
    strings.add("371449635398432");
    strings.add("5405 0412 3456 7893");
    strings.add("6011111111111117");
    strings.add("4417 1234 5678 9112");
    strings.add("4408 0412 3456 7893");
    strings.add("9408 0412 3456 7833");


    for (String string : strings) {
      var response = validateCard(string);
      System.out.println(response);
    }
  }

  public static Response validateCard(String card){
    //  4417 1234 5678 9112
    String trimmedCard = trimString(card);        //  4417123456789112
    CardType cardtype = getCardType(trimmedCard); // VISA
    String validation = luhnValidation(trimmedCard);
    return new Response(trimmedCard, cardtype, validation);
  }

  private static String trimString(String card){
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<card.length(); i++){
      char c = card.charAt(i);
      if(Character.isDigit(c)){
        sb.append(c);
      }
    }
    return sb.toString();
  }

  private static CardType getCardType(String cardNumber){
    if(cardNumber.length() < 13 || cardNumber.length() > 16){
      return CardType.UNKNOWN;
    }
    var twoDigit = cardNumber.substring(0,2);
    if(cardNumber.length() == 15 &&
      (twoDigit.equals("34") || twoDigit.equals("37"))){
      return CardType.AMEX;
    }

    if(cardNumber.length() == 16){
      if(cardNumber.substring(0,4).equals("6011")){
        return CardType.DISCOVER;
      }
      if(twoDigit.equals("51") ||
      twoDigit.equals("52") ||
      twoDigit.equals("53") ||
      twoDigit.equals("54") ||
      twoDigit.equals("55")){
        return CardType.MASTERCARD;
      }
    }

    if((cardNumber.length() == 13 || cardNumber.length() == 16) &&
      cardNumber.charAt(0) == '4'){
      return CardType.VISA;
    }
    return CardType.UNKNOWN;
  }

  private static String luhnValidation(String card){
    // 4417123456789112
    //             i
    // Sum = 14
    // isdouble = false
    int len = card.length();
    int sum = 0;
    boolean isDouble = false;
    for(int i=len-1; i>=0 ; i--){
      int num = card.charAt(i) - '0';
      if(isDouble){
        num *= 2;
      }
      // 18
      sum += num % 10; // 8
      sum += num / 10; // 1
      isDouble = !isDouble;
    }
    return sum % 10 == 0 ? "Valid" : "Invalid";
  }

}

enum CardType{
  AMEX, DISCOVER, MASTERCARD, VISA, UNKNOWN
}

class Response{
  String cardNumber;
  CardType cardType;
  String validity;

  public Response(String card, CardType type, String validity){
    this.cardNumber = card;
    this.cardType = type;
    this.validity = validity;
  }

  public String toString(){
    return "[" + this.cardNumber + "," + this.cardType + "," + this.validity + "]";
  }

}
