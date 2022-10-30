package com.example.razorpay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn= findViewById(R.id.btnpay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }

        });


    }

    public void startPayment() {

        Checkout checkout = new Checkout();

        checkout.setImage(R.drawable.image);

        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();
            options.put("name", R.string.app_name);
            options.put("description", "Payment for Anything");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", false);

            options.put("currency", "USD");
            options.put("amount", "10000");
            JSONObject preFill = new JSONObject();

            preFill.put("email", "email");
            preFill.put("contact", "phone");

            options.put("prefill", preFill);
            checkout.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment : " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "payment success"+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "payment error"+s, Toast.LENGTH_SHORT).show();

    }
}