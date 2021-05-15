package com.example.reviewreadurl;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity implements ContainBook{
    TextView txtName;
    TextView txtPrice;
    TextView txtDescrip;
    BottomNavigationView buyNavigation;
    Book book=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent= getIntent();
        Bundle bundle= intent.getBundleExtra("info");
        book= bundle.getParcelable("bookInfo");
        //dun gla nh vay


        setContentView(R.layout.activity_product);
        getSupportActionBar().hide();
        buyNavigation=findViewById(R.id.buyNavigation);
        txtName=findViewById(R.id.nameProduct);
        txtPrice=findViewById(R.id.priceProduct);
        txtName.setText(book.getName());
        txtPrice.setText(MyUtils.formatMoney(String.valueOf(book.getPrice())));
        final Book book1=book;
        buyNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart.addItem(book1,1);
                Toast.makeText(ProductActivity.this,String.valueOf(Cart.getCount()),Toast.LENGTH_SHORT).show();
            }
        });
        setUpDetail();
    }
    void setUpDetail(){
        TextView txtAuthor=findViewById(R.id.author);
        TextView txtPublish=findViewById(R.id.publishment);
        TextView txtpPages=findViewById(R.id.pagesNumber);
        TextView txtVendor=findViewById(R.id.vendor);
        TextView txtCategory = findViewById(R.id.category);
        txtAuthor.setText(book.getAuthor());
        txtCategory.setText("Math Olympic");
        txtpPages.setText("240");
        txtVendor.setText("KMP Trading");
        txtPublish.setText("NXB Đại học Quốc gia Hà Nội");

        txtDescrip=findViewById(R.id.description);
        txtDescrip.setText("Số học là một phân nhánh toán học lâu đời nhất và sơ cấp nhất, được hầu hết mọi người thường xuyên sử dụng từ những công việc thường nhật cho đến các tính toán khoa học và kinh doanh cao cấp qua các phép tính cộng, trừ, nhân, chia. Người ta thường dùng thuật ngữ này để chỉ một phân nhánh toán học chú trọng đến các thuộc tính sơ cấp của một số phép tính trên các con số. Những nhà toán học đôi khi dùng chữ số học (cao cấp) để nhắc đến môn lý thuyết số, nhưng không nên nhầm lý thuyết này với số học sơ cấp. Các ngôn ngữ sử dụng từ vựng gốc Hán khác lại gọi môn này là toán thuật; từ số học lại được dùng để gọi môn học mà người Việt gọi là toán học. Nhằm giúp cho các em học sinh chuyên Toán, các giáo viên dạy chuyên Toán và các học viên cao học chuyên ngành phương pháp toán sơ cấp có thêm một tài liệu số học để tham khảo trong quá trình học tập, giảng dạy và nghiên cứu tôi mạnh dạn viết cuốn sách này.");
    }
    @Override
    public Book getBook() {
        return this.book;
    }
}
