package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by Jakobob on 04.10.2016.
 * This class generates the db package and all classes within in our project. Its a module which will be only executed once to create our classes so we dont have to write them on our own.
 * To execute this module press Ctrl+Shift+F10 (no VirtuelDevice oder real Device is needed)
 */
public class GreenDaoGenerator {

    public static void main(String args[]) throws Exception {
        // package name of the generated classes
        Schema schema = new Schema(1, "pubfinder.pubfinder.db");

        // entity without any 1toMany or ManytoMany relations, because we dont need them right now
        Entity pub = schema.addEntity("Pub");
        pub.addIdProperty();
        pub.addStringProperty("name");
        pub.addStringProperty("address");
        pub.addStringProperty("music");

        // destination where to create the db classes
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

}
