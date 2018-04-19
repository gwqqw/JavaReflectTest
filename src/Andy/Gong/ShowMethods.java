// Using of reflection to show all the methods of a class,
// even if the methods are defined in the base class.
// {Args: ShowMethods}
package Andy.Gong;

import java.lang.reflect.*;
import java.util.regex.*;


public class ShowMethods {
    private static String usage =
            "usage:\n" +
                    "ShowMethods qualified.class.name\n" +
                    "To show all methods in class or:\n" +
                    "ShowMethods qualified.class.name word\n" +
                    "To search for methods involving 'word'";
    private static Pattern p = Pattern.compile("\\w+\\.");

    public  void test(String str){ System.out.println(str + " is called!!!!!" );};
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println(usage);
            System.exit(0);
        }

        int lines = 0;
        try{
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();
            if (args.length == 1){
                for (Method method : methods ) {
                    //System.out.println(p.matcher(method.toString()).replaceAll(""));
                    String str = method.toString();
                    if (str.indexOf("test") != -1) {
                        try {
                            method.invoke(c.newInstance(), str);
                        } catch (IllegalAccessException e)
                        {
                            e.printStackTrace();
                        }catch (InstantiationException e)
                        {
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(str);
                }
                for (Constructor ctor : ctors)
                    //System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                    System.out.println(ctor);
                lines = methods.length + ctors.length;
            } else{
                for (Method method : methods)
                    if (method.toString().indexOf(args[1]) != -1){
                    System.out.println(p.matcher(method.toString()).replaceAll(""));
                    lines++;
                    }
                for (Constructor ctor : ctors)
                    if (ctor.toString().indexOf(args[1]) != -1){
                        System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                        lines++;
                    }
            }
        } catch ( ClassNotFoundException e){
            System.out.println("No such class: " + e);
        }
    }
}
