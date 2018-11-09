package com.mycompany.pizzanow.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mycompany.pizzanow.database.dao.PizzaDao;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import java.util.List;
import java.util.concurrent.Executors;

@android.arch.persistence.room.Database(entities = {PizzaEntity.class}, version = 1)

public abstract class Database extends RoomDatabase{
    public abstract PizzaDao pizzaDao();

    private static Database sInstance;

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private static Database buildDatabase(final Context appContext) {

        return Room.databaseBuilder(appContext, Database.class, "PizzaNow")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            Database database = Database.getInstance(appContext);
                            initializeData(database);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    public static Database getInstance(Context context) {

        if (sInstance == null) {
            synchronized (Database.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath("PizzaNow").exists()) {
            setDatabaseCreated();
        }
    }

    public static void initializeData(final Database database) {

        Executors.newSingleThreadExecutor().execute(() -> {
            database.runInTransaction(() -> {

                // Generate the data for pre-population
                List<PizzaEntity> pizzas = DataGenerator.generatePizzas();
                database.pizzaDao().insertAll(pizzas);
            });
        });

    }

}
