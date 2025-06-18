/* Aqui no cliente Dao iremos fazer o CRUD no banco de dados */

package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import db.DataBase;

public class AgendaDao {

    private SQLiteDatabase db;
    private DataBase dbHelper;

    public AgendaDao(Context context) {
        dbHelper = new DataBase(context);
        db = dbHelper.getWritableDatabase();
    }

    // Método para inserir um novo registro na tabela agenda
    public long inserirAgenda(int idSoliAgenda, String diaSemana) {
        ContentValues values = new ContentValues();
        values.put("idsoliagenda", idSoliAgenda);
        values.put("diasemana", diaSemana);

        // Executa a inserção no banco de dados
        return db.insert("agenda", null, values);
    }
}
