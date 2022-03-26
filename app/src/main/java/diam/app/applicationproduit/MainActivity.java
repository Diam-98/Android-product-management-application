package diam.app.applicationproduit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addProductButton;
    DataBaseHandler dataBaseHandler;
    ArrayList<ProductModel> ListProductAll;
    ArrayList<String> productName, productCategory;
    ArrayList<Integer> productId, productPrice;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProductButton = findViewById(R.id.adProductButton);
        recyclerView = findViewById(R.id.productList);

        dataBaseHandler = new DataBaseHandler(MainActivity.this);

        //list des produit
        ListProductAll = new ArrayList<>();

        ListProductAll = dataBaseHandler.getAllProducts();

        productId = new ArrayList<>();
        productName = new ArrayList<>();
        productCategory = new ArrayList<>();
        productPrice = new ArrayList<>();

        for (ProductModel pm : ListProductAll){
            productId.add(pm.getIdProduit());
            productName.add(pm.getNomProduit());
            productPrice.add(pm.getPrixProduit());
        }

        adapter = new ProductAdapter(MainActivity.this, ListProductAll);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddingProductActivity.class);
                startActivity(intent);
            }
        });

        adapter.notifyDataSetChanged();
    }
}