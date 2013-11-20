package in.nikitapek.insightweb;

import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationContextFacade;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.realm.JDBCRealm;

import javax.servlet.ServletContext;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class JDBC {
    public static JDBCRealm realm;

    public static void initialize(ServletContext context) {
        try {
            realm = JDBC.getRealm(context);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static JDBCRealm getRealm(ServletContext context) throws NoSuchFieldException, IllegalAccessException {
        ApplicationContextFacade acf = (ApplicationContextFacade) context;
        Field privateField = ApplicationContextFacade.class.getDeclaredField("context");
        privateField.setAccessible(true);
        ApplicationContext appContext = (ApplicationContext) privateField.get(acf);
        Field privateField2 = ApplicationContext.class.getDeclaredField("context");
        privateField2.setAccessible(true);
        StandardContext stdContext = (StandardContext) privateField2.get(appContext);
        return (JDBCRealm) stdContext.getRealm();
    }

    public static boolean userExists(String username) {
        String password = getPassword(username);

        return password == null;
    }

    public static String getPassword(String username) {
        try {
            return (String) JDBCRealm.class.getDeclaredMethod("getPassword", String.class).invoke(realm, username);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void configureRealm(JDBCRealm realm, String username, String password, String url, String port) {
        realm.setConnectionName(username);
        realm.setConnectionPassword(password);
        realm.setConnectionURL("jdbc:mysql://"+ url + ":" + port + "/insight");
        realm.setDriverName("org.mariadb.jdbc.Driver");
    }
}
