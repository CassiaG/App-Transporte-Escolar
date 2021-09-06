package com.example.transporteescolar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bblogin extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blogin);
		
		Button btentrar = (Button) findViewById (R.id.button2);
		final EditText Login = (EditText) findViewById(R.id.editText1);
		final EditText Senha = (EditText) findViewById(R.id.editText2);
				Button Limpar = (Button) findViewById(R.id.button1);
		
btentrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String logincerto = "Android";
				String senhacerta = "2319";
				String strLogin = Login.getText().toString();
				String strSenha = Senha.getText().toString();
			
				if (strLogin.equals(logincerto) && strSenha.equals(senhacerta)) {
					
					Toast.makeText(Bblogin.this, "Login feito com sucesso!", Toast.LENGTH_SHORT).show();
					startActivity(new Intent (Bblogin.this, Cmeni.class));
					
				}else {
				
					Toast.makeText(Bblogin.this, "Login ou Senha estão incorretos!", Toast.LENGTH_SHORT).show();
					startActivity(new Intent (Bblogin.this, Bblogin.class));
				} 
				}
			
		});
				Limpar.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		Login.setText("");
		Senha.setText("");
		
	}
});

}
	
}