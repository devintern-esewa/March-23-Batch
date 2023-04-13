package week_2.day10.serialization;

import java.io.*;

class Account implements Serializable {
    String userName = "i am ";
    transient String password = "don";

    transient int pin = 1234;

    @Serial
    private void writeObject(ObjectOutputStream os) throws Exception{
        os.defaultWriteObject();
        String encryptedPass = "123"+password;
        os.writeObject(encryptedPass);
        int encryptedPin = 123+pin;
        os.writeObject(encryptedPin);
    }

    @Serial
    private void readObject(ObjectInputStream is) throws Exception{
        is.defaultReadObject();
        String decryptedPass = (String)is.readObject();
        password = decryptedPass.substring(3);
        int decryptedPin = is.readInt();
        pin = decryptedPin-123;
    }
}

public class F3_CustomizedSerialization {
    /*

        In the given example, before serialization account object can provide proper username and password but after
        deserialization, account object can provide only username but not password, this is due to declaring password
         variable as transient.
         Hence, default serialization there may be chance of loss of information because of transient keyword.To
         recover this loss of information we should go for customized serialization.
     */
    /*
        We can implement customized serialization by using following two methods:

        private void writeObject(ObjectOutputStream os) throws Exception
            - this method will be executed automatically at the time of serialization. Hence, at the time of
              serialization if we want to perform any activity we have to define that in this method.

        private void readObject(ObjectInputStream os) throws Exception
            - this method will be executed automatically at the time of deserialization. Hence, at the time of
              deserialization if we want to perform any activity we have to define that in this method.

        The above methods are callback methods because these are executed automatically by the jvm.
        While performing which object serialization, we have to do extra work in the corresponding class, we have to define above methods.
        for e.g. while performing account object serialization, if we require to do extra work in the account class, we have to define above methods.

     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Account a = new Account();
        System.out.println(a.userName + " " + a.password+ " "+ a.pin);

        FileOutputStream fos = new FileOutputStream("don.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(a);

        FileInputStream fis = new FileInputStream("don.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Account a2 = (Account) ois.readObject();
        System.out.println(a2.userName + " " + a2.password+ " "+ a2.pin);

    }
}
