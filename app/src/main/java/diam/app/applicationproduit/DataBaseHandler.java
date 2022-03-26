package diam.app.applicationproduit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDb";
    private static final String PRODUCT_TABLE = "product";
    private static final String KEY_ID = "id";
    private static final String PRODUCT_NAME = "nomProduit";
    private static final String PRODUCT_CATEGORY = "categorieProduit";
    private static final String PRODUCT_PRICE = "prixProduit";
    private static final String PRODUCT_DESCRIPTION = "descriptionProduit";

    public DataBaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_PRODUCT_TABLE = " CREATE TABLE "+PRODUCT_TABLE+"("
                +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +PRODUCT_NAME+" VARCHAR(255) NOT NULL ,"
                +PRODUCT_CATEGORY+" VARCHAR (255) NOT NULL ,"
                +PRODUCT_PRICE+" INT DEFAULT 0 ,"
                +PRODUCT_DESCRIPTION+" VARCHAR(255) ) ";

        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+PRODUCT_TABLE);

        onCreate(sqLiteDatabase);
    }


    public void addProduct(ProductModel productModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PRODUCT_NAME, productModel.getNomProduit());
        values.put(PRODUCT_CATEGORY, productModel.getCategorieProduit());
        values.put(PRODUCT_PRICE, productModel.getPrixProduit());
        values.put(PRODUCT_DESCRIPTION, productModel.getDescriptionProduit());

        sqLiteDatabase.insert(PRODUCT_TABLE, null, values);

        sqLiteDatabase.close();
    }

    public void deleteProduct(ProductModel productModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(PRODUCT_TABLE, KEY_ID+"=?", new String[]{String.valueOf(productModel.getIdProduit())});
        sqLiteDatabase.close();
    }

    public int updateProduct(ProductModel productModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PRODUCT_NAME, productModel.getNomProduit());
        values.put(PRODUCT_CATEGORY, productModel.getCategorieProduit());
        values.put(PRODUCT_PRICE, productModel.getPrixProduit());
        values.put(PRODUCT_DESCRIPTION, productModel.getDescriptionProduit());

        return sqLiteDatabase.update(PRODUCT_TABLE, values,KEY_ID+"=?",
                new String[]{String.valueOf(productModel.getIdProduit())});
    }

    public ProductModel getProductById(int productID){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(PRODUCT_TABLE,
                new String[]{KEY_ID, PRODUCT_NAME, PRODUCT_CATEGORY, PRODUCT_PRICE, PRODUCT_DESCRIPTION},
                KEY_ID+"=?", new String[]{String.valueOf(productID)}, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        ProductModel productModel = new ProductModel(Integer.parseInt(cursor.getString(0)),
                String.valueOf(cursor.getString(1)), String.valueOf(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), String.valueOf(cursor.getString(4)));

        return productModel;
    }

    public int getProductCount(){
        String countQuery =" SELECT * FROM "+ PRODUCT_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public ArrayList<ProductModel> getAllProducts(){

        ArrayList<ProductModel> productModels = new ArrayList<>();
        String productQuery = " SELECT * FROM "+PRODUCT_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(productQuery, null);

        if (cursor.moveToFirst()){
            do{
                ProductModel productModel = new ProductModel();
                productModel.setIdProduit(Integer.parseInt(cursor.getString(0)));
                productModel.setNomProduit(String.valueOf(cursor.getString(1)));
                productModel.setCategorieProduit(String.valueOf(cursor.getString(2)));
                productModel.setPrixProduit(Integer.parseInt(cursor.getString(3)));
                productModel.setDescriptionProduit(String.valueOf(cursor.getString(4)));

                productModels.add(productModel);
            }while (cursor.moveToNext());
        }

        return productModels;

    }
}
