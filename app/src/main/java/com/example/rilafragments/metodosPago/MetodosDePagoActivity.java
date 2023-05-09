package com.example.rilafragments.metodosPago;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rilafragments.MainActivity;
import com.example.rilafragments.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class MetodosDePagoActivity extends AppCompatActivity {

    //CONSTANTES
    public static final String clienteId = "AU5N9lLvW8VLvsISpXqrZa4BpJK9olwSD_3CktrxIfit4-1UdXXQXmX9UFDXa9mvLW_EN-pE9N2RmeNq";
    public static final int PAYPAL_REQUEST_CODE = 123;
    public static final int SCAN_RESULT = 100;

    //configuracion con paypal
    public static PayPalConfiguration configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(clienteId);

    Button btnPagar;
    CheckBox cbManual;
    CheckBox cbScanner;
    TextView textViewTargeta;
    TextView textViewFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_de_pago);

        btnPagar = findViewById(R.id.btnPagarMetodosPago);
        cbManual = findViewById(R.id.cbManualMetodosPago);
        cbScanner = findViewById(R.id.cbScannerMetodosPago);
        textViewTargeta = findViewById(R.id.lblnumCuenta);
        textViewFecha = findViewById(R.id.lblnumCaducidad);

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbManual.isChecked()) {
                    Toast.makeText(MetodosDePagoActivity.this, "Has seleccionado Paypal", Toast.LENGTH_SHORT).show();
                    getPayment();
                }else if(cbScanner.isChecked()){
                    Toast.makeText(MetodosDePagoActivity.this, "Has seleccionado Tarjeta de Credito", Toast.LENGTH_SHORT).show();
                    getScanner();
                }
            }
        });

        cbManual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cbScanner.setChecked(false);
                }
            }
        });

        cbScanner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cbManual.setChecked(false);
                }
            }
        });

    }

    private void getPayment() {
        String precio = "3"; //Borrar este una vez implementado el todo
        //precio = lblPrecio.getText().toString(); Todo -> Rellenar con el precio de la actividad abierta

        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(precio)), "USD", "learn", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent (this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    private void getScanner(){
        Intent intent = new Intent(this, CardIOActivity.class)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
        startActivityForResult(intent, SCAN_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == PAYPAL_REQUEST_CODE){
            PaymentConfirmation config = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if(config != null){

                try {

                    String paymentDetails = config.toJSONObject().toString(4);
                    JSONObject payObj = new JSONObject(paymentDetails);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Error", "Something went wrong");
                }
            }
        }else if(requestCode == Activity.RESULT_CANCELED){
            Log.i("Error", "Something went wrong");
        }
        else if(requestCode==PaymentActivity.RESULT_EXTRAS_INVALID){
            Log.i("Payment", "Invalid Payment");
        }else if(requestCode == SCAN_RESULT){
            if(data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT));
            CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
            textViewTargeta.setText(scanResult.getRedactedCardNumber());

            if(scanResult.isExpiryValid()){
                String mes = String.valueOf(scanResult.expiryMonth);
                String an = String.valueOf(scanResult.expiryYear);
                textViewTargeta.setText(mes +"/"+ an);
            }
        }
    }
}