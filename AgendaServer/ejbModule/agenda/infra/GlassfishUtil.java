package agenda.infra;

import java.util.Properties;

public class GlassfishUtil {
 
    public static Properties getGlassfishInitProperties() {
        Properties resultado = new Properties();
        
        resultado.setProperty("java.naming.factory.initial",
                "com.sun.enterprise.naming.SerialInitContextFactory");
        resultado.setProperty("java.naming.factory.url.pkgs",
                "com.sun.enterprise.naming");
        resultado.setProperty("java.naming.factory.state",
                "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
 
        resultado.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
        resultado.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
 
        return resultado;
    }
    
}