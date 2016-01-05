package com.github.dwursteisen.poc;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.TimeoutException;

public class MainSecond {

    public static void main(String[] args) throws TimeoutException {
        final RethinkDB r = RethinkDB.r;

        Connection conn = r.connection().hostname("localhost").port(32769).connect();

        final Cursor run = r.table("tv_shows").changes().run(conn);

        Observable.create(new Observable.OnSubscribe<Object>() {
            public void call(Subscriber<? super Object> subscriber) {
                while (run.hasNext()) {
                    subscriber.onNext(run.next());
                }
                subscriber.onCompleted();
            }
        }).subscribe(System.out::println);


    }
}
