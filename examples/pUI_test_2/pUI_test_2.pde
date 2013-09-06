import com.martinleopold.pui.*;

void setup() {
  PUI ui = new PUI(this);
  println();
  
  //ui.testReflection();
  println();
  
//  println("objects");
//  println("-------");
//  ui.testObject("publicClassInstance");
//  ui.testObject("classInstance");
//  ui.testObject("protectedClassInstance");
//  ui.testObject("privateClassInstance");
//  println("");
  

  println("members");
  println("-------");
  ui.testField("publicField");
  println("publicField: " + publicField);
  ui.testField("field");
  println("field: " + field);
  ui.testField("protectedField");
  println("protectedField: " + protectedField);
  ui.testField("privateField");
  println("privateField: " + privateField);
  ui.testMethod("publicMethod");
  ui.testMethod("method");
  ui.testMethod("protectedMethod");
  ui.testMethod("privateMethod");
  println();


  println("inner class");
  println("-----------");
  ui.testField(privateClassInstance, "publicField");
  println("publicField: " + privateClassInstance.publicField());
  ui.testField(privateClassInstance, "field");
  println("field: " + privateClassInstance.field());
  ui.testField(privateClassInstance, "protectedField");
  println("protectedField: " + privateClassInstance.protectedField());
  ui.testField(privateClassInstance, "privateField");
  println("privateField: " + privateClassInstance.privateField());
  ui.testMethod(privateClassInstance, "publicMethod");
  ui.testMethod(privateClassInstance, "method");
  ui.testMethod(privateClassInstance, "protectedMethod");
  ui.testMethod(privateClassInstance, "privateMethod");
  println("");


  println("extending class");
  println("---------------");
  ui.testField(extendingClassInstance, "publicField");
  println("publicField: " + extendingClassInstance.publicField());
  ui.testField(extendingClassInstance, "field");
  println("field: " + extendingClassInstance.field());
  ui.testField(extendingClassInstance, "protectedField");
  println("protectedField: " + extendingClassInstance.protectedField());
  ui.testField(extendingClassInstance, "privateField");
  println("privateField: " + extendingClassInstance.privateField());
  ui.testField(extendingClassInstance, "extraField");
  println("extraField: " + extendingClassInstance.extraField());
  ui.testMethod(extendingClassInstance, "publicMethod");
  ui.testMethod(extendingClassInstance, "method");
  ui.testMethod(extendingClassInstance, "protectedMethod");
  ui.testMethod(extendingClassInstance, "privateMethod");
  ui.testMethod(extendingClassInstance, "extraMethod");
  println();
  
  
  exit();
}

private   int privateField;
protected int protectedField;
          int field;
public    int publicField;

private   void privateMethod()   { println("invoked privateMethod"); }
protected void protectedMethod() { println("invoked protectedMethod"); }
          void method()          { println("invoked method"); }
public    void publicMethod()    { println("invoked publicMethod"); }


private class PrivateClass {
  private   int privateField;
  protected int protectedField;
            int field;
  public    int publicField;
  
  public    int privateField()   { return privateField; }
  public    int protectedField() { return protectedField; }
  public    int field()          { return field; }
  public    int publicField()    { return publicField; }
  
  private   void privateMethod()   { println("invoked PrivateClass.privateMethod"); }
  protected void protectedMethod() { println("invoked PrivateClass.protectedMethod"); }
            void method()          { println("invoked PrivateClass.method"); }
  public    void publicMethod()    { println("invoked PrivateClass.publicMethod"); }
}

protected class ProtectedClass {
  private   int privateField;
  protected int protectedField;
            int field;
  public    int publicField;
  
  public    int privateField()   { return privateField; }
  public    int protectedField() { return protectedField; }
  public    int field()          { return field; }
  public    int publicField()    { return publicField; }
  
  private   void privateMethod()   { println("invoked ProtectedClass.privateMethod"); }
  protected void protectedMethod() { println("invoked ProtectedClass.protectedMethod"); }
            void method()          { println("invoked ProtectedClass.method"); }
  public    void publicMethod()    { println("invoked ProtectedClass.publicMethod"); }
}

class Class {
  private   int privateField;
  protected int protectedField;
            int field;
  public    int publicField;
  
  public    int privateField()   { return privateField; }
  public    int protectedField() { return protectedField; }
  public    int field()          { return field; }
  public    int publicField()    { return publicField; }
  
  private   void privateMethod()   { println("invoked Class.privateMethod"); }
  protected void protectedMethod() { println("invoked Class.protectedMethod"); }
            void method()          { println("invoked Class.method"); }
  public    void publicMethod()    { println("invoked Class.publicMethod"); } 
}

public class PublicClass {
  private   int privateField;
  protected int protectedField;
            int field;
  public    int publicField;
  
  public    int privateField()   { return privateField; }
  public    int protectedField() { return protectedField; }
  public    int field()          { return field; }
  public    int publicField()    { return publicField; }
  
  private   void privateMethod()   { println("invoked PublicClass.privateMethod"); }
  protected void protectedMethod() { println("invoked PublicClass.protectedMethod"); }
            void method()          { println("invoked PublicClass.method"); }
  public    void publicMethod()    { println("invoked PublicClass.publicMethod"); }
}


private PrivateClass privateClassInstance = new PrivateClass();
private ProtectedClass protectedClassInstance = new ProtectedClass();
private Class classInstance = new Class();
private PublicClass publicClassInstance = new PublicClass();

private class ExtendingClass extends PrivateClass {
  private int extraField;
  public    int extraField()     { return extraField; }
  
  private void extraMethod() { println("invoked ExtendingClass.extraMethod"); }
}

private ExtendingClass extendingClassInstance = new ExtendingClass();

