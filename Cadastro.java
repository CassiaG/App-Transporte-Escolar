package com.example.transporteescolar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;   // banco de dados
import android.database.Cursor; 

public class Cadastro extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro);
		AbreouCriabanco ();
		
		Button btcadastrar = (Button) findViewById (R.id.btcad);
		
btcadastrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent (Cadastro.this, Cmeni.class));
				InserindoDados();
			
				
			}
		});
	}

	SQLiteDatabase bancodados = null;
	Cursor cursor;
	
	public void AbreouCriabanco () {
		try{
			bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
			String sql = "CREATE TABLE IF NOT EXISTS dpcliente"+ "(id INTEGER PRIMARY KEY, nome TEXT, rg TEXT, cpf TEXT, nomeR1 TEXT, nomeR2 TEXT, nomeR3 TEXT, rua TEXT, cidade TEXT, tel TEXT, celular TEXT, telcontato TEXT, rua2 TEXT, "
				+ "cidade2 TEXT, pagamento TEXT, preco TEXT, dnome TEXT, sexo TEXT, nascimento TEXT, serie TEXT, escola TEXT, endereco TEXT, professor TEXT, periodo TEXT, email TEXT, entrada TEXT, bairroe TEXT);"; 
		
		bancodados.execSQL(sql); 
		
		Toast.makeText(Cadastro.this, "Banco aberto com sucesso!", Toast.LENGTH_SHORT).show();
		
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
			EditText nome = (EditText) findViewById(R.id.editText10);	
			EditText rg = (EditText) findViewById(R.id.editText20);		
			EditText cpf = (EditText) findViewById(R.id.editText130);
			EditText nomeR1 = (EditText) findViewById(R.id.editText4);
			EditText nomeR2 = (EditText) findViewById(R.id.editText6);
			EditText nomeR3 = (EditText) findViewById(R.id.editText7);
			EditText rua = (EditText) findViewById(R.id.editText30);
			EditText cidade = (EditText) findViewById(R.id.editText40);
			EditText tel = (EditText) findViewById(R.id.editText50);
			EditText celular = (EditText) findViewById(R.id.editText60);
			EditText telcontato = (EditText) findViewById(R.id.editText70);
			EditText rua2 = (EditText) findViewById(R.id.editText80);
			EditText cidade2 = (EditText) findViewById(R.id.editText90);
			EditText pagamento = (EditText) findViewById(R.id.editText100);
			EditText preco = (EditText) findViewById(R.id.editText110);
			EditText dnome = (EditText) findViewById(R.id.editText120);
			EditText sexo = (EditText) findViewById(R.id.editText140);
			EditText nascimento = (EditText) findViewById(R.id.editText150);
			EditText serie = (EditText) findViewById(R.id.editText160);
			EditText escola = (EditText) findViewById(R.id.editText1);
			EditText endereco = (EditText) findViewById(R.id.editText5);
			EditText professor = (EditText) findViewById(R.id.editText8);
			EditText periodo = (EditText) findViewById(R.id.EditText01);
			EditText email = (EditText) findViewById(R.id.editText2);
			EditText entrada = (EditText) findViewById(R.id.editText3);
			EditText bairroe = (EditText) findViewById(R.id.EditText02);
			
	try{
		
		bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
		cursor = bancodados.query("dpcliente", null, null, null, null, null, null, null);
			cursor.getCount();
				cursor.moveToLast();
				
				if(cursor.getCount()==0){
					cod=1;
				}else{
					 cod = cursor.getInt(cursor.getColumnIndex("id"))+1;
				}
		String sql = "INSERT INTO dpcliente (id, nome, rg, cpf, nomeR1, nomeR2, nomeR3, rua, cidade, tel, celular, telcontato, rua2, cidade2, pagamento, preco, dnome, sexo, nascimento, serie, escola, endereco, professor, periodo, email, entrada, bairroe ) values ('"+
				cod+"','"+
				nome.getText().toString()+"','"
				+rg.getText().toString()+"','"
				+cpf.getText().toString()+"','"
				+nomeR1.getText().toString()+"','"
				+nomeR2.getText().toString()+"','"
				+nomeR3.getText().toString()+"','"
				+rua.getText().toString()+"','"
				+cidade.getText().toString()+"','"
				+tel.getText().toString()+"','"
				+celular.getText().toString()+"','"
				+telcontato.getText().toString()+"','"
				+rua2.getText().toString()+"','"
				+cidade2.getText().toString()+"','"
				+pagamento.getText().toString()+"','"
				+preco.getText().toString()+"','"
				+dnome.getText().toString()+"','"
				+sexo.getText().toString()+"','"
				+nascimento.getText().toString()+"','"
				+serie.getText().toString()+"','"
				+escola.getText().toString()+"','"
				+endereco.getText().toString()+"','"
				+professor.getText().toString()+"','"
				+periodo.getText().toString()+"','"
				+email.getText().toString()+"','"
				+entrada.getText().toString()+"','"
				+bairroe.getText().toString()+"')";

		bancodados.execSQL(sql);
		
	Toast.makeText(Cadastro.this, "Dados Cadastrados com sucesso!", Toast.LENGTH_SHORT).show();
			 
	}
	catch (Exception erro) {
	mensagemExibir ("Aviso", "Erro ao gravar banco "+erro.getMessage());
	}
	}
	
	public void mensagemExibir (String titulo, String texto)
	{
	AlertDialog.Builder mensagem = new AlertDialog.Builder 


			(Cadastro.this);
	mensagem.setTitle(titulo);
	mensagem.setMessage(texto);
	mensagem.setNeutralButton("OK", null);
	mensagem.show();
	}

	
	}
	

