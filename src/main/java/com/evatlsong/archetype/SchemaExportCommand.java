package com.evatlsong.archetype;

import com.evatlsong.archetype.model.Person;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by evatlsong on 15-11-22.
 */
public class SchemaExportCommand {
    public static void main(String[] args) {
        SchemaExportCommand sec = new SchemaExportCommand();
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        sec.addAnnotatedClasses(configuration, "com.evatlsong.archetype.model");
        SchemaExport schemaExport = new SchemaExport(configuration);
        schemaExport.setOutputFile("schema.sql");
        schemaExport.setFormat(true);
        schemaExport.create(true, false);
    }
    private Configuration addAnnotatedClasses(Configuration cfg, String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        System.out.println(url.toString());
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File[] files = file.listFiles();
        for (File f : files) {
            String name = f.getName();
            System.out.println(name.split("\\.")[0]);
            try {
                cfg.addAnnotatedClass(Class.forName(packageName + "." +name.split("\\.")[0]));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return cfg;
    }
}
