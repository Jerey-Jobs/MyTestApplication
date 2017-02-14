package com.jerey.realmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*初始化*/
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("test1.realm")// 存储文件名称，类似db文件名
                .migration(new RealmMigration() { // 当本地已经存在的数据版本跟当前运行的不一致会调用此方法
                    @Override
                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
                        newVersion = oldVersion + 1;
                    }
                })
                .build();
        final Realm myRealm = Realm.getInstance(realmConfiguration);// 设置配置

        //清空表
        myRealm.beginTransaction();
        myRealm.clear(Person.class);
        myRealm.commitTransaction();

        //两种添加方式
        myRealm.beginTransaction();
        Person person = myRealm.createObject(Person.class);
        person.setId(10);
        person.setAge(10);
        person.setName("xiamin");
        myRealm.commitTransaction();

        person = new Person();
        person.setId(13);
        person.setAge(13);
        person.setName("xiamin");

        myRealm.beginTransaction();
        myRealm.copyToRealmOrUpdate(person);  // 通过主键查询它的对象，如果查询到了，则更新它，否则新建一个对象来代替。
        myRealm.commitTransaction();

        myRealm.beginTransaction();
        person = myRealm.createObject(Person.class);
        person.setId(7);
        person.setAge(7);
        person.setName("xiamin");
        myRealm.commitTransaction();

        //查 + 排序
        Log.i("xiamin", "查询结果");
        RealmResults<Person> results =
        myRealm.where(Person.class).findAllSorted("age");
        for (Person s: results) {
            Log.i("xiamin", "id:" + s.getId() + " name:" + s.getName() + " age:" + s.getAge());
        }

        //改
        Log.i("xiamin", "修改age = 7的名字为xiamin2");
        myRealm.beginTransaction();
        person = myRealm.where(Person.class).equalTo("age",7).findFirst();
        person.setName("xiamin2");
        myRealm.commitTransaction();

        RealmResults<Person> results1 =
                myRealm.where(Person.class).findAll();
        for (Person s: results1) {
            Log.i("xiamin", "id:" + s.getId() + " name:" + s.getName() + " age:" + s.getAge());
        }

        //删
        Log.i("xiamin", "删除第一个");
        final RealmResults<Person> list=  myRealm.where(Person.class).findAll();
        myRealm.beginTransaction();
        list.get(0).removeFromRealm();
        myRealm.commitTransaction();

        RealmResults<Person> results2 =
                myRealm.where(Person.class).findAll();
        for (Person s: results2) {
            Log.i("xiamin", "id:" + s.getId() + " name:" + s.getName() + " age:" + s.getAge());
        }

    }
}
