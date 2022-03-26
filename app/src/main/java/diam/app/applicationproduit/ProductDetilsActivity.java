package diam.app.applicationproduit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProductDetilsActivity extends AppCompatActivity {

    TextView productName, productDescription, productCategory, productPrice;
    Button updateButton, deleteButton;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detils);

        productCategory = findViewById(R.id.categoryOfProd);
        productDescription = findViewById(R.id.descriptionOfProd);
        productName = findViewById(R.id.nameOfProd);
        productPrice = findViewById(R.id.priceOfProduct);

        updateButton = findViewById(R.id.prodUpdateButton);
        deleteButton = findViewById(R.id.prodDeleteButton);

        Intent intent = getIntent();
        String prodId = intent.getStringExtra("prodId");
        String prodName = intent.getStringExtra("productName");
        String prodCategory = intent.getStringExtra("productcategory");
        String prodPrice = intent.getStringExtra("productPrice");
        String prodDesc = intent.getStringExtra("productDescription");

        productName.setText(prodName);
        productCategory.setText(prodCategory);
        productPrice.setText(prodPrice);
        productDescription.setText(prodDesc);

        int productId = Integer.parseInt(prodId);

        Log.d("Idprod", "L'id du Produit est : "+productId);

        dataBaseHandler = new DataBaseHandler(this);
        ProductModel productModel = dataBaseHandler.getProductById(productId);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHandler.deleteProduct(productModel);
                Intent intent1 = new Intent(ProductDetilsActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetilsActivity.this, UpdateActivity.class);
                intent.putExtra("idProduct", prodId);
                startActivity(intent);
            }
        });

    }
}