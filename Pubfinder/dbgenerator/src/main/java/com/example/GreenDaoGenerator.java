package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "pubfinder.pubfinder.db");

        Entity pub = schema.addEntity("Pub");
        pub.addIdProperty();
        pub.addStringProperty("name");
        pub.addStringProperty("address");
        pub.addStringProperty("music");

        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

}
