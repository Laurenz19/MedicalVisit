package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;

    static {
    	if(sessionFactory == null) {
    		Configuration cfg = new Configuration().configure();
    		
    		/****************************************************/
    			cfg.addResource("entity/Doctor.hbm.xml");
    			cfg.addResource("entity/Patient.hbm.xml");
    			cfg.addResource("entity/Visit.hbm.xml");
    		/****************************************************/
    			
    		StandardServiceRegistry ssRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties()).build();
    		sessionFactory = cfg.buildSessionFactory(ssRegistry);
    		  
    	}
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
