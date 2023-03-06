package com.example.metamaskjava;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthSendTransaction;

import java.math.BigInteger;
import java.util.List;
import android.os.Bundle;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import android.widget.Button;
import android.view.View;
import java.util.concurrent.ExecutionException;
import org.web3j.protocol.core.methods.request.Transaction;


public class MainActivity extends AppCompatActivity {
    private Web3j web3j;
    private Button connect,getAdressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect =   findViewById(R.id.connect);
        getAdressBtn =   findViewById(R.id.getAddress);
        setOnclicklistners();

    }
    private void setOnclicklistners() {

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectToEthereum();
            }
        });

        getAdressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getAccounts(web3j);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    void connectToEthereum() {
        Toast.makeText(this, "Start connect", Toast.LENGTH_LONG).show();

        web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/7e864c1af59a4ea187dcf8cfecf50afb"));
        try {

            Web3ClientVersion clientVersion = web3j.web3ClientVersion().sendAsync().get();
            if (!clientVersion.hasError()) {
               // customDialog.closeDialog();
                connect.setBackgroundResource(R.drawable.connected);
                connect.setText("I am connected ");

                System.out.println("wallet Connected perfectly "  );
             //  if(web3j!=null) getAccounts(web3j);
                Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
            } else {
                System.out.println("wallet Error: " +clientVersion.getError().getMessage());

                Toast.makeText(this, clientVersion.getError().getMessage(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            System.out.println("wallet Catch error: "  +e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
     }

    public static EthAccounts getAccounts(Web3j web3j) throws InterruptedException, ExecutionException {
         EthAccounts accounts = web3j.ethAccounts().sendAsync().get();
         System.out.println("Will get the balance from here: "+accounts.getAccounts().get(0)+ "\n");

        return web3j
                .ethAccounts()
                .sendAsync()
                .get();
    }

    // will call the send amount
    private void sendTransaction(String toAddress, BigInteger  amount) {
       /* web3j.ethSendTransaction(Transaction.createEtherTransaction(
                        null, null, null, null, toAddress, amount))
                .sendAsync();*/
    }




}