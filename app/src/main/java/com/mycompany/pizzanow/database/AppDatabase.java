package com.mycompany.pizzanow.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.arch.persistence.room.Database;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mycompany.pizzanow.database.dao.CollaborateurDao;
import com.mycompany.pizzanow.database.dao.MenuDao;
import com.mycompany.pizzanow.database.dao.MenuPizzaDao;
import com.mycompany.pizzanow.database.dao.PizzaDao;
import com.mycompany.pizzanow.database.dao.PosDao;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.MenuEntity;
import com.mycompany.pizzanow.database.entity.MenuPizzaEntity;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;

import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {PizzaEntity.class, PosEntity.class, MenuEntity.class, MenuPizzaEntity.class, CollaborateurEntity.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase{

    //Application Database

    private static final String TAG = "Database";

    private static AppDatabase sInstance;

    public abstract PizzaDao pizzaDao();
    public abstract PosDao posDao();
    public abstract CollaborateurDao collaborateurDao();
    public abstract MenuDao menuDao();
    public abstract MenuPizzaDao menuPizzaDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context) {

        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    Log.d(TAG, "build db" );
                    sInstance.updateDatabaseCreated(context.getApplicationContext());

                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context appContext) {
        Log.i(TAG,"DB Créer");
        return Room.databaseBuilder(appContext, AppDatabase.class, "PizzaNow")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            AppDatabase database = AppDatabase.getInstance(appContext);
                            initializeData(database);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath("PizzaNow").exists()) {
            Log.i(TAG, "Database initialized.");
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }


    public static void initializeData(final AppDatabase database) {

        Executors.newSingleThreadExecutor().execute(() -> {
            database.runInTransaction(() -> {
                // Generate the data for pre-population
                List<PizzaEntity> pizzas = DataGenerator.generatePizzas();
                List<PosEntity> pos = DataGenerator.generatePos();
                //List<MenuEntity> menus = DataGenerator.generateMenus();
                List<CollaborateurEntity> collabs = DataGenerator.generateCollaborateurs();
                //List<MenuPizzaEntity> menuPizzas = DataGenerator.generateMenuPizzas();

                database.collaborateurDao().insertAll(collabs);
                database.posDao().insertAll(pos);

                //collabs = DataGenerator.addPosToCollab(collabs);

                //database.collaborateurDao().insertAll(collabs);
                database.pizzaDao().insertAll(pizzas);
               // database.menuDao().insertAll(menus);
                //database.menuPizzaDao().insertAll(menuPizzas);

                Log.i(TAG, "Data inserted.");

            });
        });

    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}
