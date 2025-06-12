package Codigo_exemplo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cadastros.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "usuarios";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "email TEXT," +
                "telefone TEXT)";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean inserirCadastro(String nome, String email, String telefone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("telefone", telefone);
        long resultado = db.insert(TABLE_NAME, null, valores);
        return resultado != -1;
    }

    public Cursor listarCadastros() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
    public boolean atualizarCadastro(int id, String nome, String email, String telefone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("telefone", telefone);
        int resultado = db.update("usuarios", valores, "id=?", new String[]{String.valueOf(id)});
        return resultado > 0;
    }

    public boolean excluirCadastroPorId(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int resultado = db.delete("usuarios", "id=?", new String[]{String.valueOf(id)});
        return resultado > 0;
    }

}
