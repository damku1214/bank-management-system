public class Person {

    // Assign variables
    public String firstName;
    public String middleName;
    public String lastName;
    public int age;
    public final int securityNumber;
    public boolean hasAccount;
    public float moneyPurse;
    public float moneyBank;

    // Constructor
    public Person(String firstName, String middleName, String lastName, int age, int securityNumber, boolean hasAccount, float moneyPurse, float moneyBank) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.securityNumber = securityNumber;
        this.hasAccount = hasAccount;
        this.moneyPurse = moneyPurse;
        this.moneyBank = moneyBank;
    }
}
