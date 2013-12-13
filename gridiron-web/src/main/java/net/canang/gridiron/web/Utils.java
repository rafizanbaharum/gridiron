package net.canang.gridiron.web;

import java.util.Random;

/**
 * @author rafizan.baharum
 * @since 12/4/13
 */
public class Utils {

    public static String randomizeNricNo() {
        String[] nos = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer nric = new StringBuffer();

        Random rand = new Random();
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        nric.append(nos[rand.nextInt(nos.length)]);
        return nric.toString();
    }

    public static String randomizePhone() {
        String[] nos = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer phone = new StringBuffer();

        Random rand = new Random();
        phone.append("01");
        phone.append(rand.nextInt(nos.length));
        phone.append(" ");
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        phone.append(nos[rand.nextInt(nos.length)]);
        return phone.toString();
    }

    public static String randomizeName(){
        String[] names = new String[]{
                "Ahmad", "Shah", "Yusof", "Salleh", "Noor", "Nasir",
                "Said", "Yasin", "Yunos", "Zin", "Isa", "Sharif", "Khalid",
                "Nizam", "Taib", "Yatim", "Yazid", "Zain", "Arif", "Fauzi", "Rashid",
                "Razali", "Esa", "Fadil", "Aris", "Saad", "Kamal",
                "Ismail", "Azmi", "Hashim", "Nazri", "Jamil", "Zaini", "Zamri",
                "Kasim", "Fuad", "Din", "Ariffin", "Najib", "Hassan", "Sani",
                "Ishak", "Nordin", "Farid", "Hatta", "Ghazali", "Jais", "Khairi",
                "Suhaimi", "Zaidi", "Zaki"};

        StringBuffer name = new StringBuffer();
        Random rand = new Random();
        name.append(names[rand.nextInt(names.length)]);
        name.append(" ");
        name.append(names[rand.nextInt(names.length)]);
        return name.toString();
    }

    public static String randomizeCode() {
        String[] nos = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuffer code = new StringBuffer();
        Random rand = new Random();
        code.append(rand.nextInt(nos.length));
        code.append(rand.nextInt(nos.length));
        code.append(rand.nextInt(nos.length));
        code.append(rand.nextInt(nos.length));
        return code.toString();
    }

    public static int randomizeCount() {
        Random rand = new Random();
        return rand.nextInt(100000);
    }
}
