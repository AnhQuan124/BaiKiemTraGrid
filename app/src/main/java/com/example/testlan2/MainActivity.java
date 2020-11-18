package com.example.testlan2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Context context;
    RelativeLayout gridviewdata;
    ArrayList<ProductModel> productData;
    ProductAdapter productAdapter;
    ProductModel productModel;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        //getviews
        listView = findViewById(R.id.listview);
        gridviewdata = (RelativeLayout) findViewById(R.id.listdata);
        productData = new ArrayList<>();

        //add Countries Data
        populateProductData();
        listView.setOnItemLongClickListener(new ItemLongClickRemove());
        productAdapter = new ProductAdapter(context,productData);
        listView.setAdapter(productAdapter);
        registerForContextMenu(listView);
        listView = findViewById(R.id.listview);
        listView.setOnItemLongClickListener(new ItemLongClickRemove());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(context,productData.get(position).getNamedh(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
                intent.putExtra("name",productData.get(position).getNamedh());
                intent.putExtra("image",productData.get(position).getImages());
                intent.putExtra("gia",productData.get(position).getGiadh());
                startActivity(intent);
            }
        });

    }
    private void populateProductData() {
        //product1
        productModel = new ProductModel();
        productModel.setId(1);
        productModel.setNamedh("Vin Mart");
        productModel.setImages(R.drawable.bl1);
        productModel.setGiadh("Chuỗi siêu thị uy tín. Sản phẩm đa dạng, vận chuyển siêu tốc");
        productData.add(productModel);

        //product2
        productModel = new ProductModel();
        productModel.setId(2);
        productModel.setNamedh("Meijii");
        productModel.setImages(R.drawable.bl2);
        productModel.setGiadh("Nhãn hiệu sữa bán chạy số 1 tại Nhật Bản");
        productData.add(productModel);

        //product3
        productModel = new ProductModel();
        productModel.setId(3);
        productModel.setNamedh("Bác Tôm ");
        productModel.setImages(R.drawable.bl3);
        productModel.setGiadh("Thực phẩm sạch, đặc sản vùng miền");
        productData.add(productModel);

        //product4
        productModel = new ProductModel();
        productModel.setId(4);
        productModel.setNamedh("F5 Fruit");
        productModel.setImages(R.drawable.bl4);
        productModel.setGiadh("Bản lẻ trái cây, nhập khẩu uy tín");
        productData.add(productModel);

        //product5
        productModel = new ProductModel();
        productModel.setId(5);
        productModel.setNamedh("Thực phẩm Dũng Hà");
        productModel.setImages(R.drawable.bl1);
        productModel.setGiadh("Bring nature to your home");
        productData.add(productModel);

        //product6
        productModel = new ProductModel();
        productModel.setId(6);
        productModel.setNamedh("Thực phẩm Quốc Huy");
        productModel.setImages(R.drawable.bl3);
        productModel.setGiadh("Gạo ngon cho mọi gia đình");
        productData.add(productModel);

        productModel = new ProductModel();
        productModel.setId(7);
        productModel.setNamedh("Hoàng Đông Food");
        productModel.setImages(R.drawable.bl4);
        productModel.setGiadh("Nhà phân phối bán lẻ thực phẩm sạch");
        productData.add(productModel);
    }
    private class ItemLongClickRemove implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Bạn có muốn xóa cửa hàng này?");
            alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // xóa sp đang nhấn giữ
                    productData.remove(position);
                    //cập nhật lại gridview
                    productAdapter.notifyDataSetChanged();
                }
            });
            alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialogBuilder.show();
            return true;
        }
    }
}