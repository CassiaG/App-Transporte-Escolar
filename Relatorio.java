package com.example.transporteescolar;



import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Relatorio extends Activity {
	SQLiteDatabase bancodados;
	Cursor cursor;
	double n1, n2, n3, resultado, n4, n5, n6, n7, n8, n9, resultado2, resultado3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relatorio);
		AbreouCriabanco();
		BuscarDados();	
		
		Button btvai = (Button) findViewById (R.id.button2);
		Button bt = (Button) findViewById (R.id.button4);
		Button btcont = (Button) findViewById (R.id.button1);
		final EditText km = (EditText) findViewById(R.id.editText2);
		final EditText temp = (EditText) findViewById(R.id.EditText03);
		final EditText prec = (EditText) findViewById(R.id.EditText04);
		final TextView result = (TextView) findViewById(R.id.textView5);
		final EditText outro = (EditText) findViewById(R.id.EditText02);
		final EditText desp = (EditText) findViewById(R.id.EditText01);
		final TextView fim = (TextView) findViewById(R.id.textView10);
		 final double total = cursor.getDouble(0);
		final TextView preco = (TextView) findViewById(R.id.textView8);
		final TextView totali = (TextView) findViewById(R.id.textView12);
		Button gravar = (Button) findViewById (R.id.button3);
	
bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent (Relatorio.this, Relatorio2.class));
			
				
		}
		});
		
gravar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			InserindoDados();
			
				
		}
		});
		
btcont.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				try
				{
				NumberFormat formato3 = NumberFormat.getCurrencyInstance(new Locale("SP", "BRA"));
				//n4 = Double.parseDouble(preco.getText().toString());
				//n5 = Double.parseDouble(desp.getText().toString());
				resultado3 = total-resultado2;
				totali.setText(formato3.format(resultado3));
				
				
			
				}	catch(Exception erro){
					//mensagemExibir("Erro no banco", "Erro ao abrir ou criar o banco" + erro.getMessage());
					//Toast.makeText(Relatorio.this, "Digite Todos os valores para efetuar a conta!", Toast.LENGTH_SHORT).show();
					}
			
				
		}
		});
		
		btvai.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try
				
				{
					NumberFormat formato2 = NumberFormat.getCurrencyInstance(new Locale("SP", "BRA"));
					//NumberFormat formato5 = new DecimalFormat(".#");
				n1 = Double.parseDouble(km.getText().toString());
				n2 = Double.parseDouble(temp.getText().toString());
				n3 = Double.parseDouble(prec.getText().toString());
				n4 = Double.parseDouble(outro.getText().toString());
				n5 = Double.parseDouble(desp.getText().toString());
				resultado = n1/n2*n3;
				resultado2 = resultado+n4+n5;
				result.setText(formato2.format(resultado));
				fim.setText(formato2.format(resultado2));
				
				}	catch(Exception erro){
				//mensagemExibir("Erro no banco", "Erro ao abrir ou criar o banco" + erro.getMessage());
				//Toast.makeText(Relatorio.this, "Digite Todos os valores para efetuar a conta!", Toast.LENGTH_SHORT).show();
				}
		}
		});
}
	
	
	public void AbreouCriabanco () {
		try{
			bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
			String sql = "CREATE TABLE IF NOT EXISTS relatorio"+ "(id INTEGER PRIMARY KEY, ano TEXT, combustivel TEXT, gastos_v TEXT, gastos_o TEXT, total_d TEXT, lucro_g TEXT, lucro_l TEXT);"; 
		
		bancodados.execSQL(sql); 
		
		Toast.makeText(Relatorio.this, "Banco aberto com sucesso!", Toast.LENGTH_SHORT).show();
		
		}
		catch(Exception erro){
			mensagemExibir("Erro no banco", "Erro ao abrir ou criar o banco" + erro.getMessage());
			
		}
		}
	
	public void fechaBanco (){
		
		try{
			
			bancodados.close(); //fecha banco de dados
			
	} catch (Exception Erro) {
		mensagemExibir ("Erro Banco", "Erro ao fechar banco : "
				 +Erro.getMessage());
	}
	}
		public void InserindoDados()
		{
			int cod;
				EditText ano = (EditText) findViewById(R.id.editText1);	
				TextView combustivel = (TextView) findViewById(R.id.textView5);		
				EditText gastos_v = (EditText) findViewById(R.id.EditText02);
				EditText gastos_o = (EditText) findViewById(R.id.EditText01);
				TextView total_d = (TextView) findViewById(R.id.textView10);
				TextView lucro_g = (TextView) findViewById(R.id.textView8);
				TextView lucro_l = (TextView) findViewById(R.id.textView12);
				
		try{
			
			bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
			cursor = bancodados.query("relatorio", null, null, null, null, null, null, null);
				cursor.getCount();
					cursor.moveToLast();
					
					if(cursor.getCount()==0){
						cod=1;
					}else{
						 cod = cursor.getInt(cursor.getColumnIndex("id"))+1;
					}
			String sql = "INSERT INTO relatorio (id, ano, combustivel, gastos_v, gastos_o, total_d, lucro_g, lucro_l)  values ('"+
					cod+"','"+
					ano.getText().toString()+"','"
					+combustivel.getText().toString()+"','"
					+gastos_v.getText().toString()+"','"
					+gastos_o.getText().toString()+"','"
					+total_d.getText().toString()+"','"
					+lucro_g.getText().toString()+"','"
					+lucro_l.getText().toString()+"')";

			bancodados.execSQL(sql);
			
		Toast.makeText(Relatorio.this, "Dados Cadastrados com sucesso!", Toast.LENGTH_SHORT).show();
		
				 
		}
		catch (Exception erro) {
		mensagemExibir ("Aviso", "Erro ao gravar banco "+erro.getMessage());
		}
		}
		
	
		
	private boolean BuscarDados(){
		
		final TextView preco = (TextView) findViewById(R.id.textView8);
		try{
			cursor = bancodados.rawQuery("SELECT SUM (preco) FROM dpcliente",
					null); 
			NumberFormat formato2 = NumberFormat.getCurrencyInstance(new Locale("SP", "BRA"));
			
			if (cursor.moveToFirst()) {

                double total = cursor.getDouble(0);
               
                preco.setText(formato2.format(total));

            }

			return false;
	
	}
		catch (Exception Erro) {
			mensagemExibir ("Erro Banco", "Nenhum dado encontrado "
					 +Erro.getMessage());
		
			return false;
		}
					
}
			
	
	public void mensagemExibir (String titulo, String texto)
	{
	AlertDialog.Builder mensagem = new AlertDialog.Builder 


			(Relatorio.this);
	mensagem.setTitle(titulo);
	mensagem.setMessage(texto);
	mensagem.setNeutralButton("OK ", null);
	mensagem.show();
	}
	
}