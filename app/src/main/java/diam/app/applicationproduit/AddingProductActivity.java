package diam.app.applicationproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddingProductActivity extends AppCompatActivity {

    EditText nomProduit, categorieProduit, prixProduit, descriptionProduit;
    Button BoutonEnregistrer, BoutonAnnuler;
    ProductModel productModel;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_product);

        nomProduit = findViewById(R.id.nameET);
        categorieProduit = findViewById(R.id.categoryET);
        prixProduit = findViewById(R.id.priceET);
        descriptionProduit = findViewById(R.id.descriptionET);
        dataBaseHandler = new DataBaseHandler(this);

        BoutonEnregistrer = findViewById(R.id.saveBtn);
        BoutonAnnuler = findViewById(R.id.cancelBtn);

        BoutonEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = nomProduit.getText().toString().trim();
                String productCategory = categorieProduit.getText().toString().trim();
                String productPriceString = prixProduit.getText().toString().trim();
                String productDesc = descriptionProduit.getText().toString().trim();

                if (!productName.isEmpty() && !productCategory.isEmpty() && !productPriceString.isEmpty() && !productDesc.isEmpty()){
                    int productPrice = Integer.parseInt(productPriceString);
                    productModel = new ProductModel(productName, productCategory, productPrice, productDesc);
                    dataBaseHandler.addProduct(productModel);
                    nomProduit.setText("");
                    categorieProduit.setText("");
                    prixProduit.setText("");
                    descriptionProduit.setText("");
                    Intent intent = new Intent(AddingProductActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    nomProduit.setError("Invalide");
                    categorieProduit.setError("Invalide");
                }

            }
        });

        BoutonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddingProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}