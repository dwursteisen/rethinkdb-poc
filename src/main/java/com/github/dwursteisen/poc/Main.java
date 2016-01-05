package com.github.dwursteisen.poc;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) throws TimeoutException {
        RethinkDB r = RethinkDB.r;

        Connection conn = r.connection().hostname("localhost").port(32769).connect();

        r.db("test").tableCreate("tv_shows").run(conn);
        r.table("tv_shows").insert(r.hashMap("name", "Star Trek TNG")).run(conn);
    }
}
