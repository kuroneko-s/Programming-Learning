package createObj.singleton;

import java.io.*;

public class SingletonTest {

    public void run() throws Exception{
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        System.out.println(singleton1 == singleton2);

//        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        Singleton createObj.singleton = constructor.newInstance();
//        System.out.println(createObj.singleton == singleton1);

        ObjectOutput output = new ObjectOutputStream(new FileOutputStream("createObj.singleton.obj"));
        output.writeObject(singleton1);

        ObjectInput input = new ObjectInputStream(new FileInputStream("createObj.singleton.obj"));
        Singleton singleton3 = (Singleton) input.readObject();

        System.out.println(singleton1 == singleton3);
    }
}
