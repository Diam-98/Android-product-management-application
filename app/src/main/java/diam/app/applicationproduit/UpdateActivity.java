package diam.app.applicationproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText productName, productCategory, productPrice, productDescription;
    Button updateButton, cancelButton;
    DataBaseHandler dataBaseHandler;
    public ProductModel productModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        productName = findViewById(R.id.upNameET);
        productCategory = findViewById(R.id.upCategoryET);
        productPrice = findViewById(R.id.upPriceET);
        productDescription = findViewById(R.id.upDescriptionET);
        updateButton = findViewById(R.id.upSaveBtn);
        cancelButton = findViewById(R.id.upCancelBtn);

        Intent intent = getIntent();

        String prodStringID = intent.getStringExtra("idProduct");
        int productId = Integer.parseInt(prodStringID);

        dataBaseHandler = new DataBaseHandler(this);
        productModel = dataBaseHandler.getProductById(productId);
        productName.setText(productModel.getNomProduit());
        productCategory.setText(productModel.getCategorieProduit());
        productPrice.setText(""+productModel.getPrixProduit());
        productDescription.setText(productModel.getDescriptionProduit());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prodName = productName.getText().toString().trim();
                String prodCategory = productCategory.getText().toString().trim();
                String prodPriceString = productPrice.getText().toString().trim();
                String prodDesc = productDescription.getText().toString().trim();

                if (!prodName.isEmpty() && !prodCategory.isEmpty() && !prodPriceString.isEmpty() && !prodDesc.isEmpty()){
                    int prodPrice = Integer.parseInt(prodPriceString);
                    productModel.setNomProduit(prodName);
                    productModel.setCategorieProduit(prodCategory);
                    productModel.setPrixProduit(prodPrice);
                    productModel.setDescriptionProduit(prodDesc);
                    dataBaseHandler.updateProduct(productModel);
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    productName.setError("Invalide");
                }
            }
        });





        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(UpdateActivity.this, ProductDetilsActivity.class);
                startActivity(intent1);
            }
        });

    }
}