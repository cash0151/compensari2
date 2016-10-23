package compensation.compensationsystem.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import compensation.compensationsystem.Models.Company;
import compensation.compensationsystem.Models.Constants;
import compensation.compensationsystem.Models.Methods;
import compensation.compensationsystem.R;
import compensation.compensationsystem.WebServices.ApiService;
import compensation.compensationsystem.WebServices.Requests.LoginRequest;
import compensation.compensationsystem.WebServices.Responses.LoginResponse;
import compensation.compensationsystem.WebServices.ServiceGenerator;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginActivity extends AppCompatActivity {

    String message;
    ArrayList<Company> represented_companies;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        final EditText name = (EditText)findViewById(R.id.editText);
        final EditText password = (EditText)findViewById(R.id.editText2);
        final Button login = (Button)findViewById(R.id.button);

        SharedPreferences sharedPref = this.getSharedPreferences("Authenticate",Context.MODE_PRIVATE);
        final String savedUsername = sharedPref.getString(Constants.USERNAME,"");
        final String savedPassword = sharedPref.getString(Constants.PASSWORD,"");
        name.setText(savedUsername);
        password.setText(savedPassword);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String nume = name.getText().toString();
                final String parola = password.getText().toString();
                final String token = "irina_marius_ionut";

                LoginRequest req = new LoginRequest(nume,parola,token);
                ApiService apiService = ServiceGenerator.getServiceGenerator().getApiService();
                apiService.login(req)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LoginResponse>() {
                            @Override
                            public void onCompleted()
                            {
                                switch (message)
                                {
                                    case Constants.LOGIN_NOT_AUTHORIZED:
                                        Methods.showPopUp(context,"",context.getResources().getString(R.string.error_login_not_authorized));
                                        break;
                                    case Constants.LOGIN_AUTHORIZED:
                                        Intent home = new Intent(LoginActivity.this,HomeActivity.class);
                                        if(represented_companies.size()!=0)
                                            home.putExtra(Constants.USER_COMPANIES,represented_companies);

                                        SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("Authenticate",Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.putString(Constants.USERNAME, nume);
                                        editor.putString(Constants.PASSWORD, parola);
                                        editor.commit();

                                        startActivity(home);
                                        finish();
                                        break;
                                }
                            }

                            @Override
                            public void onError(Throwable e)
                            {

                            }

                            @Override
                            public void onNext(LoginResponse loginResponse)
                            {
                                message = loginResponse.getMessage();
                                represented_companies = loginResponse.getRepresented_companies();

                            }
                        });
            }
        });
        if(!savedUsername.isEmpty() && !savedPassword.isEmpty())login.performClick();
    }
}
