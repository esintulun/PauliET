package com.example.esintulun.pauli;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import utils.SendMailTask;

public class SendMailActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        final Button send = (Button) this.findViewById(R.id.bntSendMail);

        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i("SendMailActivity", "Send Button Clicked.");

                String fromEmail = ((TextView) findViewById(R.id.etFromMail))
                        .getText().toString();
                String fromPassword = ((TextView) findViewById(R.id.etPassword))
                        .getText().toString();
                String toEmails = ((TextView) findViewById(R.id.etToMail))
                        .getText().toString();
                List toEmailList = Arrays.asList(toEmails
                        .split("\\s*,\\s*"));
                Log.i("SendMailActivity", "To List: " + toEmailList);
                String emailSubject = ((TextView) findViewById(R.id.etSubject))
                        .getText().toString();
                String emailBody = ((TextView) findViewById(R.id.etBody))
                        .getText().toString();
                new SendMailTask(SendMailActivity.this).execute(fromEmail,
                        fromPassword, toEmailList, emailSubject, emailBody);
            }
        });
    }
}