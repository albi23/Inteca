export class Credits {

  public firstName: string;
  public lastName: string;
  public pesel: string;
  public productName: string;
  public productValue: number;
  public creditName: string;


  constructor(firstName: string, lastName: string, pesel: string, productName: string, productValue: number, creditName: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.pesel = pesel;
    this.productName = productName;
    this.productValue = productValue;
    this.creditName = creditName;
  }

}
